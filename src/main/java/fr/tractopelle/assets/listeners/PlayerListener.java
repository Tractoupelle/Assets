package fr.tractopelle.assets.listeners;

import fr.tractopelle.assets.CorePlugin;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffectType;

public class PlayerListener implements Listener {

    private CorePlugin corePlugin;

    public PlayerListener (CorePlugin corePlugin){

        this.corePlugin = corePlugin;

    }

    @EventHandler
    public void onPlayerFall(EntityDamageEvent event) {

        if (event.getEntity().getType() == EntityType.PLAYER && event.getCause() == EntityDamageEvent.DamageCause.FALL) {

            Player player = (Player) event.getEntity();

            if (corePlugin.getProfile().getNoFallPlayers().contains(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerGetBuff(PotionSplashEvent event) {

        event.getAffectedEntities().forEach(livingEntity ->
        {
            if(livingEntity instanceof Player){

                Player player = (Player) livingEntity;

                Potion potion = Potion.fromItemStack(event.getPotion().getItem());
                PotionEffectType potionEffect = potion.getType().getEffectType();

                if(corePlugin.getProfile().getNoDebuffPlayers().contains(player) &&
                        corePlugin.getConfiguration().getStringList("ASSET-NO-DEBUFF").contains(potionEffect.getName())){

                    event.setCancelled(true);

                }
            }
        });

    }

}
