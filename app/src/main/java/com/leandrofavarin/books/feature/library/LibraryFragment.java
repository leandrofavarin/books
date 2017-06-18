package com.leandrofavarin.books.feature.library;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.leandrofavarin.books.R;
import com.leandrofavarin.books.di.AppComponent;
import com.leandrofavarin.books.entities.Book;
import com.leandrofavarin.books.feature.shared.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class LibraryFragment extends BaseFragment implements LibraryView {
  @BindView(android.R.id.list) RecyclerView recyclerView;
  @BindView(android.R.id.empty) View emptyView;

  @Inject LibraryPresenter presenter;

  private BookAdapter adapter;

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    recyclerView.setAdapter(adapter = new BookAdapter(getContext()));

    presenter.onCreate(this);
  }

  @Override public void onDestroyView() {
    presenter.onDestroy();
    super.onDestroyView();
  }

  @Override protected void onCreateComponent(AppComponent appComponent) {
    appComponent.inject(this);
  }

  @Override protected int getLayout() {
    return R.layout.fragment_book_list;
  }

  @Override public void showBooks(List<Book> books) {
    recyclerView.setVisibility(VISIBLE);
    emptyView.setVisibility(GONE);
    adapter.setBooks(books);
  }

  @Override public void showEmptyLibrary() {
    recyclerView.setVisibility(GONE);
    emptyView.setVisibility(VISIBLE);
  }
}
