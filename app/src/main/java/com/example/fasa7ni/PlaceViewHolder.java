package com.example.fasa7ni;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlaceViewHolder extends RecyclerView.ViewHolder
{
    ImageView image;
    TextView name,location, openingHours;

    public PlaceViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface)
    {
        super(itemView);
        image = itemView.findViewById(R.id.Place_Image);
        name = itemView.findViewById(R.id.Name);
        location = itemView.findViewById(R.id.Location);
        openingHours = itemView.findViewById(R.id.Opening_Hours);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(recyclerViewInterface != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        recyclerViewInterface.onItemClicked(0,position);
                    }
                }
            }
        });
    }
    public void bind(Place place) {
        name.setText(place.getName());
        location.setText(place.getLocation());
        openingHours.setText(place.getOpeningTime());
//        image.setImageResource(place.getImage());
    }
}


