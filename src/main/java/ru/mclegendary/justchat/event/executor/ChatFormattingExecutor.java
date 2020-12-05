package ru.mclegendary.justchat.event.executor;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Set;

import static me.clip.placeholderapi.PlaceholderAPI.setPlaceholders;

import static ru.mclegendary.justchat.JustChat.main;
import static ru.mclegendary.justchat.util.Util.removePrefix;

public class ChatFormattingExecutor {

    public static void chatFormatter(AsyncPlayerChatEvent e) {
        final String message = e.getMessage();
        final Player player = e.getPlayer();
        final Set<Player> rec = e.getRecipients();

        FileConfiguration config = main.getConfig();
        String globalPrefix = config.getString("Global.Prefix");
        String staffPrefix = config.getString("Staff.Prefix");

        String shortMessageWarn = config.getString("Too short message");

        String globalFormat = setPlaceholders(player, config.getString("Global.Format"));
        String staffFormat = setPlaceholders(player, config.getString("Staff.Format"));
        String localFormat = setPlaceholders(player, config.getString("Local.Format"));


        String fixedMessage = message.replace("%", "%%");

        if (player.hasPermission("justchat.colors")) {
            fixedMessage = ChatColor.translateAlternateColorCodes('&', fixedMessage);
        }


        boolean isGlobal = false;
        boolean isStaff = false;

        if (message.startsWith(globalPrefix)) {
            isGlobal = true;
        }
        if (message.startsWith(staffPrefix) && player.hasPermission("justchat.staff")) {
            isStaff = true;
        }

        if (isGlobal) {
            if (message.length() < 2) { player.sendMessage(shortMessageWarn); e.setCancelled(true); }
            e.setFormat(globalFormat + removePrefix(fixedMessage, globalPrefix));

        } else {
            if (isStaff) {
                if (message.length() < 2) { player.sendMessage(shortMessageWarn); e.setCancelled(true); }
                e.setFormat(staffFormat + removePrefix(fixedMessage, staffPrefix));

                rec.removeIf(recipients -> recipients.hasPermission("justchat.user"));
            } else {
                e.setFormat(localFormat + fixedMessage);

                rec.removeIf(recipients -> player.getWorld() != recipients.getWorld() || recipients.getLocation().distance(player.getLocation()) > config.getInt("Local.Distance"));
            }
        }

    }
}
