package com.rosed.wildernesschestloot.customitems.impl.equippable;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class AegisEffectThingie extends BukkitRunnable {

    /**
     * This just a task taht keeps tracks of arrows and entities that are
     * wusing Aegis Dispersor works by a runnable running ever 3 ticks
     * checking all arrows if they are near any one of the aegis dispursor users
     */

    private List<LivingEntity> aegisUsers;

    public AegisEffectThingie()   {
        aegisUsers = new ArrayList<>();
    }

    /**
     * Checks each arrow if the arrow is near a player
     * if arrow is near a player then destroy the arrow
     *
     */
    @Override
    public void run() {
        List<Arrow> arrows = new ArrayList<>();
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity instanceof Arrow) arrows.add((Arrow) entity);
            }
        }

        for (Arrow arrow : arrows) {
            for (LivingEntity entity : aegisUsers) {
                if (arrow.getLocation().distance(entity.getLocation()) < 5)
                    Bukkit.getWorld(arrow.getWorld().getUID()).spawnParticle(Particle.EXPLOSION_NORMAL, arrow.getLocation(), 1, 1);
                    Bukkit.getWorld(arrow.getWorld().getUID()).playSound(arrow.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);
                    arrow.remove();
            }
        }
    }

    public List<LivingEntity> getAegisUsers() { return aegisUsers; }
}
