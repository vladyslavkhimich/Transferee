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
import com.example.transferee.helpers.DoubleHelper;
import com.example.transferee.helpers.StringHelper;
import com.example.transferee.models.Match;
import com.example.transferee.web.RetrofitService;
import com.example.transferee.web.pojo.MatchesPOJO;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.ArrayList;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewHolder>{
    private ArrayList<MatchesPOJO> Matches = new ArrayList<>();
    Context Context;
    public MatchAdapter() {

    }

    public void setMatches(ArrayList<MatchesPOJO> matches) {
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
        MatchesPOJO match = Matches.get(position);

        try {
            holder.MatchDateTextView.setText(DateHelper.getStringDateWithDay(match.getMatchDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Picasso.get().load(RetrofitService.getBaseURLShorten() + match.getClubURL()).into(holder.MatchClubImageView);
        holder.MatchClubTextView.setText(match.getClubName());
        holder.MatchScoreTextView.setText(match.getMatchScore());
        if (match.getIsWin())
            holder.MatchScoreTextView.setTextColor(ContextCompat.getColor(Context, R.color.yellow_green));
        else if (match.getIsLoss())
            holder.MatchScoreTextView.setTextColor(ContextCompat.getColor(Context, R.color.red));
        holder.MatchPlayerGoalsTextView.setText(StringHelper.getDashIfNumberIsZero(match.getPlayerGoals()));
        holder.MatchPlayerAssistsTextView.setText(StringHelper.getDashIfNumberIsZero(match.getPlayerAssists()));
        holder.MatchPlayerPositionTextView.setText(StringHelper.getDashIfStringIsNull(match.getMatchPosition()));
        holder.MatchPlayerYellowCardTextView.setText(StringHelper.getNumberWithApostropheIfValueIsNotZero(match.getYellowCard()));
        holder.MatchPlayerRedCardTextView.setText(StringHelper.getNumberWithApostropheIfValueIsNotZero(match.getRedCard()));
        holder.MatchPlayerMinutesPlayedTextView.setText(StringHelper.getNumberWithApostropheIfValueIsNotZero(match.getMinutesPlayed()));
        holder.MatchPlayerRatingTextView.setText(DoubleHelper.getDashIfNumberIsNullOrZero(match.getPlayerRating()));
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
