package com.leandrofavarin.books.db.columnadapters;

import android.support.annotation.NonNull;

import com.squareup.sqldelight.ColumnAdapter;

import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;

public final class ZonedDateTimeColumnAdapter implements ColumnAdapter<ZonedDateTime, String> {
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

  @NonNull @Override public ZonedDateTime decode(String databaseValue) {
    return FORMATTER.parse(databaseValue, ZonedDateTime.FROM);
  }

  @Override public String encode(@NonNull ZonedDateTime value) {
    return FORMATTER.format(value);
  }
}
