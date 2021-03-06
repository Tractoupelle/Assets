package fr.tractopelle.assets.manager;

import fr.tractopelle.assets.CorePlugin;
import fr.tractopelle.assets.base.Asset;
import fr.tractopelle.assets.base.type.AssetType;
import fr.tractopelle.assets.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import java.util.*;

public class AssetsManager {

    private CorePlugin corePlugin;
    private List<Asset> assets = new ArrayList<>();

    public AssetsManager(CorePlugin corePlugin) {
        this.corePlugin = corePlugin;
        loadAssets();
    }

    public List<Asset> getAtoutList() {
        return assets;
    }

    public void addAtoutList(AssetType atoutList, Integer level, ItemStack itemStack, String permission) {

        this.assets.add(new Asset(itemStack, atoutList, level, permission));

    }

    public boolean isAssets(AssetType s){

        for(Asset asset : assets){

            if(asset.getAssetType().equals(s)){
                return true;
            }

        }

        return false;

    }

    public Asset getAssetsFromAssetsType(AssetType s){

        for(Asset asset : assets){

            if(asset.getAssetType().equals(s)){
                return asset;
            }

        }

        return null;

    }


    public void loadAssets() {

        int i = 1;

        for (String s : corePlugin.getConfiguration().getConfigurationSection("ASSETS-GUI.ITEMS").getKeys(false)) {

            String assetName = corePlugin.getConfiguration().getString("ASSETS-GUI.ITEMS." + i + ".EFFECT");

            if (AssetType.isAsset(assetName.toLowerCase(Locale.ROOT))) {

                AssetType assetType = AssetType.getAssetFromString(assetName);
                addAtoutList(assetType,
                        corePlugin.getConfiguration().getInt("ASSETS-GUI.ITEMS." + i + ".LEVEL")
                        , new ItemBuilder(Material.getMaterial(corePlugin.getConfiguration().getString("ASSETS-GUI.ITEMS." + i + ".MATERIAL")))
                                .setName(corePlugin.getConfiguration().getString("ASSETS-GUI.ITEMS." + i + ".NAME")).addGlow(corePlugin.getConfiguration().getBoolean("ASSETS-GUI.ITEMS." + i + ".GLOW"))
                                .toItemStack(),
                corePlugin.getConfiguration().getString("ASSETS-GUI.ITEMS." + i + ".PERMISSION"));

            }

            i++;

        }
    }
}
