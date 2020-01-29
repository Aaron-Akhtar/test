package cc.traphq.uhc.game;

import static org.bukkit.Bukkit.getServer;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import cc.traphq.uhc.GameUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class UhcCommand implements CommandExecutor, Listener {


    public static String uhc = "uhc";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase(uhc)) {
            if (sender.hasPermission("uhc.host")) {
                if (args.length != 0) {
                    if (args[0].equalsIgnoreCase("start")) {
                        if (sender.hasPermission("uhc.host")) {
                            //TODO: Spectator Feature


                            for(Player spec : Bukkit.getOnlinePlayers()){
                                if(cc.traphq.uhc.GameUtils.spectator.contains(spec)){
                                    spec.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "UHC" + ChatColor.GRAY + "] " + ChatColor.YELLOW + "You've not been included in the UHC as you are participating as a spectator!");
                                }
                            }

                            cc.traphq.uhc.GameUtils.pregame.clear();
                            for (Player alll : Bukkit.getOnlinePlayers() ) {
                                GameUtils.pregame.add(alll);
                            }
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mv create uhc world");
                            World uhc = getServer().getWorld("uhc");
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "worldborder set 2000 2000");
                            for (Player on : Bukkit.getOnlinePlayers()) {
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mvtp " + on.getName() + " uhc");
                                int x = ThreadLocalRandom.current().nextInt(-2000, 2000);
                                int z = ThreadLocalRandom.current().nextInt(-2000, 2000);
                                double y = getServer().getWorld("uhc").getHighestBlockYAt(x, z) + 2.5;
                                on.teleport(new Location(uhc, x, y, z));

                                if(!cc.traphq.uhc.GameUtils.spectator.contains(on)){
                                    cc.traphq.uhc.GameUtils.uhc.add(on);

                                    on.getInventory().clear();
                                    sender.sendMessage(ChatColor.GREEN + "UHC Started");
                                    sender.sendMessage(ChatColor.GREEN + "MAKE SURE YOU USE '/MUTECHAT' & '/PLAYRULES'");

                                    double radius = 200;
                                    double radiusSquared = radius*radius;
                                    List<Entity> entities = on.getNearbyEntities(radius, radius, radius);
                                    for (Entity entity : entities) {

                                        if(entity.getLocation().distanceSquared(on.getLocation()) > radiusSquared) continue;

                                        if(entity instanceof Player) {

                                            while (entity.getLocation().distanceSquared(on.getLocation()) < radiusSquared && entity instanceof Player) {
                                                x = ThreadLocalRandom.current().nextInt(-2000, 2000);
                                                z = ThreadLocalRandom.current().nextInt(-2000, 2000);
                                                y = getServer().getWorld("uhc").getHighestBlockYAt(x, z) + 2.5;
                                                on.teleport(new Location(uhc, x, y, z));
                                            }
                                        }
                                        return true;
                                    }
                                    if(on.getLocation().getY() < 60){
                                        on.getLocation().setY(200);
                                    }
                                    if(!GameUtils.spectator.contains(on)) {
                                        cc.traphq.uhc.GameUtils.preGameSetup();
                                        on.sendMessage(ChatColor.GREEN + "You Were Scattered!");
                                        cc.traphq.uhc.GameUtils.uhc.add(on);
                                        on.getInventory().clear();
                                        StarterKit.starterKit(on);
                                    }
                                }
                            }
                        }
                    }
                    else if (args[0].equalsIgnoreCase("end") || args[0].equalsIgnoreCase("stop")) {
                        if (sender.hasPermission("uhc.host") || sender.hasPermission("uhc.*")) {
                            ItemStack air = new ItemStack(Material.AIR, 1);
                            for (Player on : Bukkit.getOnlinePlayers()) {
                                if (on.getWorld().getName().contains("uhc")) {
                                    World world = getServer().getWorld("world");
                                    cc.traphq.uhc.GameUtils.uhc.remove(on);
                                    cc.traphq.uhc.GameUtils.pregame.remove(on);
                                    on.getInventory().setHelmet(air);
                                    on.getInventory().setChestplate(air);
                                    on.getInventory().setLeggings(air);
                                    on.getInventory().setBoots(air);
                                    on.getInventory().clear();
                                    on.teleport(new Location(world, 0, 100, 0));
                                }
                            }
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mv delete uhc");
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mvconfirm");

                            sender.sendMessage(ChatColor.GREEN + "UHC Stopped");
                        }
                    }
                    else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c------------------------------\n----------&a  UHC v1  &c----------\n------------------------------\n&e+ /uhc start &7(Starts the game)\n&e+ /uhc <end/stop> &7(End the game)"));
                    }
                }
                else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c------------------------------\n----------&a  UHC v1  &c----------\n------------------------------\n&e+ /uhc start &7(Starts the game)\n&e+ /uhc <end/stop> &7(End the game)"));
                }
            }
            else {
                sender.sendMessage(ChatColor.RED + "Insufficient Permissions");
            }
        }
        return true;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        @SuppressWarnings("unused")
        Player p = e.getPlayer();
        for (Player all : Bukkit.getOnlinePlayers()){
            if(cc.traphq.uhc.GameUtils.pregame.contains(all)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onbreak(BlockBreakEvent e){
        @SuppressWarnings("unused")
        Player p = e.getPlayer();
        for (Player all : Bukkit.getOnlinePlayers()){
            if(cc.traphq.uhc.GameUtils.pregame.contains(all)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onplace(BlockPlaceEvent e){
        @SuppressWarnings("unused")
        Player p = e.getPlayer();
        for (Player all : Bukkit.getOnlinePlayers()){
            if(cc.traphq.uhc.GameUtils.pregame.contains(all)) {
                e.setCancelled(true);
            }
        }
    }


}
