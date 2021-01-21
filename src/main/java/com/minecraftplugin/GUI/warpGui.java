package com.minecraftplugin.GUI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class warpGui implements Listener {

    public void createInventory() {
        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.GOLD + "Warps");
        ItemStack item = new ItemStack(Material.DIRT);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Miniera");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("Click here to warp");
    }

    public void clikEvent(InventoryClickEvent e) {
        if (!e.getView().getTitle().contains("Warps"))
            return;
        if (e.getCurrentItem() == null)
            return;
        if (e.getCurrentItem().getItemMeta() == null)
            return;

        Player player = (Player) e.getWhoClicked();
        e.setCancelled(true);

        if (e.getClickedInventory().getType() == InventoryType.PLAYER)
            return;
    }
}
