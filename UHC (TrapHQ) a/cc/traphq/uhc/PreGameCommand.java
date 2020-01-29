package cc.traphq.uhc;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import cc.traphq.uhc.game.GameUtils;
import cc.traphq.uhc.game.UHC;

public class PreGameCommand implements CommandExecutor {

    private UHC plugin;
    public PreGameCommand(UHC pl) {
        plugin = pl;
    }


    static PotionEffect ab = GameUtils.ab;
    static PotionEffect reg = GameUtils.reg;
    static PotionEffect res = GameUtils.res;
    static PotionEffect water = GameUtils.water;
    public static String pregame = "pregame";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(p.hasPermission("uhc.host")){
            if(cmd.getName().equalsIgnoreCase(pregame)) {

                for(Player all : Bukkit.getOnlinePlayers()){
                    if(GameUtils.pregame.contains(all)) {
                        GameUtils.pregame.remove(all);
                        all.sendMessage(plugin.prefix + "Pregame Has Ended... You may now adventure the wilderness!");
                        all.removePotionEffect(ab.getType());;
                        all.removePotionEffect(reg.getType());
                        all.removePotionEffect(res.getType());
                        all.removePotionEffect(water.getType());
                    }
                    else {
                    	all.sendMessage(plugin.prefix + "Pregame Has Ended...");
                    }
                }

            }

        }


        return true;
    }
}
