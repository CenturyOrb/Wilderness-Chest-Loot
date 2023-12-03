package com.rosed.wildernesschestloot.customitems;

import com.rosed.wildernesschestloot.util.Util;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
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

}
