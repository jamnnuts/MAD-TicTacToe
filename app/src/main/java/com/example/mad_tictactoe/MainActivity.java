package com.example.mad_tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    MainMenuFrag mainMenuFragment = new MainMenuFrag();
    GameBoardFrag gameBoardFrag = new GameBoardFrag();
    PlayerCreationFrag playerCreationFrag = new PlayerCreationFrag();

    GameBoardFrag4x4 gameBoardFrag4x4 = new GameBoardFrag4x4();
    GameBoardFrag5x5 gameBoardFrag5x5 = new GameBoardFrag5x5();

    StatFrag statFrag = new StatFrag();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadMainMenu();

        SessionDataViewModel sessionData = new ViewModelProvider(this).get(SessionDataViewModel.class);
        /*View Model for observing which fragment the player has chosen to load. */

        sessionData.clickedFragment.observe(this, new Observer<Integer>() {
            /*Observing changes in clickedFragment val which will decide which fragment to load */
            @Override
            public void onChanged(Integer integer) {
                if (sessionData.getClickedFragment() == 1) {
                    loadMainMenu();
                }
                else if (sessionData.getClickedFragment() == 2) {
                    loadGameBoard5x5();
                }
                else if (sessionData.getClickedFragment() == 3) {
                    loadPlayerCreation();
                }
                else if (sessionData.getClickedFragment() == 4) {
                    loadPlayerStats();
                }
                else if (sessionData.getClickedFragment() == 5) {
                    loadGameBoard4x4();
                }
                else if (sessionData.getClickedFragment() == 6) {
                    loadGameBoard5x5();
                }
            }
        });

    }


    /* Loads Main Menu using a fragment container (NEEDS TO BE REVISED UPON ADDITION OF OTHER FRAGMENTS) */
    private void loadMainMenu() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.first_f_container);

        if (frag == null) {
            fm.beginTransaction().add(R.id.first_f_container, mainMenuFragment).commit();
        }
        else {
            fm.beginTransaction().replace(R.id.first_f_container, mainMenuFragment).commit();
        }

    }

    /* Loads the Game Board using Fragment Container (MAY NEED TO REVISE LAYOUT FORMAT OR FRAME CONTAINER) */
    private void loadGameBoard() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.first_f_container);

        if (frag == null) {
            fm.beginTransaction().add(R.id.first_f_container, gameBoardFrag).commit();
        }
        else {
            fm.beginTransaction().replace(R.id.first_f_container, gameBoardFrag).commit();
        }
    }

    private void loadGameBoard4x4() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.first_f_container);

        if (frag == null) {
            fm.beginTransaction().add(R.id.first_f_container, gameBoardFrag4x4).commit();
        }
        else {
            fm.beginTransaction().replace(R.id.first_f_container, gameBoardFrag4x4).commit();
        }
    }
    private void loadGameBoard5x5() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.first_f_container);

        if (frag == null) {
            fm.beginTransaction().add(R.id.first_f_container, gameBoardFrag5x5).commit();
        }
        else {
            fm.beginTransaction().replace(R.id.first_f_container, gameBoardFrag5x5).commit();
        }
    }

    private void loadPlayerCreation() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.first_f_container);

        if (frag == null) {
            fm.beginTransaction().add(R.id.first_f_container, playerCreationFrag).commit();
        }
        else {
            fm.beginTransaction().replace(R.id.first_f_container, playerCreationFrag).commit();
        }
    }

    private void loadPlayerStats() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.first_f_container);

        if (frag == null) {
            fm.beginTransaction().add(R.id.first_f_container, statFrag).commit();
        }
        else {
            fm.beginTransaction().replace(R.id.first_f_container, statFrag).commit();
        }
    }
}