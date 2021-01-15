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
import com.example.transferee.helpers.DoubleHelper;
import com.example.transferee.models.PlayerTransfer;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PlayerTransferAdapter extends RecyclerView.Adapter<PlayerTransferAdapter.PlayerTransferViewHolder> {
    private ArrayList<PlayerTransfer> PlayerTransfers;
    public PlayerTransferAdapter() {

    }

    public void setPlayerTransfers(ArrayList<PlayerTransfer> playerTransfers) {
        PlayerTransfers = playerTransfers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlayerTransferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View playerTransferView = inflater.inflate(R.layout.player_transfer_item, parent, false);

        return new PlayerTransferViewHolder(playerTransferView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerTransferViewHolder holder, int position) {
        PlayerTransfer playerTransfer = PlayerTransfers.get(position);
        //holder.PlayerTransferDateTextView.setText(DateHelper.getStringDateWithDay(playerTransfer.TransferDate));
        holder.PlayerTransferDepartureClubTextView.setText(playerTransfer.DepartureClub.ClubName);
        holder.PlayerTransferDepartureClubImageView.setImageResource(playerTransfer.DepartureClub.ImageID);
        holder.PlayerTransferJoiningClubImageView.setImageResource(playerTransfer.JoiningClub.ImageID);
        holder.PlayerTransferJoiningClubTextView.setText(playerTransfer.JoiningClub.ClubName);
        if (playerTransfer.Fee > 10.0) {
            int feeInteger = (int) playerTransfer.Fee;
            holder.PlayerTransferFeeTextView.setText(holder.itemView.getContext().getString(R.string.market_value_formatted_integer, feeInteger));
        }
        else {
            holder.PlayerTransferFeeTextView.setText(holder.itemView.getContext().getString(R.string.market_value_formatted_string, DoubleHelper.formatDouble(playerTransfer.Fee)));
        }
        //holder.PlayerTransferContractTextView.setText(holder.itemView.getContext().getString(R.string.contract_date, DateHelper.getStringDate(playerTransfer.StartContractDate), DateHelper.getStringDate(playerTransfer.EndContractDate)));
        if (playerTransfer.MarketValue > 10.0) {
            int marketValueInteger = (int) playerTransfer.MarketValue;
            holder.PlayerTransferMarketValueTextView.setText(holder.itemView.getContext().getString(R.string.market_value_formatted_integer, marketValueInteger));
        }
        else
            holder.PlayerTransferMarketValueTextView.setText(holder.itemView.getContext().getString(R.string.market_value_formatted_string, DoubleHelper.formatDouble(playerTransfer.MarketValue)));
    }

    @Override
    public int getItemCount() {
        return PlayerTransfers.size();
    }

    public class PlayerTransferViewHolder extends RecyclerView.ViewHolder {
        public TextView PlayerTransferDateTextView;
        public TextView PlayerTransferDepartureClubTextView;
        public ImageView PlayerTransferDepartureClubImageView;
        public ImageView PlayerTransferJoiningClubImageView;
        public TextView PlayerTransferJoiningClubTextView;
        public TextView PlayerTransferFeeTextView;
        public TextView PlayerTransferContractTextView;
        public TextView PlayerTransferMarketValueTextView;

        public PlayerTransferViewHolder(@NonNull View itemView) {
            super(itemView);

            PlayerTransferDateTextView = (TextView) itemView.findViewById(R.id.playerTransferDateTextView);
            PlayerTransferDepartureClubTextView = (TextView) itemView.findViewById(R.id.playerTransferDepartureClubTextView);
            PlayerTransferDepartureClubImageView = (ImageView) itemView.findViewById(R.id.playerTransferDepartureClubImageView);
            PlayerTransferJoiningClubImageView = (ImageView) itemView.findViewById(R.id.playerTransferJoiningClubImageView);
            PlayerTransferJoiningClubTextView = (TextView) itemView.findViewById(R.id.playerTransferJoiningClubTextView);
            PlayerTransferFeeTextView = (TextView) itemView.findViewById(R.id.playerTransferFeeTextView);
            PlayerTransferContractTextView = (TextView) itemView.findViewById(R.id.playerTransferContractTextView);
            PlayerTransferMarketValueTextView = (TextView) itemView.findViewById(R.id.playerTransferMarketValueTextView);
        }
    }
}
