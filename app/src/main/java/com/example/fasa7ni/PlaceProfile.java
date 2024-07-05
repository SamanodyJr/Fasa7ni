package com.example.fasa7ni;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PlaceProfile extends AppCompatActivity implements View.OnClickListener
{
    private ImageButton BackButton;
    private Button Show_Events;
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Start();
    }

    private void Start()
    {
        BackButton = findViewById(R.id.backButton);
        BackButton.setOnClickListener(this);

        Show_Events = findViewById(R.id.show_fosa7_btn);
        Show_Events.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId() == BackButton.getId())
            finish();

        else if (v.getId() == Show_Events.getId())
            Go_Events();

        else
            return;
    }

    private void Go_Events()
    {
        Intent P_E = new Intent(PlaceProfile.this, Events.class);
        startActivity(P_E);
    }
}