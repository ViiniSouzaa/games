package br.edu.utfpr.danielproenca.games.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.edu.utfpr.danielproenca.games.model.RegisterSoccer;

@Dao
public interface DaoSoccer {
    @Insert
    long insert(RegisterSoccer soccer);

    @Delete
    void delete(RegisterSoccer soccer);

    @Update
    void update(RegisterSoccer soccer);

    @Query("SELECT * FROM soccer WHERE id = :id")
    RegisterSoccer getById(long id);

    @Query("SELECT * FROM soccer ORDER BY team ASC")
    List<RegisterSoccer> getAll();
}
