package com.example.fasa7ni;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    private Context context;
    private List<Friend> friends;

    public FriendAdapter(RecyclerViewInterface recyclerViewInterface, Context context, List<Friend> friends) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
        this.friends = friends;
    }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.friends_row, parent, false);
        return new FriendViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {
        holder.name.setText(friends.get(position).getName());
        holder.mutual.setText(Integer.toString(friends.get(position).getMutual())+ " Mutual Friends");
        holder.remove.setImageResource(R.drawable.x_sign);

        String imagePath = "file:///android_asset/" + friends.get(position).getImage();

        Glide.with(context)
                .load(imagePath)
                .into(holder.image);

        holder.remove.setOnClickListener(v -> {
            friends.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, friends.size());
        });
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }
}