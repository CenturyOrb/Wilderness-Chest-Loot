package com.rosed.wildernesschestloot;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class WildernessChestLoot extends JavaPlugin {

    @Override
    public void onEnable() {
        InstanceManager.INSTANCE.start(this);
        Bukkit.broadcastMessage("Wilderness-Chest-Loot has started");
    }

    @Override
    public void onDisable() {

    }
}
