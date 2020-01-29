package cc.traphq.uhc.game.scenarios;

import cc.traphq.uhc.game.scenarios.type.BloodDiamonds;
import cc.traphq.uhc.game.scenarios.type.Lavaless;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ScenarioManager {

    public static void scenarioList(Player p){
        String name = ChatColor.AQUA + "" + ChatColor.BOLD + "Scenario Manager »";
        Inventory inv = Bukkit.createInventory(null, 9*1, name);

        ItemStack bd = new ItemStack(Material.DIAMOND);
        ItemMeta bd1 = bd.getItemMeta();
        bd1.setDisplayName(ChatColor.RED + "Blood Diamonds");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Blood Diamonds makes it so once a player mines a diamond ore they will lose 1 heart!");
        lore.add(" ");
        if(!BloodDiamonds.isEnabled){
            lore.add(ChatColor.DARK_RED + "Blood Diamonds is not Enabled!");
        }else{
            lore.add(ChatColor.GREEN + "Blood Diamonds is Enabled!");
        }
        bd1.setLore(lore);
        bd.setItemMeta(bd1);
        inv.setItem(0, bd);


        ItemStack ls = new ItemStack(Material.LAVA_BUCKET);
        ItemMeta l1 = ls.getItemMeta();
        l1.setDisplayName(ChatColor.DARK_RED + "Lavaless (Fireless)");
        ArrayList<String> lore1 = new ArrayList<>();
        lore1.add(ChatColor.YELLOW + "Lavaless makes it so players cannot take any damage from lava or fire.");
        if(!Lavaless.enabled){
            lore.add(ChatColor.DARK_RED + "Lavaless is not Enabled!");
        }else{
            lore.add(ChatColor.GREEN + "Lavaless is Enabled!");
        }
        l1.setLore(lore1);
        ls.setItemMeta(l1);
        inv.setItem(1, ls);

        ItemStack hh = new ItemStack(Material.REDSTONE);
        ItemMeta h1 = hh.getItemMeta();
        h1.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Half Health");
        ArrayList<String> lore2 = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Half Health gives all players 5 hearts instead of 10.");
        h1.setLore(lore2);
        hh.setItemMeta(h1);
        inv.setItem(2, hh);

        p.openInventory(inv);


    }




}
