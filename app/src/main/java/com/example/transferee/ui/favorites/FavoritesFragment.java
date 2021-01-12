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
import com.example.transferee.models.FavoritePlayer;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    private FavoritesViewModel favoritesViewModel;
    public FavoritePlayerAdapter FavoritePlayerAdapter;
    public RecyclerView FavoritesRecyclerView;

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        favoritesViewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);
        View root =  inflater.inflate(R.layout.fragment_favorites, container, false);
        FavoritePlayerAdapter = new FavoritePlayerAdapter();
        favoritesViewModel.getFavoritePlayers().observe(getViewLifecycleOwner(), new Observer<ArrayList<FavoritePlayer>>() {
            @Override
            public void onChanged(ArrayList<FavoritePlayer> favoritePlayers) {
                FavoritePlayerAdapter.setFavoritePlayers(favoritePlayers);
            }
        });
        FavoritesRecyclerView = (RecyclerView) root.findViewById(R.id.favoritesRecyclerView);
        FavoritesRecyclerView.setAdapter(FavoritePlayerAdapter);
        FavoritesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }

    /*@Override*/
    /*public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);
        // TODO: Use the ViewModel
    }*/

}