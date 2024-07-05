package com.example.fasa7ni;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventViewHolder extends RecyclerView.ViewHolder
{
    ImageView  image;
    TextView name,location,hostName, openingHours;

    public EventViewHolder(@NonNull View itemView)
    {
        super(itemView);
        image = itemView.findViewById(R.id.Place_Image);
        name = itemView.findViewById(R.id.Name);
        location = itemView.findViewById(R.id.Location);
        hostName = itemView.findViewById(R.id.Hostname);
        openingHours = itemView.findViewById(R.id.Opening_Hours);

    }
}