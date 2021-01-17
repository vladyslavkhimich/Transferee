package com.example.transferee.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transferee.PlayerActivity;
import com.example.transferee.R;
import com.example.transferee.helpers.FileHelper;
import com.example.transferee.models.FavoritePlayer;
import com.example.transferee.web.RetrofitService;
import com.example.transferee.web.pojo.PlayerPOJO;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavoritePlayerAdapter extends RecyclerView.Adapter<FavoritePlayerAdapter.FavoritePlayerViewHolder> {

    ArrayList<PlayerPOJO> FavoritePlayers = new ArrayList<>();

    public FavoritePlayerAdapter() {

    }

    public void setFavoritePlayers(ArrayList<PlayerPOJO> favoritePlayers) {
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
        PlayerPOJO favoritePlayer = FavoritePlayers.get(position);

        Picasso.get().load(RetrofitService.getBaseURLShorten() + favoritePlayer.getImageURL()).into(holder.FavoritePlayerImageView);
        holder.FavoritePlayerNameTextView.setText(favoritePlayer.getName());
        Picasso.get().load(RetrofitService.getBaseURLShorten() + favoritePlayer.getClubURL()).into(holder.FavoritePlayerClubImageView);
        holder.FavoritePlayerClubTextView.setText(favoritePlayer.getClubName());
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
        public ImageView FavoritePlayerStarImageView;

        public FavoritePlayerViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("Player_ID", FavoritePlayers.get(getAdapterPosition()).getPlayerID());
                    Intent playerActivityIntent = new Intent(v.getContext(), PlayerActivity.class).putExtras(bundle);
                    v.getContext().startActivity(playerActivityIntent);
                }
            });
            FavoritePlayerImageView = (ImageView) itemView.findViewById(R.id.favoritePlayerImageView);
            FavoritePlayerNameTextView = (TextView) itemView.findViewById(R.id.favoritePlayerNameTextView);
            FavoritePlayerClubImageView = (ImageView) itemView.findViewById(R.id.favoritePlayerClubImageView);
            FavoritePlayerClubTextView = (TextView) itemView.findViewById(R.id.favoritePlayerClubTextView);
            FavoritePlayerStarImageView = (ImageView) itemView.findViewById(R.id.favoritePlayerStarImageView);
            FavoritePlayerStarImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FileHelper.removePlayerIdFromStorage(v.getContext(), FavoritePlayers.get(getAdapterPosition()).getPlayerID());
                    FavoritePlayers.remove(FavoritePlayers.get(getAdapterPosition()));
                    setFavoritePlayers(FavoritePlayers);
                }
            });
        }
    }
}
