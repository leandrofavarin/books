package com.leandrofavarin.books.db;

import android.database.sqlite.SQLiteDatabase;

import com.leandrofavarin.books.entities.Book;
import com.leandrofavarin.books.entities.BookModel;

import org.threeten.bp.ZonedDateTime;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class Populator {
  @Inject public Populator() {
  }

  public void populate(SQLiteDatabase db) {
    List<Book> books = Arrays.asList(
        Book.create("7 habits", "George", ZonedDateTime.now(), 12),
        Book.create("1984", "Orwel", ZonedDateTime.now(), 7),
        Book.create("Who ate my risotto?", "Alain", ZonedDateTime.now(), 16),
        Book.create("Eating people is wrong", "Malcolm", ZonedDateTime.now(), 9)
    );

    BookModel.InsertRow insertRow = new BookModel.InsertRow(db, Book.FACTORY);

    try {
      db.beginTransaction();
      for (Book book : books) {
        insertRow.bind(book.title(), book.publishedAt(), book.author(), book.numChapters());
        insertRow.program.executeInsert();
      }
      db.setTransactionSuccessful();
    } finally {
      db.endTransaction();
    }
  }
}
