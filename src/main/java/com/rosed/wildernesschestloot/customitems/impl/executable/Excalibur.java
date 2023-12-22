package com.rosed.wildernesschestloot.customitems.impl.executable;

import com.google.common.collect.Lists;
import com.rosed.wildernesschestloot.InstanceManager;
import com.rosed.wildernesschestloot.customitems.tracker.ExcaliburTracker;
import com.rosed.wildernesschestloot.util.Util;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Excalibur implements ExecutableItem {

    @Override
    public boolean shouldTrigger(Event trigger) {
        return trigger instanceof EntityDamageByEntityEvent;
    }

    @Override
    public ItemStack itemStack() {
        ItemStack sword = new ItemStack(Material.GOLDEN_SWORD);
        sword.editMeta(meta -> {
            meta.displayName(Component.text("Excalibur", NamedTextColor.BLUE).decoration(TextDecoration.ITALIC, false));
            meta.addEnchant(org.bukkit.enchantments.Enchantment.DAMAGE_ALL, 6, true);
            meta.lore(Lists.newArrayList(
                            Component.text("Sharpness VI", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                            Component.text("Dancing with blades", NamedTextColor.DARK_PURPLE),
                            Component.empty(),
                            Component.text("- Strength Combo", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false)
                    )
            );
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        });
        return Util.saveCustomItem(sword, this);
    }

    @Override
    public String plainName() {
        return "Excalibur";
    }

    @Override
    public Component name()  {return Component.text("Excalibur", NamedTextColor.BLUE); }

    @Override
    public void execute(LivingEntity executor, Entity... targets) {
        ExcaliburTracker excaliburHitTracker = InstanceManager.INSTANCE.getExcaliburTracker();
        if (excaliburHitTracker.get(executor) == - 1)   {
            excaliburHitTracker.add(executor);
        }

        excaliburHitTracker.increment(executor);
        if (excaliburHitTracker.get(executor) == 0) {
            executor.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 0, true, false, true));
        }
    }
}
