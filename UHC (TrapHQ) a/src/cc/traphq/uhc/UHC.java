package cc.traphq.uhc;

import java.util.HashMap;
import java.util.UUID;

import cc.traphq.uhc.game.*;
import cc.traphq.uhc.game.listeners.JoinEvent;
import cc.traphq.uhc.game.listeners.ShowHealthUnderName;
import cc.traphq.uhc.game.scenarios.type.HalfHealth;
import cc.traphq.uhc.game.scenarios.type.Lavaless;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import cc.traphq.uhc.game.scenarios.type.BloodDiamonds;

public class UHC extends JavaPlugin {
    private UHC plugin;

    private UhcCommand uhc = new UhcCommand();
    private PreGameCommand pregame = new PreGameCommand(this);
    private AlertsCommand alerts = new AlertsCommand(this);
    private ScenarioCommand scenario = new ScenarioCommand();
    private TestCommand test = new TestCommand();
    private HelpOpCommand helpop = new HelpOpCommand(this);
    private PlayRulesCommand playrules = new PlayRulesCommand(this);
    private HalfHealth ii1baj = new HalfHealth();
    public static HashMap<UUID,Integer> helpopCD = new HashMap<UUID,Integer>();

    @Override
    public void onEnable() {
        helpopCD.clear();
        registerCommands();
        registerEvents();
        plugin = this;
    }


    PluginManager pm = Bukkit.getServer().getPluginManager();
    public void registerEvents(){

        pm.registerEvents(new BloodDiamonds(), this);
        pm.registerEvents(new ScenarioCommand(), this);
        pm.registerEvents(new UhcCommand(), this);
        pm.registerEvents(new JoinEvent(), this);
        pm.registerEvents(new Lavaless(), this);
        pm.registerEvents(new ShowHealthUnderName(), this);
    }

    public final static String prefix = ChatColor.GRAY + "[" + ChatColor.BLUE + "UHC" + ChatColor.GRAY + "] ";


    public void registerCommands(){
        getCommand(UhcCommand.uhc).setExecutor(uhc);
        getCommand(ScenarioCommand.scenarios).setExecutor(scenario);
        getCommand(PlayRulesCommand.playrules).setExecutor(playrules);
        getCommand(TestCommand.test).setExecutor(test);
        getCommand(HelpOpCommand.helpop).setExecutor(helpop);
        getCommand(PreGameCommand.pregame).setExecutor(pregame);
        getCommand(AlertsCommand.alerts).setExecutor(alerts);
        getCommand(HalfHealth.ii1baj).setExecutor(ii1baj);
    }
    private int i=0;
    public void playRulesRules(Player p){
        String[] msgs = {"Xraying on TrapHQ is disallowed!", "http://discord.traphq.cc", "TrapHQ UHC is in beta, if you find any bugs please contact us on discord.", "Texture Packs that give you an unfair advantage will result in a ban!", "Hacking is disallowed on TrapHQ!", "'/helpop' to send reports and requests.", "Those are the rules, enjoy the game!"};
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() { public void run() {
            if (i == msgs.length) {
                Bukkit.getScheduler().cancelTasks(plugin);
                return;
            }
            getServer().broadcastMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "UHC" + ChatColor.GRAY + "] " + ChatColor.GREEN +  msgs[i]);
            i = i + 1;
        }
        }, 0, 150L);

    }

	/*

                    all.sendMessage(prefix + ChatColor.GREEN + "'/helpop' to send reports and help requests.");

	 */


}
