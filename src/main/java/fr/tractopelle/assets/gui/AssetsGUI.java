package fr.tractopelle.assets.gui;

import fr.tractopelle.assets.CorePlugin;
import fr.tractopelle.assets.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.stream.Collectors;

public class AssetsGUI {

    private final CorePlugin corePlugin;
    private String name;
    private int i;

    public AssetsGUI (CorePlugin corePlugin){
        this.corePlugin = corePlugin;
    }

    public void openGUI(Player player) {


        i = 1;

        int size = corePlugin.getConfiguration().getInt("ASSETS-GUI.INVENTORY-SIZE");
        this.name = corePlugin.getConfiguration().getString("ASSETS-GUI.INVENTORY-NAME");

        Inventory inventory = Bukkit.createInventory(null, size, name);

        int data = corePlugin.getConfiguration().getInt("ASSETS-GUI.GLASS-DATA");
        String glassName = corePlugin.getConfiguration().getString("ASSETS-GUI.GLASS-NAME");

        for (String values : corePlugin.getConfiguration().getStringList("ASSETS-GUI.GLASS")) {
            inventory.setItem(Integer.parseInt(values), new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) data).setName(glassName).toItemStack());
        }


        for(String s : corePlugin.getConfiguration().getConfigurationSection("ASSETS-GUI.ITEMS").getKeys(false)){

            inventory.setItem(corePlugin.getConfiguration().getInt("ASSETS-GUI.ITEMS." + i + ".SLOT"),
                    new ItemBuilder(Material.getMaterial(corePlugin.getConfiguration().getString("ASSETS-GUI.ITEMS." + i + ".MATERIAL")))
                            .setName(corePlugin.getConfiguration().getString("ASSETS-GUI.ITEMS." + i + ".NAME"))
                            .setListLore(corePlugin.getConfiguration().getStringList("ASSETS-GUI.ITEMS." + i + ".LORE").stream().map(l -> l.replace("%chooseLore%", (player.hasPermission(corePlugin.getConfiguration().getString("ASSETS-GUI.ITEMS." + i + ".PERMISSION")) ? corePlugin.getConfiguration().getString("ACTIVATE-ASSET") : corePlugin.getConfiguration().getString("NOT-HAVE-ASSET")))).collect(Collectors.toList()))
                            .addGlow(corePlugin.getConfiguration().getBoolean("ASSETS-GUI.ITEMS." + i + ".GLOW"))
                            .toItemStack());

                i++;

        }

        player.openInventory(inventory);

    }

    public String getName() { return name; }

}
