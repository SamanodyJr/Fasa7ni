package com.example.fasa7ni;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Friends extends AppCompatActivity implements View.OnClickListener
{
    private String email;
    private ImageButton List;
    private ImageButton Home;
    private ImageButton Profile;
    private ImageButton Events;
    private ImageButton Recommender;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends);
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

        List = findViewById(R.id.small_list_btn);
        Events = findViewById(R.id.small_event_btn);
        Home = findViewById(R.id.small_home_btn);
        Recommender = findViewById(R.id.small_recommender_btn);
        Profile = findViewById(R.id.profile_btn);

        List.setOnClickListener(this);
        Events.setOnClickListener(this);
        Home.setOnClickListener(this);
        Recommender.setOnClickListener(this);
        Profile.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==List.getId())
            Go_List();

        else if (v.getId()==Events.getId())
            Go_Events();

        else if (v.getId()==Home.getId())
            Go_Home();

        else if (v.getId()==Recommender.getId())
            Go_Recommender();

        else if (v.getId()==Profile.getId())
            Go_Profile();

        else
            return;

    }

    private void Go_List()
    {
        Intent F_L = new Intent(Friends.this, Listat.class);
        F_L.putExtra("Email", email);
        startActivity(F_L);
    }

    private void Go_Events()
    {
        Intent F_E = new Intent(Friends.this, Events.class);
        F_E.putExtra("Email", email);
        startActivity(F_E);
    }

    private void Go_Home()
    {
        Intent F_H = new Intent(Friends.this, Home.class);
        F_H.putExtra("Email", email);
        startActivity(F_H);
    }

    private void Go_Recommender()
    {
        Intent F_R = new Intent(Friends.this, Recommender.class);
        F_R.putExtra("Email", email);
        startActivity(F_R);
    }

    private void Go_Profile()
    {
        Intent F_P = new Intent(Friends.this, Profile.class);
        F_P.putExtra("Email", email);
        startActivity(F_P);
    }
}