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
import com.example.transferee.models.TopMarketValuePlayer;
import com.example.transferee.web.RetrofitService;
import com.example.transferee.web.pojo.TopMarketValuePlayersPOJO;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TopMarketValuePlayerAdapter extends RecyclerView.Adapter<TopMarketValuePlayerAdapter.TopMarketValuePlayerViewHolder>{
    private ArrayList<TopMarketValuePlayersPOJO> TopMarketValuePlayers = new ArrayList<>();

    public TopMarketValuePlayerAdapter() {

    }

    public void setTopMarketValuePlayers(ArrayList<TopMarketValuePlayersPOJO> topMarketValuePlayers) {
        TopMarketValuePlayers = topMarketValuePlayers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TopMarketValuePlayerAdapter.TopMarketValuePlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View topRatedPlayerView = inflater.inflate(R.layout.top_market_value_player_item, parent, false);

        return new TopMarketValuePlayerViewHolder(topRatedPlayerView);
    }

    @Override
    public void onBindViewHolder(@NonNull TopMarketValuePlayerAdapter.TopMarketValuePlayerViewHolder holder, int position) {
        TopMarketValuePlayersPOJO topMarketValuePlayerPOJO = TopMarketValuePlayers.get(position);
        Picasso.get().load(RetrofitService.getBaseURLShorten() + topMarketValuePlayerPOJO.getImageURL()).into(holder.TopMarketValuePlayerImageView);
        holder.TopMarketValuePlayerNameTextView.setText(topMarketValuePlayerPOJO.getName());
        Picasso.get().load(RetrofitService.getBaseURLShorten() + topMarketValuePlayerPOJO.getClubURL()).into(holder.TopMarketValuePlayerClubImageView);
        holder.TopMarketValuePlayerClubTextView.setText(topMarketValuePlayerPOJO.getClubName());
        if (topMarketValuePlayerPOJO.getMarketPrice() > 1.0) {
            int marketValueInteger = (int) topMarketValuePlayerPOJO.getMarketPrice();
            holder.TopMarketValuePlayerValueTextView.setText(holder.itemView.getContext().getString(R.string.market_value_formatted_integer, marketValueInteger));
        }
        else {
            holder.TopMarketValuePlayerValueTextView.setText(holder.itemView.getContext().getString(R.string.market_value_formatted_float, topMarketValuePlayerPOJO.getMarketPrice()));
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("Player_ID", TopMarketValuePlayers.get(getAdapterPosition()).getPlayerID());
                    Intent playerActivityIntent = new Intent(v.getContext(), PlayerActivity.class).putExtras(bundle);
                    v.getContext().startActivity(playerActivityIntent);
                }
            });
            TopMarketValuePlayerImageView = (ImageView) itemView.findViewById(R.id.topMarketValuePlayerImageView);
            TopMarketValuePlayerNameTextView = (TextView) itemView.findViewById(R.id.topMarketValuePlayerNameTextView);
            TopMarketValuePlayerClubImageView = (ImageView) itemView.findViewById(R.id.topMarketValuePlayerClubImageView);
            TopMarketValuePlayerClubTextView = (TextView) itemView.findViewById(R.id.topMarketValuePlayerClubTextView);
            TopMarketValuePlayerValueTextView = (TextView) itemView.findViewById(R.id.playerTransferFeeTextView);
        }
    }
}
