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
import com.example.transferee.web.RetrofitService;
import com.example.transferee.web.pojo.TopRatedPlayersPOJO;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TopRatedPlayerAdapter extends RecyclerView.Adapter<TopRatedPlayerAdapter.TopRatedPlayerViewHolder> {

    private ArrayList<TopRatedPlayersPOJO> TopRatedPlayersPOJO = new ArrayList<>();

    public TopRatedPlayerAdapter() {

    }

    public void setTopRatedPlayersPOJO(ArrayList<TopRatedPlayersPOJO> topRatedPlayersPOJO) {
        TopRatedPlayersPOJO = topRatedPlayersPOJO;
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
        TopRatedPlayersPOJO topRatedPlayersPOJO = TopRatedPlayersPOJO.get(position);
        Picasso.get().load(RetrofitService.getBaseURLShorten() + topRatedPlayersPOJO.getImageURL()).into(holder.TopRatedPlayerImageView);
        holder.TopRatedPlayerNameTextView.setText(topRatedPlayersPOJO.getName());
        Picasso.get().load(RetrofitService.getBaseURLShorten() + topRatedPlayersPOJO.getClubURL()).into(holder.TopRatedPlayerClubImageView);
        holder.TopRatedPlayerClubTextView.setText(topRatedPlayersPOJO.getClubName());
        holder.TopRatedPlayerRatingTextView.setText(Double.toString(topRatedPlayersPOJO.getAverageRating()));
    }

    @Override
    public int getItemCount() {
        return TopRatedPlayersPOJO.size();
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
                    Bundle bundle = new Bundle();
                    bundle.putInt("Player_ID", TopRatedPlayersPOJO.get(getAdapterPosition()).getPlayerID());
                    Intent playerActivityIntent = new Intent(v.getContext(), PlayerActivity.class).putExtras(bundle);
                    v.getContext().startActivity(playerActivityIntent);
                }
            });
            TopRatedPlayerImageView = (ImageView) itemView.findViewById(R.id.topMarketValuePlayerImageView);
            TopRatedPlayerNameTextView = (TextView) itemView.findViewById(R.id.topMarketValuePlayerNameTextView);
            TopRatedPlayerClubImageView = (ImageView) itemView.findViewById(R.id.topMarketValuePlayerClubImageView);
            TopRatedPlayerClubTextView = (TextView) itemView.findViewById(R.id.topMarketValuePlayerClubTextView);
            TopRatedPlayerRatingTextView = (TextView) itemView.findViewById(R.id.playerTransferFeeTextView);
        }
    }

}
