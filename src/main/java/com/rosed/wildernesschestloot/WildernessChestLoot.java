package com.rosed.wildernesschestloot;

import org.bukkit.plugin.java.JavaPlugin;

public final class WildernessChestLoot extends JavaPlugin {

    @Override
    public void onEnable() {
        InstanceManager.INSTANCE.start(this);
    }

    @Override
    public void onDisable() {

    }

}
