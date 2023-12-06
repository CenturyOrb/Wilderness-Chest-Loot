package com.rosed.wildernesschestloot.customitems.impl;

import com.google.common.collect.Lists;
import com.rosed.wildernesschestloot.InstanceManager;
import com.rosed.wildernesschestloot.customitems.CustomItem;
import com.rosed.wildernesschestloot.util.Util;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class SovereignSword implements CustomItem {

    @Override
    public void execute(LivingEntity executor, Entity... targets) {

        ItemMeta meta = executor.getEquipment().getItemInMainHand().getItemMeta();

        new BukkitRunnable() {
            @Override
            public void run () {
                ((Damageable) meta).setDamage(0);
                executor.getEquipment().getItemInMainHand().setItemMeta(meta);
            }
        }.runTaskLater(InstanceManager.INSTANCE.getPlugin(), 1);

    }

    @Override
    public List<Class<?>> triggers() {
        List<Class<?>> list = Lists.newArrayList();
        list.add(EntityDeathEvent.class);
        return list;
    }

    @Override
    public ItemStack itemStack() {
        ItemStack sword = new ItemStack(Material.GOLDEN_SWORD);
        sword.editMeta(meta -> {
            meta.displayName(Component.text("Sovereign Sword", NamedTextColor.BLUE).decoration(TextDecoration.ITALIC, false));
            meta.addEnchant(org.bukkit.enchantments.Enchantment.DAMAGE_ALL, 4, true);
            meta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 3, true);
            meta.addEnchant(org.bukkit.enchantments.Enchantment.SWEEPING_EDGE, 10, true);
            meta.lore(Lists.newArrayList(
                    Component.text("Sharpness IV", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                            Component.text("Looting III", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                    Component.text("Sweeping Edge X", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                    Component.text("Blade of the old ruler", NamedTextColor.DARK_PURPLE),
                    Component.empty(),
                    Component.text("- Renews on frag", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false)
            )
            );
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        });
        return Util.saveCustomItem(sword, this);
    }

}
