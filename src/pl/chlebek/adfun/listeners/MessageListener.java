package pl.chlebek.adfun.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class MessageListener implements Listener {
    @EventHandler
    public void onMessage(AsyncPlayerChatEvent e) {
        if (e.getPlayer().isOp())
            e.setFormat("§c%s§7: §f%s");
        else
            e.setFormat("§7%s: §f%s");
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        if (!e.getPlayer().isOp()) {
            e.getPlayer().sendMessage("cicho bonc");
            e.setCancelled(true);
        }
    }
}
