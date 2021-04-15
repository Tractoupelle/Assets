package fr.tractopelle.assets;

import fr.tractopelle.assets.manager.ProfilesManager;
import fr.tractopelle.assets.commands.command.AssetAdminCommand;
import fr.tractopelle.assets.commands.command.AssetCommand;
import fr.tractopelle.assets.config.Config;
import fr.tractopelle.assets.listeners.PlayerListener;
import fr.tractopelle.assets.manager.AssetsManager;
import fr.tractopelle.assets.gui.AssetsGUI;
import fr.tractopelle.assets.listeners.AssetsListener;
import fr.tractopelle.assets.utils.Logger;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class CorePlugin extends JavaPlugin {

    private Config configuration;
    private AssetsGUI assetsGUI;
    private AssetsManager assetsManager;
    private LuckPerms luckPerms;
    private final Logger log = new Logger(this.getDescription().getFullName());
    private ProfilesManager profile;

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

        if (!(Bukkit.getPluginManager().isPluginEnabled("LuckPerms"))) {
            log.info(" Need LuckPerms dependency to work", Logger.LogType.ERROR);
            log.info(" Plugin disabled", Logger.LogType.ERROR);
            Bukkit.getPluginManager().disablePlugin(this);
        } else {
            log.info(" Added LuckPerms dependency", Logger.LogType.SUCCESS);
        }

        log.info(" Author: Tractopelle#4020", Logger.LogType.SUCCESS);
        log.info("=======================================", Logger.LogType.SUCCESS);


        this.assetsGUI = new AssetsGUI(this);

        this.assetsManager = new AssetsManager(this);

        this.profile = new ProfilesManager();

        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            this.luckPerms = provider.getProvider();

        }

    }

    private void registerCommands() {

        new AssetCommand(this);
        new AssetAdminCommand(this);

    }

    private void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new AssetsListener(this), this);
        pm.registerEvents(new PlayerListener(this), this);

    }

    public Config getConfiguration() {
        return configuration;
    }

    public AssetsGUI getAtoutsGUI() { return assetsGUI; }

    public AssetsManager getAssetsManager() { return assetsManager; }

    public LuckPerms getLuckPerms() { return luckPerms; }

    public ProfilesManager getProfile() { return profile; }

}
