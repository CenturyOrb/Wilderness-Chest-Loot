package com.rosed.wildernesschestloot.customitems.impl.executable;

import com.rosed.wildernesschestloot.util.Util;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

public class ExplosiveBow implements ExecutableItem {

    @Override
    public void execute(LivingEntity executor, Entity... targets) {

    }

    @Override
    public boolean shouldTrigger(Event trigger) {
        return trigger instanceof EntityDamageByEntityEvent || trigger instanceof ProjectileHitEvent;
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

    @Override
    public String plainName() {
        return "Explosive Bow";
    }

    @Override
    public Component name() {
        return Component.text("Explosive Bow", NamedTextColor.RED);
    }

}
