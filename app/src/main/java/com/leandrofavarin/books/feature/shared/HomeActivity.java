package com.leandrofavarin.books.feature.shared;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.leandrofavarin.books.R;
import com.leandrofavarin.books.feature.home.HomeFragment;
import com.leandrofavarin.books.feature.library.LibraryFragment;
import com.leandrofavarin.books.feature.settings.SettingsFragment;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {
  @BindView(R.id.viewPager) ViewPager viewPager;
  @BindView(R.id.navigation) BottomNavigationView navigation;
  private Adapter adapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_home);
    ButterKnife.bind(this);

    adapter = new Adapter(getSupportFragmentManager());
    viewPager.setAdapter(adapter);
    navigation.setOnNavigationItemSelectedListener(item -> {
      viewPager.setCurrentItem(adapter.getTabPosition(item.getItemId()), false);
      return true;
    });
  }

  private static class Adapter extends FragmentPagerAdapter {
    private static final List<Integer> TABS = Arrays.asList(
        R.id.navigation_home,
        R.id.navigation_library,
        R.id.navigation_settings
    );

    private Fragment currentFragment;

    Adapter(FragmentManager fm) {
      super(fm);
    }

    @Override public Fragment getItem(int position) {
      int tabId = TABS.get(position);
      switch (tabId) {
        case R.id.navigation_home:
          return new HomeFragment();
        case R.id.navigation_library:
          return new LibraryFragment();
        case R.id.navigation_settings:
          return new SettingsFragment();
        default:
          throw new RuntimeException(
              String.format(Locale.US, "Adapter has no item at %d position.", position));
      }
    }

    @Override public int getCount() {
      return TABS.size();
    }

    @Override public void setPrimaryItem(ViewGroup container, int position, Object object) {
      if (getCurrentFragment() != object) {
        currentFragment = (Fragment) object;
      }
      super.setPrimaryItem(container, position, object);
    }

    Fragment getCurrentFragment() {
      return currentFragment;
    }

    int getTabPosition(@IdRes int tabId) {
      return TABS.indexOf(tabId);
    }
  }
}
