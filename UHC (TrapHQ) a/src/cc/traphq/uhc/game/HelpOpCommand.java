package cc.traphq.uhc.game;

import java.util.HashMap;
import java.util.UUID;

import cc.traphq.uhc.UHC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpOpCommand implements CommandExecutor {

    public static String helpop = "helpop";
    private HashMap<UUID,Integer> helpopCD = UHC.helpopCD;
    private int i = 120;
    private UHC plugin;
    public HelpOpCommand(UHC pl) {
        plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(cmd.getName().equalsIgnoreCase(helpop)){
            Player p = (Player) sender;
            if(args.length < 1){
                p.sendMessage(UHC.prefix + ChatColor.RED + "Correct usage for helpop is '/helpop <what you need help with>'!");
                return true;
            }

            StringBuilder sb = new StringBuilder("");

            for (int i = 0; i < args.length; i++) {
                sb.append(args[i]).append(" ");
            }

            String msg = ChatColor.translateAlternateColorCodes('&', sb.toString().trim());
            if (helpopCD.containsKey(p.getUniqueId())) {
                int cdlefts = helpopCD.get(p.getUniqueId());
                int cdleftm = helpopCD.get(p.getUniqueId()) / 60;
                cdleftm = cdleftm + 1;
                if (cdleftm == 2) {
                    p.sendMessage(UHC.prefix + ChatColor.RED + "You are currently on cooldown for " + cdleftm + " minutes.");
                }
                else {
                    p.sendMessage(UHC.prefix + ChatColor.RED + "You are currently on cooldown for " + cdlefts + " seconds.");
                }
                return true;
            }
            if(cc.traphq.uhc.GameUtils.alerts.size() == 0){
                p.sendMessage(UHC.prefix + "Helpop could not be sent as their is no one with alerts toggled on.");
            }else{
                p.sendMessage(UHC.prefix + "Helpop Sent!");
                helpopCD.put(p.getUniqueId(), i);
                Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

                    @Override
                    public void run() {
                        if (helpopCD.containsKey(p.getUniqueId())) {
                            if (helpopCD.get(p.getUniqueId()) <= 0) {
                                p.sendMessage(UHC.prefix + ChatColor.GREEN + "You are no longer on cooldown.");
                                while (helpopCD.containsKey(p.getUniqueId())) {
                                    helpopCD.remove(p.getUniqueId());
                                }
                                i = 120;
                                Bukkit.getScheduler().cancelTasks(plugin);
                                return;
                            }
                            else {
                                helpopCD.replace(p.getUniqueId(), i, i - 1);
                                i = i - 1;
                            }
                        }
                        else {
                            Bukkit.getScheduler().cancelTasks(plugin);
                            return;
                        }

                    }
                }, 0, 20);
            }

            for(Player all : Bukkit.getOnlinePlayers()){
                if(!cc.traphq.uhc.GameUtils.alerts.contains(all))return true;
                all.sendMessage(ChatColor.RED + "[Helpop] " + ChatColor.GREEN + msg + ChatColor.YELLOW +
                        "" + ChatColor.BOLD + " > Sent by " + p.getDisplayName());
                all.playSound(p.getLocation(), Sound.VILLAGER_YES, 5, 1);
            }


        }


        return true;
    }
}
