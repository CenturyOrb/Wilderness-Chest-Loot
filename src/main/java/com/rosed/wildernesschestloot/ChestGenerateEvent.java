package com.rosed.wildernesschestloot;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootContext;
import org.bukkit.loot.LootTable;

import java.util.List;
import java.util.Random;

public class ChestGenerateEvent implements Listener {

    @EventHandler
    public void onChestGenerate(LootGenerateEvent e)   {

        Entity entity = e.getEntity();
        InventoryHolder inventoryHolder = e.getInventoryHolder();
        LootTable lootTable = e.getLootTable();
        LootContext lootContext = e.getLootContext();
        List<ItemStack> loot = e.getLoot();

        Bukkit.broadcastMessage("getEntity: " + entity);
        Bukkit.broadcastMessage("inventoryHolder: " + inventoryHolder);
        Bukkit.broadcastMessage("lootTable: " + lootTable);
        Bukkit.broadcastMessage("lootContext: " + lootContext);

        Bukkit.broadcastMessage("loot: ");
        for (ItemStack item : loot)   {
            Bukkit.broadcastMessage(item.getI18NDisplayName());
        }

        Inventory inventory = inventoryHolder.getInventory();
        boolean[] chosen = new boolean[inventory.getSize()];
        Random random = new Random();
        int slot;
        do {
            slot = random.nextInt(inventory.getSize());
        } while(chosen[slot]);

        chosen[slot] = true;
        inventory.setItem(random.nextInt(inventory.getSize()), new ItemStack(Material.NETHERITE_AXE));
    }

}
