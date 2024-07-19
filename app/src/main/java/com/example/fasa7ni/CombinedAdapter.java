package com.example.fasa7ni;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CombinedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_EVENT = 2;
    private static final int TYPE_PLACE = 3;

    private List<Object> items;
    private final RecyclerViewInterface recyclerViewInterface;
    private Context context;

    public CombinedAdapter(Context context, List<Object> items, RecyclerViewInterface recyclerViewInterface)
    {
        this.context = context;
        this.items = items;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @Override
    public int getItemViewType(int position)
    {
        if (items.get(position) instanceof Event)
            return TYPE_EVENT;
        else if (items.get(position) instanceof Place)
            return TYPE_PLACE;
        return -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        if (viewType == TYPE_EVENT)
        {
            View view = LayoutInflater.from(context).inflate(R.layout.events_row, parent, false);
            return new EventViewHolder(view, recyclerViewInterface);
        }
        else if (viewType == TYPE_PLACE)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
            return new PlaceViewHolder(view, recyclerViewInterface);
        }
        throw new RuntimeException("Unknown view type");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        if (holder instanceof EventViewHolder)
        {
            ((EventViewHolder) holder).bind((Event) items.get(position), context);
        }
        else if (holder instanceof PlaceViewHolder)
        {
            ((PlaceViewHolder) holder).bind((Place) items.get(position), context);
        }
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

}