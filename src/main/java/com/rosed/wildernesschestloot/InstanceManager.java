package com.rosed.wildernesschestloot;

import com.rosed.wildernesschestloot.customitems.ItemManager;
import com.rosed.wildernesschestloot.customitems.impl.executable.Excalibur;
import com.rosed.wildernesschestloot.customitems.listeners.EquippableItemListener;
import com.rosed.wildernesschestloot.customitems.listeners.ExecutableItemListener;
import com.rosed.wildernesschestloot.customitems.ItemCommand;
import com.rosed.wildernesschestloot.customitems.tracker.ExcaliburTracker;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public enum InstanceManager {

    INSTANCE;

    private WildernessChestLoot plugin;
    private ItemManager itemManager;
    private ExcaliburTracker excaliburTracker;

    public void start(WildernessChestLoot plugin) {
        assert plugin != null : "Error while starting Wilderness-Chest-Loot";
        this.plugin = plugin;

        register();
    }

    private void register() {

        // register managers
        itemManager = new ItemManager();

        // register events
        PluginManager pluginManager = Bukkit.getPluginManager();
        // Custom item listener
        pluginManager.registerEvents(new ExecutableItemListener(), plugin);
        pluginManager.registerEvents(new EquippableItemListener(), plugin);
        // Loot listener
        pluginManager.registerEvents(new ChestGenerateEvent(), plugin);
        // Trackers
        excaliburTracker = new ExcaliburTracker();

        // Register commands
        // We use the command map to register commands instead of declaring them into the plugin.yml
        Bukkit.getCommandMap().register("wilditems", new ItemCommand());
    }

    public WildernessChestLoot getPlugin() {
        return plugin;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public ExcaliburTracker getExcaliburTracker() { return excaliburTracker; }

}
