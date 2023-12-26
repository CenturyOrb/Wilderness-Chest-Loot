package com.rosed.wildernesschestloot.customitems.impl.executable;

import com.google.common.collect.Lists;
import com.rosed.wildernesschestloot.InstanceManager;
import com.rosed.wildernesschestloot.util.Util;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

public class SovereignSword implements ExecutableItem {

    @Override
    public void execute(Event trigger, LivingEntity executor, Entity... targets) {
        Bukkit.getScheduler().runTaskLater(InstanceManager.INSTANCE.getPlugin(), () -> {
            executor.getEquipment().getItemInMainHand().editMeta(Damageable.class, meta -> meta.setDamage(0));
        }, 1);

    }

    @Override
    public boolean shouldTrigger(Event trigger) {
        return trigger instanceof EntityDeathEvent;
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

    @Override
    public String plainName() {
        return "Sovereign Sword";
    }

    @Override
    public Component name() {
        return Component.text("Sovereign Sword", NamedTextColor.BLUE);
    }

}
