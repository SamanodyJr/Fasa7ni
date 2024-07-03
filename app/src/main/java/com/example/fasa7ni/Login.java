package com.example.fasa7ni;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity implements View.OnClickListener
{
    private Button Log_in;
    private Button Show;
    private Button Create;
    private EditText EmailText;
    private EditText PassText;
    private String Email;
    private String Pass;
    private boolean Signedin;
    private boolean showing = false;


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Start();
    }

    private void Start()
    {
        Log_in = findViewById(R.id.login_btn);
        Show = findViewById(R.id.show_pass_btn);
        Create = findViewById(R.id.new_account_btn);

        EmailText = findViewById(R.id.email);
        PassText = findViewById(R.id.pass);

        Log_in.setOnClickListener(this);
        Show.setOnClickListener(this);
        Create.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v)
    {
        if (v.getId() == Log_in.getId())
        {
            Email = EmailText.getText().toString();
            Pass = PassText.getText().toString();
            Signedin = Verify(Email, Pass);

            if (Signedin)
                Go_Home();
            else
                Toast.makeText(Login.this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
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
        else if (v.getId() == Create.getId())
            Go_Signup();
        else
            return;
    }

    private boolean Verify(String email, String pass)
    {
        //check with the database
        return true;
    }

    private void Go_Home()
    {
        Intent L_H = new Intent(Login.this, Home.class);
        L_H.putExtra("Email", Email);
        startActivity(L_H);
    }

    private void Go_Signup()
    {
        Intent L_S = new Intent(Login.this, Signup.class);
        startActivity(L_S);
    }


}
