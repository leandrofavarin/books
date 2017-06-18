package com.leandrofavarin.books;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;
import com.leandrofavarin.books.di.AppComponent;
import com.leandrofavarin.books.di.AppModule;
import com.leandrofavarin.books.di.ComponentProvider;
import com.leandrofavarin.books.di.DaggerAppComponent;

import timber.log.Timber;

public class BooksApplication extends Application implements ComponentProvider {
  private AppComponent component;

  @Override public void onCreate() {
    super.onCreate();
    AndroidThreeTen.init(this);
    buildComponentAndInject();
    activateLogging();
  }

  private void buildComponentAndInject() {
    component = DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .build();
  }

  @Override public AppComponent getComponent() {
    return component;
  }

  protected void activateLogging() {
    Timber.uprootAll();
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }
}
