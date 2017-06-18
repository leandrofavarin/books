package com.leandrofavarin.books.feature.library;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leandrofavarin.books.entities.Book;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
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
    return new ViewHolder(BookView.create(inflater, parent));
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    BookView view = ((BookView) holder.itemView);
    view.bindTo(items.get(position));
  }

  @Override public int getItemCount() {
    return items.size();
  }

  static class ViewHolder extends RecyclerView.ViewHolder {
    ViewHolder(View itemView) {
      super(itemView);
    }
  }
}
