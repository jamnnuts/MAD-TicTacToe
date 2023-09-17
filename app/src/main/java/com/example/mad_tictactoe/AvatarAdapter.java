package com.example.mad_tictactoe;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AvatarAdapter extends RecyclerView.Adapter<AvatarAdapter.AvatarVH> {

    ArrayList<Integer> avatarArray;
    IAvatarRecycler mListener;

    public int avatarReference = 0;
    public AvatarAdapter(ArrayList<Integer>avatarArray, IAvatarRecycler mListener) {
        this.avatarArray = avatarArray;
        this.mListener = mListener;
    }
    @NonNull
    @Override
    public AvatarVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.avatar_layout,parent,false);


        return new AvatarVH(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AvatarVH holder, int position) {
        avatarReference = avatarArray.get(position);
        holder.avatar.setImageResource(avatarReference);
    }

    @Override
    public int getItemCount() {return avatarArray.size(); }

    public class AvatarVH extends RecyclerView.ViewHolder {
        public ImageView avatar;
        IAvatarRecycler mListener;

        public AvatarVH(@NonNull View itemView, IAvatarRecycler mListener) {
            super(itemView);
            this.mListener = mListener;
            avatar = itemView.findViewById(R.id.avatarImage);

            avatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.exampleMethod(avatarReference);
                }
            });
        }
    }

    interface IAvatarRecycler{
        void exampleMethod(int avatarID);
    }
}
