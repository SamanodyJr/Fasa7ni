package com.example.fasa7ni;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

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

    private String generatedToken;

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

            valid = Validate(Phone);
            if (valid)
                Go_Verification();
            else
                Toast.makeText(Signup.this, "Email or Phone format is incorrect", Toast.LENGTH_SHORT).show();
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
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("TAWFIKo", "Permission granted");
            }
        }
    }



    private void Go_Verification()
    {
        if (Phone.isEmpty())
        {
            Toast.makeText(Signup.this, "Number is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        generatedToken = generateToken();
        startVerification();
    }


    private String generateToken()
    {
        Random random = new Random();
        int token = 100000 + random.nextInt(900000);
        return String.valueOf(token);
    }

    private boolean Validate(String phone)
    {
        return phone.matches(Phone_Pat);
    }

    private void startVerification()
    {
        //permission check
        if(ContextCompat.checkSelfPermission(Signup.this, Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
            Log.d("TAWFIKo", "Permission granted");
        }else{
            ActivityCompat.requestPermissions(Signup.this,new String[]{Manifest.permission.SEND_SMS},100);
        }
        sendSMS(Phone, generatedToken);
        Intent S_V = new Intent(Signup.this, Verification.class);
        S_V.putExtra("Phone", Phone);
        S_V.putExtra("Username", Username);
        S_V.putExtra("Pass", Pass);
        S_V.putExtra("Token", generatedToken);
        startActivity(S_V);
    }
    private void sendSMS(String phone, String token) {

        if (phone == null || token == null) {
            Log.e("TAWFIKo", "Phone or token is null. Phone: " + phone + ", Token: " + "Verification Code For Fasa7ni: " + token);
            return;
        }

        String url = "http://10.0.2.2:4000/sendSMS?phone=" + phone + "&body= Verification Code For Fasa7ni: " + token;
        Log.d("TAWFIKo", "URL: " + url);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(
                com.android.volley.Request.Method.GET, url,
                response -> {
                    try {
                        if (response.equals("success")) {
                            Log.d("TAWFIKo", "SMS sent successfully");
                        } else {
                            Log.d("TAWFIKo", "Failed to send SMS");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    error.printStackTrace();
                    Log.e("TAWFIKo", "Error in sending SMS: " + error.getMessage());
                }
        );

        requestQueue.add(stringRequest);
    }

}