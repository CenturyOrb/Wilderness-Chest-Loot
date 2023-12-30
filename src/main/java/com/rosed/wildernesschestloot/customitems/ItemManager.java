package com.rosed.wildernesschestloot.customitems;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rosed.wildernesschestloot.InstanceManager;
import com.rosed.wildernesschestloot.customitems.impl.CustomItem;
import com.rosed.wildernesschestloot.customitems.impl.equippable.AegisDispersor;
import com.rosed.wildernesschestloot.customitems.impl.equippable.AegisEffectThingie;
import com.rosed.wildernesschestloot.customitems.impl.equippable.GoldenFleece;
import com.rosed.wildernesschestloot.customitems.impl.equippable.TalariaBoots;
import com.rosed.wildernesschestloot.customitems.impl.executable.Excalibur;
import com.rosed.wildernesschestloot.customitems.impl.executable.ExplosiveBow;
import com.rosed.wildernesschestloot.customitems.impl.executable.SovereignSword;
import com.rosed.wildernesschestloot.customitems.impl.executable.StyxScythe;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class ItemManager {

    private final Map<String, CustomItem<?>> items = Maps.newHashMap();
    private final AegisEffectThingie aegisEffectThingie;

    public ItemManager() {
        // Register all the items by class
        // Executable items
        registerItem(SovereignSword.class);
        registerItem(ExplosiveBow.class);
        registerItem(StyxScythe.class);
        registerItem(Excalibur.class);
        registerItem(AegisDispersor.class);

        // Equippable Items
        registerItem(TalariaBoots.class);
        registerItem(GoldenFleece.class);
        aegisEffectThingie = new AegisEffectThingie();
        aegisEffectThingie.runTaskTimer(InstanceManager.INSTANCE.getPlugin(), 0L, 3L);

        // Throwable Items
    }

    /**
     * Gets an item by name
     *
     * @param name The name of the item
     * @return The item, or null if it doesn't exist
     */
    public CustomItem<?> getItem(String name) {
        return items.get(name);
    }

    /**
     * Gets a list of all the item names
     *
     * @return A list of all the item names
     */
    public List<String> getItemNames() {
        return Lists.newArrayList(items.keySet());
    }

    // Reflections FTW

    /**
     * Registers an item by class
     *
     * @param itemClass The class of the item
     * @param <T>       The type of the item
     */
    private <T extends CustomItem<?>> void registerItem(Class<T> itemClass) {
        try {
            // We use reflections to get the no args constructor and create a new instance of the item
            T item = itemClass.getDeclaredConstructor().newInstance();
            // We call the onRegister method for the custom item to register listeners or run other logic
            item.onRegister();
            // We get the name of item and put it in the map
            items.put(item.plainName(), item);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            InstanceManager.INSTANCE.getPlugin().getLogger().severe("Failed to register custom item: " + itemClass.getSimpleName());
        }
    }

    public AegisEffectThingie getAegisEffectThingie() {
        return aegisEffectThingie;
    }

}
