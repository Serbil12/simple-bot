package com.tdatingapp.chatbot.util;

public class TelegramStringUtils {

    public static boolean isEmpty(String source) {
        return source == null || source.isEmpty() || source.equalsIgnoreCase("null");
    }

}
