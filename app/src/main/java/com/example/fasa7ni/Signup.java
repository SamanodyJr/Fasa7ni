package com.example.fasa7ni;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Signup extends AppCompatActivity implements View.OnClickListener
{
    private Button Sign_up;
    private Button Show;
    private ImageButton Back;
    private EditText UsernameText;
    private EditText PassText;
    private EditText PhoneText;
    private String Username;
    private String Pass;
    private String Phone;
    private boolean valid;
    private boolean showing = false;
    private String Phone_Pat = "^[0-9]{11}";

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Start();
    }

    private void Start()
    {
        Sign_up = findViewById(R.id.signup_btn);
        Show = findViewById(R.id.show_pass_btn);
        Back = findViewById(R.id.backButton);

        UsernameText = findViewById(R.id.username);
        PassText = findViewById(R.id.pass);
        PhoneText = findViewById(R.id.phone_number);

        Sign_up.setOnClickListener(this);
        Show.setOnClickListener(this);
        Back.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v)
    {
        if (v.getId() == Sign_up.getId())
        {
            Username = UsernameText.getText().toString();
            Pass = PassText.getText().toString();
            Phone = PhoneText.getText().toString();

            valid = Validate(Username, Phone, Pass);

            if (valid)
                Go_Verification();
            else
                Toast.makeText(Signup.this, "Phone format is incorrect", Toast.LENGTH_SHORT).show();
        }
        else if (v.getId() == Show.getId())
        {
            if (showing)
            {
                PassText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                Show.setText("Show");
            }
            else
            {
                PassText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                Show.setText("Hide");
            }
            showing = !showing;
        }
        else if (v.getId() == Back.getId())
            finish();
        else
            return;
    }

    private void Go_Verification()
    {
        Intent S_V = new Intent(Signup.this, Verification.class);
        S_V.putExtra("Phone", Phone);
        S_V.putExtra("Username", Username);
        startActivity(S_V);
    }

    private boolean Validate(String username,String phone, String pass)
    {
        if (phone.matches(Phone_Pat))
        {
            //add to database
            return true;
        }
        else
            return false;
    }
}