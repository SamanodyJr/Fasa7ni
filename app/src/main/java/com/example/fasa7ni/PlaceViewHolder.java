package com.example.fasa7ni;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

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

        itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(recyclerViewInterface != null)
                {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION)
                    {
                        recyclerViewInterface.onItemClicked(0,position);
                    }
                }
            }
        });
    }
    public void bind(Place place, Context context)
    {
        name.setText(place.getName());
        location.setText(place.getLocation());
        String imagePath = "file:///android_asset/" + place.getImage();

        Glide.with(context)
                .load(imagePath)
                .apply(RequestOptions.circleCropTransform())
                .into(image);
    }
}


