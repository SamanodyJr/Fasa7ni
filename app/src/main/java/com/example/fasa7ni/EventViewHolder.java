package com.example.fasa7ni;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class EventViewHolder extends RecyclerView.ViewHolder
{
    ImageView  image;
    TextView name,location,hostName, openingHours;

    public EventViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface)
    {
        super(itemView);
        image = itemView.findViewById(R.id.Place_Image);
        name = itemView.findViewById(R.id.Name);
        location = itemView.findViewById(R.id.Location);
        hostName = itemView.findViewById(R.id.Hostname);
        openingHours = itemView.findViewById(R.id.Opening_Hours);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(recyclerViewInterface != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        recyclerViewInterface.onItemClicked(1,position);
                    }
                }
            }
        });
    }
    public void bind(Event event, Context context)
    {
        name.setText(event.getName());
        location.setText(event.getLocation());
        hostName.setText(event.getHostName());
        openingHours.setText(event.getDate());
       String imagePath = "file:///android_asset/" + event.getImage();

        Glide.with(context)
                .load(imagePath)
                .apply(RequestOptions.circleCropTransform())
                .into(image);

    }
}