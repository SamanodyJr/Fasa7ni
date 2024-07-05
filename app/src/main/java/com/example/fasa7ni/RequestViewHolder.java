package com.example.fasa7ni;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RequestViewHolder extends RecyclerView.ViewHolder
{
    ImageView  image;
    ImageButton remove;
    Button add;
    TextView name,mutual;

    public RequestViewHolder(@NonNull View itemView)
    {
        super(itemView);
        image = itemView.findViewById(R.id.Place_Image);
        name = itemView.findViewById(R.id.Name);
        add = itemView.findViewById(R.id.add_friend_btn);
        mutual = itemView.findViewById(R.id.mutual);
        remove = itemView.findViewById(R.id.remove_friend_btn);
    }
}