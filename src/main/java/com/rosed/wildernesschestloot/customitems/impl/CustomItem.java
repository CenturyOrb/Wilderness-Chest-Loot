package com.rosed.wildernesschestloot.customitems.impl;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.Serializable;

public interface CustomItem<T> extends Serializable {

    /**
     * The generic type of the class represents the trigger type
     * This method should return true if the trigger should trigger the item
     *
     * @param trigger The trigger
     * @return True if the trigger should trigger the item
     */
    boolean shouldTrigger(T trigger);

    /**
     * The itemstack that represents this item
     *
     * @return The itemstack
     */
    ItemStack itemStack();

    /**
     * The name of the item, as used in the code
     *
     * @return The name of the item
     */
    String plainName();

    /**
     * The name of the item, as displayed in the item
     *
     * @return The name of the item
     */
    Component name();

    /**
     * Gives the item to the player, dropping it on the ground if their inventory is full
     *
     * @param player The player to give the item to
     */
    default void give(Player player) {
        // The Inventory#addItem method returns a map of items that couldn't be added
        // We drop these items on the ground
        player.getInventory().addItem(itemStack()).forEach((integer, itemStack) -> {
            player.getWorld().dropItem(player.getLocation(), itemStack);
        });
    }

}
