package com.example.fasa7ni;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder>
{
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    List<Event> fos7as;
    public EventAdapter(RecyclerViewInterface recyclerViewInterface, Context context, List<Event> fos7as)
    {
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
        this.fos7as = fos7as;
    }
    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.events_row,parent,false);
        return new EventViewHolder(view,recyclerViewInterface);
    }
    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        holder.name.setText(fos7as.get(position).getName());
        holder.image.setImageResource(fos7as.get(position).getImage());
        holder.location.setText(fos7as.get(position).getLocation());
        holder.hostName.setText(fos7as.get(position).getHostName());
        holder.openingHours.setText(fos7as.get(position).getDate());    }
    @Override
    public int getItemCount()
    {
        return fos7as.size();
    }


}