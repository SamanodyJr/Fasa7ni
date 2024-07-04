package com.example.fasa7ni;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;

import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Home extends AppCompatActivity implements View.OnClickListener
{
    private String email;
    private ImageButton List;
    private ImageButton Events;
    private ImageButton Friends;
    private ImageButton Recommender;
    private ImageButton Profile;

    private static final String TAG = "HomeActivity";


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

        List = findViewById(R.id.small_list_btn);
        Events = findViewById(R.id.small_event_btn);
        Friends = findViewById(R.id.small_friends_btn);
        Recommender = findViewById(R.id.small_recommender_btn);
        Profile = findViewById(R.id.profile_btn);

        List.setOnClickListener(this);
        Events.setOnClickListener(this);
        Friends.setOnClickListener(this);
        Recommender.setOnClickListener(this);
        Profile.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==List.getId())
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
        Log.d(TAG, "Go_List: Starting List activity");


        Intent H_L = new Intent(Home.this, List.class);
//        H_L.putExtra("Email", email);
        startActivity(H_L);

//        Log.d(TAG, "Go_List: List activity started with email: " + email);


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


}