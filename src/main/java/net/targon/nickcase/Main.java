package net.targon.nickcase;

import net.targon.nickcase.commands.NoNickChange;
import net.targon.nickcase.database.SQL;
import net.targon.nickcase.listeners.PlayerLogin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class Main extends JavaPlugin {

    private static Main instance;
    public static Main getInstance() { return instance; }

    private SQL sql;

    private HashMap<String, String> players = new HashMap<>();

    @Override
    public void onEnable() {

        instance = this;

        if(!getDataFolder().exists())
            getDataFolder().mkdirs();

        saveDefaultConfig();

        sql = new SQL(getConfig().getBoolean("Storage.useMysql") ? SQL.DatabaseType.MySQL : SQL.DatabaseType.SQLite,
                getConfig().getString("Storage.ip,"),
                getConfig().getString("Storage.database"),
                getConfig().getString("Storage.username"),
                getConfig().getString("Storage.password"));

        sql.getCache(players);
        sql.startCacheTimer(this, getConfig().getInt("Cache.timeInMinutes"), players);

    }

    @Override
    public void onDisable() {

        sql.saveCacheThread(players);
        sql.closeConnection();

    }

    private void registerListeners() {

        Bukkit.getPluginManager().registerEvents(new PlayerLogin(), this);

    }

    private void registerCommands() {

        getCommand("NoNickChange").setExecutor(new NoNickChange());

    }

    public HashMap<String, String> getPlayers() {
        return this.players;
    }

}
