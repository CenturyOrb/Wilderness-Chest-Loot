package com.rosed.wildernesschestloot.util;

import com.google.common.collect.Lists;
import com.google.gson.reflect.TypeToken;
import com.rosed.wildernesschestloot.InstanceManager;
import com.rosed.wildernesschestloot.customitems.impl.CustomItem;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.io.*;
import java.util.List;

public class Util {

    public static final NamespacedKey augmentKey = new NamespacedKey(InstanceManager.INSTANCE.getPlugin(), "custom_item");

    private Util() {
    }

    public static <T extends CustomItem<?>> List<T> equippedCustomItems(EntityEquipment equipment) {
        List<T> customItems = Lists.newArrayList();

        if (equipment == null)
            return customItems;

        for (ItemStack item : equipment.getArmorContents()) {
            T customItem = getCustomItem(item);
            if (customItem != null)
                customItems.add(customItem);
        }

        T customItem = getCustomItem(equipment.getItemInMainHand());
        if (customItem != null)
            customItems.add(customItem);

        return customItems;
    }

    public static <T extends CustomItem<?>> ItemStack saveCustomItem(ItemStack item, T customItem) {
        if (item == null || item.getType().isAir())
            return null;
        ItemMeta meta = item.getItemMeta();
        byte[] data = serialize(customItem);

        if (data == null)
            return item;

        meta.getPersistentDataContainer().set(augmentKey, PersistentDataType.BYTE_ARRAY, data);
        item.setItemMeta(meta);
        return item;
    }

    public static <T extends CustomItem<?>> T getCustomItem(ItemStack item) {
        if (item == null || item.getType().isEmpty())
            return null;

        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();

        if (!pdc.has(augmentKey, PersistentDataType.BYTE_ARRAY))
            return null;

        // Hacky way to get the class of the generic type
        Class<?> clazz = new TypeToken<T>() {
        }.getRawType();
        byte[] data = pdc.get(augmentKey, PersistentDataType.BYTE_ARRAY);
        Object customItem = deserialize(data);
        if (!clazz.isInstance(customItem))
            return null;
        return deserialize(data);
    }

    @SuppressWarnings("unchecked")
    public static <T extends CustomItem<?>> T deserialize(byte[] serialized) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(serialized);
             ObjectInputStream ois = new ObjectInputStream(bais)) {
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    public static <T extends CustomItem<?>> byte[] serialize(T customItem) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(customItem);
            return baos.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }

}
