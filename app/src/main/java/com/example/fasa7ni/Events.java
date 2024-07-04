package com.example.fasa7ni;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Events extends AppCompatActivity implements View.OnClickListener
{
    private String email;
    private ImageButton List;
    private ImageButton Home;
    private ImageButton Friends;
    private ImageButton Recommender;
    private ImageButton Profile;
    private ImageButton AddFos7a;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Start();

        List<Fos7a> list = new ArrayList<Fos7a>();
        list.add(new Fos7a("Yalla Bowling","AUC","Friday","Heggi",R.drawable.paintball_fos7a_image));
        list.add(new Fos7a("Yalla Koraa","AUC","Friday","Heggi",R.drawable.paintball_fos7a_image));
        list.add(new Fos7a("Yalla Basket","AUC","Friday","Heggi",R.drawable.paintball_fos7a_image));
        list.add(new Fos7a("Yalla bs","AUC","Friday","Heggi",R.drawable.paintball_fos7a_image));
        list.add(new Fos7a("Yalla Bowling","AUC","Friday","Heggi",R.drawable.paintball_fos7a_image));
        list.add(new Fos7a("Yalla Bowling","AUC","Friday","Heggi",R.drawable.paintball_fos7a_image));
        list.add(new Fos7a("Yalla Bowling","AUC","Friday","Heggi",R.drawable.paintball_fos7a_image));
        RecyclerView recyclerView = findViewById(R.id.fosa7_recylcerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter(getApplicationContext(),list));
    }

    private void Start() {

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            email = bundle.getString("Email");
        }

        List = findViewById(R.id.small_list_btn);
        Home = findViewById(R.id.small_home_btn);
        Friends = findViewById(R.id.small_friends_btn);
        Recommender = findViewById(R.id.small_recommender_btn);
        Profile = findViewById(R.id.profile_btn);
        AddFos7a = findViewById(R.id.add_fos7a_btn);

        List.setOnClickListener(this);
        Home.setOnClickListener(this);
        Friends.setOnClickListener(this);
        Recommender.setOnClickListener(this);
        Profile.setOnClickListener(this);
        AddFos7a.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==List.getId())
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
            return;

    }

    private void Go_List() {
        Intent E_L = new Intent(Events.this, List.class);
        E_L.putExtra("Email", email);
        startActivity(E_L);
    }

    private void Go_Home() {
        Intent E_H = new Intent(Events.this, Home.class);
        E_H.putExtra("Email", email);
        startActivity(E_H);
    }

    private void Go_Friends() {
        Intent E_F = new Intent(Events.this, Friends.class);
        E_F.putExtra("Email", email);
        startActivity(E_F);
    }

    private void Go_Profile() {
        Intent E_P = new Intent(Events.this, Profile.class);
        E_P.putExtra("Email", email);
        startActivity(E_P);
    }

    private void Go_Recommender() {
        Intent E_R = new Intent(Events.this, Recommender.class);
        E_R.putExtra("Email", email);
        startActivity(E_R);
    }

    private void Go_Create() {
        Intent E_C = new Intent(Events.this, Create.class);
        E_C.putExtra("Email", email);
        startActivity(E_C);
    }


}