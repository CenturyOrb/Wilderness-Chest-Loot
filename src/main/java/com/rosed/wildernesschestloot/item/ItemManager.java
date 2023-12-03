package com.rosed.wildernesschestloot.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemManager {

    private ItemStack sovereign;

    public ItemManager()   {

        initializeItemStacks();

    }

    private void initializeItemStacks()   {

        sovereign = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta sovereignMeta = sovereign.getItemMeta();
        sovereignMeta.setLocalizedName("weapon.sovereign");

        sovereignMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&9Sovereign Sword"));
        sovereignMeta.addEnchant(Enchantment.DAMAGE_ALL, 4, true);
        sovereignMeta.addEnchant(Enchantment.SWEEPING_EDGE, 10, true);

        ArrayList<String> sovereignLore = new ArrayList<>();
        sovereignLore.add(ChatColor.translateAlternateColorCodes('&', "&7Sharpness IV"));
        sovereignLore.add(ChatColor.translateAlternateColorCodes('&', "&7Sweeping Edge IV"));
        sovereignLore.add("Blade of the old ruler");
        sovereignLore.add("");
        sovereignLore.add(ChatColor.translateAlternateColorCodes('&', "&a- Renews on frag"));

        sovereignMeta.setLore(sovereignLore);

        sovereignMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        sovereign.setItemMeta(sovereignMeta);
    }

    public ItemStack getSovereign()   { return sovereign; }

}
