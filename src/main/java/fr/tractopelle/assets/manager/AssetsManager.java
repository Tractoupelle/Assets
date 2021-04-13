package fr.tractopelle.assets.manager;

import fr.tractopelle.assets.CorePlugin;
import fr.tractopelle.assets.utils.AssetType;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AssetsManager {

    private CorePlugin corePlugin;
    private Map<AssetType, Integer> atoutList = new HashMap<>();

    public AssetsManager(CorePlugin corePlugin) {
        this.corePlugin = corePlugin;
        loadAssets();
    }

    public Map<AssetType, Integer> getAtoutList() {
        return atoutList;
    }

    public void addAtoutList(AssetType atoutList, Integer level) {

        this.atoutList.put(atoutList, level);

    }

    public void loadAssets() {

        int i = 1;


        for (String s : corePlugin.getConfiguration().getConfigurationSection("ASSETS-GUI.ITEMS").getKeys(false)) {

            String assetName = corePlugin.getConfiguration().getString("ASSETS-GUI.ITEMS." + i + ".EFFECT");

            if(AssetType.isAsset(assetName.toLowerCase(Locale.ROOT))) {

                AssetType assetType = AssetType.getAssetFromString(assetName);
                addAtoutList(assetType, corePlugin.getConfiguration().getInt("ASSETS-GUI.ITEMS." + i + ".LEVEL"));

            }

            i++;

        }
    }
}
