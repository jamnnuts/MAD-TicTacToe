package com.example.mad_tictactoe;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/* GET CLICKED VALUES
    - 0: default
    - 1: Main Menu
    - 2: Load 3x3 Game Board
    - 3: Load 4x4 Game Board
    - 4: Load 5x5 Game Board
    - 5: Load Leaderboard
 */
public class SessionDataViewModel extends ViewModel {


    public MutableLiveData<Integer> clickedFragment;



    public SessionDataViewModel() {
        clickedFragment = new MutableLiveData<Integer>();

        clickedFragment.setValue(0);
    }

    public int getClickedFragment() {
        return clickedFragment.getValue();
    }

    public void setClickedFragment(int value) {
        clickedFragment.setValue(value);
    }

}
