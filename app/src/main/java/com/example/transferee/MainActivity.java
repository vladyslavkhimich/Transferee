package com.example.transferee;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.transferee.ui.favorites.FavoritesFragment;
import com.example.transferee.ui.favorites.FavoritesViewModel;
import com.example.transferee.ui.home.HomeFragment;
import com.example.transferee.ui.search.SearchFragment;
import com.example.transferee.ui.transfers.TransfersFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_transfers_fragment, R.id.navigation_search_fragment)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);
        NavigationUI.setupWithNavController(navView, navController);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        switchToHomeFragment();
                        break;
                    case R.id.navigation_transfers:
                        switchToTransfersFragment();
                        break;
                    case R.id.navigation_search:
                        switchToSearchFragment();
                        break;
                    case R.id.navigation_favorites:
                        switchToFavoritesFragment();
                        break;
                }
                return true;
            }
        });
    }

    private void switchToHomeFragment() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.nav_host_fragment, new HomeFragment()).commit();
    }

    private void switchToTransfersFragment() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.nav_host_fragment, new TransfersFragment()).commit();
    }

    private void switchToSearchFragment() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.nav_host_fragment, new SearchFragment()).commit();
    }

    private void switchToFavoritesFragment() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.nav_host_fragment, FavoritesFragment.newInstance()).commit();
    }
}