package com.example.fasa7ni;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Verification extends AppCompatActivity  implements View.OnClickListener
{
    private Button Verify;
    private  String email;
    private String phone;
    private EditText User_Code;
    private boolean verified;
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verification);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Start();
    }

    private void Start()
    {

        Verify = findViewById(R.id.verify_btn);
        Verify.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null)
        {
            email = bundle.getString("Email");
            phone = bundle.getString("Phone");
        }
        //request code from API
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==Verify.getId()) {
            verified = Confirm_Code();

            if(verified)
                Go_Home();
            else
                Toast.makeText(Verification.this, "Incorrect Code", Toast.LENGTH_SHORT).show();
        }
        else
            return;
    }

    private boolean Confirm_Code()
    {
        String code;
        code= User_Code.getText().toString();

        //Check with requested code

        return true;
    }

    private void Go_Home()
    {
        Intent V_H = new Intent(Verification.this, Home.class);
        V_H.putExtra("Email", email);
        startActivity(V_H);
    }
}