package fr.tractopelle.assets.commands.command;

import fr.tractopelle.assets.CorePlugin;
import fr.tractopelle.assets.base.Asset;
import fr.tractopelle.assets.base.type.AssetType;
import fr.tractopelle.assets.commands.ACommand;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class AssetAdmin extends ACommand {

    private CorePlugin corePlugin;

    public AssetAdmin(CorePlugin corePlugin) {
        super(corePlugin, "assetsadmin", true, "ASSETSADMIN.ADMIN");
        this.corePlugin = corePlugin;
    }


    @Override
    public boolean execute(CommandSender commandSender, String[] args) {

        String prefix = corePlugin.getConfiguration().getString("PREFIX");

        if(args.length == 1 && args[0].equalsIgnoreCase("list")){

            List<String> assetsName = corePlugin.getAssetsManager().getAtoutList().stream()
                    .map(object -> object.getAssetType().name()).collect(Collectors.toList());

            commandSender.sendMessage(prefix + corePlugin.getConfiguration().getString("ASSET-LIST")
                    .replace("%assets%", StringUtils.join(assetsName, ", ")));

            return false;

        } else if(args.length != 3){

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
                        Asset asset = corePlugin.getAssetsManager().getAssetsFromAssetsType(assetType);

                        if(!(target.hasPermission(asset.getPermission()))) {

                            User user = corePlugin.getLuckPerms().getPlayerAdapter(Player.class).getUser(target);
                            addPermission(user, asset.getPermission());
                            commandSender.sendMessage(prefix + corePlugin.getConfiguration().getString("ADD-ASSET"));

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
                        Asset asset = corePlugin.getAssetsManager().getAssetsFromAssetsType(assetType);

                        if((target.hasPermission(asset.getPermission()))) {

                            User user = corePlugin.getLuckPerms().getPlayerAdapter(Player.class).getUser(target);
                            removePermission(user, asset.getPermission());
                            commandSender.sendMessage(prefix + corePlugin.getConfiguration().getString("REMOVE-ASSET"));

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
