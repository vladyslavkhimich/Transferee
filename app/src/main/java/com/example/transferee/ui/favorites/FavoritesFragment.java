package com.example.transferee.ui.favorites;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.transferee.R;
import com.example.transferee.adapters.FavoritePlayerAdapter;
import com.example.transferee.helpers.FileHelper;
import com.example.transferee.helpers.RecyclerViewEmptySupport;
import com.example.transferee.models.FavoritePlayer;
import com.example.transferee.web.pojo.PlayerPOJO;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    private FavoritesViewModel favoritesViewModel;
    public FavoritePlayerAdapter FavoritePlayerAdapter;
    public RecyclerViewEmptySupport FavoritesRecyclerView;

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_favorites, container, false);
        favoritesViewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);
        favoritesViewModel.setFavoritePlayersVoid(FileHelper.returnStoredPlayersIds(getContext()));
        FavoritePlayerAdapter = new FavoritePlayerAdapter();
        favoritesViewModel.getFavoritePlayers().observe(getViewLifecycleOwner(), new Observer<ArrayList<PlayerPOJO>>() {
            @Override
            public void onChanged(ArrayList<PlayerPOJO> favoritePlayers) {
                FavoritePlayerAdapter.setFavoritePlayers(favoritePlayers);
            }
        });
        FavoritesRecyclerView = (RecyclerViewEmptySupport) root.findViewById(R.id.favoritesRecyclerView);
        FavoritesRecyclerView.setAdapter(FavoritePlayerAdapter);
        FavoritesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FavoritesRecyclerView.setEmptyView(root.findViewById(R.id.favoritePlayersEmpty));
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}