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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import java.util.Stack;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameBoardFrag4x4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameBoardFrag4x4 extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int[][] winningPositions4x4 = {{0,1,2},{1,2,3}, {4,5,6}, {5,6,7}, {8,9,10}, {9,10,11}, {12,13,14}, {13,14,15},
            {0,4,8}, {0,5,10}, {1,5,9}, {1,6,11}, {2,5,8}, {2,6,10}, {3,6,9}, {3,7,11}, {4,8,12}, {4,9,14}, {5,9,13}, {5,10,15}, {6,9,12},
            {6,10,14}, {7,10,13}, {7,11,15}};

    private static final int[][] winningPositions4x4_4InARow = {{0,1,2,3},{4,5,6,7},{8,9,10,11}, {12,13,14,15}, {0,4,8,12}, {1,5,9,13},
            {2,6,10,14}, {3,7,11,15}, {0, 5, 10, 15}, {3, 6, 9, 12}};

    private int[] gamestate = {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
    private Button[] buttonList = new Button[16];

    private TextView playerTurn;
    private Stack<Integer> undoMoves = new Stack<Integer>();
    private int rounds;
    private boolean playerOneActive;
    ArrayList avatarArray = new ArrayList<Integer>();

    ArrayList markerArray = new ArrayList<Integer>();

    private boolean fourInARowWinCond = false;

    private boolean playerVsPlayer = true; //Activation boolean for bot game or playervsplayer game **REMEMBER TO CHANGE WHEN SETTINGS PAGE IS IMPLEMENTED
    private MutableLiveData<Boolean> botsTurn;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GameBoardFrag4x4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameBoardFrag4x4.
     */
    // TODO: Rename and change types and number of parameters
    public static GameBoardFrag4x4 newInstance(String param1, String param2) {
        GameBoardFrag4x4 fragment = new GameBoardFrag4x4();
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
        View rootView = inflater.inflate(R.layout.fragment_game_board_frag4x4, container, false);
        SessionDataViewModel sessionData = new ViewModelProvider(getActivity()).get(SessionDataViewModel.class);
        Button returnButton = rootView.findViewById(R.id.returnToMenuButton4x4);
        Button resetButton = rootView.findViewById(R.id.resetButton4x4);
        Button undoButton = rootView.findViewById(R.id.Undo);
        ImageView p1Avatar = rootView.findViewById(R.id.p1Avatar);
        ImageView p2Avatar = rootView.findViewById(R.id.p2Avatar);
        playerTurn = rootView.findViewById(R.id.Status);
        Random rand = new Random();

        avatarArray.add(R.drawable.avatar1);
        avatarArray.add(R.drawable.avatar2);
        avatarArray.add(R.drawable.avatar3);
        avatarArray.add(R.drawable.avatar4);
        avatarArray.add(R.drawable.avatar5);
        avatarArray.add(R.drawable.avatar6);

        markerArray.add(R.drawable.marker1);
        markerArray.add(R.drawable.marker2);
        markerArray.add(R.drawable.marker3);
        markerArray.add(R.drawable.marker4);
        markerArray.add(R.drawable.marker5);
        markerArray.add(R.drawable.marker6);

        p1Avatar.setImageResource((Integer) avatarArray.get(sessionData.playerOne.getValue().getAvatarID()));
        p2Avatar.setImageResource((Integer) avatarArray.get(sessionData.playerTwo.getValue().getAvatarID()));
        playerTurn.setText(sessionData.playerOne.getValue().getPlayerName() + "'s turn");

        playerTurn = rootView.findViewById(R.id.Status);
        botsTurn = new MutableLiveData<Boolean>();
        botsTurn.setValue(false);

        playerTurn.setText(sessionData.playerOne.getValue().getPlayerName().toString() +"'s turn");

        gamestate = new int[]{2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2}; //Reset Game

        if (sessionData.getGameMode() == 1) {
            playerVsPlayer = false;
        }
        if (sessionData.getWinCondition() == 1) {
            fourInARowWinCond = true;
        }

        buttonList[0] = rootView.findViewById(R.id.button4x4_0);
        buttonList[1] = rootView.findViewById(R.id.button4x4_1);
        buttonList[2] = rootView.findViewById(R.id.button4x4_2);
        buttonList[3] = rootView.findViewById(R.id.button4x4_3);
        buttonList[4] = rootView.findViewById(R.id.button4x4_4);
        buttonList[5] = rootView.findViewById(R.id.button4x4_5);
        buttonList[6] = rootView.findViewById(R.id.button4x4_6);
        buttonList[7] = rootView.findViewById(R.id.button4x4_7);
        buttonList[8] = rootView.findViewById(R.id.button4x4_8);
        buttonList[9] = rootView.findViewById(R.id.button4x4_9);
        buttonList[10] = rootView.findViewById(R.id.button4x4_10);
        buttonList[11] = rootView.findViewById(R.id.button4x4_11);
        buttonList[12] = rootView.findViewById(R.id.button4x4_12);
        buttonList[13] = rootView.findViewById(R.id.button4x4_13);
        buttonList[14] = rootView.findViewById(R.id.button4x4_14);
        buttonList[15] = rootView.findViewById(R.id.button4x4_15);

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
                            emptyButtonIndex = rand.nextInt(16);
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
                gamestate = new int[]{2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2}; //Reset Game
                for (int i = 0; i < buttonList.length; i++) {
                    buttonList[i].setText("");
                    buttonList[i].setBackgroundColor(0x000000);
                }
                playerTurn.setText(sessionData.playerOne.getValue().getPlayerName().toString() +"'s turn");
                playerTurn.setTextColor(Color.parseColor("#7EFB02"));
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
                        playerTurn.setTextColor(Color.parseColor("#FB0202"));
                    }
                    else {
                        playerTurn.setText("Bot's turn");
                    }
                    playerOneActive = !playerOneActive;
                }
                else {
                    playerTurn.setText(sessionData.playerOne.getValue().getPlayerName().toString() +"'s turn");
                    playerTurn.setTextColor(Color.parseColor("#7EFB02"));
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
                if (sessionData.playerOne.getValue().getMarkerID() != 100) {
                    ((Button)view).setBackgroundResource((Integer) markerArray.get(sessionData.playerOne.getValue().getMarkerID()));
                }
                else {
                    ((Button) view).setText("X");
                    ((Button) view).setTextSize(30);
                    ((Button) view).setTextColor(Color.parseColor("#FFA500"));
                }

                playerTurn.setText(sessionData.playerTwo.getValue().getPlayerName().toString() + "'s turn");
                playerTurn.setTextColor(Color.parseColor("#7EFB02"));

                gamestate[gameStatePointer] = 0;
                undoMoves.push(gameStatePointer);

            } else {
                if (sessionData.playerTwo.getValue().getMarkerID() != 100) {
                    ((Button)view).setBackgroundResource((Integer) markerArray.get(sessionData.playerTwo.getValue().getMarkerID()));
                }
                else {
                    ((Button) view).setText("0");
                    ((Button) view).setTextSize(30);
                    ((Button) view).setTextColor(Color.parseColor("#0000FF"));
                }

                playerTurn.setText(sessionData.playerOne.getValue().getPlayerName().toString() + "'s turn");
                playerTurn.setTextColor(Color.parseColor("#FB0202"));

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
            } else if (rounds == 16) {
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
                if (sessionData.playerOne.getValue().getMarkerID() != 100) {
                    ((Button)view).setBackgroundResource((Integer) markerArray.get(sessionData.playerOne.getValue().getMarkerID()));
                }
                else {
                    ((Button) view).setText("X");
                    ((Button) view).setTextSize(30);
                    ((Button) view).setTextColor(Color.parseColor("#FFA500"));
                }

                playerTurn.setText("Bot's turn");
                playerTurn.setTextColor(Color.parseColor("#7EFB02"));

                gamestate[gameStatePointer] = 0;
                undoMoves.push(gameStatePointer);

            } else {
                ((Button) view).setText("O");
                ((Button) view).setTextSize(30);
                ((Button) view).setTextColor(Color.parseColor("#0000FF"));
                playerTurn.setText(sessionData.playerOne.getValue().getPlayerName().toString() + "'s turn");
                playerTurn.setTextColor(Color.parseColor("#FB0202"));

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
            } else if (rounds == 16) {
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
        if (!fourInARowWinCond) {
            for (int[] winningPositions : winningPositions4x4) {
                if (gamestate[winningPositions[0]] == gamestate[winningPositions[1]] &&
                        gamestate[winningPositions[1]] == gamestate[winningPositions[2]] &&
                        gamestate[winningPositions[0]] != 2) {

                    winnerDetected = true;

                }
            }
        } else {
            for (int[] winningPositions : winningPositions4x4_4InARow) {
                if (gamestate[winningPositions[0]] == gamestate[winningPositions[1]] &&
                        gamestate[winningPositions[1]] == gamestate[winningPositions[2]] &&
                        gamestate[winningPositions[2]] == gamestate[winningPositions[3]] &&
                        gamestate[winningPositions[0]] != 2) {

                    winnerDetected = true;

                }
            }
        }

        return winnerDetected;
    }
}