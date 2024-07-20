package com.example.fasa7ni;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TimePicker;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

public class Create extends AppCompatActivity implements View.OnClickListener
{
    private String username;
    private String place_name;
    private ImageButton Listat;
    private ImageButton Events;
    private ImageButton Friends;
    private ImageButton Recommender;
    private ImageButton Home;
    private Button Public;
    private Button Private;
    private boolean IsPublic;
    private EditText NameText;
    private Spinner Loc_Spinner;
    private EditText DescriptionText;
    private EditText DateText;
    private EditText TimeText;
    private EditText CapText;
    private TextView LocText;
    private TextView Message;
    private String Name="";
    private String Location="";
    private String Desc="";
    private String Date="";
    private String Time="";
    private int Cap=0;
    private Button Create;
    private Calendar calendar;




    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Start();
    }

    private void Start()
    {

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            username = bundle.getString("Username");
            place_name = bundle.getString("Place_Name");
        }

        calendar = Calendar.getInstance();

        Listat = findViewById(R.id.small_list_btn);
        Events = findViewById(R.id.small_event_btn);
        Friends = findViewById(R.id.small_friends_btn);
        Recommender = findViewById(R.id.small_recommender_btn);
        Home = findViewById(R.id.small_home_btn);

        Listat.setOnClickListener(this);
        Events.setOnClickListener(this);
        Friends.setOnClickListener(this);
        Recommender.setOnClickListener(this);
        Home.setOnClickListener(this);

        Public = findViewById(R.id.public_info_select);
        Public.setOnClickListener(this);

        Private = findViewById(R.id.private_info_select);
        Private.setOnClickListener(this);

        Message = findViewById(R.id.Message);

        Create = findViewById(R.id.create_btn);
        Create.setOnClickListener(this);

        NameText = findViewById(R.id.Fos7a_Name);
        LocText = findViewById(R.id.Fos7a_Location);
        Loc_Spinner = findViewById(R.id.spinnerPlaces);
        DateText = findViewById(R.id.Fos7a_Date);
        TimeText = findViewById(R.id.Fos7a_Time);
        DescriptionText = findViewById(R.id.Fos7a_Desc);
        CapText = findViewById(R.id.Fos7a_Cap);

        LocText.setOnClickListener(this);

        if (place_name != null)
        {
            Loc_Spinner.setVisibility(View.GONE);
            Loc_Spinner.setEnabled(false);
            LocText.setEnabled(false);
            LocText.setTextColor(getResources().getColor(R.color.main_grey));
            LocText.setVisibility(View.VISIBLE);
            LocText.setText(place_name);
        }

    }

    public void showDatePickerDialog(View v) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        calendar.set(year, month, day);
                        updateDateTextView();
                    }
                }, year, month, day);

        datePickerDialog.show();
    }

    private void updateDateTextView()
    {
        String myFormat = "YYYY-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
        DateText.setText(sdf.format(calendar.getTime()));
    }

    public void showTimePickerDialog(View v)
    {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, hourOfDay, minute1) ->
                {
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    calendar.set(Calendar.MINUTE, minute1);
                    updateTimeTextView();
                }, hour, minute, true);

        timePickerDialog.show();
    }

    private void updateTimeTextView()
    {
        String myFormat = "HH:mm"; // 24-hour format
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
        TimeText.setText(sdf.format(calendar.getTime())+":00");
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==Listat.getId())
            Go_List();

        else if (v.getId()==Events.getId())
            Go_Events();

        else if (v.getId()==Friends.getId())
            Go_Friends();

        else if (v.getId()==Recommender.getId())
            Go_Recommender();

        else if (v.getId()==Home.getId())
            Go_Home();

        else if (v.getId()==LocText.getId()){
            LocText.setVisibility(View.GONE);
            Loc_Spinner.setVisibility(View.VISIBLE);
            Loc_Spinner.performClick();
        }


        else if (v.getId()==Create.getId())
        {

            Name = NameText.getText().toString().trim();
            Location = Loc_Spinner.getSelectedItem().toString().trim();
            Desc = DescriptionText.getText().toString().trim();
            Date = DateText.getText().toString().trim();
            Time = TimeText.getText().toString().trim();
            try
            {
                Cap = Integer.parseInt(CapText.getText().toString().trim());
            }
            catch (NumberFormatException e)
            {
                Cap=0;
            }
            if(Name.equals("")|| Location.equals("")||Desc.equals("")||Date.equals("") ||Time.equals("")||Cap==0)
            {
                Message.setVisibility(View.VISIBLE);
            }
            else
            {
                Message.setVisibility(View.INVISIBLE);
                try
                {
                    Add_Fos7a(Name, Location, Desc, Date, Time, Cap);
                }
                catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
                catch (JSONException e)
                {
                    throw new RuntimeException(e);
                }
            }
        }

        else
            Go_Filter(v.getId());

    }

    private void Go_List()
    {
        Intent CE_L = new Intent(Create.this, Listat.class);
        CE_L.putExtra("Username", username);
        startActivity(CE_L);
    }

    private void Go_Events()
    {
        Intent CE_E = new Intent(Create.this, Events.class);
        CE_E.putExtra("Username", username);
        startActivity(CE_E);
    }

    private void Go_Friends()
    {
        Intent CE_F = new Intent(Create.this, Friends.class);
        CE_F.putExtra("Username", username);
        startActivity(CE_F);
    }

    private void Go_Recommender()
    {
        Intent CE_R = new Intent(Create.this, Recommender.class);
        CE_R.putExtra("Username", username);
        startActivity(CE_R);
    }

    private void Go_Home()
    {
        Intent CE_H = new Intent(Create.this, Home.class);
        CE_H.putExtra("Username", username);
        startActivity(CE_H);
    }

    public void Go_Filter(int id)
    {
        if (id==Public.getId())
        {
            IsPublic = true;
            Public.setBackgroundColor(Color.parseColor("#2C2B2B"));
            Public.setTextColor(Color.parseColor("#41F2F8"));

            Private.setBackgroundColor(Color.parseColor("#41F2F8"));
            Private.setTextColor(Color.parseColor("#2C2B2B"));

        }
        else if (id==Private.getId())
        {
            IsPublic = false;
            Private.setBackgroundColor(Color.parseColor("#2C2B2B"));
            Private.setTextColor(Color.parseColor("#41F2F8"));

            Public.setBackgroundColor(Color.parseColor("#41F2F8"));
            Public.setTextColor(Color.parseColor("#2C2B2B"));
        }
    }

    private void Add_Fos7a(String Name,String Location,String Desc,String Date,String Time,int Cap) throws IOException, JSONException
    {
        String url = "http://10.0.2.2:4000/Create_Fos7a";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JSONObject jsonObject = new JSONObject();
        try
        {
            jsonObject.put("Fos7a_Name", Name);
            jsonObject.put("Host_Username", username);
            jsonObject.put("Description", Desc);
            jsonObject.put("Capacity", Cap);
            jsonObject.put("Fos7a_Time", Time);
            jsonObject.put("Fos7a_Date", Date);
            jsonObject.put("Place_Name", Location);
            jsonObject.put("Is_Public", IsPublic ? 1 : 0);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, url, jsonObject,
                response ->
                        Go_Events(),
                Throwable::printStackTrace
        );
        requestQueue.add(jsonObjectRequest);
    }

}