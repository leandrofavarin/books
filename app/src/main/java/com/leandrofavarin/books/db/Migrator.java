package com.leandrofavarin.books.db;

import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class Migrator {
  private static final SparseArray<Class<? extends Migration>> MIGRATIONS = new SparseArray<Class<? extends Migration>>() {{
  }};

  @Inject public Migrator() {
  }

  public void migrate(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.beginTransaction();
    try {
      List<Migration> migrations = getMigrations(oldVersion, newVersion);
      for (Migration migration : migrations) {
        runMigration(migration, db);
      }
      db.setTransactionSuccessful();
    } finally {
      db.endTransaction();
    }
  }

  private List<Migration> getMigrations(int oldVersion, int newVersion) {
    List<Migration> migrations = new ArrayList<>(newVersion - oldVersion);

    for (int n = oldVersion + 1; n <= newVersion; n++) {
      Class<? extends Migration> migrationClass = MIGRATIONS.get(n);

      if (migrationClass == null) {
        continue;
      }

      Migration migration;
      try {
        migration = migrationClass.newInstance();
      } catch (Exception e) {
        throw new RuntimeException("Migration class needs empty and accessible constructor!", e);
      }

      if (migration != null) {
        migrations.add(migration);
      }
    }

    return migrations;
  }

  private void runMigration(Migration migration, SQLiteDatabase db) {
    String name = migration.getClass().getName();
    Timber.d("Running %s", name);
    migration.up(db);
    Timber.d("Finished %s", name);
  }
}
