package cc.traphq.uhc.game;

import cc.traphq.uhc.GameUtils;
import cc.traphq.uhc.UHC;
import cc.traphq.uhc.game.scenarios.ScenarioManager;
import cc.traphq.uhc.game.scenarios.type.BloodDiamonds;
import cc.traphq.uhc.game.scenarios.type.HalfHealth;
import cc.traphq.uhc.game.scenarios.type.Lavaless;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;

import static org.bukkit.Material.AIR;

public class ScenarioCommand implements CommandExecutor, Listener {

    public static final String prefix = ChatColor.GRAY + "[" + ChatColor.BLUE + "ScenarioManager" + ChatColor.GRAY + "] ";


    public static final String scenarios = "scenarios";


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


        if(cmd.getName().equalsIgnoreCase(scenarios)){
            if(sender.hasPermission("uhc.host")){
                Player p = (Player) sender;
                ScenarioManager.scenarioList(p);
            }
        }


        return true;
    }


    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (ChatColor.stripColor(e.getInventory().getName()).equalsIgnoreCase("Scenario Manager »")) {
            Player p = (Player) e.getWhoClicked();
            e.setCancelled(true);
            if (e.getCurrentItem() == null || e.getCurrentItem().getType() == AIR) {
                p.closeInventory();
                return;
            }

            switch (e.getCurrentItem().getType()) {
                case DIAMOND:
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        if (!BloodDiamonds.isEnabled) {

                            BloodDiamonds.isEnabled = true;
                            e.getWhoClicked().sendMessage(prefix + "Blood Diamonds enabled!");


                            all.sendMessage(UHC.prefix + ChatColor.GREEN + e.getWhoClicked().getName() +
                                    ChatColor.GRAY + " has enabled " + ChatColor.RED + "Blood Diamonds!");

                        } else {
                            e.getWhoClicked().sendMessage(prefix + "Blood Diamonds disabled!");
                            BloodDiamonds.isEnabled = false;

                                all.sendMessage(UHC.prefix + ChatColor.GREEN + e.getWhoClicked().getName() +
                                        ChatColor.GRAY + " has disabled " + ChatColor.RED + "Blood Diamonds!");
                            }

                        break;
                    }
                case LAVA_BUCKET:
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        if (!Lavaless.enabled) {

                            Lavaless.enabled = true;
                            e.getWhoClicked().sendMessage(prefix + "Lavaless enabled!");


                            all.sendMessage(UHC.prefix + ChatColor.GREEN + e.getWhoClicked().getName() +
                                    ChatColor.GRAY + " has enabled " + ChatColor.DARK_RED + "Lavaless");

                        } else {
                            e.getWhoClicked().sendMessage(prefix + "Lavaless disabled!");
                            Lavaless.enabled = false;

                            all.sendMessage(UHC.prefix + ChatColor.GREEN + e.getWhoClicked().getName() +
                                    ChatColor.GRAY + " has disabled " + ChatColor.DARK_RED + "Lavaless");
                        }


                    }
                case REDSTONE:
                    for (Player all : Bukkit.getOnlinePlayers()){
                        Bukkit.dispatchCommand(e.getWhoClicked(), "ii1baj");
                    }


            }
        }
    }



}
