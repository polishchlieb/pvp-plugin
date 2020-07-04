package pl.chlebek.adfun;

import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Battle {
    // only 2 elements
    public static List<String> current = new ArrayList<>();

    public static void setLoser(String playerName) {
        current.remove(playerName);
    }

    public static String getOne() {
        return current.get(0);
    }

    public static void start(String... players) {
        current.addAll(Arrays.asList(players));
    }

    public static void stop() {
        Player winner = Main.getInstance().getServer().getPlayer(getOne());
        resetPlayer(winner);
        winner.getWorld().spawn(winner.getLocation(), Firework.class);

        Battle.current.clear();
    }

    public static void resetPlayer(Player p) {
        p.getEquipment().setHelmet(null);
        p.getEquipment().setChestplate(null);
        p.getEquipment().setLeggings(null);
        p.getEquipment().setBoots(null);
        p.getEquipment().clear();

        p.setHealth(20);

        Location spawn = Main.getInstance().getServer().getWorlds().get(0).getSpawnLocation();
        new BukkitRunnable() {
            @Override
            public void run() {
                p.teleport(spawn);
            }
        }.runTaskLater(Main.getInstance(), 60);
    }
}
