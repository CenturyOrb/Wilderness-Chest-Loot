package com.rosed.wildernesschestloot.customitems.impl.equippable;

import com.rosed.wildernesschestloot.customitems.impl.CustomItem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EquipmentSlot;

public interface EquippableItem extends CustomItem<EquipmentSlot> {

    void onEquip(LivingEntity target);

    void onUnequip(LivingEntity target);

}
