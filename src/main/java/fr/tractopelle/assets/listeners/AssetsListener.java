package fr.tractopelle.assets.listeners;

import fr.tractopelle.assets.CorePlugin;
import fr.tractopelle.assets.base.Asset;
import fr.tractopelle.assets.base.type.AssetType;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class AssetsListener implements Listener {

    private CorePlugin corePlugin;

    public AssetsListener(CorePlugin corePlugin) {
        this.corePlugin = corePlugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        Inventory inventory = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();
        String prefix = corePlugin.getConfiguration().getString("PREFIX");

        if (inventory != null && inventory.getName().equalsIgnoreCase(corePlugin.getAtoutsGUI().getName())) {

            if (itemStack == null) return;
            if (itemStack.getType() == null) return;
            if (itemStack.equals(Material.AIR)) return;
            event.setCancelled(true);
            if (!itemStack.hasItemMeta()) return;

            for (Asset asset : corePlugin.getAssetsManager().getAtoutList()) {

                if (asset.getItemStack().getItemMeta().getDisplayName().equals(itemStack.getItemMeta().getDisplayName())) {

                    if (player.hasPermission(asset.getPermission())) {


                        addAssets(asset, player, PotionEffectType.getByName(asset.getAssetType().name()), asset.getLevel(), prefix);
                        return;

                    } else {

                        player.sendMessage(prefix + corePlugin.getConfiguration().getString("NOT-HAVE-ASSET"));
                        return;

                    }

                }
            }
        }
    }


    public void addAssets(Asset asset, Player player, PotionEffectType effectType, int amplifier, String prefix) {

        if (asset.getAssetType().equals(AssetType.NO_FALL)) {

            if (corePlugin.getProfile().getNoFallPlayers().contains(player)) {

                corePlugin.getProfile().removeNoFallPlayers(player);
                player.sendMessage(prefix + corePlugin.getConfiguration().getString("ASSET-DESACTIVATION").replace("%asset%", AssetType.NO_FALL.name()));
                return;

            } else {

                corePlugin.getProfile().addNoFallPlayers(player);
                player.sendMessage(prefix + corePlugin.getConfiguration().getString("ASSET-ACTIVATION").replace("%asset%",AssetType.NO_FALL.name()));
                return;

            }

        } else if (asset.getAssetType().equals(AssetType.NO_DEBUFF)) {

            if (corePlugin.getProfile().getNoDebuffPlayers().contains(player)) {

                corePlugin.getProfile().removeNoDebuffPlayers(player);
                player.sendMessage(prefix + corePlugin.getConfiguration().getString("ASSET-DESACTIVATION").replace("%asset%", AssetType.NO_DEBUFF.name()));
                return;

            } else {

                corePlugin.getProfile().addNoDebuffPlayers(player);
                player.sendMessage(prefix + corePlugin.getConfiguration().getString("ASSET-ACTIVATION").replace("%asset%", AssetType.NO_DEBUFF.name()));
                return;

            }

        }

        if (player.hasPotionEffect(effectType)) {

            player.removePotionEffect(effectType);

            player.sendMessage(prefix + corePlugin.getConfiguration().getString("ASSET-ACTIVATION").replace("%asset%", effectType.getName()));

        } else {

            player.addPotionEffect(effectType.createEffect(100000000, amplifier - 1));

            player.sendMessage(prefix + corePlugin.getConfiguration().getString("ASSET-DESACTIVATION").replace("%asset%", effectType.getName()));

        }
    }
}
