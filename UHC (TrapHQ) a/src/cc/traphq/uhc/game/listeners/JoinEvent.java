package cc.traphq.uhc.game.listeners;

import cc.traphq.uhc.GameUtils;
import cc.traphq.uhc.UHC;
import cc.traphq.uhc.game.SpecCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {



    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        if(GameUtils.spectator.contains(p)){
            e.setJoinMessage(SpecCommand.prefix + ChatColor.BLUE + p.getName() + ChatColor.GRAY + " has joined!");
            return;
        }


        if(!GameUtils.uhc.contains(p)){
            if(!GameUtils.spectator.contains(p)) {
                e.setJoinMessage(UHC.prefix + ChatColor.GRAY + "(" + ChatColor.GREEN + "+" + ChatColor.GRAY + ") "
                        + ChatColor.BLUE + p.getName() + ChatColor.YELLOW + " has joined UHC");
            }
            return;
        }

        if(GameUtils.uhc.contains(p)){
            e.setJoinMessage(UHC.prefix + ChatColor.GRAY + "(" + ChatColor.GREEN + "+" + ChatColor.GRAY + ") "
                    + ChatColor.BLUE + p.getName() + ChatColor.YELLOW + " has joined back!");
            return;
        }


    }



}
