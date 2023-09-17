package com.example.mad_tictactoe;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link settings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class settings extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public settings() {
        // Required empty public constructor
    }
    public static settings newInstance(String param1, String param2) {
        settings fragment = new settings();
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

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        Button pvp = view.findViewById(R.id.PVP);
        Button pve = view.findViewById(R.id.PVE);

        Button b3x3 = view.findViewById(R.id.board3x3);
        Button b4x4 = view.findViewById(R.id.board4x4);
        Button b5x5 = view.findViewById(R.id.board5x5);

        Button w3x3 = view.findViewById(R.id.board3x3);
        Button w4x4 = view.findViewById(R.id.board4x4);
        Button w5x5 = view.findViewById(R.id.board5x5);

        Button xomarker = view.findViewById(R.id.xomarkers);
        Button custommarker = view.findViewById(R.id.customiconmarkers);

        b3x3.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), MainActivity.class);
            }
        });
        b4x4.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), MainActivity.class);
            }
        });
        b5x5.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), MainActivity.class);
            }
        });

        w3x3.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), MainActivity.class);
            }
        });
        w4x4.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), MainActivity.class);
            }
        });
        w5x5.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), MainActivity.class);
            }
        });

        xomarker.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), MainActivity.class);
            }
        });
        custommarker.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), MainActivity.class);
            }
        });

        pvp.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), MainActivity.class);
            }
        });

        pve.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), MainActivity.class);
            }
        });



        return inflater.inflate(R.layout.fragment_settings, container, false);
    }
}