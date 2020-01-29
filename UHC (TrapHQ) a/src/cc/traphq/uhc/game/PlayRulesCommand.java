package cc.traphq.uhc.game;

import cc.traphq.uhc.UHC;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayRulesCommand implements CommandExecutor {

    private UHC plugin;
    public PlayRulesCommand(UHC pl) {
        plugin = pl;
    }
    public static String playrules = "playrules";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase(playrules)){
            if(p.hasPermission("uhc.host")){
                plugin.playRulesRules(p);
            }
        }

        return true;
    }
}
