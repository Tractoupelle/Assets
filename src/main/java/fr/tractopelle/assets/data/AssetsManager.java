package fr.tractopelle.assets.data;

import fr.tractopelle.assets.CorePlugin;
import fr.tractopelle.assets.utils.AssetType;

import java.util.List;

public class AssetsManager {

    private CorePlugin corePlugin;
    private List<AssetType> atoutList;

    public AssetsManager (CorePlugin corePlugin){
        this.corePlugin = corePlugin;
    }

    public List<AssetType> getAtoutList() { return atoutList; }

    public void addAtoutList(AssetType atoutList) {
        this.atoutList.add(atoutList);
    }
}
