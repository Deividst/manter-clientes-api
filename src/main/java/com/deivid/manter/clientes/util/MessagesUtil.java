package com.deivid.manter.clientes.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Classe utilitária para recuperar uma mensagem do Message.properties passando parâmetros.
 *
 * @author Deivid
 */
public class MessagesUtil {

    public static String getMessage(String key, Object params){
        ResourceBundle bundle = ResourceBundle.getBundle("messages");
        return MessageFormat.format(bundle.getString(key), params);
    }

}
