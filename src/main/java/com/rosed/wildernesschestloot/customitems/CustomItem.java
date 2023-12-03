package com.rosed.wildernesschestloot.customitems;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import java.io.Serializable;
import java.util.List;

public interface CustomItem extends Serializable {

    void execute(LivingEntity executor, Entity... targets);

    <T extends Event> List<Class<T>> triggers();

    ItemStack itemStack();

}
