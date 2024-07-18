
package com.example.fasa7ni;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceViewHolder>
{
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    List<Place> places;
    public PlaceAdapter(RecyclerViewInterface recyclerViewInterface, Context context, List<Place> places)
    {
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
        this.places = places;
    }
    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.list_row,parent,false);
        return new PlaceViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position)
    {
        holder.name.setText(places.get(position).getName());
        String imagePath = "file:///android_asset/" + places.get(position).getImage();
        Glide.with(context)
                .load(imagePath)
                .into(holder.image);
        holder.location.setText(places.get(position).getLocation());
    }
    @Override
    public int getItemCount() {
        return places.size();
    }


}