package com.example.mad_tictactoe;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
/* MUTABLE LIVE DATA VALUES
*  ClickedFragment: Details which fragment to load for MainActivity
*  0 = default
*  1 = MainMenu
*  2 = GameBoard3x3
*  3 = Player Creation
*  4 = Stats Page
*  5 = Settings page
*
*  gridSize: Chooses between gameboards of sizes: 3x3, 4x4 or 5x5
*  0 = 3x3 = default
*  1 = 4x4
*  2 = 5x5
*
*  winCondition: Chooses between win conditions of: 3 in a row, 4 in a row or 5 in a row
*  0 = 3 in a row = default
*  1 = 4 in a row
*  2 = 5 in a row
* */
public class SessionDataViewModel extends ViewModel {

    public MutableLiveData<Integer> clickedFragment;
    public MutableLiveData<Player> playerOne;
    public MutableLiveData<Player> playerTwo;
    public MutableLiveData<Integer> gridSize;
    public MutableLiveData<Integer> winCondition;

    public SessionDataViewModel() {
        clickedFragment = new MutableLiveData<Integer>();
        gridSize = new MutableLiveData<Integer>();
        winCondition = new MutableLiveData<Integer>();
        playerOne = new MutableLiveData<Player>();
        playerTwo = new MutableLiveData<Player>();

        clickedFragment.setValue(0);
        gridSize.setValue(0);
        winCondition.setValue(0);
        playerOne.setValue(new Player("Player 1"));
        playerTwo.setValue(new Player("Player 2"));
    }

    public int getClickedFragment() {
        return clickedFragment.getValue();
    }
    public void setClickedFragment(int value) {
        clickedFragment.setValue(value);
    }

    public int getGridSize() { return gridSize.getValue();}
    public void setGridSize(int value) { gridSize.setValue(value);}
    public int getWinCondition() {return winCondition.getValue();}
    public void setWinCondition(int value) {gridSize.setValue(value);}

}
