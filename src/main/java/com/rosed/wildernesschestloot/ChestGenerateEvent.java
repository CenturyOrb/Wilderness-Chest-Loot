package com.rosed.wildernesschestloot;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.ItemStack;


public class ChestGenerateEvent implements Listener {

    @EventHandler
    public void onChestGenerate(LootGenerateEvent e)   {

        Bukkit.broadcastMessage("fired event");

        double random = Math.random();

        // 3% chance of a custom tier 1 item
        if (random <= 0.03 && e.getInventoryHolder() instanceof Chest)   {
            e.getLoot().add(new ItemStack(Material.NETHERITE_AXE));
        }

        // 15% chance of a golden apple
        if (random <= 0.15 && e.getInventoryHolder() instanceof Chest)   {
            e.getLoot().add(new ItemStack(Material.GOLDEN_APPLE));
        }

        // 2% chance of a custom tier 2 item
        if (random <= 0.02 && e.getInventoryHolder() instanceof Chest)   {
            e.getLoot().add(new ItemStack(Material.NETHERITE_SWORD));
        }

    }

}
