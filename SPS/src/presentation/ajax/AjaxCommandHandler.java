/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.ajax;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Buddhini
 */
public interface AjaxCommandHandler {
    public String[] getValidCommands();
    
    public AjaxResponse executeCommand(HttpServletRequest request, String commandName, Map parameters);
}
