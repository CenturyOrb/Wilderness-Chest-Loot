package com.rosed.wildernesschestloot.customitems.impl;

import com.google.common.collect.Lists;
import com.rosed.wildernesschestloot.customitems.CustomItem;
import com.rosed.wildernesschestloot.util.Util;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class SovereignSword implements CustomItem {

    @Override
    public void execute(LivingEntity executor, Entity... targets) {

    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Event> List<Class<T>> triggers() {
        List<Class<T>> list = Lists.newArrayList();
        list.add((Class<T>) EntityDamageByEntityEvent.class);
        return list;
    }

    @Override
    public ItemStack itemStack() {
        ItemStack sword = new ItemStack(Material.NETHERITE_SWORD);
        sword.editMeta(meta -> {
            meta.displayName(Component.text("Sovereign Sword", NamedTextColor.AQUA));
            meta.addEnchant(org.bukkit.enchantments.Enchantment.DAMAGE_ALL, 4, true);
            meta.addEnchant(org.bukkit.enchantments.Enchantment.SWEEPING_EDGE, 10, true);
            meta.lore(Lists.newArrayList(
                    Component.text("Sharpness IV", NamedTextColor.GRAY),
                    Component.text("Sweeping Edge IV", NamedTextColor.GRAY),
                    Component.text("Blade of the old ruler", NamedTextColor.GRAY),
                    Component.empty(),
                    Component.text("- Renews on frag", NamedTextColor.GREEN)
            ));
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        });
        return Util.saveCustomItem(sword, this);
    }

}
