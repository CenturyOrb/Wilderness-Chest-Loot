package com.rosed.wildernesschestloot.customitems.impl.equippable;

import com.google.common.collect.Lists;
import com.rosed.wildernesschestloot.util.Util;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class GoldenFleece implements EquippableItem {

    @Override
    public boolean shouldTrigger(EquipmentSlot trigger) {
        return trigger == EquipmentSlot.CHEST;
    }

    @Override
    public ItemStack itemStack() {
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        chestplate.editMeta(meta -> {
            meta.displayName(Component.text("Golden Fleece", NamedTextColor.GOLD).decoration(TextDecoration.ITALIC, false));
            meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
            meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 3, true);
            meta.addEnchant(Enchantment.DURABILITY, 10, true);
            meta.lore(Lists.newArrayList(
                            Component.text("Woven from the ethereal threads of the", NamedTextColor.DARK_PURPLE),
                            Component.text("legendary Gilded Ewe", NamedTextColor.DARK_PURPLE),
                            Component.empty(),
                            Component.text("- Regeneration", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false)
                    )
            );
            //meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        });
        return Util.saveCustomItem(chestplate, this);
    }

    @Override
    public String plainName() {
        return "Golden Fleece";
    }

    @Override
    public Component name() {
        return Component.text("Golden Fleece", NamedTextColor.GOLD);
    }

    @Override
    public void onEquip(LivingEntity target) {
        target.addPotionEffect(PotionEffectType.REGENERATION.createEffect(-1, 0));
    }

    @Override
    public void onUnequip(LivingEntity target) {
        target.removePotionEffect(PotionEffectType.REGENERATION);
    }
}
