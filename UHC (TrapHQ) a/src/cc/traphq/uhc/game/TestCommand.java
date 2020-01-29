package cc.traphq.uhc.game;

import cc.traphq.uhc.GameUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {


    public static String test = "test";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(sender.isOp()){
            if(cmd.getName().equalsIgnoreCase(test)){
                GameUtils.alerts.add(p);
                p.sendMessage("Added to Alerts Arraylist");

            }
        }


        return true;
    }
}
