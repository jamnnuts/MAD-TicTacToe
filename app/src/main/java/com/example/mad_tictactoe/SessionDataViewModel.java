package com.example.mad_tictactoe;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SessionDataViewModel extends ViewModel {

    public MutableLiveData<Integer> clickedFragment;
    public MutableLiveData<Player> playerOne;
    public MutableLiveData<Player> playerTwo;

    public SessionDataViewModel() {
        clickedFragment = new MutableLiveData<Integer>();
        playerOne = new MutableLiveData<Player>();
        playerTwo = new MutableLiveData<Player>();

        clickedFragment.setValue(0);
        playerOne.setValue(new Player("Player 1"));
        playerTwo.setValue(new Player("Player 2"));
    }

    public int getClickedFragment() {
        return clickedFragment.getValue();
    }
    public void setClickedFragment(int value) {
        clickedFragment.setValue(value);
    }
    public Player getPlayerOne() {return playerOne.getValue();}
    public void setPlayerOne(Player inPlayer) {playerOne.setValue(inPlayer);}
    public Player getPlayerTwo() {return playerTwo.getValue();}
    public void setPlayerTwo(Player inPlayer){playerTwo.setValue(inPlayer);}
}
