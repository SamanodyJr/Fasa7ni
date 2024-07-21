package com.example.fasa7ni;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;


public class Verification extends AppCompatActivity
{
    private Button verifyBtn;
    private  String Username;
    private String phone;
    private String pass;
    private EditText User_Code;
    private String receivedToken;

    private boolean verified;

    private static final String CHANNEL_ID = "sms_simulation_channel";
    private static final int NOTIFICATION_ID = 1;

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

        User_Code = findViewById(R.id.verification_text);
        verifyBtn = findViewById(R.id.verify_btn);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null)
        {
            Username = bundle.getString("Username");
            phone = bundle.getString("Phone");
            pass = bundle.getString("Pass");
            receivedToken = getIntent().getStringExtra("Token");
        }
        getSMS();

        verifyBtn.setOnClickListener(v ->
        {
            String enteredToken = User_Code.getText().toString();
            if (enteredToken.equals(receivedToken))
            {
                Toast.makeText(Verification.this, "Token verified successfully!", Toast.LENGTH_SHORT).show();
                Add_User();
                Go_Home();
            }
            else
            {
                Toast.makeText(Verification.this, "Invalid token. Please try again.", Toast.LENGTH_SHORT).show();
                Verification.this.finish();
            }
        });
    }


    private void Go_Home()
    {
        Intent V_H = new Intent(Verification.this, Home.class);
        V_H.putExtra("Username", Username);
        startActivity(V_H);
    }


    public void getSMS(){
        String phoneNumber = "+15551234567"; // Replace with the recipient's phone number
        String message = "Your Verification Code is: "+receivedToken; // Replace with your desired message

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, message, null, null);
        Toast.makeText(this, "SMS sent successfully", Toast.LENGTH_SHORT).show();
    }
    private void Add_User()
    {
        String url = "http://10.0.2.2:4000/Add_User";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JSONArray jsonObject = new JSONArray();
        jsonObject.put(Username);
        jsonObject.put(pass);
        jsonObject.put(phone);

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.POST, url, jsonObject,
                response ->
                {
                    Toast.makeText(Verification.this, "User added successfully!", Toast.LENGTH_SHORT).show();
                },
                Throwable::printStackTrace
        );
        requestQueue.add(jsonObjectRequest);
    }
}