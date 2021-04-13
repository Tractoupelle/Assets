package fr.tractopelle.assets.base.type;

public enum AssetType {

    ABSORPTION,
    BLINDNESS,
    CONFUSION,
    DAMAGE_RESISTANCE,
    FAST_DIGGING,
    FIRE_RESISTANCE,
    HARM,
    HEAL,
    HEALTH_BOOST,
    HUNGER,
    INCREASE_DAMAGE,
    INVISIBILITY,
    JUMP,
    NIGHT_VISION,
    POISON,
    REGENERATION,
    SATURATION,
    SLOW,
    SPEED,
    SLOW_DIGGING,
    WATER_BREATHING,
    WEAKNESS,
    NO_FALL,
    NO_DEBUFF,
    WITHER;

    public static boolean isAsset(String asset) {

        for (AssetType me : AssetType.values()) {
            if (me.name().equalsIgnoreCase(asset)) {
                return true;
            }
        }

        return false;
    }

    public static AssetType getAssetFromString(String asset) {

        for (AssetType me : AssetType.values()) {
            if (me.name().equalsIgnoreCase(asset)) {
                return me;
            }
        }

        return null;

    }

}
