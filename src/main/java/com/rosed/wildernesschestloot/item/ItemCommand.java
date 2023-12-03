package com.rosed.wildernesschestloot.item;

import com.rosed.wildernesschestloot.InstanceManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ItemCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player))   return false;
        Player player = (Player) sender;

        player.getInventory().addItem(InstanceManager.INSTANCE.getItemManager().getSovereign());
        return false;
    }

}
