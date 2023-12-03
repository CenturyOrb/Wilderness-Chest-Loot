package com.rosed.wildernesschestloot;

import com.rosed.wildernesschestloot.item.ItemCommand;
import com.rosed.wildernesschestloot.item.ItemManager;
import org.bukkit.Bukkit;


public enum InstanceManager {

    INSTANCE;

    private WildernessChestLoot wildernessChestLoot;
    private ItemManager itemManager;

    public void start(WildernessChestLoot wildernessChestLoot)   {

        assert wildernessChestLoot != null : "Error while starting Wilderness-Chest-Loot";
        this.wildernessChestLoot = wildernessChestLoot;

        register();

    }

    private void register()   {

        // register managers
        itemManager = new ItemManager();

        // register events
        Bukkit.getPluginManager().registerEvents(new ChestGenerateEvent(), wildernessChestLoot);

        // register commands
        wildernessChestLoot.getCommand("wilditems").setExecutor(new ItemCommand());
    }

    public ItemManager getItemManager()   { return itemManager; }


}
