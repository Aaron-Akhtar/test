package cc.traphq.uhc;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import cc.traphq.uhc.game.GameUtils;
import cc.traphq.uhc.game.UHC;

public class AlertsCommand implements CommandExecutor, Listener {

    @SuppressWarnings("unused")
	private UHC plugin;
    public AlertsCommand(UHC pl) {
        plugin = pl;
    }



    public static String alerts = "alerts";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(p.hasPermission("uhc.alerts")){
            if(cmd.getName().equalsIgnoreCase(alerts)) {
            	if (GameUtils.alerts.contains(p)) {
            		GameUtils.alerts.remove(p);
                    p.sendMessage(ChatColor.RED + "Alerts Disabled");
                    return true;
            	}
            	GameUtils.alerts.add(p);
                p.sendMessage(ChatColor.GREEN + "Alerts Enabled");

            }

        }


        return true;
    }
    
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
    	
    	Player p = e.getPlayer();
    	if (GameUtils.alerts.contains(p)) {
    		GameUtils.alerts.remove(p);
    		return;
    	}
    	
    }
    
}