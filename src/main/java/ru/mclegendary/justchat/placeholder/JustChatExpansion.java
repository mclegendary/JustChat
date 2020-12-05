package ru.mclegendary.justchat.placeholder;

import at.pcgamingfreaks.MarriageMaster.Bukkit.MarriageMaster;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import static ru.mclegendary.justchat.JustChat.main;

public class JustChatExpansion extends PlaceholderExpansion {
    @Override
    public String getIdentifier() {
        return "justchat";
    }

    @Override
    public String getAuthor() {
        return "mclegendary";
    }

    @Override
    public String getVersion() {
        return "4.8";
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player player, String params) {
        if (player == null) return (ChatColor.RED + "Игрок не найден.");

        MarriageMaster marry = main.getMarriageMaster();

        if (params.equals("married")) {
            if (marry.HasPartner(player)) {
                return ChatColor.translateAlternateColorCodes('&', "&c❤ &r");
            } else {
                return ChatColor.translateAlternateColorCodes('&', "&8❤ &r");
            }
        }

        return null;
    }
}
