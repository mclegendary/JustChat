package ru.mclegendary.justchat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import ru.mclegendary.justchat.event.Listeners;
import ru.mclegendary.justchat.placeholder.JustChatExpansion;

import at.pcgamingfreaks.MarriageMaster.Bukkit.MarriageMaster;

public class JustChat extends JavaPlugin {
    private static JustChat main;

    @Override
    public void onEnable() {
        // Main setup
        main = this;
        this.saveResource("config.yml", false);

        placeholdersSetup();
        getLogger().info(ChatColor.AQUA + "Enabled");

        // Placeholder plugin check && Events registering
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.getPluginManager().registerEvents(new Listeners(), this);
        } else {
            Bukkit.getPluginManager().disablePlugin(this);
        }

        // Commands
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.AQUA + "Disabled");
    }


    public static JustChat getMain() {
        return main;
    }
    public void placeholdersSetup() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            getLogger().info(ChatColor.DARK_RED + "Something went wrong with PAPI :P");
            return;
        }

        new JustChatExpansion().register();
    }
    public MarriageMaster getMarriageMaster() {
        Plugin bukkitPlugin = Bukkit.getPluginManager().getPlugin("MarriageMaster");
        if(!(bukkitPlugin instanceof MarriageMaster)) {
            getLogger().info(ChatColor.DARK_RED + "MarriageMaster плагин не найден :(");
            return null;
        }
        return (MarriageMaster) bukkitPlugin;
    }
}
