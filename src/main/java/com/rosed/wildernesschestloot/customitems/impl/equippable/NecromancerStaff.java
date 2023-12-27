package com.rosed.wildernesschestloot.customitems.impl.equippable;

import com.google.common.collect.Lists;
import com.rosed.wildernesschestloot.customitems.impl.executable.ExecutableItem;
import com.rosed.wildernesschestloot.util.ItemBuilder;
import com.rosed.wildernesschestloot.util.Util;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class NecromancerStaff implements ExecutableItem {

    @Override
    public boolean shouldTrigger(Event trigger) {
        return trigger instanceof PlayerDeathEvent || trigger instanceof EntityDamageByEntityEvent;
    }

    @Override
    public ItemStack itemStack() {
        return ItemBuilder.start(Material.STICK).build();
    }

    @Override
    public String plainName() {
        return "Necromancer Staff";
    }

    @Override
    public Component name() {
        return Component.text("Necromancer Staff", NamedTextColor.DARK_GRAY);
    }

    private List<UUID> minions = Lists.newArrayList();

    @Override
    public void execute(Event trigger, LivingEntity executor, Entity... targets) {
        if (trigger instanceof PlayerDeathEvent deathEvent) {
            for (Entity target : targets) {
                UUID spawned = spawnMinion(target);
                if (spawned != null)
                    minions.add(spawned);
            }
        }

        if (trigger instanceof EntityDamageByEntityEvent damageByEntityEvent) {
            Iterator<UUID> iterator = minions.iterator();
            while (iterator.hasNext()) {
                UUID uuid = iterator.next();

                Entity minion = Bukkit.getEntity(uuid);
                if(minion == null || !minion.isValid())
                    

            }
        }
    }

    private UUID spawnMinion(Entity target) {
        // 30% chance to spawn a minion from a killed entity
        boolean shouldSpawn = Util.rollDice(30);
        if (!shouldSpawn)
            return null;

        // 50/50 for skeleton and zombie to spawn as a minion of a killed player
        Class<? extends LivingEntity> type = ThreadLocalRandom.current().nextBoolean() ? Skeleton.class : Zombie.class;
        Entity spawned = target.getWorld().spawn(target.getLocation(), type, e -> {
            // Here we can customize the entity as we like, with armor, potions, etc
        });
        return spawned.getUniqueId();
    }

}
