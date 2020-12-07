package ru.mclegendary.justchat.util;

import org.bukkit.ChatColor;

public class Util {

    public static String removePrefix(String s, String prefix) {

        if (s != null && prefix != null && s.startsWith(prefix)){
            return s.substring(prefix.length());
        }

        return s;
    }

    public static String colorize(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
