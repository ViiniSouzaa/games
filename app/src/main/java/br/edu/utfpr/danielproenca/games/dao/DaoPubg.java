package br.edu.utfpr.danielproenca.games.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.edu.utfpr.danielproenca.games.model.RegisterPubg;

@Dao
public interface DaoPubg {
    @Insert
    long insert(RegisterPubg pubg);

    @Delete
    void delete(RegisterPubg pubg);

    @Update
    void update(RegisterPubg pubg);

    @Query("SELECT * FROM pubg WHERE id = :id")
    RegisterPubg getById(long id);

    @Query("SELECT * FROM pubg ORDER BY nickname ASC")
    List<RegisterPubg> getAll();
}
