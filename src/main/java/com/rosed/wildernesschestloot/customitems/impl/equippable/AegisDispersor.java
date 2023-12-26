package com.rosed.wildernesschestloot.customitems.impl.equippable;

import com.google.common.collect.Lists;
import com.rosed.wildernesschestloot.InstanceManager;
import com.rosed.wildernesschestloot.util.Util;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class AegisDispersor implements EquippableItem {

    @Override
    public boolean shouldTrigger(EquipmentSlot trigger) {
        return trigger == EquipmentSlot.HAND;
    }

    @Override
    public ItemStack itemStack() {
        ItemStack clock = new ItemStack(Material.HEART_OF_THE_SEA);
        clock.editMeta(meta -> {
            meta.displayName(Component.text("Aegis Dispersor", NamedTextColor.GOLD).decoration(TextDecoration.ITALIC, false));
            meta.addEnchant(Enchantment.DURABILITY, 1, true);
            meta.lore(Lists.newArrayList(
                            Component.text("Forged in the heart of celestial forges", NamedTextColor.DARK_PURPLE),
                            Component.empty(),
                            Component.text("- Disrupt projectiles", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false)
                    )
            );
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        });
        return Util.saveCustomItem(clock, this);
    }

    @Override
    public String plainName() {
        return "Aegis Dispersor";
    }

    @Override
    public Component name() {
        return Component.text("Aegis Dispersor", NamedTextColor.GOLD);
    }

    @Override
    public void onEquip(LivingEntity target) {
        InstanceManager.INSTANCE.getItemManager().getAegisEffectThingie().getAegisUsers().add(target);
    }

    @Override
    public void onUnequip(LivingEntity target) {
        InstanceManager.INSTANCE.getItemManager().getAegisEffectThingie().getAegisUsers().remove(target);
    }

}
