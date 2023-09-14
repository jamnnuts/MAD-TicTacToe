package com.example.mad_tictactoe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StatFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static StatFrag newInstance(String param1, String param2) {
        StatFrag fragment = new StatFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_stat, container, false);
        SessionDataViewModel sessionData = new ViewModelProvider(getActivity()).get(SessionDataViewModel.class);
        Button returnButton = rootView.findViewById(R.id.returnButton);

        TextView player1Name = rootView.findViewById(R.id.playerName1);
        TextView player1Wins = rootView.findViewById(R.id.winValue1);
        TextView player1Lose = rootView.findViewById(R.id.lossValue1);
        TextView player1Draw = rootView.findViewById(R.id.drawValue1);
        TextView player1GamesPlayed = rootView.findViewById(R.id.gamesPlayedValue1);
        TextView player1WLP = rootView.findViewById(R.id.winPercentageValue1);

        TextView player2Name = rootView.findViewById(R.id.playerName2);
        TextView player2Wins = rootView.findViewById(R.id.winValue2);
        TextView player2Lose = rootView.findViewById(R.id.lossValue2);
        TextView player2Draw = rootView.findViewById(R.id.drawValue2);
        TextView player2GamesPlayed = rootView.findViewById(R.id.gamesPlayedValue2);
        TextView player2WLP = rootView.findViewById(R.id.winPercentageValue2);

        player1Name.setText(sessionData.playerOne.getValue().getPlayerName().toString());
        player1Wins.setText(Integer.toString(sessionData.playerOne.getValue().getWins()));
        player1Lose.setText(Integer.toString(sessionData.playerOne.getValue().getLosses()));
        player1Draw.setText(Integer.toString(sessionData.playerOne.getValue().getDraws()));
        player1GamesPlayed.setText(Integer.toString(sessionData.playerOne.getValue().getGamesPlayed()));
        if(sessionData.playerOne.getValue().getGamesPlayed() == 0) {
            player1WLP.setText("_");
        }
        else {
            player1WLP.setText(Double.toString((sessionData.playerOne.getValue().getWins())/(sessionData.playerOne.getValue().getGamesPlayed())*100) + "%");
        }

        player2Name.setText(sessionData.playerTwo.getValue().getPlayerName().toString());
        player2Wins.setText(Integer.toString(sessionData.playerTwo.getValue().getWins()));
        player2Lose.setText(Integer.toString(sessionData.playerTwo.getValue().getLosses()));
        player2Draw.setText(Integer.toString(sessionData.playerTwo.getValue().getDraws()));
        player2GamesPlayed.setText(Integer.toString(sessionData.playerTwo.getValue().getGamesPlayed()));
        if(sessionData.playerTwo.getValue().getGamesPlayed() == 0) {
            player2WLP.setText("_");
        }
        else {
            player2WLP.setText(Double.toString((sessionData.playerTwo.getValue().getWins())/(sessionData.playerTwo.getValue().getGamesPlayed()) * 100) + "%");
        }


        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionData.setClickedFragment(1);
            }
        });

        return rootView;
    }
}