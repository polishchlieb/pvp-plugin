package pl.chlebek.adfun;

import org.bukkit.Location;
import org.bukkit.entity.Player;

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
        Location spawn = Main.getInstance().getServer().getWorlds().get(0).getSpawnLocation();
        Player winner = Main.getInstance().getServer().getPlayer(getOne());

        winner.getEquipment().setHelmet(null);
        winner.getEquipment().setChestplate(null);
        winner.getEquipment().setLeggings(null);
        winner.getEquipment().setBoots(null);
        winner.getInventory().clear();

        winner.setHealth(20);
        winner.teleport(spawn);

        Battle.current.clear();
    }
}
