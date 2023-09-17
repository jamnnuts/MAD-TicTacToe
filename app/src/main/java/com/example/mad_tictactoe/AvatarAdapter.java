package com.example.mad_tictactoe;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AvatarAdapter extends RecyclerView.Adapter<AvatarVH> {

    ArrayList<Integer> avatarArray;
    public AvatarAdapter(ArrayList<Integer>avatarArray) {this.avatarArray = avatarArray;}
    @NonNull
    @Override
    public AvatarVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.avatar_layout,parent,false);

        return new AvatarVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AvatarVH holder, int position) {
        int avatarReference = avatarArray.get(position);
        holder.avatar.setImageResource(avatarReference);
    }

    @Override
    public int getItemCount() {return avatarArray.size(); }
}
