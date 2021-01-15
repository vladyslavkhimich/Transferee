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
import com.example.transferee.models.SearchedPlayer;
import com.example.transferee.web.RetrofitService;
import com.example.transferee.web.pojo.FoundPlayersPOJO;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchedPlayerAdapter extends RecyclerView.Adapter<SearchedPlayerAdapter.SearchedPlayerViewHolder>{
    private ArrayList<FoundPlayersPOJO> FoundPlayers = new ArrayList<>();

    public SearchedPlayerAdapter() {

    }

    public void setFoundPlayers(ArrayList<FoundPlayersPOJO> foundPlayers) {
        FoundPlayers = foundPlayers;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SearchedPlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View searchedPlayerView = inflater.inflate(R.layout.search_player_item, parent, false);

        return new SearchedPlayerViewHolder(searchedPlayerView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchedPlayerViewHolder holder, int position) {
        FoundPlayersPOJO foundPlayer = FoundPlayers.get(position);
        Picasso.get().load(RetrofitService.getBaseURLShorten() + foundPlayer.getImageURL()).into(holder.SearchedPlayerImageView);
        holder.SearchedPlayerNameTextView.setText(foundPlayer.getName());
        Picasso.get().load(RetrofitService.getBaseURLShorten() + foundPlayer.getClubURL()).into(holder.SearchedPlayerClubImageView);
        holder.SearchedPlayerClubTextView.setText(foundPlayer.getClubName());
        Picasso.get().load(RetrofitService.getBaseURLShorten() + foundPlayer.getCountryURL()).into(holder.SearchedPlayerCountryImageView);
        holder.SearchedPlayerCountryTextView.setText(foundPlayer.getCountryName());
    }

    @Override
    public int getItemCount() {
        return FoundPlayers.size();
    }

    public class SearchedPlayerViewHolder extends RecyclerView.ViewHolder {
        public ImageView SearchedPlayerImageView;
        public TextView SearchedPlayerNameTextView;
        public ImageView SearchedPlayerClubImageView;
        public TextView SearchedPlayerClubTextView;
        public ImageView SearchedPlayerCountryImageView;
        public TextView SearchedPlayerCountryTextView;

        public SearchedPlayerViewHolder(View itemView) {
            super(itemView);

            SearchedPlayerImageView = (ImageView) itemView.findViewById(R.id.searchedPlayerImageView);
            SearchedPlayerNameTextView = (TextView) itemView.findViewById(R.id.searchedPlayerNameTextView);
            SearchedPlayerClubImageView = (ImageView) itemView.findViewById(R.id.searchedPlayerClubImageView);
            SearchedPlayerClubTextView = (TextView) itemView.findViewById(R.id.searchedPlayerClubTextView);
            SearchedPlayerCountryImageView = (ImageView) itemView.findViewById(R.id.searchedPlayerCountryImageView);
            SearchedPlayerCountryTextView = (TextView) itemView.findViewById(R.id.searchedPlayerCountryTextView);
        }
    }
}
