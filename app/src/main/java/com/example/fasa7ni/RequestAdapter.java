package com.example.fasa7ni;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestViewHolder>
{
    Context context;
    List<Request> requests;
    public RequestAdapter(Context context, List<Request> requests)
    {
        this.context = context;
        this.requests = requests;
    }
    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.request_row,parent,false);
        return new RequestViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position)
    {
        holder.name.setText(requests.get(position).getName());
        holder.image.setImageResource(requests.get(position).getImage());
        holder.mutual.setText(requests.get(position).getMutual());
        holder.remove.setImageResource(R.drawable.x_sign);
        holder.add.setText("ADD");

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