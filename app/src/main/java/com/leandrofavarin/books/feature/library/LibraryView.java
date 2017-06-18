package com.leandrofavarin.books.feature.library;

import com.leandrofavarin.books.entities.Book;

import java.util.List;

interface LibraryView {
  void showBooks(List<Book> books);

  void showEmptyLibrary();
}
