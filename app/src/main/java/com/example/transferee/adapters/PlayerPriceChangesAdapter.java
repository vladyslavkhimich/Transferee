package com.example.transferee.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transferee.R;
import com.example.transferee.helpers.DateHelper;
import com.example.transferee.models.PriceChange;

import java.util.ArrayList;

public class PlayerPriceChangesAdapter extends RecyclerView.Adapter<PlayerPriceChangesAdapter.PriceChangeViewHolder> {

    private ArrayList<PriceChange> PriceChanges;

    public PlayerPriceChangesAdapter() {

    }

    public void setPriceChanges(ArrayList<PriceChange> priceChanges) {
        PriceChanges = priceChanges;
    }

    @NonNull
    @Override
    public PriceChangeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View priceChangeView = inflater.inflate(R.layout.price_change_item, parent, false);

        return new PriceChangeViewHolder(priceChangeView);
    }

    @Override
    public void onBindViewHolder(@NonNull PriceChangeViewHolder holder, int position) {
        PriceChange priceChange = PriceChanges.get(position);

        //holder.PriceChangeDateTextView.setText(DateHelper.getStringDateWithDay(priceChange.PriceChangeDate));
        if (priceChange.PreviousPrice > 1.0) {
            int previousPriceInteger = (int) priceChange.PreviousPrice;
            holder.PreviousPriceTextView.setText(holder.itemView.getContext().getString(R.string.market_value_formatted_integer, previousPriceInteger));
        }
        else {
            holder.PreviousPriceTextView.setText(holder.itemView.getContext().getString(R.string.market_value_formatted_float, priceChange.PreviousPrice));
        }
        if (priceChange.IsRise)
            holder.PriceArrowImageView.setImageResource(R.drawable.ic_arrow_up_main_green);
        else
            holder.PriceArrowImageView.setImageResource(R.drawable.ic_arrow_down_red);
        holder.PriceChangeClubImageView.setImageResource(priceChange.Club.ImageID);
        if (priceChange.NewPrice > 1.0) {
            int newPriceInteger = (int) priceChange.NewPrice;
            holder.NewPriceTextView.setText(holder.itemView.getContext().getString(R.string.market_value_formatted_integer, newPriceInteger));
        }
        else {
            holder.NewPriceTextView.setText(holder.itemView.getContext().getString(R.string.market_value_formatted_float, priceChange.NewPrice));
        }
    }

    @Override
    public int getItemCount() {
        return PriceChanges.size();
    }


    public class PriceChangeViewHolder extends RecyclerView.ViewHolder {
        TextView PriceChangeDateTextView;
        TextView PreviousPriceTextView;
        ImageView PriceArrowImageView;
        ImageView PriceChangeClubImageView;
        TextView NewPriceTextView;

        public PriceChangeViewHolder(@NonNull View itemView) {
            super(itemView);

            PriceChangeDateTextView = (TextView) itemView.findViewById(R.id.priceChangeDateTextView);
            PreviousPriceTextView = (TextView) itemView.findViewById(R.id.previousPriceTextView);
            PriceArrowImageView = (ImageView) itemView.findViewById(R.id.priceArrowImageView);
            PriceChangeClubImageView = (ImageView) itemView.findViewById(R.id.priceChangeClubImageView);
            NewPriceTextView = (TextView) itemView.findViewById(R.id.newPriceTextView);
        }
    }
}
