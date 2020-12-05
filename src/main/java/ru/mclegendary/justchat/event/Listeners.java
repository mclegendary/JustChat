package ru.mclegendary.justchat.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static ru.mclegendary.justchat.event.executor.ChatFormattingExecutor.chatFormatter;

public class Listeners implements Listener {

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent e) {
        chatFormatter(e);
    }
}
