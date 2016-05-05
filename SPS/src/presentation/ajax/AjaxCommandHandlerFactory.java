/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.ajax;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Nirmalie
 */
public class AjaxCommandHandlerFactory {

    private static AjaxCommandHandlerFactory instance = new AjaxCommandHandlerFactory();
    private Map commandHandlerMapping; //(key=command name, value=CommandHandler instance)

    public static AjaxCommandHandlerFactory getInstance() {
        return instance;
    }

    private AjaxCommandHandlerFactory() {
        commandHandlerMapping = new HashMap();
    }

    public void addCommandHandler(AjaxCommandHandler ajaxCommandHandler) {
        if (ajaxCommandHandler != null) {
            for (int i = 0; i < ajaxCommandHandler.getValidCommands().length; i++) {
                String command = ajaxCommandHandler.getValidCommands()[i];
                AjaxCommandHandler old = (AjaxCommandHandler) commandHandlerMapping.get(command);
                if (old != null && !old.getClass().getName().equals(ajaxCommandHandler.getClass().getName())) {
                    throw new IllegalArgumentException("Ajax command " + command + " has already been registered by " + old.getClass().getName()); //Just to prevent developers from breaking the unicity constraint of command names
                }
                commandHandlerMapping.put(command, ajaxCommandHandler);
            }
        }
    }

    public AjaxCommandHandler getAjaxCommandHandler(String command) {
        return (AjaxCommandHandler) commandHandlerMapping.get(command);
    }
}
