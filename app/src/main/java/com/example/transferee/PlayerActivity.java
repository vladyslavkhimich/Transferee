package com.example.transferee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;

import com.example.transferee.adapters.PlayerActivityPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class PlayerActivity extends AppCompatActivity {

    public PlayerActivityPagerAdapter PlayerActivityPagerAdapter;
    public ViewPager PlayerViewPager;
    public TabLayout PlayerTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        Toolbar playerToolbar = (Toolbar) findViewById(R.id.player_toolbar);
        setSupportActionBar(playerToolbar);
        playerToolbar.setNavigationIcon(R.drawable.ic_arrow_left);
        getSupportActionBar().setTitle("");
        playerToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        PlayerActivityPagerAdapter = new PlayerActivityPagerAdapter(getSupportFragmentManager());
        PlayerViewPager = findViewById(R.id.playerViewPager);
        PlayerViewPager.setAdapter(PlayerActivityPagerAdapter);
        PlayerTabLayout = findViewById(R.id.playerTabLayout);
        PlayerTabLayout.setupWithViewPager(PlayerViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.player_activity_options_menu, menu);
        return true;
    }
}