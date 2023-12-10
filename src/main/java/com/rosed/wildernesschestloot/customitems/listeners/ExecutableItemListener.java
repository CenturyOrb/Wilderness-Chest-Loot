package com.rosed.wildernesschestloot.customitems.listeners;

import com.rosed.wildernesschestloot.customitems.impl.executable.ExecutableItem;
import com.rosed.wildernesschestloot.util.Util;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

public class ExecutableItemListener implements Listener {

    /**
     * Event executes custom item when an entity damages
     * another entity using a custom item in main hand
     *
     * @param event EntityDamageByEntityEvent
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

        // Check if the source is a living entity
        if (event.getDamager() instanceof LivingEntity source)
            executeItemIfPresent(event, source, event.getEntity());
    }

    /**
     * Event executes custom item when a player gets killed by
     * a player using a custom item in main hand
     *
     * @param event PlayerDeathEvent
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerDeathByEntity(PlayerDeathEvent event) {

        // Check if the source is a Player
        Player source = event.getEntity().getKiller();
        if (source != null)
            executeItemIfPresent(event, source, event.getEntity());
    }

    /**
     * Event executes custom item when an Entity gets killed by
     * a player using a custom item in main hand
     *
     * @param event EntityDeathEvent
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onEntityDeathByPlayer(EntityDeathEvent event) {

        // Check if the source is a Player
        Player source = event.getEntity().getKiller();
        if (source != null)
            executeItemIfPresent(event, source, event.getEntity());

    }

    /**
     * Event executes custom item when an Entity gets killed by
     * a player using a custom item in main hand
     *
     * @param sourceEvent  Event
     * @param sourceEntity LivingEntity
     *                     <p>
     *                     Executes the custom item if the sourceEntity has a custom item in main hand
     *                     and the custom item triggers the sourceEvent
     *                     </p>
     *                     <p>
     *                     The custom item will be executed on the
     *                     targetEntity
     *                     </p>
     * @param targets      Entity...
     *                     <p>
     *                     The targets of the custom item
     *                     </p>
     */
    private void executeItemIfPresent(Event sourceEvent, LivingEntity sourceEntity, Entity... targets) {
        EntityEquipment entityEquipment = sourceEntity.getEquipment();
        if (entityEquipment == null)
            return;

        ItemStack handItem = entityEquipment.getItemInMainHand();

        // Check if the item is a custom item
        ExecutableItem customItem = Util.getCustomItem(ExecutableItem.class, handItem);
        if (customItem == null)
            return;

        // Check if the item should be triggered by this event
        if (!customItem.shouldTrigger(sourceEvent))
            return;

        // Execute the custom item
        customItem.execute(sourceEntity, targets);
    }

}
