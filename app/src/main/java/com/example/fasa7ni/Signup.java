package com.example.fasa7ni;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
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
        new Signup.SendTokenTask().execute(Phone, generatedToken);
        Intent S_V = new Intent(Signup.this, Verification.class);
        S_V.putExtra("Phone", Phone);
        S_V.putExtra("Username", Username);
        S_V.putExtra("Pass", Pass);
        S_V.putExtra("Token", generatedToken);
        startActivity(S_V);
    }

    private class SendTokenTask extends AsyncTask<String, Void, Boolean>
    {
        @Override
        protected Boolean doInBackground(String... params) {
            String phone = params[0];
            String token = params[1];
            Log.d("ho2", "phone: " + phone + ", token: " + token);
            try {
                URL url = new URL("http://10.0.2.2:3000/sendSMS");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                JSONObject jsonParam = new JSONObject();
                jsonParam.put("phone", phone);
                jsonParam.put("body", "Your verification code is: " + token);

                OutputStream os = conn.getOutputStream();
                os.write(jsonParam.toString().getBytes());
                os.flush();
                os.close();

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK)
                    return true;
                else
                    Log.e("ho2", "responseCode: " + responseCode);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result)
        {
            if (result)
                Toast.makeText(Signup.this, "Token sent successfully!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(Signup.this, "Failed to send token. Please try again.", Toast.LENGTH_SHORT).show();

        }
    }
}