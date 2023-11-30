package com.rosed.wildernesschestloot;

import org.bukkit.Bukkit;

public enum InstanceManager {

    INSTANCE;

    private WildernessChestLoot wildernessChestLoot;

    public void start(WildernessChestLoot wildernessChestLoot)   {

        assert wildernessChestLoot != null : "Error while starting Wilderness-Chest-Loot";
        this.wildernessChestLoot = wildernessChestLoot;

        register();

    }

    private void register()   {

        // register events
        Bukkit.getPluginManager().registerEvents(new ChestGenerateEvent(), wildernessChestLoot);

    }


}
