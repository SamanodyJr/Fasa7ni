package com.example.fasa7ni;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Interests extends AppCompatActivity implements View.OnClickListener
{
    private ImageButton BackButton;
    private Button btnSave;
    private String username;
    private String Interests;
    private String[] interests;
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
    private void Start()
    {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            username = bundle.getString("Username");
        }
        BackButton = findViewById(R.id.backButton);
        BackButton.setOnClickListener(this);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

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
        GetInterests();

    }
    @Override
    public void onClick(View v)
    {
        if(v.getId()==BackButton.getId())
            Go_Profile();
        else if(v.getId()==btnSave.getId())
        {
            Interests = "";
            for (int i = 0; i < 36; i++)
            {
                if(status[i])
                    Interests += interestButtons[i].getText().toString()+",";
            }
            Interests = Interests.substring(0, Interests.length()-1);
            AddInterests();
            Go_Profile();
        }
        else
            Go_Select_Intrests(v.getId());

    }

    private void GetInterests()
    {
        String url;
        url = "http://10.0.2.2:4000/Get_Interests?Username="+username;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                response ->
                {
                    try
                    {
                        interests = new String[response.length()];
                        for (int i = 0; i < response.length(); i++)
                        {
                            JSONObject interest = response.getJSONObject(i);
                            interests[i] = interest.getString("Interest");
                        }

                        runOnUiThread(() ->
                        {
                            for (int i = 0; i < interests.length; i++)
                            {
                                for (int j = 0; j < 36; j++)
                                {
                                    if(interests[i].equals(interestButtons[j].getText().toString()))
                                        status[j]=true;

                                    if(status[j]) //selected
                                    {
                                        interestButtons[j].setBackgroundColor(Color.parseColor("#41F2F8"));
                                        interestButtons[j].setTextColor(Color.parseColor("#2C2B2B"));
                                    }
                                    else
                                    {
                                        interestButtons[j].setBackgroundColor(Color.parseColor("#FF6767"));
                                        interestButtons[j].setTextColor(Color.parseColor("#EDEDED"));
                                    }
                                }
                            }
                        });
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                },
                Throwable::printStackTrace
        );
        requestQueue.add(jsonArrayRequest);

    }

    private void AddInterests()
    {
        String url;
        url = "http://10.0.2.2:4000/Add_Interests?Username="+username+"&Interests="+Interests;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                response ->
                        Toast.makeText(Interests.this, "Updated Successfully", Toast.LENGTH_SHORT).show(),
                Throwable::printStackTrace
        );
        requestQueue.add(jsonArrayRequest);
    }

    private void Go_Profile()
    {
        Intent I_P = new Intent(Interests.this, Profile.class);
        I_P.putExtra("Username", username);
        startActivity(I_P);
    }

    private void Go_Select_Intrests(int Id)
    {

        for (int i = 0; i < 36; i++)
        {
            if(Id==interestButtons[i].getId())
            {
                status[i]=!status[i];
                if(status[i]) //selected
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