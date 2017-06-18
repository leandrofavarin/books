package com.leandrofavarin.books.db;

import android.database.sqlite.SQLiteDatabase;

interface Migration {
  void up(SQLiteDatabase db);
}
