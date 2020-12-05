package ru.mclegendary.justchat.util;

public class Util {

    public static String removePrefix(String s, String prefix) {

        if (s != null && prefix != null && s.startsWith(prefix)){
            return s.substring(prefix.length());
        }

        return s;
    }

}
