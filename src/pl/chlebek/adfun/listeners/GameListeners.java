package pl.chlebek.adfun.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.chlebek.adfun.Battle;
import pl.chlebek.adfun.Main;

public class GameListeners implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        final String name = e.getPlayer().getName();
        if (!Battle.current.contains(e.getPlayer().getName()))
            return;

        Location spawn = Main.getInstance().getServer().getWorlds().get(0).getSpawnLocation();
        e.getPlayer().teleport(spawn);

        Battle.setLoser(e.getPlayer().getName());
        Main.broadcast("§e" + e.getPlayer().getName() + " §ropuscil rozgrywke. Zwycieza §e" + Battle.getOne() + "§r. Gratulacje!");
        Battle.stop();

        e.getPlayer().setHealth(0); // kill
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        e.getDrops().clear();

        if (!Battle.current.contains(e.getEntity().getName()))
            return;
        if (e.getEntity().getKiller() == null)
            Main.broadcast("cos sie zesralo");

        Battle.setLoser(e.getEntity().getName());
        Main.broadcast("Zwycieza §e" + Battle.getOne() + "§r. Gratulacje!");
        Battle.stop();
    }

    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player && e.getEntity() instanceof Player) || e.getDamager().isOp())
            return;
        if (!Battle.current.contains(e.getDamager().getName()) || !Battle.current.contains(e.getEntity().getName()))
            e.setCancelled(true);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }
}
