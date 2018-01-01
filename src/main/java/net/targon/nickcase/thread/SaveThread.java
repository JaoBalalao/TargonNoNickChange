package net.targon.nickcase.thread;

import net.targon.nickcase.Main;
import net.targon.nickcase.database.SQL;

import java.util.HashMap;

public class SaveThread implements Runnable {

    private SQL sql;
    private HashMap<String, String> hashMap;


    public SaveThread(SQL sql, HashMap<String, String> hashMap) {

        this.sql = sql;
        this.hashMap = hashMap;

    }

    @Override
    public void run() {

        sql.saveCache(hashMap);

    }
}
