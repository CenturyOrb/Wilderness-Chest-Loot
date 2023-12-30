package com.rosed.wildernesschestloot.customitems.impl.equippable;

import com.google.common.collect.Lists;
import com.rosed.wildernesschestloot.InstanceManager;
import com.rosed.wildernesschestloot.customitems.impl.executable.ExecutableItem;
import com.rosed.wildernesschestloot.util.ItemBuilder;
import com.rosed.wildernesschestloot.util.Util;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class NecromancerStaff implements ExecutableItem {

    private static final NamespacedKey minionKey = new NamespacedKey(InstanceManager.INSTANCE.getPlugin(), "minion");

    @Override
    public void onRegister() {
        Bukkit.getServer().getPluginManager().registerEvents(new Listener() {
            @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
            // This event is fired when a mob/entity targets another *living* entity
            // We will use this to stop the minions from targeting the summoner
            public void onDeath(EntityTargetLivingEntityEvent event) {
                // If the entity is not a minion, we don't care
                String storedUUID = event.getEntity().getPersistentDataContainer().get(minionKey, PersistentDataType.STRING);
                if (storedUUID == null)
                    return;

                // If the target is not a player, we don't care
                if (!(event.getTarget() instanceof Player))
                    return;

                UUID summonerUUID = UUID.fromString(storedUUID);
                UUID targetUUID = event.getTarget().getUniqueId();
                // If the target is the summoner, we cancel the event
                if (targetUUID.equals(summonerUUID))
                    event.setCancelled(true);
            }
        }, InstanceManager.INSTANCE.getPlugin());
    }

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

    private final List<UUID> minions = Lists.newArrayList();

    @Override
    public void execute(Event trigger, LivingEntity executor, Entity... targets) {
        if (trigger instanceof PlayerDeathEvent) {
            for (Entity target : targets) {
                UUID spawned = spawnMinion(executor, target);
                if (spawned != null)
                    minions.add(spawned);
            }
        }

        if (trigger instanceof EntityDamageByEntityEvent damageByEntityEvent) {
            // If the hit entity is not a living entity, we don't care
            if (!(damageByEntityEvent.getEntity() instanceof LivingEntity target))
                return;

            Iterator<UUID> iterator = minions.iterator();
            while (iterator.hasNext()) {
                UUID uuid = iterator.next();

                // We check if the minion is still valid (alive or loaded) and remove it from the list if it's not
                Entity minion = Bukkit.getEntity(uuid);
                if (minion == null || !minion.isValid()) {
                    iterator.remove();
                    continue;
                }

                // In theory this is always true as the minion are always a Mob as per our implementation of spawnMinion
                // But anyway, we check it just to be sure and cast it to access the setTarget method on the Mob class
                if (minion instanceof Mob mob)
                    // We set the target of the minion to the entity that was hit by the summoner
                    mob.setTarget(target);
            }
        }
    }

    private UUID spawnMinion(Entity summoner, Entity target) {
        // 30% chance to spawn a minion from a killed entity
        boolean shouldSpawn = Util.rollDice(30);
        if (!shouldSpawn)
            return null;

        // 50/50 for skeleton and zombie to spawn as a minion of a killed player
        Class<? extends LivingEntity> type = ThreadLocalRandom.current().nextBoolean() ? Skeleton.class : Zombie.class;
        Entity spawned = target.getWorld().spawn(target.getLocation(), type, e -> {
            // Here we can customize the entity as we like, with armor, potions, etc

            // We store the UUID of the summoner in the entity's persistent data container, so we can use elsewhere
            // We also use this to check if the entity is a minion or not
            e.getPersistentDataContainer().set(minionKey, PersistentDataType.STRING, summoner.getUniqueId().toString());

        });
        return spawned.getUniqueId();
    }

}
