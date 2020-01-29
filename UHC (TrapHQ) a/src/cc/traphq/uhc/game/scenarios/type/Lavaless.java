package cc.traphq.uhc.game.scenarios.type;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class Lavaless implements Listener {


    public static boolean enabled = false;



    @EventHandler
    public void lavaless(EntityDamageEvent e){


        if(e.getCause() == EntityDamageEvent.DamageCause.LAVA) {
            if (enabled) {
                e.setCancelled(true);
            }
            return;
        }

        if(e.getCause() == EntityDamageEvent.DamageCause.FIRE) {
            if (enabled) {
                e.setCancelled(true);
            }
            return;
        }

        if (e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK){
            e.setCancelled(true);
        }


    }

}
