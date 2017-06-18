package com.leandrofavarin.books.feature.library;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leandrofavarin.books.R;
import com.leandrofavarin.books.entities.Book;

import org.threeten.bp.ZoneId;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
  private static final DateTimeFormatter DATE_DISPLAY_FORMAT =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.US)
          .withZone(ZoneId.systemDefault());

  private final LayoutInflater inflater;
  private final List<Book> items;

  public BookAdapter(Context context) {
    inflater = LayoutInflater.from(context);
    items = new ArrayList<>();
  }

  public void setBooks(List<Book> books) {
    items.clear();
    items.addAll(books);
    notifyDataSetChanged();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(inflater.inflate(R.layout.view_book, parent, false));
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    holder.bindTo(items.get(position));
  }

  @Override public int getItemCount() {
    return items.size();
  }

  static class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.title) TextView title;
    @BindView(R.id.author) TextView author;
    @BindView(R.id.publishedAt) TextView publishedAt;
    @BindView(R.id.numChapters) TextView numChapters;

    ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    void bindTo(Book book) {
      title.setText(book.title());
      author.setText(book.author());
      publishedAt.setText(
          book.publishedAt() == null ? "" : DATE_DISPLAY_FORMAT.format(book.publishedAt()));
//      numChapters.setText(R.string.num_chapters_format, book.title());
    }
  }
}
