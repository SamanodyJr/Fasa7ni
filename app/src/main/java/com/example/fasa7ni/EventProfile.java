package com.example.fasa7ni;

import android.annotation.SuppressLint;
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

public class EventProfile extends AppCompatActivity implements View.OnClickListener
{
    private ImageButton BackButton;
    private Button Join;
    private boolean pending;
    private boolean status = false;
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

        Join = findViewById(R.id.Join_btn);
        Join.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId()==BackButton.getId())
            finish();

        else if(v.getId()==Join.getId())
        {
            if(!status && !pending)
            {
                Join.setText("Pending");
                Join.setBackgroundColor(getResources().getColor(R.color.support_grey));;
                Join.setTextColor(getResources().getColor(R.color.light_grey));
                pending = true;
                //add request
            }

            else if (status || (!status && pending))
            {
                Join.setText("Join Fos7a");
                Join.setBackgroundColor(getResources().getColor(R.color.blue));
                Join.setTextColor(getResources().getColor(R.color.black));
                status = false;
                pending = false;
                //Delete request
            }
        }


    }
}