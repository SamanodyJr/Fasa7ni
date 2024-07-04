package com.example.fasa7ni;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class List extends AppCompatActivity implements View.OnClickListener
{
    private String email;
    private ImageButton Home;
    private ImageButton Events;
    private ImageButton Friends;
    private ImageButton Recommender;
    private ImageButton Profile;

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

        Events = findViewById(R.id.small_event_btn);
        Home = findViewById(R.id.small_home_btn);
        Friends = findViewById(R.id.small_friends_btn);
        Recommender = findViewById(R.id.small_recommender_btn);
        Profile = findViewById(R.id.profile_btn);

        Events.setOnClickListener(this);
        Home.setOnClickListener(this);
        Friends.setOnClickListener(this);
        Recommender.setOnClickListener(this);
        Profile.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == Events.getId())
            Go_Events();

        else if (v.getId() == Home.getId())
            Go_Home();

        else if (v.getId() == Friends.getId())
            Go_Friends();

        else if (v.getId() == Recommender.getId())
            Go_Recommender();

        else if (v.getId()==Profile.getId())
            Go_Profile();

        else
            return;

    }

    private void Go_Events() {
        Intent L_E = new Intent(List.this, Events.class);
        L_E.putExtra("Email", email);
        startActivity(L_E);
    }

    private void Go_Home() {
        Intent L_H = new Intent(List.this, Home.class);
        L_H.putExtra("Email", email);
        startActivity(L_H);
    }

    private void Go_Friends() {
        Intent L_F = new Intent(List.this, Friends.class);
        L_F.putExtra("Email", email);
        startActivity(L_F);
    }

    private void Go_Recommender() {
        Intent L_R = new Intent(List.this, Recommender.class);
        L_R.putExtra("Email", email);
        startActivity(L_R);
    }

    private void Go_Profile()
    {
        Intent L_P = new Intent(List.this, Profile.class);
        L_P.putExtra("Email", email);
        startActivity(L_P);
    }
}