package com.example.fasa7ni;

import android.annotation.SuppressLint;
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

public class Interests extends AppCompatActivity implements View.OnClickListener
{
    private ImageButton BackButton;
    String email;
    private Button[] interestButtons = new Button[37];
    private boolean[] status= new boolean[37];

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interests);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Start();
    }
    private void Start() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            email = bundle.getString("Email");
        }
        BackButton = findViewById(R.id.backButton);

        BackButton.setOnClickListener(this);

        int[] buttonIDs =
                {
                R.id.interest1, R.id.interest2, R.id.interest3, R.id.interest4, R.id.interest5,
                R.id.interest6, R.id.interest7, R.id.interest8, R.id.interest9, R.id.interest10,
                R.id.interest11, R.id.interest12, R.id.interest13, R.id.interest14, R.id.interest15,
                R.id.interest16, R.id.interest17, R.id.interest18, R.id.interest19, R.id.interest20,
                R.id.interest21, R.id.interest22, R.id.interest23, R.id.interest24, R.id.interest25,
                R.id.interest26, R.id.interest27, R.id.interest28, R.id.interest29, R.id.interest30,
                R.id.interest31, R.id.interest32, R.id.interest33, R.id.interest34, R.id.interest35,
                R.id.interest36, R.id.interest37
        };

        for (int i = 0; i < 36; i++)
        {
            interestButtons[i] = findViewById(buttonIDs[i]);
            interestButtons[i].setOnClickListener(this);
        }

    }
    @Override
    public void onClick(View v)
    {
        if(v.getId()==BackButton.getId())
            Go_BackButton();
        else
        {
            Go_Select_Intrests(v.getId());
        }

    }

    private void Go_BackButton()
    {
        finish();
    }

    private void Go_Select_Intrests(int Id)
    {

        for (int i = 0; i < 36; i++)
        {
            if(Id==interestButtons[i].getId())
            {
                status[i]=!status[i];
                if(status[i])
                {
                    interestButtons[i].setBackgroundColor(Color.parseColor("#41F2F8"));
                    interestButtons[i].setTextColor(Color.parseColor("#2C2B2B"));
                }
                else
                {
                    interestButtons[i].setBackgroundColor(Color.parseColor("#FF6767"));
                    interestButtons[i].setTextColor(Color.parseColor("#EDEDED"));
                }
            }
        }
    }
}