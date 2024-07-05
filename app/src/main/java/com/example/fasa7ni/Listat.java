package com.example.fasa7ni;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class Listat extends AppCompatActivity implements View.OnClickListener, RecyclerViewInterface
{
    private String email;
    private ImageButton Event;
    private ImageButton Home;
    private ImageButton Friends;
    private ImageButton Recommender;
    private ImageButton Profile;
    private Button[] List_Buttons = new android.widget.Button[5];
    private boolean[] List_Status= new boolean[5];

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Start();
    }
    private void Start()
    {

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            email = bundle.getString("Email");
        }

        List<Place> places_list = new ArrayList<Place>();
        places_list.add(new Place("Place1","AUC","Friday",R.drawable.paintball_image));
        places_list.add(new Place("Place2","AUC","Friday",R.drawable.paintball_image));
        places_list.add(new Place("Place3","AUC","Friday",R.drawable.paintball_image));
        places_list.add(new Place("Place4","AUC","Friday",R.drawable.paintball_image));
        places_list.add(new Place("Place5","AUC","Friday",R.drawable.paintball_image));
        places_list.add(new Place("Place6","AUC","Friday",R.drawable.paintball_image));
        RecyclerView recyclerView2 = findViewById(R.id.places_recyclerview);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setAdapter(new PlaceAdapter(this,getApplicationContext(), places_list));

        Event = findViewById(R.id.small_event_btn);
        Home = findViewById(R.id.small_home_btn);
        Friends = findViewById(R.id.small_friends_btn);
        Recommender = findViewById(R.id.small_recommender_btn);
        Profile = findViewById(R.id.profile_btn);

        Event.setOnClickListener(this);
        Home.setOnClickListener(this);
        Friends.setOnClickListener(this);
        Recommender.setOnClickListener(this);
        Profile.setOnClickListener(this);

        int[] ListbuttonIDs =
                {
                        R.id.list_filter_btn, R.id.sports_filter_btn, R.id.food_filter_btn, R.id.activity_filter_btn,
                        R.id.sightseeing_filter_btn
                };

        for (int i = 0; i < 5; i++) {
            List_Buttons[i] = findViewById(ListbuttonIDs[i]);
            List_Buttons[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId()==Event.getId())
            Go_Event();

        else if (v.getId()==Home.getId())
            Go_Home();

        else if (v.getId()==Friends.getId())
            Go_Friends();

        else if (v.getId()==Recommender.getId())
            Go_Recommender();

        else if (v.getId()==Profile.getId())
            Go_Profile();

        else
            Go_Filter(v.getId());

    }

    private void Go_Home()
    {
        Intent L_H = new Intent(Listat.this, Home.class);
        L_H.putExtra("Email", email);
        startActivity(L_H);
    }

    private void Go_Friends()
    {
        Intent L_F = new Intent(Listat.this, Friends.class);
        L_F.putExtra("Email", email);
        startActivity(L_F);
    }

    private void Go_Profile()
    {
        Intent L_P = new Intent(Listat.this, Profile.class);
        L_P.putExtra("Email", email);
        startActivity(L_P);
    }

    private void Go_Recommender()
    {
        Intent L_R = new Intent(Listat.this, Recommender.class);
        L_R.putExtra("Email", email);
        startActivity(L_R);
    }

    private void Go_Event()
    {
        Intent L_E = new Intent(Listat.this, Events.class);
        L_E.putExtra("Email", email);
        startActivity(L_E);
    }

    public void Go_Filter(int id) {
        for (int i = 0; i < 5; i++) {
            if (id == List_Buttons[i].getId())
            {
                List_Status[i] = !List_Status[i];

                if (List_Status[i])
                {
                    List_Buttons[i].setBackgroundColor(Color.parseColor("#2C2B2B"));
                    List_Buttons[i].setTextColor(Color.parseColor("#41F2F8"));
                }
                else
                {
                    List_Buttons[i].setBackgroundColor(Color.parseColor("#41F2F8"));
                    List_Buttons[i].setTextColor(Color.parseColor("#2C2B2B"));
                }
            }
            else
            {
                List_Buttons[i].setBackgroundColor(Color.parseColor("#41F2F8"));
                List_Buttons[i].setTextColor(Color.parseColor("#2C2B2B"));
            }

        }
    }

    @Override
    public void onItemClicked(int recycleViewID, int position) {
        Intent intent = new Intent(this, PlaceProfile.class);
        startActivity(intent);
    }
}