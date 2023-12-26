package com.rosed.wildernesschestloot.customitems.impl.executable;

import com.rosed.wildernesschestloot.customitems.impl.CustomItem;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;

public interface ExecutableItem extends CustomItem<Event> {

    void execute(Event trigger, LivingEntity executor, Entity... targets);

}
