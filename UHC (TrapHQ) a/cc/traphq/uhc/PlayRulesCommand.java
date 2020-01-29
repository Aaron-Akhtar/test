package cc.traphq.uhc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import cc.traphq.uhc.game.UHC;

public class PlayRulesCommand implements CommandExecutor {

    private UHC plugin;
    public PlayRulesCommand(UHC pl) {
        plugin = pl;
    }
    public static String playrules = "playrules";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase(playrules)){
            if(sender.hasPermission("uhc.host")){
                plugin.playRulesRules();
            }
        }

        return true;
    }
}
