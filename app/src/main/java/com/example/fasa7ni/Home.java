package com.example.fasa7ni;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity implements View.OnClickListener,RecyclerViewInterface
{
    private String email;
    private ImageButton Listat;
    private ImageButton Events;
    private ImageButton Friends;
    private ImageButton Recommender;
    private ImageButton Profile;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
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

        List<Event> list = new ArrayList<Event>();
        List<Place> places_list = new ArrayList<Place>();
        list.add(new Event("Yalla Koraa","AUC","Friday","Heggi",R.drawable.paintball_image));
        list.add(new Event("Yalla Balling","AUC","Friday","Heggi",R.drawable.paintball_image));
        list.add(new Event("Yalla ay 7aga","AUC","Friday","Heggi",R.drawable.paintball_image));
        places_list.add(new Place("Place1","AUC","Friday",R.drawable.paintball_image));
        places_list.add(new Place("Place2","AUC","Friday",R.drawable.paintball_image));
        places_list.add(new Place("Place3","AUC","Friday",R.drawable.paintball_image));
        places_list.add(new Place("Place4","AUC","Friday",R.drawable.paintball_image));
        places_list.add(new Place("Place5","AUC","Friday",R.drawable.paintball_image));
        places_list.add(new Place("Place6","AUC","Friday",R.drawable.paintball_image));

        RecyclerView recyclerView = findViewById(R.id.upcoming_fosa7_recyclerView);
        RecyclerView recyclerView2 = findViewById(R.id.intrests_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new EventAdapter(this,getApplicationContext(),list));
        recyclerView2.setAdapter(new PlaceAdapter(this,getApplicationContext(),places_list));

        Listat = findViewById(R.id.small_list_btn);
        Events = findViewById(R.id.small_event_btn);
        Friends = findViewById(R.id.small_friends_btn);
        Recommender = findViewById(R.id.small_recommender_btn);
        Profile = findViewById(R.id.profile_btn);

        Listat.setOnClickListener(this);
        Events.setOnClickListener(this);
        Friends.setOnClickListener(this);
        Recommender.setOnClickListener(this);
        Profile.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if(v.getId()==Listat.getId())
            Go_List();

        else if (v.getId()==Events.getId())
            Go_Events();

        else if (v.getId()==Friends.getId())
            Go_Friends();

        else if (v.getId()==Recommender.getId())
            Go_Recommender();

        else if (v.getId()==Profile.getId())
            Go_Profile();

        else
            return;

    }

    private void Go_List()
    {
        Intent H_L = new Intent(Home.this, Listat.class);
        H_L.putExtra("Email", email);
        startActivity(H_L);
    }

    private void Go_Events()
    {
        Intent H_E = new Intent(Home.this, Events.class);
        H_E.putExtra("Email", email);
        startActivity(H_E);
    }

    private void Go_Friends()
    {
        Intent H_F = new Intent(Home.this, Friends.class);
        H_F.putExtra("Email", email);
        startActivity(H_F);
    }

    private void Go_Recommender()
    {
        Intent H_R = new Intent(Home.this, Recommender.class);
        H_R.putExtra("Email", email);
        startActivity(H_R);
    }

    private void Go_Profile()
    {
        Intent H_P = new Intent(Home.this, Profile.class);
        H_P.putExtra("Email", email);
        startActivity(H_P);
    }


    @Override
    public void onItemClicked(int recycleViewID, int position) {
        if (recycleViewID == 0){ //Place
            Intent intent = new Intent(this, PlaceProfile.class);
            startActivity(intent);
        } else if (recycleViewID == 1) { //Event
            Intent intent = new Intent(this, EventProfile.class);
            startActivity(intent);
        }

    }
}