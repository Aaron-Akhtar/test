package cc.traphq.uhc;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GameUtils {

    public static ArrayList<Player> spectator = new ArrayList<>();
    public static ArrayList<Player> alerts = new ArrayList<>();
    public static ArrayList<Player> pregame = new ArrayList<>();
    public static ArrayList<Player> uhc = new ArrayList<>();
    public static PotionEffect ab = new PotionEffect(PotionEffectType.ABSORPTION, Integer.MAX_VALUE, 3, false);
    public static PotionEffect reg = new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 1, false);
    public static PotionEffect res = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 100, false);
    public static PotionEffect water = new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, 1, false);
    public static void preGameSetup(){
        pregame.clear();
        for(Player all : Bukkit.getOnlinePlayers()){
            pregame.add(all);
            all.addPotionEffect(ab);
            all.addPotionEffect(reg);
            all.addPotionEffect(res);
            all.addPotionEffect(water);
            String pregamep = ChatColor.GRAY + "[" + ChatColor.BLUE + "PreGame" + ChatColor.GRAY + "] ";
            all.sendMessage(pregamep + ChatColor.YELLOW + "You've been added to PreGame...");
        }
    }



}
