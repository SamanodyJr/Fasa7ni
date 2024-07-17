package com.example.fasa7ni;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Profile extends AppCompatActivity implements View.OnClickListener
{
    private String username;
    private ImageButton BackButton;
    private Button EditButton;
    private EditText name;
    private EditText DOB;
    private EditText MobileNumber;
    private EditText interest1;
    private EditText interest2;
    private EditText interest3;
    private ImageButton EditIntrest;
    private Spinner spinner;
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Start();
    }

    private void Start()
    {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            username = bundle.getString("Username");
        }

        spinner = findViewById(R.id.spinnerLocation);
        BackButton = findViewById(R.id.backButton);
        name = findViewById(R.id.name);
        DOB = findViewById(R.id.dob);
        MobileNumber = findViewById(R.id.mobile_icon_profile);
        interest1 = findViewById(R.id.interest1);
        interest2 = findViewById(R.id.interest2);
        interest3 = findViewById(R.id.interest3);
        EditButton= findViewById(R.id.editButton);
        EditIntrest= findViewById(R.id.addInterests);

        spinner.setEnabled(false);
        EditButton.setOnClickListener(this);
        BackButton.setOnClickListener(this);
        DOB.setOnClickListener(this);
        MobileNumber.setOnClickListener(this);
        interest1.setOnClickListener(this);
        interest2.setOnClickListener(this);
        interest3.setOnClickListener(this);
        EditIntrest.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==BackButton.getId())
            Go_BackButton();
        else if(v.getId()==EditButton.getId())
        {
            Go_EditButton();
        }
        else if(v.getId()==EditIntrest.getId())
        {
            Go_Interest();
        }
        else
            return;

    }

    private void Go_BackButton()
    {
        finish();
    }

    private void Go_EditButton()
    {
        boolean isEnabled = !name.isEnabled();
        name.setEnabled(isEnabled);
        DOB.setEnabled(isEnabled);
        spinner.setEnabled(isEnabled);
        MobileNumber.setEnabled(isEnabled);
        interest1.setEnabled(isEnabled);
        interest2.setEnabled(isEnabled);
        interest3.setEnabled(isEnabled);

        if (isEnabled) {
            EditButton.setText("SAVE");
            name.requestFocus();
            DOB.requestFocus();
            MobileNumber.requestFocus();
        }
        else
        {
            EditButton.setText("EDIT");
        }
    }

    private void Go_Interest()
    {
        Intent P_I = new Intent(Profile.this, Interests.class);
        P_I.putExtra("Username", username);
        startActivity(P_I);
    }

}