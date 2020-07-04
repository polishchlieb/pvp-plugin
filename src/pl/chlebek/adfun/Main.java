package pl.chlebek.adfun;

import org.bukkit.plugin.java.JavaPlugin;

import pl.chlebek.adfun.commands.*;
import pl.chlebek.adfun.listeners.*;

public class Main extends JavaPlugin {
    private static Main instance;
    public static Main getInstance() {
        return instance;
    }

    public static void broadcast(String message) {
        getInstance().getServer().broadcastMessage("§b§l[ADFUN]§r " + message);
    }

    @Override
    public void onEnable() {
        instance = this;

        getServer().getPluginManager().registerEvents(new GameListeners(), this);
        getServer().getPluginManager().registerEvents(new JoinLeaveListeners(), this);
        getServer().getPluginManager().registerEvents(new MessageListener(), this);
        getServer().getPluginManager().registerEvents(new PreventListeners(), this);
        getServer().getPluginManager().registerEvents(new WeatherChangeListener(), this);

        getCommand("start").setExecutor(new StartCommand());
        getCommand("broadcast").setExecutor(new BroadcastCommand());
        getCommand("stopa").setExecutor(new StopaCommand());
    }
}
