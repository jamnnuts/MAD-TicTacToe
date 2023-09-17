package com.example.mad_tictactoe;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Stack;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameBoardFrag5x5#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameBoardFrag5x5 extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static final int[][] winningPositions5x5 = {{0,1,2}, {1,2,3}, {2,3,4}, {5,6,7}, {6,7,8}, {7,8,9}, {10,11,12},
            {11,12,13}, {12,13,14}, {15,16,17}, {16,17,18}, {17,18,19}, {20,21,22}, {21,22,23}, {22,23,24}, {0,5,10},
            {5,10,15}, {10,15,20}, {1,6,11}, {6,11,16}, {11,16,21}, {2,7,12}, {7,12,17}, {12,17,22}, {3,8,13},
            {8,13,18}, {13,18,23}, {4,9,14}, {9,14,19}, {14,19,24}, {2,6,10}, {3,7,11}, {7,11,15}, {4,8,12},
            {8,12,16}, {12,16,20}, {9,13,17}, {13,17,21}, {14,18,22}, {2,8,14}, {1,7,13}, {7,13,19}, {0,6,12},
            {6,12,18}, {12,18,24}, {5,11,17}, {11,17,23}, {10,16,22}};

    public static final int[][] winningPositions5x5_4inARow = {{0,1,2,3}, {1,2,3,4}, {5,6,7,8}, {6,7,8,9}, {10,11,12,13},
            {11,12,13,14}, {15,16,17,18}, {16,17,18,19}, {20,21,22,23}, {21,22,23,24}, {0,5,10,15}, {5,10,15,20}, {1,6,11,16},
            {6,11,16,21}, {2,7,12,17}, {7,12,17,22}, {3,8,13,18}, {8,13,18,23}, {4,9,14,19}, {9,14,19,24}, {3,7,11,15},
            {4,8,12,16}, {8,12,16,20}, {9,13,17,21}, {1,7,13,19}, {0,6,12,18}, {6,12,18,24}, {5,11,17,23}};

    public static final int[][] winningPositions5x5_5inARow = {{0,1,2,3,4}, {5,6,7,8,9}, {10,11,12,13,14}, {15,16,17,18,19},
            {20,21,22,23,24}, {0,5,10,15,20}, {1,6,11,16,21}, {2,7,12,17,22}, {3,8,13,18,23}, {4,9,14,19,24}, {0,6,12,18,24},
            {4,8,12,16,20}};

    private int[] gamestate = {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
    private Button[] buttonList = new Button[25];

    private TextView playerTurn;
    private Stack<Integer> undoMoves = new Stack<Integer>();
    private int rounds;
    private boolean playerOneActive;

    private boolean fourInARowWinCond = false;
    private boolean fiveInARowWinCond = false;

    private boolean playerVsPlayer = true; //Activation boolean for bot game or playervsplayer game **REMEMBER TO CHANGE WHEN SETTINGS PAGE IS IMPLEMENTED
    private MutableLiveData<Boolean> botsTurn;
    public GameBoardFrag5x5() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameBoardFrag5x5.
     */
    // TODO: Rename and change types and number of parameters
    public static GameBoardFrag5x5 newInstance(String param1, String param2) {
        GameBoardFrag5x5 fragment = new GameBoardFrag5x5();
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
        View rootView = inflater.inflate(R.layout.fragment_game_board_frag5x5, container, false);
        SessionDataViewModel sessionData = new ViewModelProvider(getActivity()).get(SessionDataViewModel.class);
        Button returnButton = rootView.findViewById(R.id.returnToMenuButton5x5);
        Button resetButton = rootView.findViewById(R.id.resetButton5x5);
        Button undoButton = rootView.findViewById(R.id.Undo);
        Random rand = new Random();

        playerTurn = rootView.findViewById(R.id.Status);
        botsTurn = new MutableLiveData<Boolean>();
        botsTurn.setValue(false);

        playerTurn.setText(sessionData.playerOne.getValue().getPlayerName().toString() +"'s turn");

        gamestate = new int[]{2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2}; //Reset Game

        if (sessionData.getGameMode() == 1) {
            playerVsPlayer = false;
        }
        if (sessionData.getWinCondition() == 1) {
            fourInARowWinCond = true;
            fiveInARowWinCond = false;
        }
        else if (sessionData.getWinCondition() == 2) {
            fourInARowWinCond = false;
            fiveInARowWinCond = true;
        }


        buttonList[0] = rootView.findViewById(R.id.button5x5_0);
        buttonList[1] = rootView.findViewById(R.id.button5x5_1);
        buttonList[2] = rootView.findViewById(R.id.button5x5_2);
        buttonList[3] = rootView.findViewById(R.id.button5x5_3);
        buttonList[4] = rootView.findViewById(R.id.button5x5_4);
        buttonList[5] = rootView.findViewById(R.id.button5x5_5);
        buttonList[6] = rootView.findViewById(R.id.button5x5_6);
        buttonList[7] = rootView.findViewById(R.id.button5x5_7);
        buttonList[8] = rootView.findViewById(R.id.button5x5_8);
        buttonList[9] = rootView.findViewById(R.id.button5x5_9);
        buttonList[10] = rootView.findViewById(R.id.button5x5_10);
        buttonList[11] = rootView.findViewById(R.id.button5x5_11);
        buttonList[12] = rootView.findViewById(R.id.button5x5_12);
        buttonList[13] = rootView.findViewById(R.id.button5x5_13);
        buttonList[14] = rootView.findViewById(R.id.button5x5_14);
        buttonList[15] = rootView.findViewById(R.id.button5x5_15);
        buttonList[16] = rootView.findViewById(R.id.button5x5_16);
        buttonList[17] = rootView.findViewById(R.id.button5x5_17);
        buttonList[18] = rootView.findViewById(R.id.button5x5_18);
        buttonList[19] = rootView.findViewById(R.id.button5x5_19);
        buttonList[20] = rootView.findViewById(R.id.button5x5_20);
        buttonList[21] = rootView.findViewById(R.id.button5x5_21);
        buttonList[22] = rootView.findViewById(R.id.button5x5_22);
        buttonList[23] = rootView.findViewById(R.id.button5x5_23);
        buttonList[24] = rootView.findViewById(R.id.button5x5_24);

        for (int i = 0; i < buttonList.length; i++) {
            buttonList[i].setOnClickListener(this);
        }
        playerOneActive = true;
        rounds = 0;

        if (!playerVsPlayer) {
            botsTurn.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    if (botsTurn.getValue() == true) {
                        int emptyButtonIndex = 0;
                        boolean emptyIndexFound = false;
                        while (!emptyIndexFound) { //Search for an empty grid cell
                            emptyButtonIndex = rand.nextInt(25);
                            if (gamestate[emptyButtonIndex] == 2) {
                                emptyIndexFound = true;
                            }
                        }
                        buttonList[emptyButtonIndex].performClick();
                        botsTurn.setValue(false);
                    }
                }
            });
        }

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
                gamestate = new int[]{2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2}; //Reset Game
                for (int i = 0; i < buttonList.length; i++) {
                    buttonList[i].setText("");
                }
                playerTurn.setText(sessionData.playerOne.getValue().getPlayerName().toString() +"'s turn");
                playerOneActive = true;
                botsTurn.setValue(false);
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

                if (playerOneActive) {
                    if (playerVsPlayer) {
                        playerTurn.setText(sessionData.playerTwo.getValue().getPlayerName().toString() + "'s turn");
                    }
                    else {
                        playerTurn.setText("Bot's turn");
                    }
                    playerOneActive = !playerOneActive;
                }
                else {
                    playerTurn.setText(sessionData.playerOne.getValue().getPlayerName().toString() +"'s turn");
                    playerOneActive = !playerOneActive;
                }
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

        if (playerVsPlayer) { //Player Mode
            if (playerOneActive) {
                ((Button) view).setText("X");
                ((Button) view).setTextSize(30);
                ((Button) view).setTextColor(Color.parseColor("#FFA500"));
                playerTurn.setText(sessionData.playerTwo.getValue().getPlayerName().toString() + "'s turn");

                gamestate[gameStatePointer] = 0;
                undoMoves.push(gameStatePointer);

            } else {
                ((Button) view).setText("O");
                ((Button) view).setTextSize(30);
                ((Button) view).setTextColor(Color.parseColor("#0000FF"));
                playerTurn.setText(sessionData.playerOne.getValue().getPlayerName().toString() + "'s turn");

                gamestate[gameStatePointer] = 1;
                undoMoves.push(gameStatePointer);

            }

            rounds++;

            if (checkWinner()) {
                if (playerOneActive) {
                    Toast.makeText(getActivity(), sessionData.playerOne.getValue().getPlayerName() + " wins!", Toast.LENGTH_SHORT).show();
                    playerTurn.setText("Game over!");

                    sessionData.playerOne.getValue().setWins(sessionData.playerOne.getValue().getWins() + 1);
                    sessionData.playerOne.getValue().setGamesPlayed(sessionData.playerOne.getValue().getGamesPlayed() + 1);

                    sessionData.playerTwo.getValue().setLosses(sessionData.playerTwo.getValue().getLosses() + 1);
                    sessionData.playerTwo.getValue().setGamesPlayed(sessionData.playerTwo.getValue().getGamesPlayed() + 1);
                } else {
                    Toast.makeText(getActivity(), sessionData.playerTwo.getValue().getPlayerName() + " wins!", Toast.LENGTH_SHORT).show();
                    playerTurn.setText("Game over!");

                    sessionData.playerTwo.getValue().setWins(sessionData.playerTwo.getValue().getWins() + 1);
                    sessionData.playerTwo.getValue().setGamesPlayed(sessionData.playerTwo.getValue().getGamesPlayed() + 1);

                    sessionData.playerOne.getValue().setLosses(sessionData.playerOne.getValue().getLosses() + 1);
                    sessionData.playerOne.getValue().setGamesPlayed(sessionData.playerOne.getValue().getGamesPlayed() + 1);
                }
            } else if (rounds == 25) {
                Toast.makeText(getActivity(), "No winner, Game result = Draw.", Toast.LENGTH_SHORT).show();
                playerTurn.setText("Game over!");

                sessionData.playerOne.getValue().setDraws(sessionData.playerOne.getValue().getDraws() + 1);
                sessionData.playerTwo.getValue().setDraws(sessionData.playerTwo.getValue().getDraws() + 1);

                sessionData.playerOne.getValue().setGamesPlayed(sessionData.playerOne.getValue().getGamesPlayed() + 1);
                sessionData.playerTwo.getValue().setGamesPlayed(sessionData.playerTwo.getValue().getGamesPlayed() + 1);
            } else {
                playerOneActive = !playerOneActive;
            }
        }

        else { //Player vs AI mode
            if (playerOneActive) {
                ((Button) view).setText("X");
                ((Button) view).setTextSize(30);
                ((Button) view).setTextColor(Color.parseColor("#FFA500"));
                playerTurn.setText("Bot's turn");

                gamestate[gameStatePointer] = 0;
                undoMoves.push(gameStatePointer);

            } else {
                ((Button) view).setText("O");
                ((Button) view).setTextSize(30);
                ((Button) view).setTextColor(Color.parseColor("#0000FF"));
                playerTurn.setText(sessionData.playerOne.getValue().getPlayerName().toString() + "'s turn");

                gamestate[gameStatePointer] = 1;
                undoMoves.push(gameStatePointer);

            }

            rounds++;

            if (checkWinner()) {
                if (playerOneActive) {
                    Toast.makeText(getActivity(), sessionData.playerOne.getValue().getPlayerName() + " wins!", Toast.LENGTH_SHORT).show();
                    playerTurn.setText("Game over!");

                    sessionData.playerOne.getValue().setWins(sessionData.playerOne.getValue().getWins() + 1);
                    sessionData.playerOne.getValue().setGamesPlayed(sessionData.playerOne.getValue().getGamesPlayed() + 1);
                } else {
                    Toast.makeText(getActivity(),  "Bot wins!", Toast.LENGTH_SHORT).show();
                    playerTurn.setText("Game over!");

                    sessionData.playerOne.getValue().setLosses(sessionData.playerOne.getValue().getLosses() + 1);
                    sessionData.playerOne.getValue().setGamesPlayed(sessionData.playerOne.getValue().getGamesPlayed() + 1);
                }
            } else if (rounds == 25) {
                Toast.makeText(getActivity(), "No winner, Game result = Draw.", Toast.LENGTH_SHORT).show();
                playerTurn.setText("Game over!");

                sessionData.playerOne.getValue().setDraws(sessionData.playerOne.getValue().getDraws() + 1);
                sessionData.playerOne.getValue().setGamesPlayed(sessionData.playerOne.getValue().getGamesPlayed() + 1);
            }
            else if (playerOneActive) {
                playerOneActive = !playerOneActive;
                botsTurn.setValue(true);
            }
            else {
                playerOneActive = !playerOneActive;
            }
        }
    }

    private boolean checkWinner() {
        boolean winnerDetected = false;
        if (fourInARowWinCond) {
            for (int[] winningPositions : winningPositions5x5_4inARow ){
                if (gamestate[winningPositions[0]] == gamestate[winningPositions[1]] &&
                        gamestate[winningPositions[1]] == gamestate[winningPositions[2]] &&
                        gamestate[winningPositions[2]] == gamestate[winningPositions[3]] &&
                        gamestate[winningPositions[0]] != 2) {

                    winnerDetected = true;
                }
            }
        } else if (fiveInARowWinCond) {
            for (int[] winningPositions : winningPositions5x5_5inARow) {
                if (gamestate[winningPositions[0]] == gamestate[winningPositions[1]] &&
                        gamestate[winningPositions[1]] == gamestate[winningPositions[2]] &&
                        gamestate[winningPositions[2]] == gamestate[winningPositions[3]] &&
                        gamestate[winningPositions[3]] == gamestate[winningPositions[4]] &&
                        gamestate[winningPositions[0]] != 2) {

                    winnerDetected = true;
                }
            }
        } else {
            for (int[] winningPositions : winningPositions5x5) {
                if (gamestate[winningPositions[0]] == gamestate[winningPositions[1]] &&
                        gamestate[winningPositions[1]] == gamestate[winningPositions[2]] &&
                        gamestate[winningPositions[0]] != 2) {

                    winnerDetected = true;

                }
            }
        }

        return winnerDetected;
    }
}