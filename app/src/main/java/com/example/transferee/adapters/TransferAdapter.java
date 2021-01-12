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
import com.example.transferee.helpers.DateHelper;
import com.example.transferee.models.Transfer;

import java.util.ArrayList;

public class TransferAdapter extends RecyclerView.Adapter<TransferAdapter.TransferViewHolder> {

    private ArrayList<Transfer> Transfers;
    public TransferAdapter() {

    }

    public void setTransfers(ArrayList<Transfer> transfers) {
        Transfers = transfers;
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
        Transfer transfer = Transfers.get(position);
        holder.TransferPlayerImageView.setImageResource(transfer.TransferredPlayer.PlayerImageID);
        holder.TransferDateTextView.setText(DateHelper.getStringDateWithDay(transfer.TransferDate));
        holder.TransferPlayerName.setText(transfer.TransferredPlayer.PlayerName);
        holder.DepartureClubTextView.setText(transfer.DepartureClub.ClubName);
        holder.DepartureClubImageView.setImageResource(transfer.DepartureClub.ImageID);
        holder.JoiningClubImageView.setImageResource(transfer.JoiningClub.ImageID);
        holder.JoiningClubTextView.setText(transfer.JoiningClub.ClubName);
        if (transfer.Fee > 1.0) {
            int feeInteger = (int) transfer.Fee;
            holder.FeeTextView.setText(holder.itemView.getContext().getString(R.string.market_value_formatted_integer, feeInteger));
        }
        else {
            holder.FeeTextView.setText(holder.itemView.getContext().getString(R.string.market_value_formatted_float, transfer.Fee));
        }
        holder.TransferContractTextView.setText(holder.itemView.getContext().getString(R.string.contract_date, DateHelper.getStringDate(transfer.StartContractDate), DateHelper.getStringDate(transfer.EndContractDate)));
        if (transfer.MarketValue > 1.0) {
            int marketValueInteger = (int) transfer.MarketValue;
            holder.MarketValueTextView.setText(holder.itemView.getContext().getString(R.string.market_value_formatted_integer, marketValueInteger));
        }
        else
            holder.MarketValueTextView.setText(holder.itemView.getContext().getString(R.string.market_value_formatted_float, transfer.MarketValue));
    }

    @Override
    public int getItemCount() {
        return Transfers.size();
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

            TransferPlayerImageView = (ImageView) itemView.findViewById(R.id.transferPlayerImageView);
            TransferDateTextView = (TextView) itemView.findViewById(R.id.transferDateTextView);
            TransferPlayerName = (TextView) itemView.findViewById(R.id.transferPlayerName);
            DepartureClubTextView = (TextView) itemView.findViewById(R.id.departureClubTextView);
            DepartureClubImageView = (ImageView) itemView.findViewById(R.id.departureClubImageView);
            JoiningClubImageView = (ImageView) itemView.findViewById(R.id.joiningClubImageView);
            JoiningClubTextView = (TextView) itemView.findViewById(R.id.joiningClubTextView);
            FeeTextView = (TextView) itemView.findViewById(R.id.feeTextView);
            TransferContractTextView = (TextView) itemView.findViewById(R.id.transferContractTextView);
            MarketValueTextView = (TextView) itemView.findViewById(R.id.marketValueTextView);
        }
    }
}
