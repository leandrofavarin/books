package com.leandrofavarin.books.entities;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.leandrofavarin.books.db.columnadapters.ZonedDateTimeColumnAdapter;

import org.threeten.bp.ZonedDateTime;

@AutoValue public abstract class Book implements BookModel, Parcelable {
  private static final ZonedDateTimeColumnAdapter DATE_TIME_ADAPTER =
      new ZonedDateTimeColumnAdapter();

  public static Book create(String title, String author, ZonedDateTime publishedAt,
                            int numChapters) {
    return new AutoValue_Book(0, title, author, publishedAt, numChapters);
  }

  public static final Factory<Book> FACTORY = new Factory<>(AutoValue_Book::new, DATE_TIME_ADAPTER);

  public static final Mapper<Book> ROW_MAPPER = FACTORY.selectAllMapper();
}
