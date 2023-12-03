package com.rosed.wildernesschestloot;

import com.rosed.wildernesschestloot.item.ItemCommand;
import com.rosed.wildernesschestloot.item.ItemManager;
import org.bukkit.Bukkit;


public enum InstanceManager {

    INSTANCE;

    private WildernessChestLoot plugin;
    private ItemManager itemManager;

    public void start(WildernessChestLoot plugin) {

        assert plugin != null : "Error while starting Wilderness-Chest-Loot";
        this.plugin = plugin;

        register();

    }

    private void register() {

        // register managers
        itemManager = new ItemManager();

        // register events
        Bukkit.getPluginManager().registerEvents(new ChestGenerateEvent(), plugin);

        // register commands
        plugin.getCommand("wilditems").setExecutor(new ItemCommand());
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public WildernessChestLoot getPlugin() {
        return plugin;
    }

}
