package com.rosed.wildernesschestloot.customitems;

import com.rosed.wildernesschestloot.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

public class ItemListener implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

        // Check if the source is a living entity
        if (!(event.getDamager() instanceof LivingEntity source))
            return;

        // Check if the source has equipment
        EntityEquipment entityEquipment = source.getEquipment();
        if (entityEquipment == null)
            return;

        // Get the item on the source's hand
        ItemStack handItem = entityEquipment.getItemInMainHand();

        // Check if the item is a custom item
        CustomItem customItem = Util.getCustomItem(handItem);
        if (customItem == null)
            return;

        // Check if the item should be triggered by this event
        if (!customItem.triggers().contains(event.getClass()))
            return;

        // Execute the custom item
        customItem.execute(source, event.getEntity());
    }

    /**
     * Event executes custom item when a player gets killed by
     * a player using a custom item in main hand
     * @param event PlayerDeathEvent
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerDeathByEntity(PlayerDeathEvent event) {

        // Check if the source is a Player
        Player source;
        if (event.getPlayer().getKiller() == null)
            return;
        else source = event.getPlayer().getKiller();

        // Get the item on the source's hand
        EntityEquipment entityEquipment = source.getEquipment();
        ItemStack handItem = entityEquipment.getItemInMainHand();

        // Check if the item is a custom item
        CustomItem customItem = Util.getCustomItem(handItem);
        if (customItem == null)
            return;

        // Check if the item should be triggered by this event
        if (!customItem.triggers().contains(event.getClass()))
            return;

        // Execute the custom item
        customItem.execute(source, event.getEntity());
    }

    /**
     * Event executes custom item when an Entity gets killed by
     * a player using a custom item in main hand
     * @param event EntityDeathEvent
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onEntityDeathByPlayer(EntityDeathEvent event) {

        // Check if the source is a Player
        Player source;
        if (event.getEntity().getKiller() == null)
            return;
        else source = event.getEntity().getKiller();

        // Get the item on the source's hand
        EntityEquipment entityEquipment = source.getEquipment();
        ItemStack handItem = entityEquipment.getItemInMainHand();

        // Check if the item is a custom item
        CustomItem customItem = Util.getCustomItem(handItem);
        if (customItem == null)
            return;

        // Check if the item should be triggered by this event
        if (!customItem.triggers().contains(event.getClass()))
            return;

        // Execute the custom item
        customItem.execute(source, event.getEntity());
    }

}
