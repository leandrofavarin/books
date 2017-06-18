package com.leandrofavarin.books.feature.library;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leandrofavarin.books.R;
import com.leandrofavarin.books.entities.Book;

import org.threeten.bp.ZoneId;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookView extends LinearLayout {
  private static final DateTimeFormatter DATE_DISPLAY_FORMAT =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.US)
          .withZone(ZoneId.systemDefault());

  @BindView(R.id.title) TextView title;
  @BindView(R.id.author) TextView author;
  @BindView(R.id.publishedAt) TextView publishedAt;
  @BindView(R.id.numChapters) TextView numChapters;

  public static BookView create(LayoutInflater inflater, ViewGroup parent) {
    return (BookView) inflater.inflate(R.layout.view_book, parent, false);
  }

  public BookView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    ButterKnife.bind(this);
  }

  void bindTo(Book book) {
    title.setText(book.title());
    author.setText(book.author());
    publishedAt.setText(
        book.publishedAt() == null ? "" : DATE_DISPLAY_FORMAT.format(book.publishedAt()));
    numChapters.setText(getResources().getString(R.string.num_chapters_format, book.numChapters()));
  }
}
