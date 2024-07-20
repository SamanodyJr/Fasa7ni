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
    TextView title;
    ImageView  image;
    ImageButton remove;
    Button add;
    TextView name,mutual;

    public RequestViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface)
    {
        super(itemView);
        image = itemView.findViewById(R.id.Place_Image);
        name = itemView.findViewById(R.id.Name);
        add = itemView.findViewById(R.id.add_friend_btn);
        mutual = itemView.findViewById(R.id.mutual);
        remove = itemView.findViewById(R.id.remove_friend_btn);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(recyclerViewInterface!=null){
                    int position = getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION){
                        recyclerViewInterface.onItemClicked(3,position);
                    }
                }
            }
        });

    }
}