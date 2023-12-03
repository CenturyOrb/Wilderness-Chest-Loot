package com.rosed.wildernesschestloot.util;

import com.rosed.wildernesschestloot.InstanceManager;
import com.rosed.wildernesschestloot.customitems.CustomItem;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.io.*;

public class Util {

    public static final NamespacedKey augmentKey = new NamespacedKey(InstanceManager.INSTANCE.getPlugin(), "custom_item");

    private Util() {
    }

    public static <T extends CustomItem> ItemStack saveCustomItem(ItemStack item, T augment) {
        if (item == null || item.getType().isAir())
            return null;
        ItemMeta meta = item.getItemMeta();
        byte[] data = serialize(augment);

        if (data == null)
            return item;

        meta.getPersistentDataContainer().set(augmentKey, PersistentDataType.BYTE_ARRAY, data);
        item.setItemMeta(meta);
        return item;
    }

    public static <T extends CustomItem> T getCustomItem(ItemStack item) {
        if (item == null || item.getType().isEmpty())
            return null;

        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();

        if (!pdc.has(augmentKey, PersistentDataType.BYTE_ARRAY))
            return null;

        byte[] data = pdc.get(augmentKey, PersistentDataType.BYTE_ARRAY);
        return deserialize(data);
    }

    @SuppressWarnings("unchecked")
    public static <T extends CustomItem> T deserialize(byte[] serialized) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(serialized);
             ObjectInputStream ois = new ObjectInputStream(bais)) {
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    public static <T extends CustomItem> byte[] serialize(T customItem) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(customItem);
            return baos.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }

}
