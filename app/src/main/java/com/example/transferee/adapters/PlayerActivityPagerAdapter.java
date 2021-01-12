package com.example.transferee.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.transferee.ui.playercareer.PlayerCareerFragment;
import com.example.transferee.ui.playermarket.PlayerMarketFragment;
import com.example.transferee.ui.playeroverview.PlayerOverviewFragment;
import com.example.transferee.ui.playerstats.PlayerStatsFragment;

public class PlayerActivityPagerAdapter extends FragmentPagerAdapter {
    public PlayerActivityPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new PlayerOverviewFragment();
                break;
            case 1:
                fragment = new PlayerMarketFragment();
                break;
            case 2: 
                fragment = new PlayerStatsFragment();
                break;
            case 3:
                fragment = new PlayerCareerFragment();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "Default";
        switch (position) {
            case 0:
                title = "Overview";
                break;
            case 1:
                title = "Market";
                break;
            case 2:
                title = "Stats";
                break;
            case 3:
                title = "Career";
                break;
        }
        return title;
    }
}
