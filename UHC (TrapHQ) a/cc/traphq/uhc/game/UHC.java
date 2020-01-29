package cc.traphq.uhc.game;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import cc.traphq.uhc.AlertsCommand;
import cc.traphq.uhc.HelpOpCommand;
import cc.traphq.uhc.PlayRulesCommand;
import cc.traphq.uhc.PreGameCommand;
import cc.traphq.uhc.TestCommand;
import cc.traphq.uhc.UhcCommand;

public class UHC extends JavaPlugin {
	private UHC plugin;

	private UhcCommand uhc = new UhcCommand();
	private PreGameCommand pregame = new PreGameCommand(this);
	private AlertsCommand alerts = new AlertsCommand(this);
	private TestCommand test = new TestCommand();
	private HelpOpCommand helpop = new HelpOpCommand(this);
	private PlayRulesCommand playrules = new PlayRulesCommand(this);
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
		pm.registerEvents(uhc, this);
		pm.registerEvents(uhc, this);
		pm.registerEvents(uhc, this);
		pm.registerEvents(alerts, this);
	}

	public final String prefix = ChatColor.GRAY + "[" + ChatColor.BLUE + "UHC" + ChatColor.GRAY + "] ";


	public void registerCommands(){
		getCommand(UhcCommand.uhc).setExecutor(uhc);
		getCommand(PlayRulesCommand.playrules).setExecutor(playrules);
		getCommand(TestCommand.test).setExecutor(test);
		getCommand(HelpOpCommand.helpop).setExecutor(helpop);
		getCommand(PreGameCommand.pregame).setExecutor(pregame);
		getCommand(AlertsCommand.alerts).setExecutor(alerts);
	}
	private int i=0;
	public void playRulesRules(){
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
