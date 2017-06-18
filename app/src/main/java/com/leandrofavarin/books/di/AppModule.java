package com.leandrofavarin.books.di;

import android.content.Context;

import com.leandrofavarin.books.BooksApplication;
import com.leandrofavarin.books.BuildConfig;
import com.leandrofavarin.books.db.DatabaseOpenHelper;
import com.leandrofavarin.books.db.Migrator;
import com.leandrofavarin.books.db.Populator;
import com.squareup.sqlbrite2.BriteDatabase;
import com.squareup.sqlbrite2.SqlBrite;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@Module public class AppModule {
  private final BooksApplication booksApplication;

  public AppModule(BooksApplication booksApplication) {
    this.booksApplication = booksApplication;
  }

  @Provides @Singleton Context provideApplicationContext() {
    return booksApplication.getApplicationContext();
  }

  @Provides @Singleton DatabaseOpenHelper provideSqliteHelper(Context context,
                                                              Migrator migrator,
                                                              Populator populator) {
    return new DatabaseOpenHelper(context, migrator, populator);
  }

  @Provides @Singleton SqlBrite provideSqlBrite() {
    return new SqlBrite.Builder()
        .logger(message -> Timber.tag("Database").v(message))
        .build();
  }

  @Provides @Singleton BriteDatabase provideBriteDatabase(SqlBrite sqlBrite,
                                                          DatabaseOpenHelper databaseOpenHelper) {
    BriteDatabase db = sqlBrite.wrapDatabaseHelper(databaseOpenHelper, Schedulers.io());
    db.setLoggingEnabled(BuildConfig.DEBUG);
    return db;
  }
}
