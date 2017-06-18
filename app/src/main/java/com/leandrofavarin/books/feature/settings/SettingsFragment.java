package com.leandrofavarin.books.feature.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.leandrofavarin.books.R;
import com.leandrofavarin.books.di.AppComponent;
import com.leandrofavarin.books.feature.shared.BaseFragment;

public class SettingsFragment extends BaseFragment {
  @Override protected void onCreateComponent(AppComponent appComponent) {
    appComponent.inject(this);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @Override protected int getLayout() {
    return R.layout.fragment_settings;
  }
}
