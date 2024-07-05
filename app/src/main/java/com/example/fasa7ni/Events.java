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

public class Events extends AppCompatActivity implements View.OnClickListener
{
    private String email;
    private ImageButton Listat;
    private ImageButton Home;
    private ImageButton Friends;
    private ImageButton Recommender;
    private ImageButton Profile;
    private ImageButton AddFos7a;
    private Button[] Event_Buttons = new Button[4];
    private boolean[] Event_Status= new boolean[4];

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Start();

        List<Event> list = new ArrayList<Event>();
        list.add(new Event("Yalla Bowling","AUC","Friday","Heggi",R.drawable.paintball_image));
        list.add(new Event("Yalla Koraa","AUC","Friday","Heggi",R.drawable.paintball_image));
        list.add(new Event("Yalla Basket","AUC","Friday","Heggi",R.drawable.paintball_image));
        list.add(new Event("Yalla bs","AUC","Friday","Heggi",R.drawable.paintball_image));
        list.add(new Event("Yalla Bowling","AUC","Friday","Heggi",R.drawable.paintball_image));
        list.add(new Event("Yalla Bowling","AUC","Friday","Heggi",R.drawable.paintball_image));
        list.add(new Event("Yalla Bowling","AUC","Friday","Heggi",R.drawable.paintball_image));
        RecyclerView recyclerView = findViewById(R.id.fosa7_recylcerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new EventAdapter(getApplicationContext(),list));
    }

    private void Start()
    {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            email = bundle.getString("Email");
        }

        Listat = findViewById(R.id.small_list_btn);
        Home = findViewById(R.id.small_home_btn);
        Friends = findViewById(R.id.small_friends_btn);
        Recommender = findViewById(R.id.small_recommender_btn);
        Profile = findViewById(R.id.profile_btn);
        AddFos7a = findViewById(R.id.add_fos7a_btn);

        Listat.setOnClickListener(this);
        Home.setOnClickListener(this);
        Friends.setOnClickListener(this);
        Recommender.setOnClickListener(this);
        Profile.setOnClickListener(this);
        AddFos7a.setOnClickListener(this);

        int[] EventbuttonIDs =
                {
                        R.id.events_filter_btn, R.id.public_filter_btn, R.id.private_filter_btn, R.id.private_filter_btn
                };

        for (int i = 0; i < 4; i++) {
            Event_Buttons[i] = findViewById(EventbuttonIDs[i]);
            Event_Buttons[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v)
    {

        if(v.getId()==Listat.getId())
            Go_List();

        else if (v.getId()==Home.getId())
            Go_Home();

        else if (v.getId()==Friends.getId())
            Go_Friends();

        else if (v.getId()==Recommender.getId())
            Go_Recommender();

        else if (v.getId()==Profile.getId())
            Go_Profile();

        else if (v.getId()==AddFos7a.getId())
            Go_Create();
        else
            Go_Filter(v.getId());

    }

    private void Go_List()
    {
        Intent E_L = new Intent(Events.this, Listat.class);
        E_L.putExtra("Email", email);
        startActivity(E_L);
    }

    private void Go_Home()
    {
        Intent E_H = new Intent(Events.this, Home.class);
        E_H.putExtra("Email", email);
        startActivity(E_H);
    }

    private void Go_Friends()
    {
        Intent E_F = new Intent(Events.this, Friends.class);
        E_F.putExtra("Email", email);
        startActivity(E_F);
    }

    private void Go_Profile()
    {
        Intent E_P = new Intent(Events.this, Profile.class);
        E_P.putExtra("Email", email);
        startActivity(E_P);
    }

    private void Go_Recommender()
    {
        Intent E_R = new Intent(Events.this, Recommender.class);
        E_R.putExtra("Email", email);
        startActivity(E_R);
    }

    private void Go_Create()
    {
        Intent E_C = new Intent(Events.this, Create.class);
        E_C.putExtra("Email", email);
        startActivity(E_C);
    }

    public void Go_Filter(int id) {
        for (int i = 0; i < 4; i++) {
            if (id == Event_Buttons[i].getId())
            {
                Event_Status[i] = !Event_Status[i];

                if (Event_Status[i])
                {
                    Event_Buttons[i].setBackgroundColor(Color.parseColor("#2C2B2B"));
                    Event_Buttons[i].setTextColor(Color.parseColor("#41F2F8"));
                }
                else
                {
                    Event_Buttons[i].setBackgroundColor(Color.parseColor("#41F2F8"));
                    Event_Buttons[i].setTextColor(Color.parseColor("#2C2B2B"));
                }
            }
            else
            {
                Event_Buttons[i].setBackgroundColor(Color.parseColor("#41F2F8"));
                Event_Buttons[i].setTextColor(Color.parseColor("#2C2B2B"));
            }

        }
    }


}