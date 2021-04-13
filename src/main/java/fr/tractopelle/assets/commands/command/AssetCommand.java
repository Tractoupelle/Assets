package fr.tractopelle.assets.commands.command;

import fr.tractopelle.assets.CorePlugin;
import fr.tractopelle.assets.commands.ACommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AssetCommand extends ACommand {

    private CorePlugin corePlugin;

    public AssetCommand(CorePlugin corePlugin) {
        super(corePlugin, "assets", false, "NONE");
        this.corePlugin = corePlugin;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {

        String prefix = corePlugin.getConfiguration().getString("PREFIX");

        Player player = (Player) commandSender;

        if (args.length != 0) {

            player.sendMessage(prefix + corePlugin.getConfiguration().getString("USAGE"));

        } else {

            corePlugin.getAtoutsGUI().openGUI(player);
            player.sendMessage(prefix + corePlugin.getConfiguration().getString("OPENING-GUI"));

        }

        return false;
    }
}
