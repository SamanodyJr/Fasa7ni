package com.example.fasa7ni;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestViewHolder>
{
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    List<Request> requests;
    EventProfile activity;
    Friends activity2;

    public RequestAdapter(RecyclerViewInterface recyclerViewInterface, Context context, List<Request> requests)
    {
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
        this.requests = requests;
    }

    public void setActivity(EventProfile activity)
    {
        this.activity = activity;
    }
    public void setActivity2(Friends activity2) { this.activity2 = activity2;}

    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.request_row, parent, false);
        return new RequestViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position)
    {
        Log.d("RequestAdapter", "Here");
        Request request = requests.get(position);
        holder.name.setText(requests.get(position).getName());
        holder.mutual.setText(Integer.toString(requests.get(position).getMutual()) + " Mutual Friends");
        holder.add.findViewById(R.id.add_friend_btn);
        holder.remove.setImageResource(R.drawable.x_sign);
        holder.add.setText("ADD");

        String imagePath = "file:///android_asset/" + requests.get(position).getImage();

        Glide.with(context)
                .load(imagePath)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.image);

        //getIsRequest() -> 0->friends, 1->request to attend fos7a, 2->fos7a attendee
        if (request.getIsRequest()==0)
        {    //friends
            Log.d("RequestAdapter", "Here2");
            holder.add.setVisibility(View.VISIBLE);
            holder.remove.setVisibility(View.VISIBLE);
            holder.add.setOnClickListener(v ->
            {
                activity2.Accept_Friend(request.getName());
            });

            holder.remove.setOnClickListener(v ->
            {
                activity2.Remove_Friend(request.getName());
            });

        }
        else if(request.getIsRequest()==1)
        {
            holder.add.setText("ACCEPT");
            holder.add.setVisibility(View.VISIBLE);
            holder.remove.setVisibility(View.GONE);
            holder.mutual.setVisibility(View.GONE);
            holder.add.setOnClickListener(v ->
            {
                activity.updateRequest(requests.get(position).getName());
                requests.remove(position);
                notifyItemRemoved(position);
            });
        }
        else if (request.getIsRequest()==4)
        {    //search friends
            Log.d("RequestAdapter", "Here3");
            holder.add.setVisibility(View.VISIBLE);
            holder.remove.setVisibility(View.INVISIBLE);
            holder.add.setOnClickListener(v ->
            {
                activity2.RequestFriend(request.getName());
            });

        }
        else
        {     //fos7a attendee
            holder.add.setVisibility(View.GONE);
            holder.remove.setVisibility(View.GONE);
            holder.mutual.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return requests.size();
    }

}

