package cc.traphq.uhc.game.scenarios.type;

import cc.traphq.uhc.UHC;
import cc.traphq.uhc.game.ScenarioCommand;
import cc.traphq.uhc.game.scenarios.ScenarioManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class HalfHealth implements CommandExecutor {


    public static boolean enabled = false;

    public static String ii1baj = "ii1baj";


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase(ii1baj)){
            if(sender.hasPermission("uhc.host")){
                for(Player all : Bukkit.getOnlinePlayers()) {
                    if (enabled == false){
                        enabled = true;
                        all.setMaxHealth(10.0D);
                        all.playSound(all.getLocation(), Sound.VILLAGER_HIT, 3, 1);
                        all.damage(0.0D);
                        all.sendMessage(UHC.prefix + ChatColor.GREEN + sender.getName() + ChatColor.GRAY +
                                " has enabled " + ChatColor.RED + "" + ChatColor.BOLD + "Half Health!");



                        return true;
                    }

                    if (enabled == true){
                        enabled = false;


                        all.sendMessage(UHC.prefix + ChatColor.GREEN + sender.getName() + ChatColor.GRAY +
                                " has disabled " + ChatColor.RED + "" + ChatColor.BOLD + "Half Health!");

                        all.setMaxHealth(20.0D);
                        all.setHealth(20.0D);
                        all.playSound(all.getLocation(), Sound.VILLAGER_HIT, 3, 1);
                        sender.sendMessage(ScenarioCommand.prefix + ChatColor.GRAY + "Half Health Disabled!");
                        return true;
                    }

                }

            }
        }
        return true;
    }
}