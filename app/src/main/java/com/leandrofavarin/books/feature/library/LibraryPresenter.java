package com.leandrofavarin.books.feature.library;

import com.leandrofavarin.books.db.repositories.BookRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static com.leandrofavarin.books.utils.rx.Consumers.crashOnError;

public class LibraryPresenter {
  private final BookRepository bookRepository;
  private final CompositeDisposable disposables = new CompositeDisposable();

  @Inject public LibraryPresenter(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public void onCreate(LibraryView view) {
    disposables.add(bookRepository.getAllBooksStream()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(books -> {
          if (books.isEmpty()) {
            view.showEmptyLibrary();
          } else {
            view.showBooks(books);
          }
        }, crashOnError()));
  }

  public void onDestroy() {
    disposables.clear();
  }
}
