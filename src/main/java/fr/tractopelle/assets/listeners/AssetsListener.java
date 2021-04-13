package fr.tractopelle.assets.listeners;

import fr.tractopelle.assets.CorePlugin;
import fr.tractopelle.assets.base.Asset;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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

                        addAssets(player, PotionEffectType.getByName(asset.getAssetType().name()), asset.getLevel(), prefix);
                        return;

                    } else {

                        player.sendMessage(prefix + corePlugin.getConfiguration().getString("NOT-HAVE-ASSET"));
                        return;

                    }

                }
            }
        }
    }

    public void addAssets(Player player, PotionEffectType effectType, int amplifier, String prefix) {

        if (player.hasPotionEffect(effectType)) {

            player.removePotionEffect(effectType);

            player.sendMessage(prefix + corePlugin.getConfiguration().getString("ASSET-ACTIVATION"));

        } else {

            player.addPotionEffect(effectType.createEffect(100000000, amplifier - 1));

            player.sendMessage(prefix + corePlugin.getConfiguration().getString("ASSET-DESACTIVATION"));

        }
    }

}
