package fr.tractopelle.assets.listeners;

import fr.tractopelle.assets.CorePlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AtoutsListener implements Listener {

    private CorePlugin corePlugin;

    public AtoutsListener(CorePlugin corePlugin) {
        this.corePlugin = corePlugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){

        Inventory inventory = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();

        if(inventory != null && inventory.getName().equalsIgnoreCase(corePlugin.getAtoutsGUI().getName())){

            if (itemStack == null) return;
            if (itemStack.getType() == null) return;
            if (itemStack.equals(Material.AIR)) return;
            event.setCancelled(true);
            if (!itemStack.hasItemMeta()) return;


        }

    }
}
