package com.example.fasa7ni;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EventProfile extends AppCompatActivity implements View.OnClickListener, RecyclerViewInterface
{

    private String Fos7a_Name, Host_Username, Description, Capacity, Fos7a_Date, Fos7a_Time, Place_Name, Image;
    int Is_Public;

    private String username;
    private TextView fos7aTitle, fos7aDescription, fos7aDate, Fos7a_Location, fos7aCapacity, fos7aPublic, fos7aHost;
    private ImageView fos7aImage;
    private TextView ReadMore;
    private ArrayList<TextView> Tags = new ArrayList<TextView>();
    private ImageButton BackButton;
    private Button Join, show_requests_btn, show_attendees_btn;
    private boolean pending = false;
    private boolean status = false;
    private boolean isExpanded = false;
    private String page;
    private TextView title;

    private List<Request> rows = new ArrayList<Request>();
    private RecyclerView recyclerView;
    private RequestAdapter Request_Adapter;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Start();
    }


    private void Start()
    {
        BackButton = findViewById(R.id.backButton);
        BackButton.setOnClickListener(this);

        ReadMore = findViewById(R.id.read_more);
        fos7aImage = findViewById(R.id.imageView3);
        fos7aTitle = findViewById(R.id.Fos7aTitle);
        fos7aDescription = findViewById(R.id.Fos7aDescription);
        fos7aCapacity = findViewById(R.id.Fos7aCapacity);
        fos7aDate = findViewById(R.id.Fos7aDateTime);
        fos7aPublic = findViewById(R.id.Fos7aType);
        fos7aHost = findViewById(R.id.Fos7aHost);
        Fos7a_Location = findViewById(R.id.Fos7aLocation);



        ReadMore.setOnClickListener(v ->
        {
            if (isExpanded)
            {
                fos7aDescription.setMaxLines(3);
                fos7aDescription.setEllipsize(android.text.TextUtils.TruncateAt.END);
                ReadMore.setText("Read More");
            }
            else
            {
                fos7aDescription.setMaxLines(Integer.MAX_VALUE);
                fos7aDescription.setEllipsize(null);
                ReadMore.setText("Read Less");
            }
            isExpanded = !isExpanded;

        });


        Join = findViewById(R.id.Join_btn);
        Join.setOnClickListener(this);

        show_requests_btn = findViewById(R.id.show_requests_btn);
        show_requests_btn.setOnClickListener(this);

        show_attendees_btn = findViewById(R.id.showAttendees);
        show_attendees_btn.setOnClickListener(this);

        Bundle Bundle = getIntent().getExtras();
        if (Bundle != null)
        {
            page = Bundle.getString("page");
            username = Bundle.getString("Username");
            Fos7a_Name = Bundle.getString("Fos7a_Name");
            Host_Username = Bundle.getString("Host_Username");
            Description = Bundle.getString("Description");
            Capacity = Integer.toString(Bundle.getInt("Capacity"));
            Fos7a_Date = Bundle.getString("Fos7a_Date");
            Fos7a_Time = Bundle.getString("Fos7a_Time");
            Is_Public = Bundle.getInt("Is_Public");
            Place_Name = Bundle.getString("Place_Name");
            Image = Bundle.getString("Image");

            String imagePath = "file:///android_asset/" + Image;
            Glide.with(getApplicationContext())
                    .load(imagePath)
                    .into(fos7aImage);

            if(username.equals(Host_Username.toLowerCase()))
            {//I am the host
                show_requests_btn.setVisibility(View.VISIBLE);
                show_attendees_btn.setVisibility(View.VISIBLE);
                Join.setVisibility(View.INVISIBLE);
            }
            else
            {
                show_requests_btn.setVisibility(View.INVISIBLE);
                show_attendees_btn.setVisibility(View.INVISIBLE);
                Join.setVisibility(View.VISIBLE);
                checkFos7a();
            }

            fos7aTitle.setText(Fos7a_Name);
            fos7aDescription.setText(Description);
            fos7aCapacity.setText(Capacity);
            fos7aDate.setText(Fos7a_Date + ", " + Fos7a_Time);
            fos7aPublic.setText(Is_Public == 1 ? "Public" : "Private");
            fos7aHost.setText(Host_Username);
            Fos7a_Location.setText(Place_Name);
        }
    }

    private void checkFos7a()
    {
        String url = "http://10.0.2.2:4000/Check_Fos7a?Requester_USERNAME=" + username + "&Host_USERNAME=" + this.Host_Username + "&Fos7a_Name=" + this.Fos7a_Name
                + "&Fos7a_Date=" + this.Fos7a_Date + "&Fos7a_Time=" + this.Fos7a_Time ;

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                com.android.volley.Request.Method.GET, url, null,
                response ->
                {
                    try
                    {
                        Log.d("hi", response.toString());
                        if (response.length()>0)
                        {
                            JSONObject req = response.getJSONObject(0);
                            int accepted = req.getInt("Accepted");

                            if (accepted == 1)
                            {
                                status = true;
                                pending = false;
                                Join.setText("Cancel");
                                Join.setBackgroundColor(getResources().getColor(R.color.red));;
                                Join.setTextColor(getResources().getColor(R.color.white));
                            }
                            else
                            {
                                status = false;
                                pending = true;
                                Join.setText("Pending");
                                Join.setBackgroundColor(getResources().getColor(R.color.support_grey));;
                                Join.setTextColor(getResources().getColor(R.color.light_grey));
                            }
                        }
                        else
                        {
                            status = false;
                            pending = false;
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                },
                Throwable::printStackTrace
        );
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() == BackButton.getId())
        {
            if(page.equals("home"))
            {
                Intent P_H = new Intent(EventProfile.this, Home.class);
                P_H.putExtra("Username", username);
                startActivity(P_H);

            }
            else
            {
                Intent P_E = new Intent(EventProfile.this, Events.class);
                P_E.putExtra("Username", username);
                startActivity(P_E);
            }

        }

        else if (v.getId() == Join.getId())
        {
            if(!status && !pending)
            {
                RequestFos7a();
                pending = true;
                Join.setText("Pending");
                Join.setBackgroundColor(getResources().getColor(R.color.support_grey));;
                Join.setTextColor(getResources().getColor(R.color.light_grey));
            }

            else
            {
                DeleteReqFos7a();
                status = false;
                pending = false;
                Join.setText("Join Fos7a");
                Join.setBackgroundColor(getResources().getColor(R.color.blue));
                Join.setTextColor(getResources().getColor(R.color.main_grey));
            }
        }
        else if(v.getId() == show_requests_btn.getId())
        {
            showDialog("Requests",fos7aTitle.getText().toString(),fos7aHost.getText().toString(),fos7aTitle.getText().toString(),fos7aDate.getText().toString(),fos7aDate.getText().toString());
        }
        else if(v.getId() == show_attendees_btn.getId())
        {
            showDialog("Attendees",fos7aTitle.getText().toString(),fos7aHost.getText().toString(),fos7aTitle.getText().toString(),fos7aDate.getText().toString(),fos7aDate.getText().toString());
        }


    }

    private void showDialog(String Type,String Requester_USERNAME,String Host_USERNAME,String Fos7a_Name,String Fos7a_Date,String Fos7a_Time)
    {
        // Inflate the custom layout
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.requests_dialouge, null);
        title = dialogView.findViewById(R.id.title);
        title.setText(Type);


        if(Type.equals("Attendees"))
        {
            fetchRequests("Attendee");
        }
        else
        {
            fetchRequests("Request");
        }

        // Set up RecyclerView inside the dialog view
        recyclerView = dialogView.findViewById(R.id.requests_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Request_Adapter = new RequestAdapter(this,getApplicationContext(), rows);
        Request_Adapter.setActivity(this);
        recyclerView.setAdapter(Request_Adapter);


        // Create the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView)
                .create()
                .show();

    }

    private void fetchRequests(String Type)
    {
        rows.clear();
        String url = "http://10.0.2.2:4000/Fosa7_Requests?Host_Username=" + this.Host_Username + "&Fos7a_Name=" + this.Fos7a_Name
                + "&Fos7a_Date=" + this.Fos7a_Date + "&Fos7a_Time=" + this.Fos7a_Time + "&type=" + Type;

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                com.android.volley.Request.Method.GET, url, null,
                response ->
                {
                    try {
                        for (int i = 0; i < response.length(); i++)
                        {
                            JSONObject place = response.getJSONObject(i);
                            String user_name = place.getString("Username");
                            String user_image = place.getString("ProfilePic");

                            rows.add(new Request(user_name,0,0,0,user_image));
                            int type_int = Type.equals("Request")?1:2;
                            rows.get(i).setIsRequest(type_int);
                        }
                        Request_Adapter.notifyDataSetChanged();
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

    protected void updateRequest(String requester_username)
    {
        String url = "http://10.0.2.2:4000/Accept_Fos7a?Requester_USERNAME=" + requester_username + "&Host_USERNAME=" + this.Host_Username + "&Fos7a_Name=" + this.Fos7a_Name
                + "&Fos7a_Date=" + this.Fos7a_Date + "&Fos7a_Time=" + this.Fos7a_Time ;

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                com.android.volley.Request.Method.GET, url, null,
                response ->
                {
                    try
                    {
                        Request_Adapter.notifyDataSetChanged();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                },
                Throwable::printStackTrace
        );
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onItemClicked(int recycleViewID, int position)
    {}

    private void RequestFos7a()
    {
        String url = "http://10.0.2.2:4000/Request_Fos7a?Requester_USERNAME=" + username + "&Host_USERNAME=" + this.Host_Username + "&Fos7a_Name=" + this.Fos7a_Name
                + "&Fos7a_Date=" + this.Fos7a_Date + "&Fos7a_Time=" + this.Fos7a_Time ;

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                com.android.volley.Request.Method.GET, url, null,
                response ->
                {
                    try
                    {
                        Log.d("hi", response.toString());
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                },
                Throwable::printStackTrace
        );
        requestQueue.add(jsonArrayRequest);
    }

    private void DeleteReqFos7a()
    {
        String url = "http://10.0.2.2:4000/Cancel_Fos7a_Req?Requester_USERNAME=" + username + "&Host_USERNAME=" + this.Host_Username + "&Fos7a_Name=" + this.Fos7a_Name
                + "&Fos7a_Date=" + this.Fos7a_Date + "&Fos7a_Time=" + this.Fos7a_Time ;

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                com.android.volley.Request.Method.GET, url, null,
                response ->
                {
                    try
                    {
                        Log.d("hi", response.toString());
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                },
                Throwable::printStackTrace
        );
        requestQueue.add(jsonArrayRequest);
    }
}