package br.edu.utfpr.danielproenca.games.dao;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class GamesDatabase_Impl extends GamesDatabase {
  private volatile DaoPubg _daoPubg;

  private volatile DaoSoccer _daoSoccer;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `soccer` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `team` TEXT, `goals` INTEGER NOT NULL, `rounds` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `pubg` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nickname` TEXT, `level` INTEGER NOT NULL, `rounds` INTEGER NOT NULL, `slaughter` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"570b4d494b42ab8866ee8a01c0fed538\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `soccer`");
        _db.execSQL("DROP TABLE IF EXISTS `pubg`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsSoccer = new HashMap<String, TableInfo.Column>(4);
        _columnsSoccer.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsSoccer.put("team", new TableInfo.Column("team", "TEXT", false, 0));
        _columnsSoccer.put("goals", new TableInfo.Column("goals", "INTEGER", true, 0));
        _columnsSoccer.put("rounds", new TableInfo.Column("rounds", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSoccer = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSoccer = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSoccer = new TableInfo("soccer", _columnsSoccer, _foreignKeysSoccer, _indicesSoccer);
        final TableInfo _existingSoccer = TableInfo.read(_db, "soccer");
        if (! _infoSoccer.equals(_existingSoccer)) {
          throw new IllegalStateException("Migration didn't properly handle soccer(br.edu.utfpr.danielproenca.games.model.RegisterSoccer).\n"
                  + " Expected:\n" + _infoSoccer + "\n"
                  + " Found:\n" + _existingSoccer);
        }
        final HashMap<String, TableInfo.Column> _columnsPubg = new HashMap<String, TableInfo.Column>(5);
        _columnsPubg.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsPubg.put("nickname", new TableInfo.Column("nickname", "TEXT", false, 0));
        _columnsPubg.put("level", new TableInfo.Column("level", "INTEGER", true, 0));
        _columnsPubg.put("rounds", new TableInfo.Column("rounds", "INTEGER", true, 0));
        _columnsPubg.put("slaughter", new TableInfo.Column("slaughter", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPubg = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPubg = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPubg = new TableInfo("pubg", _columnsPubg, _foreignKeysPubg, _indicesPubg);
        final TableInfo _existingPubg = TableInfo.read(_db, "pubg");
        if (! _infoPubg.equals(_existingPubg)) {
          throw new IllegalStateException("Migration didn't properly handle pubg(br.edu.utfpr.danielproenca.games.model.RegisterPubg).\n"
                  + " Expected:\n" + _infoPubg + "\n"
                  + " Found:\n" + _existingPubg);
        }
      }
    }, "570b4d494b42ab8866ee8a01c0fed538", "be29441fa136b4bb940679b5903f746e");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "soccer","pubg");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `soccer`");
      _db.execSQL("DELETE FROM `pubg`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public DaoPubg daoPubg() {
    if (_daoPubg != null) {
      return _daoPubg;
    } else {
      synchronized(this) {
        if(_daoPubg == null) {
          _daoPubg = new DaoPubg_Impl(this);
        }
        return _daoPubg;
      }
    }
  }

  @Override
  public DaoSoccer daoSoccer() {
    if (_daoSoccer != null) {
      return _daoSoccer;
    } else {
      synchronized(this) {
        if(_daoSoccer == null) {
          _daoSoccer = new DaoSoccer_Impl(this);
        }
        return _daoSoccer;
      }
    }
  }
}
