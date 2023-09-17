package com.example.mad_tictactoe;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayerCreationFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayerCreationFrag extends Fragment {
    ArrayList<Integer> avatarArray;

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

        avatarArray = new ArrayList<>();
        avatarArray.add(R.drawable.avatar1);
        avatarArray.add(R.drawable.avatar2);
        avatarArray.add(R.drawable.avatar3);
        avatarArray.add(R.drawable.avatar4);
        avatarArray.add(R.drawable.avatar5);
        avatarArray.add(R.drawable.avatar6);

        View rootView = inflater.inflate(R.layout.fragment_player_creation, container, false);
        SessionDataViewModel sessionData = new ViewModelProvider(getActivity()).get(SessionDataViewModel.class);

        RecyclerView rv = rootView.findViewById(R.id.recView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(rootView.getContext(),LinearLayoutManager.HORIZONTAL, false));
        AvatarAdapter adapter = new AvatarAdapter(avatarArray);
        rv.setAdapter(adapter);

        Button returnButton = rootView.findViewById(R.id.returnButton2);
        Button player1SaveButton = rootView.findViewById(R.id.player1saveButton);
        Button player2SaveButton = rootView.findViewById(R.id.player2saveButton);
        EditText playerName = rootView.findViewById(R.id.playerName);
        TextView p1Light = rootView.findViewById(R.id.firstPlayerLight);
        TextView p2Light = rootView.findViewById(R.id.secondPlayerLight);
        TextView p1NameIndicator = rootView.findViewById(R.id.playerOneName);
        TextView p2NameIndicator = rootView.findViewById(R.id.playerTwoName);



        player1SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   sessionData.playerOne.getValue().setPlayerName(playerName.getText().toString());
                   p1Light.setTextColor(Color.GREEN);
                   p1NameIndicator.setText(sessionData.playerOne.getValue().getPlayerName().toString());
                   Toast.makeText(getActivity(), "Player 1 created: " + sessionData.playerOne.getValue().getPlayerName(), Toast.LENGTH_SHORT).show();
            }
        });
        player2SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionData.playerTwo.getValue().setPlayerName(playerName.getText().toString());
                p2Light.setTextColor(Color.GREEN);
                p2NameIndicator.setText(sessionData.playerTwo.getValue().getPlayerName().toString());
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