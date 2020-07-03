package pl.chlebek.adfun.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.chlebek.adfun.Battle;
import pl.chlebek.adfun.Main;

public class StartCommand implements CommandExecutor {
    private void preparePlayerInventory(Player p) {
        p.getInventory().addItem(
            new ItemStack(Material.IRON_SWORD, 1),
            new ItemStack(Material.GOLDEN_APPLE, 3)
        );
        p.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET, 1));
        p.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
        p.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
        p.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS, 1));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.isOp())
            return true;
        if (args.length != 2) {
            sender.sendMessage("uzyj: /start <gracz> <gracz>");
            return true;
        }

        final Player p1 = Main.getInstance().getServer().getPlayer(args[0]);
        final Player p2 = Main.getInstance().getServer().getPlayer(args[1]);
        if (p1 == null || p2 == null) {
            sender.sendMessage("uzyj: /start <gracz> <gracz>");
            return true;
        }

        final World w = Main.getInstance().getServer().getWorlds().get(0);
        p1.teleport(new Location(w, -73, 87, -17));
        p2.teleport(new Location(w, -73, 87, 26));

        preparePlayerInventory(p1);
        preparePlayerInventory(p2);

        Battle.start(p1.getName(), p2.getName());

        Main.getInstance().getServer().broadcastMessage("§b§l[ADFUN] §rTeraz walcza: §e" + args[0] + "§r i §e" + args[1]);

        return false;
    }
}
