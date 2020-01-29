package cc.traphq.uhc.game;

import cc.traphq.uhc.GameUtils;
import cc.traphq.uhc.UHC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class PreGameCommand implements CommandExecutor {

    @SuppressWarnings("unused")
    private UHC plugin;
    public PreGameCommand(UHC pl) {
        plugin = pl;
    }


    static PotionEffect ab = cc.traphq.uhc.GameUtils.ab;
    static PotionEffect reg = cc.traphq.uhc.GameUtils.reg;
    static PotionEffect res = cc.traphq.uhc.GameUtils.res;
    static PotionEffect water = cc.traphq.uhc.GameUtils.water;
    public static String pregame = "pregame";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(p.hasPermission("uhc.host")){
            if(cmd.getName().equalsIgnoreCase(pregame)) {

                for(Player all : Bukkit.getOnlinePlayers()){
                    if(cc.traphq.uhc.GameUtils.pregame.contains(all)) {
                        cc.traphq.uhc.GameUtils.pregame.remove(all);
                        all.sendMessage(UHC.prefix + "Pregame Has Ended... You may now adventure the wilderness!");
                        all.removePotionEffect(ab.getType());
                        all.removePotionEffect(reg.getType());
                        all.removePotionEffect(res.getType());
                        all.removePotionEffect(water.getType());
                    }
                    if(!GameUtils.pregame.contains(all)) {
                        p.sendMessage(UHC.prefix + "Pregame has to be on for this command to work.");
                    }
                }

            }

        }

        return true;
    }
}
