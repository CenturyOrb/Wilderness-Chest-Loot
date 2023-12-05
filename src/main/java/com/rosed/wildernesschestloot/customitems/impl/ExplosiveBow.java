package com.rosed.wildernesschestloot.customitems.impl;

import com.google.common.collect.Lists;
import com.rosed.wildernesschestloot.customitems.CustomItem;
import com.rosed.wildernesschestloot.util.Util;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ExplosiveBow implements CustomItem {

    @Override
    public void execute(LivingEntity executor, Entity... targets) {

    }

    @Override
    public List<Class<?>> triggers() {
        List<Class<?>> list = Lists.newArrayList();
        list.add(EntityDamageByEntityEvent.class);
        list.add(ProjectileHitEvent.class);
        return list;
    }

    @Override
    public ItemStack itemStack() {
        ItemStack bow = new ItemStack(Material.BOW);
        bow.editMeta(meta -> {
            meta.displayName(Component.text("Explosive Bow", NamedTextColor.RED));
            meta.setUnbreakable(true);
        });
        return Util.saveCustomItem(bow, this);
    }
}
