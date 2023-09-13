package com.example.mad_tictactoe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayerCreationFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayerCreationFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PlayerCreationFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment player_creation.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayerCreationFrag newInstance(String param1, String param2) {
        PlayerCreationFrag fragment = new PlayerCreationFrag();
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
        View rootView = inflater.inflate(R.layout.fragment_player_creation, container, false);
        SessionDataViewModel sessionData = new ViewModelProvider(getActivity()).get(SessionDataViewModel.class);
        Button returnButton = rootView.findViewById(R.id.returnButton2);
        Button player1SaveButton = rootView.findViewById(R.id.player1saveButton);
        Button player2SaveButton = rootView.findViewById(R.id.player2saveButton);
        EditText playerName = rootView.findViewById(R.id.playerName);

        player1SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   sessionData.playerOne.getValue().setPlayerName(playerName.getText().toString());
                   Toast.makeText(getActivity(), "Player 1 created: " + sessionData.playerOne.getValue().getPlayerName(), Toast.LENGTH_SHORT).show();
            }
        });
        player2SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionData.playerTwo.getValue().setPlayerName(playerName.getText().toString());
                Toast.makeText(getActivity(), "Player 2 created: " + sessionData.playerTwo.getValue().getPlayerName(), Toast.LENGTH_SHORT).show();
            }
        });
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionData.setClickedFragment(1);
            }
        });

        return rootView;
    }

}