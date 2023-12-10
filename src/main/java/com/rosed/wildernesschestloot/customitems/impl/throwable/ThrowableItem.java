package com.rosed.wildernesschestloot.customitems.impl.throwable;

import com.rosed.wildernesschestloot.customitems.impl.CustomItem;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Projectile;

public interface ThrowableItem extends CustomItem<EntityType> {

    void onThrow(Projectile projectile);

    void onHit(Projectile projectile);

    void onHitEntity(Projectile projectile, Entity target);

    void onHitBlock(Projectile projectile);

}
