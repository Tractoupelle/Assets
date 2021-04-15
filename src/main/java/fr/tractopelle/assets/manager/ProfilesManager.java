package fr.tractopelle.assets.manager;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ProfilesManager {

    private final List<Player> noFallPlayers;
    private final List<Player> noDebuffPlayers;

    public ProfilesManager() {

        this.noFallPlayers = new ArrayList<>();
        this.noDebuffPlayers = new ArrayList<>();

    }

    public List<Player> getNoFallPlayers() { return noFallPlayers; }

    public void addNoFallPlayers(Player player) {
        this.noFallPlayers.add(player);
    }

    public void removeNoFallPlayers(Player player) {
        this.noFallPlayers.remove(player);
    }

    public List<Player> getNoDebuffPlayers() { return noDebuffPlayers; }

    public void addNoDebuffPlayers(Player player) {
        this.noDebuffPlayers.add(player);
    }

    public void removeNoDebuffPlayers(Player player) {
        this.noDebuffPlayers.remove(player);
    }

}
