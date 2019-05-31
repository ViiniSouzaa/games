package br.edu.utfpr.danielproenca.games.dao;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import br.edu.utfpr.danielproenca.games.model.RegisterPubg;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class DaoPubg_Impl implements DaoPubg {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfRegisterPubg;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfRegisterPubg;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfRegisterPubg;

  public DaoPubg_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRegisterPubg = new EntityInsertionAdapter<RegisterPubg>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `pubg`(`id`,`nickname`,`level`,`rounds`,`slaughter`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, RegisterPubg value) {
        stmt.bindLong(1, value.getId());
        if (value.getNickname() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNickname());
        }
        stmt.bindLong(3, value.getLevel());
        stmt.bindLong(4, value.getRounds());
        stmt.bindLong(5, value.getSlaughter());
      }
    };
    this.__deletionAdapterOfRegisterPubg = new EntityDeletionOrUpdateAdapter<RegisterPubg>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `pubg` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, RegisterPubg value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfRegisterPubg = new EntityDeletionOrUpdateAdapter<RegisterPubg>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `pubg` SET `id` = ?,`nickname` = ?,`level` = ?,`rounds` = ?,`slaughter` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, RegisterPubg value) {
        stmt.bindLong(1, value.getId());
        if (value.getNickname() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNickname());
        }
        stmt.bindLong(3, value.getLevel());
        stmt.bindLong(4, value.getRounds());
        stmt.bindLong(5, value.getSlaughter());
        stmt.bindLong(6, value.getId());
      }
    };
  }

  @Override
  public long insert(RegisterPubg pubg) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfRegisterPubg.insertAndReturnId(pubg);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(RegisterPubg pubg) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfRegisterPubg.handle(pubg);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(RegisterPubg pubg) {
    __db.beginTransaction();
    try {
      __updateAdapterOfRegisterPubg.handle(pubg);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public RegisterPubg getById(long id) {
    final String _sql = "SELECT * FROM pubg WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfNickname = _cursor.getColumnIndexOrThrow("nickname");
      final int _cursorIndexOfLevel = _cursor.getColumnIndexOrThrow("level");
      final int _cursorIndexOfRounds = _cursor.getColumnIndexOrThrow("rounds");
      final int _cursorIndexOfSlaughter = _cursor.getColumnIndexOrThrow("slaughter");
      final RegisterPubg _result;
      if(_cursor.moveToFirst()) {
        _result = new RegisterPubg();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _result.setId(_tmpId);
        final String _tmpNickname;
        _tmpNickname = _cursor.getString(_cursorIndexOfNickname);
        _result.setNickname(_tmpNickname);
        final int _tmpLevel;
        _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
        _result.setLevel(_tmpLevel);
        final int _tmpRounds;
        _tmpRounds = _cursor.getInt(_cursorIndexOfRounds);
        _result.setRounds(_tmpRounds);
        final int _tmpSlaughter;
        _tmpSlaughter = _cursor.getInt(_cursorIndexOfSlaughter);
        _result.setSlaughter(_tmpSlaughter);
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
  public List<RegisterPubg> getAll() {
    final String _sql = "SELECT * FROM pubg ORDER BY nickname ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfNickname = _cursor.getColumnIndexOrThrow("nickname");
      final int _cursorIndexOfLevel = _cursor.getColumnIndexOrThrow("level");
      final int _cursorIndexOfRounds = _cursor.getColumnIndexOrThrow("rounds");
      final int _cursorIndexOfSlaughter = _cursor.getColumnIndexOrThrow("slaughter");
      final List<RegisterPubg> _result = new ArrayList<RegisterPubg>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final RegisterPubg _item;
        _item = new RegisterPubg();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpNickname;
        _tmpNickname = _cursor.getString(_cursorIndexOfNickname);
        _item.setNickname(_tmpNickname);
        final int _tmpLevel;
        _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
        _item.setLevel(_tmpLevel);
        final int _tmpRounds;
        _tmpRounds = _cursor.getInt(_cursorIndexOfRounds);
        _item.setRounds(_tmpRounds);
        final int _tmpSlaughter;
        _tmpSlaughter = _cursor.getInt(_cursorIndexOfSlaughter);
        _item.setSlaughter(_tmpSlaughter);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
