package ru.mclegendary.justchat.event;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import ru.mclegendary.justchat.util.Util;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static me.clip.placeholderapi.PlaceholderAPI.setPlaceholders;

import static ru.mclegendary.justchat.JustChat.getMain;
import static ru.mclegendary.justchat.util.Util.colorize;
import static ru.mclegendary.justchat.util.Util.removePrefix;

public class Listeners implements Listener {

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent e) {
        final String message = e.getMessage();
        final Player player = e.getPlayer();
        final Set<Player> rec = e.getRecipients();

        FileConfiguration config = YamlConfiguration.loadConfiguration(new File(getMain().getDataFolder(), "config.yml"));

        String globalPrefix = config.getString("Global.Prefix");
        String staffPrefix = config.getString("Staff.Prefix");

        String shortMessageWarn = colorize(config.getString("Short-Message-Warning"));
        String fixedMessage = message.replace("%", "%%");

        String globalFormat = setPlaceholders(player, config.getString("Global.Format"));
        String staffFormat = setPlaceholders(player, config.getString("Staff.Format"));
        String localFormat = setPlaceholders(player, config.getString("Local.Format"));

        List<String> colorTranslate = Arrays.asList(globalFormat, staffFormat, localFormat);
        colorTranslate.forEach(Util::colorize);


        if (player.hasPermission("justchat.colors")) {
            fixedMessage = colorize(fixedMessage);
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
