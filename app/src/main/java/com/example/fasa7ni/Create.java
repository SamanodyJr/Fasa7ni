package com.example.fasa7ni;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Create extends AppCompatActivity implements View.OnClickListener
{
    private String username;
    private ImageButton Listat;
    private ImageButton Events;
    private ImageButton Friends;
    private ImageButton Recommender;
    private ImageButton Profile;
    private ImageButton Home;
    private Button[] CE_Buttons = new android.widget.Button[2];
    private boolean[] CE_Status= new boolean[2];

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Start();
    }

    private void Start()
    {
        Listat = findViewById(R.id.small_list_btn);
        Events = findViewById(R.id.small_event_btn);
        Friends = findViewById(R.id.small_friends_btn);
        Recommender = findViewById(R.id.small_recommender_btn);
        Profile = findViewById(R.id.profile_btn);
        Home = findViewById(R.id.small_home_btn);

        Listat.setOnClickListener(this);
        Events.setOnClickListener(this);
        Friends.setOnClickListener(this);
        Recommender.setOnClickListener(this);
        Profile.setOnClickListener(this);
        Home.setOnClickListener(this);

        int[] CEbuttonIDs =
                {
                        R.id.private_info_select, R.id.public_info_select
                };

        for (int i = 0; i < 2; i++) {
            CE_Buttons[i] = findViewById(CEbuttonIDs[i]);
            CE_Buttons[i].setOnClickListener(this);
        }

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

        else if (v.getId()==Home.getId())
            Go_Home();

        else
            Go_Filter(v.getId());

    }

    private void Go_List()
    {
        Intent CE_L = new Intent(Create.this, Listat.class);
        CE_L.putExtra("Username", username);
        startActivity(CE_L);
    }

    private void Go_Events()
    {
        Intent CE_E = new Intent(Create.this, Events.class);
        CE_E.putExtra("Username", username);
        startActivity(CE_E);
    }

    private void Go_Friends()
    {
        Intent CE_F = new Intent(Create.this, Friends.class);
        CE_F.putExtra("Username", username);
        startActivity(CE_F);
    }

    private void Go_Recommender()
    {
        Intent CE_R = new Intent(Create.this, Recommender.class);
        CE_R.putExtra("Username", username);
        startActivity(CE_R);
    }

    private void Go_Profile()
    {
        Intent CE_P = new Intent(Create.this, Profile.class);
        CE_P.putExtra("Username", username);
        startActivity(CE_P);
    }

    private void Go_Home()
    {
        Intent CE_H = new Intent(Create.this, Home.class);
        CE_H.putExtra("Username", username);
        startActivity(CE_H);
    }

    public void Go_Filter(int id) {
        for (int i = 0; i < 2; i++) {
            if (id == CE_Buttons[i].getId())
            {
                CE_Status[i] = !CE_Status[i];

                if (CE_Status[i])
                {
                    CE_Buttons[i].setBackgroundColor(Color.parseColor("#2C2B2B"));
                    CE_Buttons[i].setTextColor(Color.parseColor("#41F2F8"));
                }
                else
                {
                    CE_Buttons[i].setBackgroundColor(Color.parseColor("#41F2F8"));
                    CE_Buttons[i].setTextColor(Color.parseColor("#2C2B2B"));
                }
            }
            else
            {
                CE_Buttons[i].setBackgroundColor(Color.parseColor("#41F2F8"));
                CE_Buttons[i].setTextColor(Color.parseColor("#2C2B2B"));
            }

        }
    }


}