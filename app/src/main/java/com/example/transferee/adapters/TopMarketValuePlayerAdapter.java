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
import com.example.transferee.models.TopMarketValuePlayer;

import java.util.ArrayList;

public class TopMarketValuePlayerAdapter extends RecyclerView.Adapter<TopMarketValuePlayerAdapter.TopMarketValuePlayerViewHolder>{
    private ArrayList<TopMarketValuePlayer> TopMarketValuePlayers;

    public TopMarketValuePlayerAdapter() {

    }

    public void setTopMarketValuePlayers(ArrayList<TopMarketValuePlayer> topMarketValuePlayers) {
        TopMarketValuePlayers = topMarketValuePlayers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TopMarketValuePlayerAdapter.TopMarketValuePlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View topRatedPlayerView = inflater.inflate(R.layout.top_market_value_player_item, parent, false);

        TopMarketValuePlayerAdapter.TopMarketValuePlayerViewHolder topMarketValuePlayerViewHolder = new TopMarketValuePlayerAdapter.TopMarketValuePlayerViewHolder(topRatedPlayerView);
        return  topMarketValuePlayerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TopMarketValuePlayerAdapter.TopMarketValuePlayerViewHolder holder, int position) {
        TopMarketValuePlayer topMarketValuePlayer = TopMarketValuePlayers.get(position);

        holder.TopMarketValuePlayerImageView.setImageResource(topMarketValuePlayer.Player.PlayerImageID);
        holder.TopMarketValuePlayerNameTextView.setText(topMarketValuePlayer.Player.PlayerName);
        holder.TopMarketValuePlayerClubImageView.setImageResource(topMarketValuePlayer.Club.ImageID);
        holder.TopMarketValuePlayerClubTextView.setText(topMarketValuePlayer.Club.ClubName);
        if (topMarketValuePlayer.MarketValue > 1.0) {
            int marketValueInteger = (int) topMarketValuePlayer.MarketValue;
            holder.TopMarketValuePlayerValueTextView.setText(holder.itemView.getContext().getString(R.string.market_value_formatted_integer, marketValueInteger));
        }
        else {
            holder.TopMarketValuePlayerValueTextView.setText(holder.itemView.getContext().getString(R.string.market_value_formatted_float, topMarketValuePlayer.MarketValue));
        }
    }

    @Override
    public int getItemCount() {
        return TopMarketValuePlayers.size();
    }

    public class TopMarketValuePlayerViewHolder extends RecyclerView.ViewHolder {
        public ImageView TopMarketValuePlayerImageView;
        public TextView TopMarketValuePlayerNameTextView;
        public ImageView TopMarketValuePlayerClubImageView;
        public TextView TopMarketValuePlayerClubTextView;
        public TextView TopMarketValuePlayerValueTextView;

        public TopMarketValuePlayerViewHolder(View itemView) {
            super(itemView);

            TopMarketValuePlayerImageView = (ImageView) itemView.findViewById(R.id.topMarketValuePlayerImageView);
            TopMarketValuePlayerNameTextView = (TextView) itemView.findViewById(R.id.topMarketValuePlayerNameTextView);
            TopMarketValuePlayerClubImageView = (ImageView) itemView.findViewById(R.id.topMarketValuePlayerClubImageView);
            TopMarketValuePlayerClubTextView = (TextView) itemView.findViewById(R.id.topMarketValuePlayerClubTextView);
            TopMarketValuePlayerValueTextView = (TextView) itemView.findViewById(R.id.feeTextView);
        }
    }
}
