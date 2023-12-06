package com.rosed.wildernesschestloot;

import com.rosed.wildernesschestloot.item.ItemCommand;
import org.bukkit.Bukkit;


public enum InstanceManager {

    INSTANCE;

    private WildernessChestLoot plugin;

    public void start(WildernessChestLoot plugin) {

        assert plugin != null : "Error while starting Wilderness-Chest-Loot";
        this.plugin = plugin;

        register();

    }

    private void register() {

        // register managers

        // register events
        Bukkit.getPluginManager().registerEvents(new ChestGenerateEvent(), plugin);

        // register commands
        plugin.getCommand("wilditems").setExecutor(new ItemCommand());
    }

    public WildernessChestLoot getPlugin() {
        return plugin;
    }

}
