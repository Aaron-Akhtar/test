package cc.traphq.uhc.game;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class StarterKit {


    public static void starterKit(Player p){
        ItemStack food = new ItemStack(Material.COOKED_BEEF, 16);
        p.getInventory().addItem(food);
        ItemStack book = new ItemStack(Material.BOOK);
        p.getInventory().addItem(book);
    }



}
