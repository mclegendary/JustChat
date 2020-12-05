package ru.mclegendary.justchat.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static ru.mclegendary.justchat.JustChat.main;

public class Reload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) { sender.sendMessage(ChatColor.DARK_RED + "Недостаточно аргументов!"); return false; }

        if (args[0].equalsIgnoreCase("reload")) {
            main.reloadConfig();

            sender.sendMessage(ChatColor.GREEN + "Конфиг перезагружен :)");
        }

        return true;
    }
}
