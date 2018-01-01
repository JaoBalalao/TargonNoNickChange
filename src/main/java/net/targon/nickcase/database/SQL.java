package net.targon.nickcase.database;

import net.targon.nickcase.Main;
import net.targon.nickcase.thread.SaveThread;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class SQL {

    protected Connection connection;
    protected BukkitTask bukkitTask;

    public SQL (DatabaseType databaseType, String... strings) {

        try {

            switch (databaseType) {
                case MySQL: {

                    Class.forName("com.mysql.jdbc.Driver");
                    connection = DriverManager.getConnection("jdbc:mysql://" + strings[0] + "/" + strings[1], strings[2], strings[3]);

                }
                case SQLite: {

                    File file = new File(Main.getInstance().getDataFolder() + "/database/targonpunishment.db");
                    if(!file.exists()) {
                        file.mkdirs();
                        file.createNewFile();
                    }

                    Class.forName("org.sqlite.JDBC");
                    connection = DriverManager.getConnection("jdbc:sqlite:" + Main.getInstance().getDataFolder().toString() + "/database/targonpunishment.db");

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void createTables() {

        try {

            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS tnnc_nicks(" +
                    "id INT(11) AUTO_IMPLEMENT," +
                    "player_lowercase VARCHAR(36) NOT NULL," +
                    "player_exact VARCHAR(36) NOT NULL," +
                    "primary key (id))");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getCache(HashMap<String, String> hashMap) {

        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tnnc_nicks");

            while(resultSet.next()) {

                hashMap.put(resultSet.getString("player_lowercase"), resultSet.getString("player_exact"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveCache(HashMap<String, String> hashMap) {

        try {

            for (Map.Entry<String, String> map : hashMap.entrySet()) {

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM tnnc_nicks WHERE player_lowercase = '" + map.getKey() + "'");

                if(!resultSet.next()) {

                    statement.executeUpdate("INSERT INTO tnnc_nicks(player_lowercase, player_exact) VALUES (" +
                            "'" + map.getKey() + "', '" + map.getValue() + "')");

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void startCacheTimer(Main main, Integer time, HashMap<String, String> hashMap) {

        bukkitTask = new BukkitRunnable() {

            @Override
            public void run() {

                saveCacheThread(hashMap);

            }

        }.runTaskTimerAsynchronously(main, time * 20 * 60, time * 20 * 60);
    }

    public void saveCacheThread(HashMap<String, String> hashMap) {

        SaveThread thread = new SaveThread(this, hashMap);
        thread.run();

    }

    public void closeConnection() {

        bukkitTask.cancel();

        try {

            if (connection != null && !connection.isClosed()) {
                connection.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public enum DatabaseType {
        MySQL, SQLite
    }


}
