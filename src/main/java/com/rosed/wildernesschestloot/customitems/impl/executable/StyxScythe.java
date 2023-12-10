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
import org.bukkit.inventory.ItemStack;

public class StyxScythe implements ExecutableItem {

    @Override
    public void execute(LivingEntity executor, Entity... targets) {
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
                .name("<red><b><i>Styx Scythe")
                .lore("<#dfc76c>If a running victim is hit,")
                .lore("<#dfc76c>they will be stopped")
                .lore("")
                .lore("<gray>Sharpness I")
                .lore("<gray>Unbreaking I")
                .meta(meta -> {
                    meta.addEnchant(Enchantment.DURABILITY, 1, true);
                    meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
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
