package fr.tractopelle.assets.commands;

import fr.tractopelle.assets.CorePlugin;
import fr.tractopelle.assets.utils.AssetType;
import fr.tractopelle.assets.utils.command.ACommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

public class AtoutsCommand extends ACommand {

    private CorePlugin corePlugin;

    public AtoutsCommand(CorePlugin corePlugin) {
        super(corePlugin, "assets", false);
        this.corePlugin = corePlugin;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {

        String prefix = corePlugin.getConfiguration().getString("PREFIX");

        Player player = (Player) commandSender;

        if (args.length != 0 && !(player.hasPermission("assets.admin"))) {

            player.sendMessage(prefix + corePlugin.getConfiguration().getString("USAGE"));

        } else if (player.hasPermission("assets.admin") && args.length == 0) {

            corePlugin.getAtoutsGUI().openGUI(player);
            player.sendMessage(prefix + corePlugin.getConfiguration().getString("OPENING-GUI"));
            return false;

        } else if (player.hasPermission("assets.admin") && args.length != 3) {

            for (String s : corePlugin.getConfiguration().getStringList("USAGE-ADMIN")) {

                player.sendMessage(s);

            }

            return false;


        } else if ((player.hasPermission("assets.admin") && args.length == 3)) {

            switch (args[0]) {

                case "add":

                    // ADD ASSET

                    Player target = Bukkit.getPlayer(args[1]);
                    AssetType atout;

                    if(target == null) {
                        player.sendMessage(prefix + corePlugin.getConfiguration().getString("UNKNOW-PLAYER"));
                        return false;
                    }


                    if(AssetType.isAsset(args[2].toLowerCase(Locale.ROOT))){

                        atout = AssetType.getAssetFromString(args[2]);


                    } else {
                        player.sendMessage(prefix + corePlugin.getConfiguration().getString("NOT-AN-ASSET"));
                        return false;
                    }

                    break;
                case "remove":

                    // REMOVE ASSET


                    break;
                default:
                    for (String s : corePlugin.getConfiguration().getStringList("USAGE-ADMIN")) {

                        player.sendMessage(s);

                    }

                    break;
            }

        } else {

            corePlugin.getAtoutsGUI().openGUI(player);
            player.sendMessage(prefix + corePlugin.getConfiguration().getString("OPENING-GUI"));

        }

        return false;
    }
}
