package com.rosed.wildernesschestloot.util;

import com.google.common.collect.Lists;
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
import java.util.concurrent.ThreadLocalRandom;

public class Util {

    public static final NamespacedKey augmentKey = new NamespacedKey(InstanceManager.INSTANCE.getPlugin(), "custom_item");

    private Util() {
    }

    // We check if something should happen if the random number is lower or equal than the probability
    public static boolean rollDice(int probability) {
        return ThreadLocalRandom.current().nextInt(0, 100) <= probability;
    }

    public static <T extends CustomItem<?>> List<T> equippedCustomItems(Class<T> itemType, EntityEquipment equipment) {
        List<T> customItems = Lists.newArrayList();

        if (equipment == null)
            return customItems;

        for (ItemStack item : equipment.getArmorContents()) {
            T customItem = getCustomItem(itemType, item);
            if (customItem != null)
                customItems.add(customItem);
        }

        T customItem = getCustomItem(itemType, equipment.getItemInMainHand());
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

    public static <T extends CustomItem<?>> T getCustomItem(Class<T> itemType, ItemStack item) {
        if (item == null || item.getType().isEmpty())
            return null;

        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();

        if (!pdc.has(augmentKey, PersistentDataType.BYTE_ARRAY))
            return null;

        // Hacky way to get the class of the generic type
        byte[] data = pdc.get(augmentKey, PersistentDataType.BYTE_ARRAY);
        Object customItem = deserialize(data);
        if (!itemType.isInstance(customItem))
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
