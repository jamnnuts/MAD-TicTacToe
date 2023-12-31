package com.example.mad_tictactoe;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import android.os.CountDownTimer;

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
    public static final String occupiedTag = "Occupied";
    public static final String emptyTag = "Empty";

    public static final int[][] winningPositions3x3= {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8},{0,4,8},{2,4,6}};
    private  int[] gamestate = {2,2,2,2,2,2,2,2,2};
    private Button[] buttonList = new Button[9];
    private TextView playerTurn;
    private TextView p1Timer;
    private TextView available_move_counter;
    private Stack<Integer> undoMoves = new Stack<Integer>();
    private int rounds;
    private int counter = 30;
    private int startTimer = 0;

    private int available_moves = 9;

    private MutableLiveData<Boolean> forfeitTimerWin;
    private boolean playerVsPlayer = true; //Activation boolean for bot game or playervsplayer game **REMEMBER TO CHANGE WHEN SETTINGS PAGE IS IMPLEMENTED
    private boolean playerOneActive;
    ArrayList avatarArray = new ArrayList<Integer>();
    ArrayList markerArray = new ArrayList<Integer>();


    private MutableLiveData<Boolean> botsTurn;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game_board3x3, container, false);
        SessionDataViewModel sessionData = new ViewModelProvider(getActivity()).get(SessionDataViewModel.class);
        Button returnButton = rootView.findViewById(R.id.returnToMenuButton3x3);
        Button resetButton = rootView.findViewById(R.id.resetButton3x3);
        Button undoButton = rootView.findViewById(R.id.UndoButton);



        p1Timer = rootView.findViewById(R.id.timer1);
        ImageView p1Avatar = rootView.findViewById(R.id.p1Avatar);
        ImageView p2Avatar = rootView.findViewById(R.id.p2Avatar);
        Random rand = new Random();

        available_move_counter = rootView.findViewById(R.id.availablemoves);
        playerTurn = rootView.findViewById(R.id.Status);
        botsTurn = new MutableLiveData<Boolean>();
        forfeitTimerWin = new MutableLiveData<Boolean>();
        botsTurn.setValue(false);
        forfeitTimerWin.setValue(false);

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
        playerTurn.setText(sessionData.playerOne.getValue().getPlayerName().toString() +"'s turn");



        gamestate = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2}; //Reset Game

        if (sessionData.getGameMode() == 1) {
            playerVsPlayer = false;
        }

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
            buttonList[i].setTag(emptyTag);
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
                            emptyButtonIndex = rand.nextInt(9);
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
                resetButton.performClick();
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
                    buttonList[i].setBackgroundColor(0x000000);
                    buttonList[i].setTag(emptyTag);
                }
                playerTurn.setText(sessionData.playerOne.getValue().getPlayerName().toString() +"'s turn");
                playerTurn.setTextColor(Color.parseColor("#7EFB02"));
                playerOneActive = true;
                botsTurn.setValue(false);
                forfeitTimerWin.setValue(false);
                startTimer = 0;
                counter = 30;
                available_moves = 9;
                available_move_counter.setText(Integer.toString(available_moves));
            }
        });

        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rounds == 0) {
                    return;
                }
                else if (checkWinner() || forfeitTimerWin.getValue()) {
                    return;
                }

                int lastMove = undoMoves.pop();
                buttonList[lastMove].setText("");
                buttonList[lastMove].setTag(emptyTag);
                buttonList[lastMove].setBackgroundColor(Color.TRANSPARENT);
                gamestate[lastMove] = 2;
                rounds--;
                available_moves++;
                available_move_counter.setText(Integer.toString(available_moves));
                counter = 30;

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

        forfeitTimerWin.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (forfeitTimerWin.getValue() == true) {
                    if (playerVsPlayer) {
                        if (playerOneActive) {
                            Toast.makeText(getActivity(), "Time Ran Out, " + sessionData.playerTwo.getValue().getPlayerName() + " wins!", Toast.LENGTH_SHORT).show();
                            playerTurn.setText("Game over!");
                            sessionData.playerOne.getValue().setLosses(sessionData.playerOne.getValue().getLosses() + 1);
                            sessionData.playerOne.getValue().setGamesPlayed(sessionData.playerOne.getValue().getGamesPlayed() + 1);
                            sessionData.playerTwo.getValue().setWins(sessionData.playerTwo.getValue().getWins() + 1);
                            sessionData.playerTwo.getValue().setGamesPlayed(sessionData.playerTwo.getValue().getGamesPlayed() + 1);

                        } else {
                            Toast.makeText(getActivity(), "Time Ran Out, " + sessionData.playerOne.getValue().getPlayerName() + " wins!", Toast.LENGTH_SHORT).show();
                            playerTurn.setText("Game over!");
                            sessionData.playerTwo.getValue().setLosses(sessionData.playerTwo.getValue().getLosses() + 1);
                            sessionData.playerTwo.getValue().setGamesPlayed(sessionData.playerTwo.getValue().getGamesPlayed() + 1);
                            sessionData.playerOne.getValue().setWins(sessionData.playerOne.getValue().getWins() + 1);
                            sessionData.playerOne.getValue().setGamesPlayed(sessionData.playerOne.getValue().getGamesPlayed() + 1);
                        }

                    }
                    else {
                        Toast.makeText(getActivity(), "Time Ran Out, Bot wins!", Toast.LENGTH_SHORT).show();
                        playerTurn.setText("Game over!");
                        sessionData.playerOne.getValue().setLosses(sessionData.playerOne.getValue().getLosses() + 1);
                        sessionData.playerOne.getValue().setGamesPlayed(sessionData.playerOne.getValue().getGamesPlayed() + 1);
                    }
                }
            }
        });

        return rootView;
    }
    @Override
    public void onClick(View view) {
        SessionDataViewModel sessionData = new ViewModelProvider(getActivity()).get(SessionDataViewModel.class);

        if (!((Button) view).getText().toString().equals("") || ((Button) view).getTag().equals(occupiedTag)) {
            return;
        } else if (checkWinner() || forfeitTimerWin.getValue()) {
            return;
        }

        setTimer();

        String buttonID = view.getResources().getResourceEntryName(view.getId());

        int gameStatePointer = Integer.parseInt(buttonID.substring(10, buttonID.length()));


        counter = 30;     //resets timeer
        available_moves--;
        available_move_counter.setText(Integer.toString(available_moves));

        if (playerVsPlayer) { //Player Mode
            if (playerOneActive) {
                if (sessionData.playerOne.getValue().getMarkerID() != 100) {
                    ((Button)view).setBackgroundResource((Integer) markerArray.get(sessionData.playerOne.getValue().getMarkerID()));
                    ((Button)view).setTag(occupiedTag);
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
                    ((Button)view).setTag(occupiedTag);
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
                available_move_counter.setText(Integer.toString(available_moves));

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
            } else if (rounds == 9) {
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
                    ((Button)view).setTag(occupiedTag);
                }
                else {
                    ((Button) view).setText("X");
                    ((Button) view).setTextSize(30);
                    ((Button) view).setTextColor(Color.parseColor("#FFA500"));
                }

                playerTurn.setText("Bot's turn");
                playerTurn.setTextColor(Color.parseColor("#7EFB02"));
                available_move_counter.setText(Integer.toString(available_moves));

                gamestate[gameStatePointer] = 0;
                undoMoves.push(gameStatePointer);
            }
            else {
                ((Button) view).setText("O");
                ((Button) view).setTextSize(30);
                ((Button) view).setTextColor(Color.parseColor("#0000FF"));
                playerTurn.setText(sessionData.playerOne.getValue().getPlayerName().toString() + "'s turn");
                playerTurn.setTextColor(Color.parseColor("#FB0202"));
                available_move_counter.setText(Integer.toString(available_moves));


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
            } else if (rounds == 9) {
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
        for (int[] winningPositions : winningPositions3x3) {
            if (gamestate[winningPositions[0]] == gamestate[winningPositions[1]] &&
                    gamestate[winningPositions[1]] == gamestate[winningPositions[2]] &&
                    gamestate[winningPositions[0]] != 2) {

                winnerDetected = true;

            }
        }

        return winnerDetected;
    }

    private void setTimer() {
        if(startTimer == 0) {
            new CountDownTimer(30000, 1000) {
                @Override
                public void onTick(long l) {
                    if (checkWinner() || startTimer == 0) {
                        this.cancel();
                    }
                    p1Timer.setText(String.valueOf(counter));
                    {
                        p1Timer.setText(String.valueOf(counter));
                    }
                    counter--;

                }
                @Override
                public void onFinish() {
                    p1Timer.setText("DONE!");
                    forfeitTimerWin.setValue(true);
                }

            }
            .start();
            startTimer = 1;
        }
    }
}