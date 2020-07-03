package pl.chlebek.adfun.listeners;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import pl.chlebek.adfun.Main;

public class JoinLeaveListeners implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage("§awitanie, §7" + e.getPlayer().getName());

        if (!e.getPlayer().hasPlayedBefore()) {
            final World w = Main.getInstance().getServer().getWorlds().get(0);
            e.getPlayer().teleport(w.getSpawnLocation());
        }

        if (e.getPlayer().isOp()) {
            e.getPlayer().setDisplayName("§c" + e.getPlayer().getName());
            e.getPlayer().setPlayerListName("§c" + e.getPlayer().getName());
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        e.setQuitMessage("§cdobranoc, §7" + e.getPlayer().getName());
    }

    @EventHandler
    public void onPreLogin(AsyncPlayerPreLoginEvent e) {
        for (Player p : Main.getInstance().getServer().getOnlinePlayers()) {
            if (p.getName().equals(e.getName()))
                e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, "ic stont");
        }
    }

    @EventHandler
    public void onPing(ServerListPingEvent e) {
        e.setMaxPlayers(0);
        e.setMotd("§b§lADFUN");
    }
}
