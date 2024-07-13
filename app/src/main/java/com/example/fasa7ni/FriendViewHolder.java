package com.example.fasa7ni;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FriendViewHolder extends RecyclerView.ViewHolder {
    ImageView image;
    ImageButton remove;
    TextView name, mutual;

    public FriendViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
        super(itemView);
        image = itemView.findViewById(R.id.Place_Image);
        name = itemView.findViewById(R.id.Name);
        mutual = itemView.findViewById(R.id.mutual);
        remove = itemView.findViewById(R.id.remove_friend_btn);

        // Handle click on the remove button
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerViewInterface != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        recyclerViewInterface.onItemClicked(2, position); // Remove item from list
                    }
                }
            }
        });

        // Handle click on the entire item (except remove button)
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerViewInterface != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        recyclerViewInterface.onItemClicked(1, position); // View profile
                    }
                }
            }
        });
    }
}