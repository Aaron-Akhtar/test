package cc.traphq.uhc.game.scenarios.type;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BloodDiamonds implements Listener {


    public static boolean isEnabled = false;


    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){

        final String prefix = ChatColor.GRAY + "[" + ChatColor.RED + "Blood Diamonds" + ChatColor.GRAY + "] ";


        Block block = e.getBlock();
        if(isEnabled == true){

        if(block.getType() == Material.DIAMOND_ORE) {
            Player p = e.getPlayer();

            p.damage(1.0);
            p.playSound(p.getLocation(), Sound.SPLASH2, 2, 1);
            p.sendMessage(prefix + "You've lost 1 heart because of blood diamonds!");
        }


        }
    }



}
