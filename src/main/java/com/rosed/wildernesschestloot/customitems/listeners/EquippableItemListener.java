package com.rosed.wildernesschestloot.customitems.listeners;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import com.rosed.wildernesschestloot.customitems.impl.equippable.EquippableItem;
import com.rosed.wildernesschestloot.util.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class EquippableItemListener implements Listener {

    /**
     * Event executes custom item when a player joins the server
     * only if the items are equippable items
     * <p>
     * This is used to apply the effects of the items
     * as soon as the player joins the server
     *
     * @param event PlayerJoinEvent
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        List<EquippableItem> equippedItems = Util.equippedCustomItems(EquippableItem.class, player.getEquipment());
        for (EquippableItem item : equippedItems)
            item.onEquip(player);
    }

    /**
     * Event executes custom item when a player quits the server
     * only if the items are equippable items
     * <p>
     * This is used to remove the effects of the items
     * as soon as the player quits the server
     *
     * @param event PlayerQuitEvent
     */
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        List<EquippableItem> equippedItems = Util.equippedCustomItems(EquippableItem.class, player.getEquipment());
        for (EquippableItem item : equippedItems)
            item.onUnequip(player);
    }

    /**
     * Event executes custom item when a player changes their armor
     * and if the armor is an equippable items
     *
     * @param event PlayerArmorChangeEvent
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onEquip(PlayerArmorChangeEvent event) {
        PlayerArmorChangeEvent.SlotType slotType = event.getSlotType();
        int size = EquipmentSlot.values().length - 1;
        EquipmentSlot equipmentSlot = EquipmentSlot.values()[size - slotType.ordinal()];
        executeItem(event.getPlayer(), equipmentSlot, event.getNewItem(), event.getOldItem());
    }

    /**
     * Event executes custom item when a player changes their held item
     * and if the item is an equippable items
     *
     * @param event PlayerItemHeldEvent
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onHeld(PlayerItemHeldEvent event) {
        executeItem(event.getPlayer(), EquipmentSlot.HAND, event.getPlayer().getInventory().getItem(event.getNewSlot()), event.getPlayer().getInventory().getItem(event.getPreviousSlot()));
    }

    /**
     * Event executes custom item when a player swaps their main hand item
     * and if the item is an equippable items
     *
     * @param event PlayerSwapHandItemsEvent
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onSwap(PlayerSwapHandItemsEvent event) {
        executeItem(event.getPlayer(), EquipmentSlot.HAND, event.getMainHandItem(), event.getOffHandItem());
    }

    /**
     * Executes the custom item if the new item is an equippable item
     * and disables the custom item if the old item is an equippable item
     *
     * @param player  The player that triggered the execution
     * @param trigger The EquipmentSlot that triggered the execution
     * @param newItem The new item that the player is equipping
     * @param oldItem The old item that the player is un-equipping
     */
    private void executeItem(Player player, EquipmentSlot trigger, ItemStack newItem, ItemStack oldItem) {
        EquippableItem oldCustomItem = Util.getCustomItem(EquippableItem.class, oldItem);
        EquippableItem newCustomItem = Util.getCustomItem(EquippableItem.class, newItem);

        if (oldCustomItem != null && oldCustomItem.shouldTrigger(trigger))
            oldCustomItem.onUnequip(player);

        if (newCustomItem != null && newCustomItem.shouldTrigger(trigger))
            newCustomItem.onEquip(player);

    }

}
