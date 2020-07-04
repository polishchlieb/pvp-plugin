package pl.chlebek.adfun.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chlebek.adfun.Battle;
import pl.chlebek.adfun.Main;

public class StopaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        for (String playerName : Battle.current) {
            Player p = Main.getInstance().getServer().getPlayer(playerName);
            if (p != null)
                Battle.resetPlayer(p);
        }
        Battle.current.clear();
        Main.broadcast("cos sie zesralo, ale spokojna glowa, wszystko pod kontrola!");
        return false;
    }
}
