package fr.tractopelle.assets.commands;

import fr.tractopelle.assets.CorePlugin;
import fr.tractopelle.assets.data.Assets;
import fr.tractopelle.assets.utils.AssetType;
import fr.tractopelle.assets.utils.command.ACommand;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

public class AssetAdmin extends ACommand {

    private CorePlugin corePlugin;

    public AssetAdmin(CorePlugin corePlugin) {
        super(corePlugin, "assetsadmin", true, "ASSETSADMIN.ADMIN");
        this.corePlugin = corePlugin;
    }


    @Override
    public boolean execute(CommandSender commandSender, String[] args) {

        String prefix = corePlugin.getConfiguration().getString("PREFIX");

        if(args.length != 3){

            for(String s : corePlugin.getConfiguration().getStringList("USAGE-ADMIN")){
                commandSender.sendMessage(s);
            }

        } else {

            if(Bukkit.getPlayer(args[1]) == null) {
                commandSender.sendMessage(prefix + corePlugin.getConfiguration().getString("UNKNOW-PLAYER"));
                return false;
            }

            Player target = Bukkit.getPlayer(args[1]);

            switch (args[0]) {

                case "add":

                    // ADD ASSET


                    if(corePlugin.getAssetsManager().isAssets(AssetType.getAssetFromString(args[2].toLowerCase(Locale.ROOT)))){

                        AssetType assetType = AssetType.getAssetFromString(args[2].toLowerCase(Locale.ROOT));
                        Assets asset = corePlugin.getAssetsManager().getAssetsFromAssetsType(assetType);

                        if(!(target.hasPermission(asset.getPermission()))) {

                            User user = corePlugin.getLuckPerms().getPlayerAdapter(Player.class).getUser(target);
                            addPermission(user, asset.getPermission());

                        } else {

                            commandSender.sendMessage(prefix + corePlugin.getConfiguration().getString("ALREADY-HAVE-ASSET"));
                            return false;

                        }

                    } else {
                        commandSender.sendMessage(prefix + corePlugin.getConfiguration().getString("NOT-AN-ASSET"));
                        return false;
                    }

                    break;

                case "remove":

                    // REMOVE ASSET

                    if(corePlugin.getAssetsManager().isAssets(AssetType.getAssetFromString(args[2].toLowerCase(Locale.ROOT)))){

                        AssetType assetType = AssetType.getAssetFromString(args[2].toLowerCase(Locale.ROOT));
                        Assets asset = corePlugin.getAssetsManager().getAssetsFromAssetsType(assetType);

                        if((target.hasPermission(asset.getPermission()))) {

                            User user = corePlugin.getLuckPerms().getPlayerAdapter(Player.class).getUser(target);
                            removePermission(user, asset.getPermission());

                        } else {

                            commandSender.sendMessage(prefix + corePlugin.getConfiguration().getString("ALREADY-HAVE-ASSET"));
                            return false;

                        }

                    } else {
                        commandSender.sendMessage(prefix + corePlugin.getConfiguration().getString("NOT-AN-ASSET"));
                        return false;
                    }

                    break;
                default:

                    for (String s : corePlugin.getConfiguration().getStringList("USAGE-ADMIN")) {

                        commandSender.sendMessage(s);

                    }

                    break;

            }

        }

        return false;
    }

    public void addPermission(User user, String permission) {

        user.data().add(Node.builder(permission).build());

        corePlugin.getLuckPerms().getUserManager().saveUser(user);

    }

    public void removePermission(User user, String permission) {

        user.data().remove(Node.builder(permission).build());

        corePlugin.getLuckPerms().getUserManager().saveUser(user);

    }
}