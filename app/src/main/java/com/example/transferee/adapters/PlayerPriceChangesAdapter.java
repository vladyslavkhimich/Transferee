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
import com.example.transferee.web.RetrofitService;
import com.example.transferee.web.pojo.PriceChangesPOJO;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.ArrayList;

public class PlayerPriceChangesAdapter extends RecyclerView.Adapter<PlayerPriceChangesAdapter.PriceChangeViewHolder> {

    private ArrayList<PriceChangesPOJO> PriceChanges = new ArrayList<>();

    public PlayerPriceChangesAdapter() {

    }

    public void setPriceChanges(ArrayList<PriceChangesPOJO> priceChanges) {
        PriceChanges = priceChanges;
        notifyDataSetChanged();
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
        PriceChangesPOJO priceChange = PriceChanges.get(position);
        try {
            holder.PriceChangeDateTextView.setText(DateHelper.getStringDateWithDay(priceChange.getChangeDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (priceChange.getPreviousPrice() != null) {
            Double previousPriceDouble = Double.valueOf(priceChange.getPreviousPrice().toString());
            if (previousPriceDouble > 10.0) {
                Integer previousPriceInteger = previousPriceDouble.intValue();
                holder.PreviousPriceTextView.setText(holder.itemView.getContext().getString(R.string.market_value_formatted_integer, previousPriceInteger));
            }
            else {
                holder.PreviousPriceTextView.setText(holder.itemView.getContext().getString(R.string.market_value_formatted_float, previousPriceDouble));
            }
        }
        else
        {
            holder.PreviousPriceTextView.setText("-");
        }
        if (priceChange.getIsRise() != null) {
            if ((Boolean)priceChange.getIsRise())
                holder.PriceArrowImageView.setImageResource(R.drawable.ic_arrow_up_main_green);
            else
                holder.PriceArrowImageView.setImageResource(R.drawable.ic_arrow_down_red);
        }
        else {
            holder.PriceArrowImageView.setImageResource(R.drawable.ic_minus);
        }
        Picasso.get().load(RetrofitService.getBaseURLShorten() + priceChange.getClubURL()).into(holder.PriceChangeClubImageView);
        Double newPriceDouble = Double.valueOf(priceChange.getNewPrice().toString());
        if (newPriceDouble > 10.0) {
            Integer newPriceInteger = newPriceDouble.intValue();
            holder.NewPriceTextView.setText(holder.itemView.getContext().getString(R.string.market_value_formatted_integer, newPriceInteger));
        }
        else {
            holder.NewPriceTextView.setText(holder.itemView.getContext().getString(R.string.market_value_formatted_float, priceChange.getNewPrice()));
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
