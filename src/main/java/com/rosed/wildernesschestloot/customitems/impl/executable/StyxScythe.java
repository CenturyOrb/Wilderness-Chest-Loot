package com.rosed.wildernesschestloot.customitems.impl.executable;

import com.rosed.wildernesschestloot.util.ItemBuilder;
import com.rosed.wildernesschestloot.util.Util;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class StyxScythe implements ExecutableItem {

    @Override
    public void execute(Event trigger, LivingEntity executor, Entity... targets) {
        for (Entity target : targets) {
            if (target instanceof Player victim)
                if (victim.isSprinting())
                    victim.setSprinting(false);
        }
    }

    @Override
    public boolean shouldTrigger(Event trigger) {
        return trigger instanceof EntityDamageByEntityEvent;
    }

    @Override
    public ItemStack itemStack() {
        ItemStack scythe = ItemBuilder.start(Material.IRON_HOE)
                .name("<red>Styx Scythe")
                .lore("<gray>Sharpness I")
                .lore("<gray>Unbreaking I")
                .lore("<#dfc76c><i><DARK_PURPLE>If a running victim is hit,")
                .lore("<#dfc76c><i><DARK_PURPLE>they will be stopped")
                .meta(meta -> {
                    meta.addEnchant(Enchantment.DURABILITY, 1, true);
                    meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                }).build();
        return Util.saveCustomItem(scythe, this);
    }

    @Override
    public String plainName() {
        return "Styx Scythe";
    }

    @Override
    public Component name() {
        return Component.text("Styx Scythe", NamedTextColor.RED);
    }

}
