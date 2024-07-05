package com.example.fasa7ni;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendViewHolder>
{
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    List<Friend> friends;
    public FriendAdapter(RecyclerViewInterface recyclerViewInterface, Context context, List<Friend> friends)
    {
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
        this.friends = friends;
    }
    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.friends_row,parent,false);
        return new FriendViewHolder(view, recyclerViewInterface);
    }
    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position)
    {
        holder.name.setText(friends.get(position).getName());
        holder.image.setImageResource(friends.get(position).getImage());
        holder.mutual.setText(friends.get(position).getMutual());
        holder.remove.setImageResource(R.drawable.x_sign);

        holder.remove.setOnClickListener(v ->
        {
        friends.remove(position);
        notifyItemRemoved(position);
        });
    }
    @Override
    public int getItemCount()
    {
        return friends.size();
    }

}