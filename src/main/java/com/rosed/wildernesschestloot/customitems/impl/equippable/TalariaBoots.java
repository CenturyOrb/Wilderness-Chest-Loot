package com.rosed.wildernesschestloot.customitems.impl.equippable;

import com.rosed.wildernesschestloot.util.ItemBuilder;
import com.rosed.wildernesschestloot.util.Util;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class TalariaBoots implements EquippableItem {

    @Override
    public void onEquip(LivingEntity target) {
        target.addPotionEffect(PotionEffectType.SPEED.createEffect(-1, 1));
    }

    @Override
    public void onUnequip(LivingEntity target) {
        target.removePotionEffect(PotionEffectType.SPEED);
    }

    @Override
    public String plainName() {
        return "Talaria Boots";
    }

    @Override
    public Component name() {
        return Component.text("Talaria Boots", NamedTextColor.GOLD);
    }

    @Override
    public boolean shouldTrigger(EquipmentSlot trigger) {
        return trigger == EquipmentSlot.FEET;
    }

    @Override
    public ItemStack itemStack() {
        ItemStack talariaBoots = ItemBuilder.start(Material.IRON_BOOTS)
                .name("<yellow><b><i>Talaria Boots")
                .lore("<#dfc76c>These boots were made for walking")
                .lore("<#dfc76c>and that's just what they'll do")
                .lore("<#dfc76c>one of these days these boots")
                .lore("<#dfc76c>are gonna walk all over you")
                .lore(" ")
                .lore("<gray>Protection V")
                .lore("<gray>Unbreaking V")
                .lore("<gray>Feather Falling VII")
                .meta(meta -> {
                    meta.addEnchant(Enchantment.DURABILITY, 5, true);
                    meta.addEnchant(Enchantment.PROTECTION_FALL, 7, true);
                    meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
                }).flags(ItemFlag.HIDE_ENCHANTS)
                .build();
        return Util.saveCustomItem(talariaBoots, this);
    }

}
