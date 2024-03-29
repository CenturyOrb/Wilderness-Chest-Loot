package com.rosed.wildernesschestloot.customitems.impl.equippable;

import com.google.common.collect.Lists;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class AegisEffectThingie extends BukkitRunnable {

    /**
     * This just a task that keeps tracks of arrows and entities that are
     * using Aegis Dispersor works by a runnable running ever 3 ticks
     * checking all arrows if they are near any one of the aegis dispersor users
     */
    private final List<LivingEntity> aegisUsers = Lists.newArrayList();

    /**
     * Checks each arrow if the arrow is near a player
     * if arrow is near a player then destroy the arrow
     */
    @Override
    public void run() {
        for (LivingEntity user : aegisUsers) {
            for (Entity entity : user.getNearbyEntities(5, 5, 5)) {
                if (entity instanceof Arrow arrow) {
                    arrow.getWorld().spawnParticle(Particle.END_ROD, arrow.getLocation(), 1, 0, 0, 0, 0);
                    arrow.getWorld().playSound(arrow.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);
                    arrow.remove();
                }
            }
        }
    }

    public List<LivingEntity> getAegisUsers() {
        return aegisUsers;
    }

}
