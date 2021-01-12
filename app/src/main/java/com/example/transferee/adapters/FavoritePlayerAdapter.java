package com.example.transferee.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transferee.R;
import com.example.transferee.models.FavoritePlayer;

import java.util.ArrayList;

public class FavoritePlayerAdapter extends RecyclerView.Adapter<FavoritePlayerAdapter.FavoritePlayerViewHolder> {

    ArrayList<FavoritePlayer> FavoritePlayers;

    public FavoritePlayerAdapter() {

    }

    public void setFavoritePlayers(ArrayList<FavoritePlayer> favoritePlayers) {
        FavoritePlayers = favoritePlayers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoritePlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View favoritePlayerView = inflater.inflate(R.layout.favorite_item, parent, false);

        return new FavoritePlayerViewHolder(favoritePlayerView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritePlayerViewHolder holder, int position) {
        FavoritePlayer favoritePlayer = FavoritePlayers.get(position);

        holder.FavoritePlayerImageView.setImageResource(favoritePlayer.Player.PlayerImageID);
        holder.FavoritePlayerNameTextView.setText(favoritePlayer.Player.PlayerName);
        holder.FavoritePlayerClubImageView.setImageResource(favoritePlayer.Club.ImageID);
        holder.FavoritePlayerClubTextView.setText(favoritePlayer.Club.ClubName);
    }

    @Override
    public int getItemCount() {
        return FavoritePlayers.size();
    }

    public class FavoritePlayerViewHolder extends RecyclerView.ViewHolder {
        public ImageView FavoritePlayerImageView;
        public TextView FavoritePlayerNameTextView;
        public ImageView FavoritePlayerClubImageView;
        public TextView FavoritePlayerClubTextView;

        public FavoritePlayerViewHolder(View itemView) {
            super(itemView);

            FavoritePlayerImageView = (ImageView) itemView.findViewById(R.id.favoritePlayerImageView);
            FavoritePlayerNameTextView = (TextView) itemView.findViewById(R.id.favoritePlayerNameTextView);
            FavoritePlayerClubImageView = (ImageView) itemView.findViewById(R.id.favoritePlayerClubImageView);
            FavoritePlayerClubTextView = (TextView) itemView.findViewById(R.id.favoritePlayerClubTextView);
        }
    }
}
