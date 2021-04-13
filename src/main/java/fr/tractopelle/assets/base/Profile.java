package fr.tractopelle.assets.base;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Profile {

    private final List<Player> noFallPlayers;

    public Profile() {

        this.noFallPlayers = new ArrayList<>();

    }

    public List<Player> getNoFallPlayers() {
        return noFallPlayers;
    }

    public void addNoFallPlayers(Player player) {
        this.noFallPlayers.add(player);
    }

    public void removeNoFallPlayers(Player player) {
        this.noFallPlayers.remove(player);
    }

}
