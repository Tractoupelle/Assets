package fr.tractopelle.assets;

import fr.tractopelle.assets.commands.AtoutsCommand;
import fr.tractopelle.assets.config.Config;
import fr.tractopelle.assets.gui.AssetsGUI;
import fr.tractopelle.assets.listeners.AtoutsListener;
import fr.tractopelle.assets.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CorePlugin extends JavaPlugin {

    private Config configuration;
    private AssetsGUI assetsGUI;
    private final Logger log = new Logger(this.getDescription().getFullName());

    @Override
    public void onEnable() {

        init();

    }

    public void init(){

        registerListeners();

        registerCommands();

        this.configuration = new Config(this, "config");


        log.info("=======================================", Logger.LogType.SUCCESS);
        log.info(" Plugin initialization in progress...", Logger.LogType.SUCCESS);

        if ((Bukkit.getPluginManager().isPluginEnabled("LuckPerms"))) {
            log.info(" Need LuckPerms dependency to work", Logger.LogType.ERROR);
            log.info(" Plugin disabled", Logger.LogType.ERROR);
            Bukkit.getPluginManager().disablePlugin(this);
        } else {
            log.info(" Added LuckPerms dependency", Logger.LogType.SUCCESS);
        }

        log.info(" Author: Tractopelle#4020", Logger.LogType.SUCCESS);
        log.info("=======================================", Logger.LogType.SUCCESS);




        this.assetsGUI = new AssetsGUI(this);

    }

    private void registerCommands() {

        new AtoutsCommand(this);
    }

    private void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new AtoutsListener(this), this);

    }

    public Config getConfiguration() {
        return configuration;
    }

    public AssetsGUI getAtoutsGUI() { return assetsGUI; }

}