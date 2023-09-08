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
    }

    public int getClickedFragment() {
        return clickedFragment.getValue();
    }

    public void setClickedFragment(int value) {
        clickedFragment.setValue(value);
    }
    public MutableLiveData<Player> getPlayerOne() {return playerOne;}
    public void setPlayerOne(MutableLiveData<Player> playerOne) {this.playerOne = playerOne;}
    public MutableLiveData<Player> getPlayerTwo() {return playerTwo;}
    public void setPlayerTwo(MutableLiveData<Player> playerTwo) {this.playerTwo = playerTwo;}
}
