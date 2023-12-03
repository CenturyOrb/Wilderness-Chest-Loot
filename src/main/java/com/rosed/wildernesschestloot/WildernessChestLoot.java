package com.rosed.wildernesschestloot;

import com.rosed.wildernesschestloot.customitems.ItemListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class WildernessChestLoot extends JavaPlugin {

    @Override
    public void onEnable() {
        InstanceManager.INSTANCE.start(this);

        getServer().getPluginManager().registerEvents(new ItemListener(), this);
        // The server has a logger that shows this message
//        Bukkit.broadcastMessage("Wilderness-Chest-Loot has started");
    }

    @Override
    public void onDisable() {

    }

}
