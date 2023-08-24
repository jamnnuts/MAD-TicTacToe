package com.example.mad_tictactoe;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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
