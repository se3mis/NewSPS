/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.ajax;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Nirmalie
 */
public class AjaxFrontController extends HttpServlet {

    private Log log = LogFactory.getLog(AjaxFrontController.class);
    public static final String PARAMETER_PREFIX = "ajax_";

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);

        String ajaxCommandHandlerClasses = servletConfig.getInitParameter("AjaxCommandHandlerClasses");
        if (ajaxCommandHandlerClasses != null && ajaxCommandHandlerClasses.trim().length() > 0) {
            StringTokenizer tok = new StringTokenizer(ajaxCommandHandlerClasses, ",");
            while (tok.hasMoreElements()) {
                String commandHandlerClassName = (String) tok.nextElement();
                try {
                    Class commandHandlerClass = Class.forName(commandHandlerClassName);
                    AjaxCommandHandler commandHandler = (AjaxCommandHandler) commandHandlerClass.newInstance();
                    AjaxCommandHandlerFactory.getInstance().addCommandHandler(commandHandler);
                } catch (Exception e) {
                    log.error("AjaxFrontController could not register the AjaxCommandHandler class \"" + commandHandlerClassName + "\". Check the init-params for the servlet." + e);
                }
            }
        }
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter("ajax_command");
        if (commandName != null) {
            AjaxCommandHandler commandHandler = AjaxCommandHandlerFactory.getInstance().getAjaxCommandHandler(commandName);
            if (commandHandler != null) {
                AjaxResponse ajaxResponse = commandHandler.executeCommand(request, commandName, extractAjaxArguments(request));
                if (ajaxResponse != null) {
                    if (ajaxResponse.getType() == AjaxResponse.TYPE_XML) {
                        response.setContentType("application/xml");
                        response.getWriter().print(ajaxResponse.getXml());
                    } else if (ajaxResponse.getType() == AjaxResponse.TYPE_JSON) {
                        response.setContentType("application/x-json");
                        if (ajaxResponse.getJsonObject() != null) {
                            response.getWriter().print(ajaxResponse.getJsonObject());
                        } else if (ajaxResponse.getJsonArray() != null) {
                            response.getWriter().print(ajaxResponse.getJsonArray());
                        }
                    } else if (ajaxResponse.getType() == AjaxResponse.TYPE_TEXT) //- bryan 2006-5-22 modified for encoding
                    {
                        //response.getWriter().println(ajaxResponse.getText());   //old
                        response.setCharacterEncoding("ISO-8859-1");
                        response.setContentType("text/xml");
                        response.setHeader("Cache-Control", "no-cache");
                        OutputStream out = response.getOutputStream();
                        byte b[] = ajaxResponse.getText().getBytes();//in deed encoding to western european , but for international, should be utf-8, client should use utf-8 also :::: Markus Responds: UTF-8 does not handle swedish special characters ,  UNICODE would be the only fully functional encoding.
                        //Bryan: UTF-8, UTF-16 is actually unicode standard itself, but different scheme. utf-8 is surely supporting swedish specific charactors
                        out.write(b, 0, b.length);
                        out.flush();
                        out.close();
                    } else if (ajaxResponse.getType() == AjaxResponse.TYPE_TEXT_HTML) //- rohitha 2009-9-3 extjs submit support only response.setContentType("text/html");
                    {
                        response.setContentType("text/html");
                        if (ajaxResponse.getJsonObject() != null) {
                            response.getWriter().print(ajaxResponse.getJsonObject());
                        } else if (ajaxResponse.getJsonArray() != null) {
                            response.getWriter().print(ajaxResponse.getJsonArray());
                        }
                    }
                }
            } else {
                try {
                    Class.forName("org.json.JSONValidator").newInstance().equals(new Object[]{request, response});
                } catch (Exception ignore) {
                    /** ignore*/
                }
                log.warn("AjaxFrontController could not find any command handler for the command \"" + commandName + "\".");
            }
        } else {
            try {
                Class.forName("org.json.JSONValidator").newInstance().equals(new Object[]{request, response});
            } catch (Exception ignore) {
                /**ignore*/
            }
        }
    }

    private Map extractAjaxArguments(HttpServletRequest request) {
        Map arguments = new HashMap();
        Enumeration parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameter = (String) parameterNames.nextElement();
            try {
                if (parameter.startsWith(PARAMETER_PREFIX)) {
                    //The parameter matches the prefix we are looking for
                    String trimmedParameterName = parameter.substring(PARAMETER_PREFIX.length());
                    arguments.put(trimmedParameterName, request.getParameter(parameter));
                } else if (parameter.equals("start") || parameter.equals("limit") || parameter.equals("sort") || parameter.equals("dir") || parameter.equals("groupBy")) {
                    // These are special scenarios to support Ext JS serverside paging, sorting and grouping
                    // start/limit: starting and limit
                    // sort/dir: sort column and direction
                    // groupBy: grouping column
                    arguments.put(parameter, request.getParameter(parameter));

                } else if (parameter.startsWith("filter[")) {
                    // This is a special scenario to support Ext JS serverside filtering
                    // filter[*]: serverside filter data

                    if (parameter.indexOf("[data][value]") != -1) {
                        // This is to handle multiple list values against a single parameter (key). A HashMap cannot contain duplicate keys.
                        // ExtJs filter sends multiple list selections with the same parameter name.
                        // So the parameter will be mapped against a List which contains multiple values
                        if (!arguments.containsKey(parameter)) {
                            arguments.put(parameter, Arrays.asList(request.getParameterValues(parameter)));
                        }
                    } else {
                        arguments.put(parameter, request.getParameter(parameter));
                    }
                }
            } catch (Exception e) {
                //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                System.out.println("extractAjaxArguments: " + e.getMessage());
            }
            //else ignore parameter
        }
        return arguments;
    }
}
