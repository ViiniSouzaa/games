package br.edu.utfpr.danielproenca.games.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import br.edu.utfpr.danielproenca.games.model.RegisterPubg;
import br.edu.utfpr.danielproenca.games.model.RegisterSoccer;

@Database(entities = {RegisterSoccer.class, RegisterPubg.class}, version = 1, exportSchema = false)
public abstract class GamesDatabase extends RoomDatabase {
    public abstract DaoPubg daoPubg();
    public abstract DaoSoccer daoSoccer();

    private static GamesDatabase instance;

    public static GamesDatabase getDatabase(final Context context) {

        if (instance == null) {

            synchronized (GamesDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context,
                            GamesDatabase.class,
                            "games.db").allowMainThreadQueries().build();
                }
            }
        }
        return instance;
    }
}
