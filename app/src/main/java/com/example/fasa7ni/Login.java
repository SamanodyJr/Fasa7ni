package com.example.fasa7ni;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;

public class Login extends AppCompatActivity implements View.OnClickListener
{
    private Button Log_in;
    private Button Show;
    private Button Create;
    private EditText UsernameText;
    private EditText PassText;
    private String Username;
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

        UsernameText = findViewById(R.id.username);
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
            Username = UsernameText.getText().toString();
            Pass = PassText.getText().toString();
            try {
                Verify(Username, Pass);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

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

    private void Verify(String username, String pass) throws IOException, JSONException {
        String url = "http://10.0.2.2:4000/Login"; //add type
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JSONArray jsonObject = new JSONArray();
        jsonObject.put(username);
        jsonObject.put(pass);

        Log.d("HEYYYYYYYY",jsonObject.toString());


        AtomicBoolean result = new AtomicBoolean(false);

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.POST, url, jsonObject,
                response ->
                {
                    if(response.length()==1){
                        Go_Home();
                    }
                    else{
                        Toast.makeText(Login.this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                },
                Throwable::printStackTrace
        );
        Log.d("HEYYYYYYYY",result.toString());
        requestQueue.add(jsonObjectRequest);
    }


    private void Go_Home()
    {
        Intent L_H = new Intent(Login.this, Home.class);
        L_H.putExtra("Username", Username);
        startActivity(L_H);
    }

    private void Go_Signup()
    {
        Intent L_S = new Intent(Login.this, Signup.class);
        startActivity(L_S);
    }


}