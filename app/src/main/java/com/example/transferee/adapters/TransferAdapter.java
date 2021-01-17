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
import com.example.transferee.helpers.DateHelper;
import com.example.transferee.helpers.DoubleHelper;
import com.example.transferee.web.RetrofitService;
import com.example.transferee.web.pojo.LatestTransfersPOJO;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.ArrayList;

public class TransferAdapter extends RecyclerView.Adapter<TransferAdapter.TransferViewHolder> {

    private ArrayList<LatestTransfersPOJO> LatestTransfersPOJO = new ArrayList<>();
    public TransferAdapter() {

    }

    public void setLatestTransfersPOJO(ArrayList<LatestTransfersPOJO> latestTransfersPOJO) {
        LatestTransfersPOJO = latestTransfersPOJO;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TransferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View transferView = inflater.inflate(R.layout.transfer_item, parent, false);

        return new TransferViewHolder(transferView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransferViewHolder holder, int position) {
        LatestTransfersPOJO latestTransferPOJO = LatestTransfersPOJO.get(position);
        Picasso.get().load(RetrofitService.getBaseURLShorten() + latestTransferPOJO.getImageURL()).into(holder.TransferPlayerImageView);
        try {
            holder.TransferDateTextView.setText(DateHelper.getStringDateWithDay(latestTransferPOJO.getDateOfTransfer()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.TransferPlayerName.setText(latestTransferPOJO.getName());
        holder.DepartureClubTextView.setText(latestTransferPOJO.getDepartureClubName());
        Picasso.get().load(RetrofitService.getBaseURLShorten() + latestTransferPOJO.getDepartureClubURL()).into(holder.DepartureClubImageView);
        Picasso.get().load(RetrofitService.getBaseURLShorten() + latestTransferPOJO.getJoiningClubURL()).into(holder.JoiningClubImageView);
        holder.JoiningClubTextView.setText(latestTransferPOJO.getJoiningClubName());
        if (latestTransferPOJO.getTransferPrice() > 10.0) {
            Integer feeInteger = latestTransferPOJO.getTransferPrice().intValue();
            holder.FeeTextView.setText(holder.itemView.getContext().getString(R.string.market_value_formatted_integer, feeInteger));
        }
        else {
            holder.FeeTextView.setText(holder.itemView.getContext().getString(R.string.market_value_formatted_string, DoubleHelper.formatDouble(latestTransferPOJO.getTransferPrice())));
        }
        try {
            holder.TransferContractTextView.setText(holder.itemView.getContext().getString(R.string.contract_date, DateHelper.getStringDate(latestTransferPOJO.getContractStartDate()), DateHelper.getStringDate(latestTransferPOJO.getContractFinishDate())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (latestTransferPOJO.getMarketValue() > 10.0) {
            Integer marketValueInteger = latestTransferPOJO.getMarketValue().intValue();
            holder.MarketValueTextView.setText(holder.itemView.getContext().getString(R.string.market_value_formatted_integer, marketValueInteger));
        }
        else
            holder.MarketValueTextView.setText(holder.itemView.getContext().getString(R.string.market_value_formatted_string, DoubleHelper.formatDouble(latestTransferPOJO.getMarketValue())));
    }

    @Override
    public int getItemCount() {
        return LatestTransfersPOJO.size();
    }

    public class TransferViewHolder extends RecyclerView.ViewHolder {
        public ImageView TransferPlayerImageView;
        public TextView TransferDateTextView;
        public TextView TransferPlayerName;
        public TextView DepartureClubTextView;
        public ImageView DepartureClubImageView;
        public ImageView JoiningClubImageView;
        public TextView JoiningClubTextView;
        public TextView FeeTextView;
        public TextView TransferContractTextView;
        public TextView MarketValueTextView;

        public TransferViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("Player_ID", LatestTransfersPOJO.get(getAdapterPosition()).getPlayerID());
                    Intent playerActivityIntent = new Intent(v.getContext(), PlayerActivity.class).putExtras(bundle);
                    v.getContext().startActivity(playerActivityIntent);
                }
            });
            TransferPlayerImageView = (ImageView) itemView.findViewById(R.id.transferPlayerImageView);
            TransferDateTextView = (TextView) itemView.findViewById(R.id.playerTransferDateTextView);
            TransferPlayerName = (TextView) itemView.findViewById(R.id.transferPlayerName);
            DepartureClubTextView = (TextView) itemView.findViewById(R.id.playerTransferDepartureClubTextView);
            DepartureClubImageView = (ImageView) itemView.findViewById(R.id.playerTransferDepartureClubImageView);
            JoiningClubImageView = (ImageView) itemView.findViewById(R.id.playerTransferJoiningClubImageView);
            JoiningClubTextView = (TextView) itemView.findViewById(R.id.playerTransferJoiningClubTextView);
            FeeTextView = (TextView) itemView.findViewById(R.id.playerTransferFeeTextView);
            TransferContractTextView = (TextView) itemView.findViewById(R.id.playerTransferContractTextView);
            MarketValueTextView = (TextView) itemView.findViewById(R.id.playerTransferMarketValueTextView);
        }
    }
}
