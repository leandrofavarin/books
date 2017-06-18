package com.leandrofavarin.books.db.repositories;

import android.database.Cursor;

import com.leandrofavarin.books.db.DatabaseOpenHelper;
import com.leandrofavarin.books.entities.Book;
import com.leandrofavarin.books.entities.BookModel;
import com.squareup.sqlbrite2.BriteDatabase;
import com.squareup.sqldelight.SqlDelightStatement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import timber.log.Timber;

public class BookRepository {
  private final DatabaseOpenHelper openHelper;
  private final BriteDatabase db;
  private final BookModel.InsertRow insertRow;

  @Inject public BookRepository(DatabaseOpenHelper openHelper, BriteDatabase db) {
    this.openHelper = openHelper;
    this.db = db;
    insertRow = new BookModel.InsertRow(db.getWritableDatabase(), Book.FACTORY);
  }

  // If only using SQLDelight
  public List<Book> getAllBooks() {
    SqlDelightStatement query = Book.FACTORY.selectAll();
    try (Cursor cursor = openHelper.getReadableDatabase().rawQuery(query.statement, query.args)) {
      List<Book> result = new ArrayList<>(cursor.getCount());
      while (cursor.moveToNext()) {
        result.add(Book.ROW_MAPPER.map(cursor));
      }
      Timber.d("Queried `%s` table for %d items", query.tables, result.size());
      return result;
    }
  }

  // Using SQLDelight and SQLBrite
  public Observable<List<Book>> getAllBooksStream() {
    SqlDelightStatement query = Book.FACTORY.selectAll();
    return db.createQuery(query.tables, query.statement, query.args)
        .mapToList(Book.ROW_MAPPER::map);
  }

  public void insertBooks(Collection<Book> books) {
    BriteDatabase.Transaction transaction = db.newTransaction();
    try {
      for (Book book : books) {
        //insertRow.bind(book.title(), book.author(), book.publishedAt(), book.numChapters());

      }
      transaction.markSuccessful();
    } finally {
      transaction.close();
    }
  }
}
