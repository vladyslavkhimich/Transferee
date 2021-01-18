package com.example.transferee.ui.playeroverview;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.transferee.PlayerActivity;
import com.example.transferee.R;
import com.example.transferee.helpers.DateHelper;
import com.example.transferee.repository.PlayerRepository;
import com.example.transferee.web.RetrofitService;
import com.example.transferee.web.pojo.PlayerOverviewPOJO;
import com.squareup.picasso.Picasso;

import java.text.ParseException;

public class PlayerOverviewFragment extends Fragment {

    private PlayerOverviewViewModel playerOverviewViewModel;
    public TextView PlayerHeightTextView;
    public TextView PlayerAgeTextView;
    public TextView PlayerBirthDateTextView;
    public TextView PlayerShirtNumberTextView;
    public TextView PlayerPreferredFootTextView;
    public ImageView PlayerCountryImageView;
    public LinearLayout PseudonymContainer;
    public TextView PlayerPseudonymTextView;
    public TextView PlayerPrimaryPositionTextView;
    public LinearLayout OthersPositionsContainer;
    public TextView PlayerOthersPositionsTextView;
    public ConstraintLayout PlayerPositionsContainer;

    public static PlayerOverviewFragment newInstance() {
        return new PlayerOverviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_player_overview, container, false);
        PlayerHeightTextView = (TextView) root.findViewById(R.id.playerHeightTextView);
        PlayerAgeTextView = (TextView) root.findViewById(R.id.playerAgeTextView);
        PlayerBirthDateTextView = (TextView) root.findViewById(R.id.playerBirthDateTextView);
        PlayerShirtNumberTextView = (TextView) root.findViewById(R.id.playerShirtNumberTextView);
        PlayerPreferredFootTextView = (TextView) root.findViewById(R.id.playerPreferredFootTextView);
        PlayerCountryImageView = (ImageView) root.findViewById(R.id.playerCountryImageView);
        PseudonymContainer = (LinearLayout) root.findViewById(R.id.pseudonymContainer);
        PlayerPseudonymTextView = (TextView) root.findViewById(R.id.playerPseudonymTextView);
        PlayerPrimaryPositionTextView = (TextView) root.findViewById(R.id.playerPrimaryPositionTextView);
        OthersPositionsContainer = (LinearLayout) root.findViewById(R.id.othersPositionsContainer);
        PlayerOthersPositionsTextView = (TextView) root.findViewById(R.id.playerOthersPositionsTextView);
        PlayerPositionsContainer = (ConstraintLayout) root.findViewById(R.id.playerPositionsContainer);
        playerOverviewViewModel.getPlayerOverview().observe(getViewLifecycleOwner(), new Observer<PlayerOverviewPOJO>() {
            @Override
            public void onChanged(PlayerOverviewPOJO playerOverviewPOJO) {
                try {
                    setOverviewFields(playerOverviewPOJO);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playerOverviewViewModel = new ViewModelProvider(this).get(PlayerOverviewViewModel.class);
        playerOverviewViewModel.setPlayerOverviewVoid(((PlayerActivity)getActivity()).getPlayerID());
    }

    public void setOverviewFields(PlayerOverviewPOJO playerOverviewPOJO) throws ParseException {
        PlayerHeightTextView.setText(getContext().getString(R.string.player_height, playerOverviewPOJO.getHeight()));
        PlayerAgeTextView.setText(getContext().getString(R.string.player_age, playerOverviewPOJO.getAge()));
        PlayerBirthDateTextView.setText(DateHelper.getStringDateWithDay(playerOverviewPOJO.getBirthDate()));
        PlayerShirtNumberTextView.setText(playerOverviewPOJO.getShirtNumber().toString());
        PlayerPreferredFootTextView.setText(playerOverviewPOJO.getPreferredFoot() ? "Right" : "Left");
        Picasso.get().load(RetrofitService.getBaseURLShorten() + playerOverviewPOJO.getCountryURL()).into(PlayerCountryImageView);
        if (playerOverviewPOJO.getPseudonym() == null)
            PseudonymContainer.setVisibility(View.GONE);
        else {
            PseudonymContainer.setVisibility(View.VISIBLE);
            PlayerPseudonymTextView.setText(playerOverviewPOJO.getPseudonym());
        }
        if (playerOverviewPOJO.getPrimary() != null) {
            PlayerPositionsContainer.setVisibility(View.VISIBLE);
            PlayerPrimaryPositionTextView.setText(playerOverviewPOJO.getPrimary());
            if (playerOverviewPOJO.getOthers().size() == 0)
                OthersPositionsContainer.setVisibility(View.GONE);
            else {
                OthersPositionsContainer.setVisibility(View.VISIBLE);
                StringBuilder othersPositionsStringBuilder = new StringBuilder();
                for (int i = 0; i < playerOverviewPOJO.getOthers().size(); i++) {
                    if (i == 0)
                        othersPositionsStringBuilder.append(playerOverviewPOJO.getOthers().get(i));
                    else {
                        othersPositionsStringBuilder.append(", ").append(playerOverviewPOJO.getOthers().get(i));
                    }
                }
                char[] dstArray = new char[othersPositionsStringBuilder.capacity()];
                othersPositionsStringBuilder.getChars(0, othersPositionsStringBuilder.length(), dstArray, 0);
                PlayerOthersPositionsTextView.setText(new String(dstArray));
            }
        }
        else {
            PlayerPositionsContainer.setVisibility(View.GONE);
        }
    }
}