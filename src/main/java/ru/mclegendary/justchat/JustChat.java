package ru.mclegendary.justchat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import ru.mclegendary.justchat.command.Reload;
import ru.mclegendary.justchat.event.Listeners;

import static ru.mclegendary.justchat.ConfigManager.setupConfig;

public class JustChat extends JavaPlugin {
    public static JustChat main;

    @Override
    public void onEnable() {
        // Main setup
        main = this;

        setupConfig();
        getLogger().info(ChatColor.AQUA + "Enabled");

        // Placeholder plugin check && Events registering
        if (PlaceholderAPI()) {
            Bukkit.getPluginManager().registerEvents(new Listeners(), this);
        } else {
            Bukkit.getPluginManager().disablePlugin(this);
        }

        // Commands
        this.getCommand("justchat").setExecutor(new Reload());
    }

    public JustChat getMain() {
        return main;
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.AQUA + "Disabled");
    }




    public boolean PlaceholderAPI() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            return true;
        } else {
            getLogger().warning("Не могу найти плагин PlaceholderAPI! Он необходим для моего запуска :(");
            return false;
        }
    }
}
