package br.edu.utfpr.danielproenca.games.dao;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import br.edu.utfpr.danielproenca.games.model.RegisterSoccer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class DaoSoccer_Impl implements DaoSoccer {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfRegisterSoccer;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfRegisterSoccer;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfRegisterSoccer;

  public DaoSoccer_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRegisterSoccer = new EntityInsertionAdapter<RegisterSoccer>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `soccer`(`id`,`team`,`goals`,`rounds`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, RegisterSoccer value) {
        stmt.bindLong(1, value.getId());
        if (value.getTeam() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTeam());
        }
        stmt.bindLong(3, value.getGoals());
        stmt.bindLong(4, value.getRounds());
      }
    };
    this.__deletionAdapterOfRegisterSoccer = new EntityDeletionOrUpdateAdapter<RegisterSoccer>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `soccer` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, RegisterSoccer value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfRegisterSoccer = new EntityDeletionOrUpdateAdapter<RegisterSoccer>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `soccer` SET `id` = ?,`team` = ?,`goals` = ?,`rounds` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, RegisterSoccer value) {
        stmt.bindLong(1, value.getId());
        if (value.getTeam() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTeam());
        }
        stmt.bindLong(3, value.getGoals());
        stmt.bindLong(4, value.getRounds());
        stmt.bindLong(5, value.getId());
      }
    };
  }

  @Override
  public long insert(RegisterSoccer soccer) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfRegisterSoccer.insertAndReturnId(soccer);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(RegisterSoccer soccer) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfRegisterSoccer.handle(soccer);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(RegisterSoccer soccer) {
    __db.beginTransaction();
    try {
      __updateAdapterOfRegisterSoccer.handle(soccer);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public RegisterSoccer getById(long id) {
    final String _sql = "SELECT * FROM soccer WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfTeam = _cursor.getColumnIndexOrThrow("team");
      final int _cursorIndexOfGoals = _cursor.getColumnIndexOrThrow("goals");
      final int _cursorIndexOfRounds = _cursor.getColumnIndexOrThrow("rounds");
      final RegisterSoccer _result;
      if(_cursor.moveToFirst()) {
        final String _tmpTeam;
        _tmpTeam = _cursor.getString(_cursorIndexOfTeam);
        final int _tmpGoals;
        _tmpGoals = _cursor.getInt(_cursorIndexOfGoals);
        final int _tmpRounds;
        _tmpRounds = _cursor.getInt(_cursorIndexOfRounds);
        _result = new RegisterSoccer(_tmpTeam,_tmpGoals,_tmpRounds);
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _result.setId(_tmpId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<RegisterSoccer> getAll() {
    final String _sql = "SELECT * FROM soccer ORDER BY team ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfTeam = _cursor.getColumnIndexOrThrow("team");
      final int _cursorIndexOfGoals = _cursor.getColumnIndexOrThrow("goals");
      final int _cursorIndexOfRounds = _cursor.getColumnIndexOrThrow("rounds");
      final List<RegisterSoccer> _result = new ArrayList<RegisterSoccer>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final RegisterSoccer _item;
        final String _tmpTeam;
        _tmpTeam = _cursor.getString(_cursorIndexOfTeam);
        final int _tmpGoals;
        _tmpGoals = _cursor.getInt(_cursorIndexOfGoals);
        final int _tmpRounds;
        _tmpRounds = _cursor.getInt(_cursorIndexOfRounds);
        _item = new RegisterSoccer(_tmpTeam,_tmpGoals,_tmpRounds);
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
