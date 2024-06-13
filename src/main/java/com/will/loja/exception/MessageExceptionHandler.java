package com.will.loja.exception;

import com.will.loja.exception.EnumCustomException;
import com.will.loja.utils.StringUtil;
import com.will.loja.utils.Utils;

import java.util.List;
import java.util.stream.IntStream;

public class MessageExceptionHandler {

    public static String getMessage(String message, Object argument) {
        return StringUtil.formatMessage(message, argument.toString());
    }

    public static String getMessage(String message, Object... arguments) {
        return message.concat("\r\n").concat(formatArguments(arguments));
    }

    public static String getMessage(Object... arguments) {
        return formatArguments(arguments);
    }

    public static String getMessage(String message, List<String> arguments) {
        return message.concat(formatArguments(arguments));
    }

    public static String getMessage(EnumCustomException customMessage, Object... arguments) {
        return StringUtil.formatMessage(customMessage.getMessage(), arguments);
    }

    private static String formatArguments(Object... arguments) {
        StringBuilder messageArguments = new StringBuilder("Campos: ");

        for (int i = 0; i <= arguments.length; i++) {
            if (Utils.isNotEmpty(arguments[i])) {
                messageArguments.append(arguments[i].toString());
                if (!Utils.equals(i + 1, arguments.length)) {
                    messageArguments.append("&");
                }
            }
        }
        messageArguments.append(" ]");
        return messageArguments.toString();
    }

    private static String formatArguments(List<String> arguments) {
        StringBuilder messageArguments = new StringBuilder("Campos: ");

        IntStream.range(0, arguments.size()).forEach(i -> {
            messageArguments.append(arguments.get(i));
            if (!Utils.equals(i + 1, arguments.size())) {
                messageArguments.append(" & ");
            }
        });
        messageArguments.append(" ]");

        return messageArguments.toString();
    }
}
