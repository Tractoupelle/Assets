package fr.tractopelle.assets.data;

import fr.tractopelle.assets.utils.AssetType;
import org.bukkit.inventory.ItemStack;

public class Assets {

    private String permission;
    private ItemStack itemStack;
    private AssetType assetType;
    private Integer level;

    public Assets (ItemStack itemStack, AssetType assetType, Integer level, String permission) {

        this.itemStack = itemStack;
        this.assetType = assetType;
        this.level = level;
        this.permission = permission;

    }

    public ItemStack getItemStack() { return itemStack; }

    public void setItemStack(ItemStack itemStack) { this.itemStack = itemStack; }

    public AssetType getAssetType() { return assetType; }

    public void setAssetType(AssetType assetType) {
        this.assetType = assetType;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getPermission() { return permission; }

    public void setPermission(String permission) { this.permission = permission; }

}
