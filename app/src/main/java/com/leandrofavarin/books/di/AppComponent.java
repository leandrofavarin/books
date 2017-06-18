package com.leandrofavarin.books.di;

import android.content.Context;

import com.leandrofavarin.books.feature.home.HomeFragment;
import com.leandrofavarin.books.feature.library.LibraryFragment;
import com.leandrofavarin.books.feature.settings.SettingsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = AppModule.class)
@Singleton public interface AppComponent {
  Context context();

  void inject(HomeFragment fragment);

  void inject(LibraryFragment fragment);

  void inject(SettingsFragment fragment);
}
