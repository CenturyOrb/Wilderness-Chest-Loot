package com.rosed.wildernesschestloot.customitems;

import com.rosed.wildernesschestloot.InstanceManager;
import com.rosed.wildernesschestloot.customitems.impl.CustomItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemCommand extends Command {

    private final ItemManager itemManager = InstanceManager.INSTANCE.getItemManager();

    public ItemCommand() {
        super("wilditems", "Main command for wild items", "/wilditems <item>", List.of("wi"));
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player player))
            return false;

        // Check if player provided an item name
        if (args.length < 1) {
            player.sendMessage(Component.text("Usage: /wilditems <item>", NamedTextColor.RED));
            return false;
        }

        // As items names can have spaces, we join the args together
        String itemName = String.join(" ", args);
        // Get the item from the item manager by name
        CustomItem item = itemManager.getItem(itemName);
        if (item == null) {
            player.sendMessage(Component.text("Invalid item: " + itemName, NamedTextColor.RED));
            return false;
        }

        // Give the item to the player
        item.give(player);
        sender.sendMessage(Component.text("Gave item: " + itemName, NamedTextColor.GREEN));
        return false;
    }

    /**
     * Tab complete for the command
     *
     * @param sender The sender of the command
     * @param alias  The alias of the command being executed
     * @param args   The arguments provided to the command
     * @return A list of possible tab completions
     * @throws IllegalArgumentException If the sender, alias, or args are null
     */
    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        if (args.length == 0)
            return List.of();

        // Get the last element of the args array
        String lastWord = args[args.length - 1];
        return itemManager.getItemNames()
                .stream()
                // Filter out items that don't start with the last word
                .filter((name) -> StringUtil.startsWithIgnoreCase(name, lastWord))
                // Sort alphabetically
                .sorted(String.CASE_INSENSITIVE_ORDER)
                // Convert to a list
                .toList();

    }

}
