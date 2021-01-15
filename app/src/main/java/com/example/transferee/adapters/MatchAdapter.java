package com.example.transferee.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transferee.R;
import com.example.transferee.helpers.DateHelper;
import com.example.transferee.helpers.StringHelper;
import com.example.transferee.models.Match;

import java.util.ArrayList;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewHolder>{
    private ArrayList<Match> Matches;
    Context Context;
    public MatchAdapter() {

    }

    public void setMatches(ArrayList<Match> matches) {
        Matches = matches;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(Context);

        View matchView = inflater.inflate(R.layout.match_item, parent, false);

        return new MatchViewHolder(matchView);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        Match match = Matches.get(position);

        //holder.MatchDateTextView.setText(DateHelper.getStringDateWithDay(match.MatchDate));
        holder.MatchClubImageView.setImageResource(match.ClubAgainst.ImageID);
        holder.MatchClubTextView.setText(match.ClubAgainst.ClubName);
        holder.MatchScoreTextView.setText(match.MatchScore);
        if (match.IsWin)
            holder.MatchScoreTextView.setTextColor(ContextCompat.getColor(Context, R.color.yellow_green));
        else if (match.IsLoss)
            holder.MatchScoreTextView.setTextColor(ContextCompat.getColor(Context, R.color.red));
        holder.MatchPlayerGoalsTextView.setText(StringHelper.getDashIfNumberIsZero(match.PlayerGoals));
        holder.MatchPlayerAssistsTextView.setText(StringHelper.getDashIfNumberIsZero(match.PlayerAssists));
        holder.MatchPlayerPositionTextView.setText(match.PlayerPosition);
        holder.MatchPlayerYellowCardTextView.setText(StringHelper.getNumberWithApostropheIfValueIsNotZero(match.YellowCardMinute));
        holder.MatchPlayerRedCardTextView.setText(StringHelper.getNumberWithApostropheIfValueIsNotZero(match.RedCardMinute));
        holder.MatchPlayerMinutesPlayedTextView.setText(StringHelper.getNumberWithApostropheIfValueIsNotZero(match.MinutesPlayed));
    }

    @Override
    public int getItemCount() {
        return Matches.size();
    }

    public class MatchViewHolder extends RecyclerView.ViewHolder {
        public TextView MatchDateTextView;
        public ImageView MatchClubImageView;
        public TextView MatchClubTextView;
        public TextView MatchScoreTextView;
        public TextView MatchPlayerGoalsTextView;
        public TextView MatchPlayerAssistsTextView;
        public TextView MatchPlayerPositionTextView;
        public TextView MatchPlayerYellowCardTextView;
        public TextView MatchPlayerRedCardTextView;
        public TextView MatchPlayerMinutesPlayedTextView;
        public TextView MatchPlayerRatingTextView;

        public MatchViewHolder(@NonNull View itemView) {
            super(itemView);

            MatchDateTextView = (TextView) itemView.findViewById(R.id.matchDateTextView);
            MatchClubImageView = (ImageView) itemView.findViewById(R.id.matchClubImageView);
            MatchClubTextView = (TextView) itemView.findViewById(R.id.matchClubTextView);
            MatchScoreTextView = (TextView) itemView.findViewById(R.id.matchScoreTextView);
            MatchPlayerGoalsTextView = (TextView) itemView.findViewById(R.id.matchPlayerGoalsTextView);
            MatchPlayerAssistsTextView = (TextView) itemView.findViewById(R.id.matchPlayerAssistsTextView);
            MatchPlayerPositionTextView = (TextView) itemView.findViewById(R.id.matchPlayerPositionTextView);
            MatchPlayerYellowCardTextView = (TextView) itemView.findViewById(R.id.matchPlayerYellowCardTextView);
            MatchPlayerRedCardTextView = (TextView) itemView.findViewById(R.id.matchPlayerRedCardTextView);
            MatchPlayerMinutesPlayedTextView = (TextView) itemView.findViewById(R.id.matchPlayerMinutesPlayedTextView);
            MatchPlayerRatingTextView = (TextView) itemView.findViewById(R.id.matchPlayerRatingTextView);
        }
    }
}
