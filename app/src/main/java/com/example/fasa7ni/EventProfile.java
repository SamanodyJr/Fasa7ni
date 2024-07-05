package com.example.fasa7ni;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EventProfile extends AppCompatActivity implements View.OnClickListener
{
    private ImageButton BackButton;
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
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId()==BackButton.getId())
            finish();

        else
            return;
    }
}