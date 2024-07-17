package com.example.fasa7ni;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestViewHolder>
{
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    List<Request> requests;
    public RequestAdapter(RecyclerViewInterface recyclerViewInterface, Context context, List<Request> requests)
    {
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
        this.requests = requests;
    }
    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.request_row,parent,false);
        return new RequestViewHolder(view,recyclerViewInterface);
    }
    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position)
    {
        holder.name.setText(requests.get(position).getName());
        holder.mutual.setText(Integer.toString(requests.get(position).getMutual()));
        holder.remove.setImageResource(R.drawable.x_sign);
        holder.add.setText("ADD");

        String imagePath = "file:///android_asset/" + requests.get(position).getImage();

        Glide.with(context)
                .load(imagePath)
                .into(holder.image);

        holder.remove.setOnClickListener(v ->
        {
            requests.remove(position);
            notifyItemRemoved(position);
            // remove from database
        });

        holder.add.setOnClickListener(v ->
        {
//            requests.add(position);
//            notifyItemRemoved(position);
            // add to database
        });
    }
    @Override
    public int getItemCount()
    {
        return requests.size();
    }

}