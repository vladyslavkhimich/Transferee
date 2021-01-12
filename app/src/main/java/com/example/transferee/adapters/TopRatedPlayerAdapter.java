package com.example.transferee.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transferee.PlayerActivity;
import com.example.transferee.R;
import com.example.transferee.models.TopRatedPlayer;

import java.util.ArrayList;

public class TopRatedPlayerAdapter extends RecyclerView.Adapter<TopRatedPlayerAdapter.TopRatedPlayerViewHolder> {

    private ArrayList<TopRatedPlayer> TopRatedPlayers;

    public TopRatedPlayerAdapter() {

    }

    public void setTopRatedPlayers(ArrayList<TopRatedPlayer> topRatedPlayers) {
        TopRatedPlayers = topRatedPlayers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TopRatedPlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View topRatedPlayerView = inflater.inflate(R.layout.top_rated_player_item, parent, false);

        return new TopRatedPlayerViewHolder(topRatedPlayerView);
    }

    @Override
    public void onBindViewHolder(@NonNull TopRatedPlayerViewHolder holder, int position) {
        TopRatedPlayer topRatedPlayer = TopRatedPlayers.get(position);

        holder.TopRatedPlayerImageView.setImageResource(topRatedPlayer.Player.PlayerImageID);
        holder.TopRatedPlayerNameTextView.setText(topRatedPlayer.Player.PlayerName);
        holder.TopRatedPlayerClubImageView.setImageResource(topRatedPlayer.Club.ImageID);
        holder.TopRatedPlayerClubTextView.setText(topRatedPlayer.Club.ClubName);
        holder.TopRatedPlayerRatingTextView.setText(Double.toString(topRatedPlayer.Rating));
    }

    @Override
    public int getItemCount() {
        return TopRatedPlayers.size();
    }

    public class TopRatedPlayerViewHolder extends RecyclerView.ViewHolder {
        public ImageView TopRatedPlayerImageView;
        public TextView TopRatedPlayerNameTextView;
        public ImageView TopRatedPlayerClubImageView;
        public TextView TopRatedPlayerClubTextView;
        public TextView TopRatedPlayerRatingTextView;

        public TopRatedPlayerViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent playerActivityIntent = new Intent(v.getContext(), PlayerActivity.class);
                    v.getContext().startActivity(playerActivityIntent);
                }
            });
            TopRatedPlayerImageView = (ImageView) itemView.findViewById(R.id.topMarketValuePlayerImageView);
            TopRatedPlayerNameTextView = (TextView) itemView.findViewById(R.id.topMarketValuePlayerNameTextView);
            TopRatedPlayerClubImageView = (ImageView) itemView.findViewById(R.id.topMarketValuePlayerClubImageView);
            TopRatedPlayerClubTextView = (TextView) itemView.findViewById(R.id.topMarketValuePlayerClubTextView);
            TopRatedPlayerRatingTextView = (TextView) itemView.findViewById(R.id.feeTextView);
        }
    }

}
