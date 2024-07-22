package com.example.fasa7ni;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;




public class Recommender extends AppCompatActivity implements View.OnClickListener
{
    private String username;
    private ImageButton List;
    private ImageButton Events;
    private ImageButton Friends;
    private ImageButton Home;
    private Button Generate;
    private ImageButton Profile;
    private Calendar calendar;
    private Spinner Spinner_Cap;
    private Spinner Spinner_Loc;
    private Spinner Spinner_Cat;
    private EditText DateText;
    private String Cap="";
    private String Date="h";
    private String Cat="";
    private String Loc="";
    private TextView Message;
    private String Place_Name;
    private List<Place> results = new ArrayList<Place>();




    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommender);
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


        calendar = Calendar.getInstance();


        List = findViewById(R.id.small_list_btn);
        Events = findViewById(R.id.small_event_btn);
        Friends = findViewById(R.id.small_friends_btn);
        Generate = findViewById(R.id.generate_btn);
        Profile = findViewById(R.id.profile_btn);
        Home = findViewById(R.id.small_home_btn);
        DateText = findViewById(R.id.date_text);
        Spinner_Cap = findViewById(R.id.spinnerCapacity);
        Spinner_Loc = findViewById(R.id.spinnerLocation);
        Spinner_Cat = findViewById(R.id.cat_spinner);
        Message = findViewById(R.id.Message);




        List.setOnClickListener(this);
        Events.setOnClickListener(this);
        Friends.setOnClickListener(this);
        Generate.setOnClickListener(this);
        Profile.setOnClickListener(this);
        Home.setOnClickListener(this);


    }


    public void showDatePickerDialog(View v) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.CustomDatePickerDialogTheme,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        calendar.set(year, month, day);
                        updateDateTextView();


                    }
                }, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }




    private void updateDateTextView()
    {
        String myFormat = "YYYY-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
        DateText.setText(sdf.format(calendar.getTime()));
    }






    @Override
    public void onClick(View v) {
        if(v.getId()==List.getId())
            Go_List();


        else if (v.getId()==Events.getId())
            Go_Events();


        else if (v.getId()==Friends.getId())
            Go_Friends();


        else if (v.getId()==Generate.getId()) {
            Loc = Spinner_Loc.getSelectedItem().toString().trim();
            Date = DateText.getText().toString().trim();
            Cat = Spinner_Cat.getSelectedItem().toString().trim();
            Cap = Spinner_Cap.getSelectedItem().toString().trim();




            if(Date.equals("") ||Loc.equals("")||Cat.equals("") ||Cap.equals(""))
            {
                Message.setVisibility(View.VISIBLE);
            }
            else
            {
                Message.setVisibility(View.INVISIBLE);
                Place_Name=Recommend(Loc, Date, Cat, Cap);


            }
        }
        else if (v.getId()==Profile.getId())
            Go_Profile();


        else if (v.getId()==Home.getId())
            Go_Home();
        else
            return;


    }


    private String Recommend(String loc, String date, String cat, String cap)
    {
        results.clear();
        String url;
        url = "http://10.0.2.2:4000/Recommend?Cat="+cat+"&Locat="+loc;
        RequestQueue requestQueue = Volley.newRequestQueue(this);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                com.android.volley.Request.Method.GET, url, null,
                response ->
                {
                    try {
                        for (int i = 0; i < response.length(); i++)
                        {
                            JSONObject places = response.getJSONObject(i);
                            String name = places.getString("Place_Name");
                            String location = places.getString("Address");
                            String description = places.getString("Description");
                            String phone = places.getString("Phone");
                            String openingTime = places.getString("OpeningTime");
                            String closingTime = places.getString("ClosingTime");
                            String workingDays = places.getString("WorkingDays");
                            String Image = places.getString("PlacePic");
                            results.add(new Place(name, location, description, phone, openingTime, closingTime, workingDays, Image));
                        }
                        Go_Recommended();
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                },
                Throwable::printStackTrace
        );
        requestQueue.add(jsonArrayRequest);
        return "";
    }


    private void Go_Recommended()
    {
        Collections.shuffle(results);
        Intent intent = new Intent(this, PlaceProfile.class);
        Place place = results.get(0);
        intent.putExtra("Username", username);
        intent.putExtra("Location", place.location);
        intent.putExtra("Description", place.description);
        intent.putExtra("OpeningTime", place.OpeningTime);
        intent.putExtra("ClosingTime",place.ClosingTime);
        intent.putExtra("Phone", place.phone);
        intent.putExtra("Name", place.name);
        intent.putExtra("WorkingDays", place.WorkingDays);
        intent.putExtra("Image", place.image);
        intent.putExtra("page", "recommender");
        startActivity(intent);


    }


    private void Go_Home()
    {
        Intent R_H = new Intent(Recommender.this, Home.class);
        R_H.putExtra("Username", username);
        startActivity(R_H);
    }


    private void Go_List()
    {
        Intent R_L = new Intent(Recommender.this, Listat.class);
        Log.d("", "Navigating to Generate");
        R_L.putExtra("Username", username);
        startActivity(R_L);


    }


    private void Go_Events()
    {
        Intent R_E = new Intent(Recommender.this, Events.class);
        R_E.putExtra("Username", username);
        startActivity(R_E);
    }


    private void Go_Friends()
    {
        Intent R_F = new Intent(Recommender.this, Friends.class);
        R_F.putExtra("Username", username);
        startActivity(R_F);
    }


    private void Go_Profile()
    {
        Intent R_P = new Intent(Recommender.this, Profile.class);
        R_P.putExtra("Username", username);
        startActivity(R_P);
    }
}