package com.example.fasa7ni;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.util.Log;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class Recommender extends AppCompatActivity implements View.OnClickListener
{
    private String email;
    private ImageButton List;
    private ImageButton Events;
    private ImageButton Friends;
    private ImageButton Home;
    private Button Generate;
    private ImageButton Profile;
    List<Fos7a> fosa7 = new ArrayList<Fos7a>();
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommender);
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
        Generate = findViewById(R.id.generate_btn);
        Profile = findViewById(R.id.profile_btn);
        Home = findViewById(R.id.small_home_btn);

        List.setOnClickListener(this);
        Events.setOnClickListener(this);
        Friends.setOnClickListener(this);
        Generate.setOnClickListener(this);
        Profile.setOnClickListener(this);
        Home.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==List.getId())
            Go_List();
        
        else if (v.getId()==Events.getId())
            Go_Events();

        else if (v.getId()==Friends.getId())
            Go_Friends();

        else if (v.getId()==Generate.getId()) {
            Log.d("", "Generate button clicked");
            Go_Generate();
        }
        else if (v.getId()==Profile.getId())
            Go_Profile();

        else if (v.getId()==Home.getId())
            Go_Home();
        else
            return;

    }

    private void Go_Home()
    {
        Intent R_H = new Intent(Recommender.this, Home.class);
        R_H.putExtra("Email", email);
        startActivity(R_H);
    }

    private void Go_List()
    {
        Intent R_L = new Intent(Recommender.this, List.class);
        R_L.putExtra("Email", email);
        startActivity(R_L);
    }

    private void Go_Events()
    {
        Intent R_E = new Intent(Recommender.this, Events.class);
        R_E.putExtra("Email", email);
        startActivity(R_E);
    }

    private void Go_Friends()
    {
        Intent R_F = new Intent(Recommender.this, Friends.class);
        R_F.putExtra("Email", email);
        startActivity(R_F);
    }

    private void Go_Generate()
    {
        Log.d("", "Navigating to Generate");
        Intent R_G = new Intent(Recommender.this, List.class);
        R_G.putExtra("Email", email);
        //H_R.putExtra("Fos7a", (CharSequence) fosa7);
        startActivity(R_G);
    }

    private void Go_Profile()
    {
        Intent R_P = new Intent(Recommender.this, Profile.class);
        R_P.putExtra("Email", email);
        startActivity(R_P);
    }
}