package com.leandrofavarin.books.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.leandrofavarin.books.entities.Book;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public class DatabaseOpenHelper extends SQLiteOpenHelper {
  private static final String DATABASE_NAME = "books.db";
  private static final int DATABASE_VERSION = 1;
  private final Migrator migrator;
  private final Populator populator;

  @Inject public DatabaseOpenHelper(Context context, Migrator migrator, Populator populator) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
    this.migrator = migrator;
    this.populator = populator;
  }

  @Override public void onCreate(SQLiteDatabase db) {
    db.execSQL(Book.CREATE_TABLE);
    populator.populate(db);
  }

  @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    migrator.migrate(db, oldVersion, newVersion);
  }
}
