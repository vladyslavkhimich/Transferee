package com.example.transferee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.transferee.adapters.PlayerActivityPagerAdapter;
import com.example.transferee.helpers.CallbackCustom;
import com.example.transferee.helpers.PlayerActivityInterface;
import com.example.transferee.repository.PlayerRepository;
import com.example.transferee.web.RetrofitService;
import com.example.transferee.web.pojo.PlayerPOJO;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

public class PlayerActivity extends AppCompatActivity{

    public PlayerActivityPagerAdapter PlayerActivityPagerAdapter;
    public ViewPager PlayerViewPager;
    public TabLayout PlayerTabLayout;
    public ImageView PlayerHeaderImageView;
    public TextView PlayerHeaderNameTextView;
    public ImageView PlayerHeaderClubImageView;
    public TextView PlayerHeaderClubTextView;
    public int Player_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        Bundle bundle = getIntent().getExtras();
        Player_ID = bundle.getInt("Player_ID");
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
        PlayerHeaderImageView = (ImageView) findViewById(R.id.playerHeaderImageView);
        PlayerHeaderNameTextView = (TextView) findViewById(R.id.playerHeaderNameTextView);
        PlayerHeaderClubImageView = (ImageView) findViewById(R.id.playerHeaderClubImageView);
        PlayerHeaderClubTextView = (TextView) findViewById(R.id.playerHeaderClubTextView);
        setGeneralPlayerInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.player_activity_options_menu, menu);
        return true;
    }

    public void setGeneralPlayerInfo() {
        PlayerRepository.getInstance().getPlayerByIdFromServer(new CallbackCustom<PlayerPOJO>() {
            @Override
            public void next(PlayerPOJO result) {
                Picasso.get().load(RetrofitService.getBaseURLShorten() + result.getImageURL()).into(PlayerHeaderImageView);
                PlayerHeaderNameTextView.setText(result.getName());
                Picasso.get().load(RetrofitService.getBaseURLShorten() + result.getClubURL()).into(PlayerHeaderClubImageView);
                PlayerHeaderClubTextView.setText(result.getClubName());
            }
        }, Player_ID);
    }

    public int getPlayerID() {
        return Player_ID;
    }
}