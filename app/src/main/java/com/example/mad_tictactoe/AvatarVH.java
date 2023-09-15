package com.example.mad_tictactoe;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NonNls;

public class AvatarVH extends RecyclerView.ViewHolder {
    public ImageView avatar;

    public AvatarVH(@NonNull View itemView) {
        super(itemView);
        avatar = itemView.findViewById(R.id.avatarImage);
    }
}
