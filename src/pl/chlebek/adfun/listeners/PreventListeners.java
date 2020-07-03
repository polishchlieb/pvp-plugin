package pl.chlebek.adfun.listeners;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import pl.chlebek.adfun.Main;

public class PreventListeners implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Main.getInstance().getLogger().info("block break");
        if (!e.getPlayer().isOp())
            e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent e) {
        Main.getInstance().getLogger().info("player interact");
        if (e.getPlayer().isOp())
            return;
        if (e.getRightClicked() instanceof ArmorStand || e.getRightClicked() instanceof ItemFrame)
            e.setCancelled(true);
    }

    @EventHandler
    public void onArmorStandManipulate(PlayerArmorStandManipulateEvent e) {
        Main.getInstance().getLogger().info("armor stand manipulate");
        if (!e.getPlayer().isOp())
            e.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player) || e.getDamager().isOp())
            return;
        if (e.getEntity() instanceof ItemFrame || e.getEntity() instanceof ArmorStand)
            e.setCancelled(true);
    }

    @EventHandler
    public void onItemFrameBreak(HangingBreakByEntityEvent e) {
        Main.getInstance().getLogger().info("hanging break by entity");
        if (e.getEntity() instanceof ItemFrame && e.getRemover() instanceof Player && !e.getRemover().isOp())
            e.setCancelled(true);
    }
}
