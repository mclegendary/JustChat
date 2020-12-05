package ru.mclegendary.justchat;

import org.bukkit.configuration.file.FileConfiguration;

import static ru.mclegendary.justchat.JustChat.main;

public class ConfigManager {
    static FileConfiguration config = main.getConfig();

    public static void setupConfig() {
        // General config settings
        config.addDefault("Short-Message-Warning", "&4Your message is too short!");

        // Local chat settings
        config.addDefault("Local.Format", "&7[L] &r%luckperms_prefix%%player_name%%luckperms_suffix%&7: &r");
        config.addDefault("Local.Distance", 100);

        // Global chat settings
        config.addDefault("Global.Format", "&7[&6G&7] &r%luckperms_prefix%%player_name%%luckperms_suffix%&7: &r");
        config.addDefault("Global.Prefix", "!");

        // Staff chat settings
        config.addDefault("Staff.Format", "&7[&aStaff&7] &r%luckperms_prefix%%player_name%%luckperms_suffix%&7: &r");
        config.addDefault("Staff.Prefix", "*");

        // Config saving
        config.options().copyDefaults(true);
        main.saveConfig();
    }
}
