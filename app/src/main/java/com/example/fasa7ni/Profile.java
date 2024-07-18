package com.example.fasa7ni;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class Profile extends AppCompatActivity implements View.OnClickListener
{
    private String username;
    private String olduser;
    private String picstr;
    private String birth;
    private String phone;
    private String Location;
    private ImageButton BackButton;
    private Button EditButton;
    private ImageView ProfilePic;
    private EditText User;
    private EditText DOB;
    private EditText MobileNumber;
    private Button EditIntrest;
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

        ProfilePic = findViewById(R.id.profilepic);
        spinner = findViewById(R.id.spinnerLocation);
        BackButton = findViewById(R.id.backButton);
        DOB = findViewById(R.id.dob);
        MobileNumber = findViewById(R.id.mobile_icon_profile);
        EditButton= findViewById(R.id.editButton);
        EditIntrest= findViewById(R.id.addInterests);
        User = findViewById(R.id.username);

        spinner.setEnabled(false);
        EditButton.setOnClickListener(this);
        BackButton.setOnClickListener(this);
        DOB.setOnClickListener(this);
        MobileNumber.setOnClickListener(this);
        EditIntrest.setOnClickListener(this);

        FetchUser();

    }

    @Override
    public void onClick(View v)
    {
        if(v.getId()==BackButton.getId())
            Go_Home();
        else if(v.getId()==EditButton.getId())
            Go_EditButton();
        else if(v.getId()==EditIntrest.getId())
            Go_Interest();
        else
            return;

    }

    private void Go_Home()
    {
        Intent P_H = new Intent(Profile.this, Home.class);
        P_H.putExtra("Username", username);
        startActivity(P_H);
    }

    private void Go_EditButton()
    {
        boolean isEnabled = !User.isEnabled();
        User.setEnabled(isEnabled);
        DOB.setEnabled(isEnabled);
        spinner.setEnabled(isEnabled);
        MobileNumber.setEnabled(isEnabled);

        if (isEnabled)
        {
            EditButton.setText("SAVE");
            User.requestFocus();
            DOB.requestFocus();
            MobileNumber.requestFocus();
        }
        else
        {
            EditButton.setText("EDIT");
            olduser = username;
            username = User.getText().toString();
            birth = DOB.getText().toString();
            phone = MobileNumber.getText().toString();
            Location = spinner.getSelectedItem().toString();
            UpdateUser();
        }
    }

    private void UpdateUser()
    {
        String url;
        url = "http://10.0.2.2:4000/Update_User?olduser="+olduser+"&phone="+phone+"&username="+username+"&BirthDate="+birth+"&Area="+Location;
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                response ->
                        Toast.makeText(Profile.this, "Updated Successfully", Toast.LENGTH_SHORT).show(),
                Throwable::printStackTrace
        );
        requestQueue.add(jsonArrayRequest);
    }

    private void Go_Interest()
    {
        Intent P_I = new Intent(Profile.this, Interests.class);
        P_I.putExtra("Username", username);
        startActivity(P_I);
    }

    private void FetchUser()
    {
        String url;
        url = "http://10.0.2.2:4000/Fetch_User?Username="+username;

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                response ->
                {
                    Log.d("Profile", response.toString());
                    try
                    {
                        JSONObject user = response.getJSONObject(0);
                        picstr = user.getString("ProfilePic");
                        birth = user.getString("BirthDate");
                        phone = user.getString("Phone");
                        Location = user.getString("Area");

                        String imagePath = "file:///android_asset/" + picstr;
                        Glide.with(getApplicationContext())
                                .load(imagePath)
                                .into(ProfilePic);

                        runOnUiThread(() ->
                        {
                            User.setText(username);
                            DOB.setText(birth);
                            MobileNumber.setText(phone);

                            int pos = 0;
                            switch (Location)
                            {
                                case "Maadi":
                                    pos = 0;
                                    break;
                                case "New Cairo":
                                    pos = 1;
                                    break;
                                case "Sheikh Zayed":
                                    pos = 2;
                                    break;
                                case "6th of October":
                                    pos = 3;
                                    break;
                                case "Nasr City":
                                    pos = 4;
                                    break;
                                case "Zamalek":
                                    pos = 5;
                                    break;
                                case "Heliopolis":
                                    pos = 6;
                                    break;
                                case "Katameya":
                                    pos = 7;
                                    break;
                                case "Mokattam":
                                    pos = 8;
                                    break;
                                case "Downtown":
                                    pos = 9;
                                    break;
                                case "Madinaty":
                                    pos = 10;
                                    break;
                            }
                            spinner.setSelection(pos);
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

}