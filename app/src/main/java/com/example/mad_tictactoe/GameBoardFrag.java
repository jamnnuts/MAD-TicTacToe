package com.example.mad_tictactoe;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameBoardFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameBoardFrag extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static final int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8},{0,4,8},{2,4,6}};
    private  int[] gamestate = {2,2,2,2,2,2,2,2,2};
    private Button[] buttonList = new Button[9];
    private TextView playerTurn;
    private Stack<Integer> undoMoves = new Stack<Integer>();

    private int rounds;

    private boolean playerOneActive;

    public GameBoardFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameBoardFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static GameBoardFrag newInstance(String param1, String param2) {
        GameBoardFrag fragment = new GameBoardFrag();
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
        View rootView = inflater.inflate(R.layout.fragment_game_board3x3, container, false);
        SessionDataViewModel sessionData = new ViewModelProvider(getActivity()).get(SessionDataViewModel.class);
        Button returnButton = rootView.findViewById(R.id.returnToMenuButton3x3);
        Button resetButton = rootView.findViewById(R.id.resetButton3x3);
        Button undoButton = rootView.findViewById(R.id.UndoButton);


        gamestate = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2}; //Reset Game

        buttonList[0] = rootView.findViewById(R.id.button3x3_0);
        buttonList[1] = rootView.findViewById(R.id.button3x3_1);
        buttonList[2] = rootView.findViewById(R.id.button3x3_2);
        buttonList[3] = rootView.findViewById(R.id.button3x3_3);
        buttonList[4] = rootView.findViewById(R.id.button3x3_4);
        buttonList[5] = rootView.findViewById(R.id.button3x3_5);
        buttonList[6] = rootView.findViewById(R.id.button3x3_6);
        buttonList[7] = rootView.findViewById(R.id.button3x3_7);
        buttonList[8] = rootView.findViewById(R.id.button3x3_8);


        for (int i = 0; i < buttonList.length; i++) {
            buttonList[i].setOnClickListener(this);
        }

        playerOneActive = true;
        rounds = 0;

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionData.setClickedFragment(1);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rounds = 0;
                gamestate = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2}; //Reset Game
                for (int i = 0; i < buttonList.length; i++) {
                    buttonList[i].setText("");
                }
            }
        });

        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rounds == 0) {
                    return;
                }
                else if (checkWinner()) {
                    return;
                }

                int lastMove = undoMoves.pop();
                buttonList[lastMove].setText("");
                gamestate[lastMove] = 2;
                rounds--;

            }
        });


        return rootView;
    }
    @Override
    public void onClick(View view) {
        SessionDataViewModel sessionData = new ViewModelProvider(getActivity()).get(SessionDataViewModel.class);

        if (!((Button) view).getText().toString().equals("")) {
            return;
        } else if (checkWinner()) {
            return;
        }

        String buttonID = view.getResources().getResourceEntryName(view.getId());

        int gameStatePointer = Integer.parseInt(buttonID.substring(10, buttonID.length()));

        if (playerOneActive) {
            ((Button) view).setText("X");
            ((Button) view).setTextSize(30);
            ((Button) view).setTextColor(Color.parseColor("#FFA500"));
            updatePlayerText(1,sessionData.playerOne.getValue().getPlayerName().toString());

            gamestate[gameStatePointer] = 0;
            undoMoves.push(gameStatePointer);

        } else {
            ((Button) view).setText("O");
            ((Button) view).setTextSize(30);
            ((Button) view).setTextColor(Color.parseColor("#0000FF"));
            updatePlayerText(2,sessionData.playerTwo.getValue().getPlayerName().toString());

            gamestate[gameStatePointer] = 1;
            undoMoves.push(gameStatePointer);

        }

        rounds++;

        if (checkWinner()) {
            if (playerOneActive) {
                Toast.makeText(getActivity(), sessionData.playerOne.getValue().getPlayerName() + " wins!", Toast.LENGTH_SHORT).show();

                sessionData.playerOne.getValue().setWins(sessionData.playerOne.getValue().getWins() + 1);
                sessionData.playerOne.getValue().setGamesPlayed(sessionData.playerOne.getValue().getGamesPlayed() + 1);

                sessionData.playerTwo.getValue().setLosses(sessionData.playerTwo.getValue().getLosses() + 1);
                sessionData.playerTwo.getValue().setGamesPlayed(sessionData.playerTwo.getValue().getGamesPlayed() + 1);
            } else {
                Toast.makeText(getActivity(), sessionData.playerTwo.getValue().getPlayerName() + " wins!", Toast.LENGTH_SHORT).show();

                sessionData.playerTwo.getValue().setWins(sessionData.playerTwo.getValue().getWins() + 1);
                sessionData.playerTwo.getValue().setGamesPlayed(sessionData.playerTwo.getValue().getGamesPlayed() + 1);

                sessionData.playerOne.getValue().setLosses(sessionData.playerOne.getValue().getLosses() + 1);
                sessionData.playerOne.getValue().setGamesPlayed(sessionData.playerOne.getValue().getGamesPlayed() + 1);
            }
        }
        else if (rounds == 9) {
            Toast.makeText(getActivity(), "No winner, Game result = Draw.", Toast.LENGTH_SHORT).show();

            sessionData.playerOne.getValue().setDraws(sessionData.playerOne.getValue().getDraws() + 1);
            sessionData.playerTwo.getValue().setDraws(sessionData.playerTwo.getValue().getDraws() + 1);

            sessionData.playerOne.getValue().setGamesPlayed(sessionData.playerOne.getValue().getGamesPlayed() + 1);
            sessionData.playerTwo.getValue().setGamesPlayed(sessionData.playerTwo.getValue().getGamesPlayed() + 1);
        }
        else {
            playerOneActive = !playerOneActive;
        }

    }

    private boolean checkWinner() {
        boolean winnerDetected = false;
        for (int[] winningPositions : winningPositions) {
            if (gamestate[winningPositions[0]] == gamestate[winningPositions[1]] &&
                    gamestate[winningPositions[1]] == gamestate[winningPositions[2]] &&
                    gamestate[winningPositions[0]] != 2) {

                winnerDetected = true;

            }
        }

        return winnerDetected;
    }
}