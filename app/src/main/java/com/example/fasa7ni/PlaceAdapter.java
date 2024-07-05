
package com.example.fasa7ni;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceViewHolder>
{
    Context context;
    List<Place> places;
    public PlaceAdapter(Context context, List<Place> places)
    {
        this.context = context;
        this.places = places;
    }
    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.list_row,parent,false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position)
    {
        holder.name.setText(places.get(position).getName());
        holder.image.setImageResource(places.get(position).getImage());
        holder.location.setText(places.get(position).getLocation());
    }
    @Override
    public int getItemCount() {
        return places.size();
    }


}
