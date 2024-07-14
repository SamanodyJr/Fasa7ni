package com.example.fasa7ni;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Events extends AppCompatActivity implements View.OnClickListener, RecyclerViewInterface
{
    private String email;
    private ImageButton Listat;
    private ImageButton Home;
    private ImageButton Friends;
    private ImageButton Recommender;
    private ImageButton Profile;
    private ImageButton AddFos7a;
    private PopupWindow popupWindow;
    private Button[] Event_Buttons = new Button[4];
    private boolean[] Event_Status= new boolean[4];
    private SearchView searchView;
    private List<Event> list = new ArrayList<Event>();
    private EventAdapter Event_Adapter;
    private RecyclerView recyclerView;


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Start();
        searchView = findViewById(R.id.searchView);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Perform search when user submits query
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Perform search when query text changes
                performSearch(newText);
                return true;
            }
        });

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showDropdown();
                } else {
                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
                }
            }
        });
    }

    private void FetchEvents(String Type)
    {
        list.clear();
        String url;
        if(Type=="Mine")
            url = "http://10.0.2.2:4000/Fetch_My_Fosa7?Email=" + email; //add type
        else
            url = "http://10.0.2.2:4000/Fetch_Fosa7?Type=" + Type; //add type

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        int ID[]={
                R.drawable.adrenaline_park_laser_tag,
                R.drawable.the_great_pyramid_of_giza,
                R.drawable.padel
        };

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                response ->
                {
                    try {
                        for (int i = 0; i < response.length(); i++)
                        {
                            JSONObject place = response.getJSONObject(i);
                            String name = place.getString("Fos7a_Name");
                            String Host = place.getString("Host_Email");
                            String description = place.getString("Description");
                            int cap = place.getInt("Capacity");
                            String Fos7a_Date = place.getString("Fos7a_Date").substring(0, Math.min(place.getString("Fos7a_Date").length(), 10));;
                            String Fos7a_Time = place.getString("Fos7a_Time");
                            int Is_Public = place.getInt("Is_Public");
                            String Place_Name = place.getString("Place_Name");
                            String blank=Place_Name.replaceAll("_", " ").toLowerCase();
                            int Image =ID[i];
                            list.add(new Event(name, Host, description,Fos7a_Time, Fos7a_Date,cap, Image, Is_Public, Place_Name));
//                            Log.d("Image Name 2",Integer.toString(R.drawable.the_great_pyramid_of_giza));
//                            Log.d("Image Name 3",Integer.toString(Image));
                        }
                        Event_Adapter.notifyDataSetChanged();
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                Throwable::printStackTrace
        );
        requestQueue.add(jsonArrayRequest);

    }


    private void performSearch(String text) {
        if (list.isEmpty()) {
            popupWindow.dismiss();
        } else {
            showDropdown();
        }
    }

    private void showDropdown() {
        if (popupWindow == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.dropdown_search_results, null);
            RecyclerView recyclerView = view.findViewById(R.id.recyclerView); // Corrected line
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new EventAdapter(this,getApplicationContext(),list)); // Assuming EventAdapter constructor accepts List<Event>

            popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, 1000);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setFocusable(false);

        }

        if (!popupWindow.isShowing() && !list.isEmpty()) {
            popupWindow.showAsDropDown(searchView);
        }
    }



    private void Start()
    {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            email = bundle.getString("Email");
        }

        Event_Adapter = new EventAdapter(this,getApplicationContext(),list);

        Listat = findViewById(R.id.small_list_btn);
        Home = findViewById(R.id.small_home_btn);
        Friends = findViewById(R.id.small_friends_btn);
        Recommender = findViewById(R.id.small_recommender_btn);
        Profile = findViewById(R.id.profile_btn);
        AddFos7a = findViewById(R.id.add_fos7a_btn);

        Listat.setOnClickListener(this);
        Home.setOnClickListener(this);
        Friends.setOnClickListener(this);
        Recommender.setOnClickListener(this);
        Profile.setOnClickListener(this);
        AddFos7a.setOnClickListener(this);

        int[] EventbuttonIDs =
                {
                        R.id.events_filter_btn, R.id.public_filter_btn, R.id.private_filter_btn, R.id.mine_btn
                };

        for (int i = 0; i < 4; i++) {
            Event_Buttons[i] = findViewById(EventbuttonIDs[i]);
            Event_Buttons[i].setOnClickListener(this);
        }

        Event_Buttons[0].setBackgroundColor(Color.parseColor("#2C2B2B"));
        Event_Buttons[0].setTextColor(Color.parseColor("#41F2F8"));
        Event_Status[0] = true;

        RecyclerView recyclerView = findViewById(R.id.fosa7_recylcerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(Event_Adapter);
        FetchEvents("All");
    }

    @Override
    public void onClick(View v)
    {

        if(v.getId()==Listat.getId())
            Go_List();

        else if (v.getId()==Home.getId())
            Go_Home();

        else if (v.getId()==Friends.getId())
            Go_Friends();

        else if (v.getId()==Recommender.getId())
            Go_Recommender();

        else if (v.getId()==Profile.getId())
            Go_Profile();

        else if (v.getId()==AddFos7a.getId())
            Go_Create();
        else
            Go_Filter(v.getId());

    }

    private void Go_List()
    {
        Intent E_L = new Intent(Events.this, Listat.class);
        E_L.putExtra("Email", email);
        startActivity(E_L);
    }

    private void Go_Home()
    {
        Intent E_H = new Intent(Events.this, Home.class);
        E_H.putExtra("Email", email);
        startActivity(E_H);
    }

    private void Go_Friends()
    {
        Intent E_F = new Intent(Events.this, Friends.class);
        E_F.putExtra("Email", email);
        startActivity(E_F);
    }

    private void Go_Profile()
    {
        Intent E_P = new Intent(Events.this, Profile.class);
        E_P.putExtra("Email", email);
        startActivity(E_P);
    }

    private void Go_Recommender()
    {
        Intent E_R = new Intent(Events.this, Recommender.class);
        E_R.putExtra("Email", email);
        startActivity(E_R);
    }

    private void Go_Create()
    {
        Intent E_C = new Intent(Events.this, Create.class);
        E_C.putExtra("Email", email);
        startActivity(E_C);
    }

    public void Go_Filter(int id) {
        for (int i = 0; i < 4; i++)
        {
            if (id == Event_Buttons[i].getId())
                Event_Status[i] = true;
            else
                Event_Status[i] = false;

            if (Event_Status[i])
            {
                Event_Buttons[i].setBackgroundColor(Color.parseColor("#2C2B2B"));
                Event_Buttons[i].setTextColor(Color.parseColor("#41F2F8"));
            }
            else
            {
                Event_Buttons[i].setBackgroundColor(Color.parseColor("#41F2F8"));
                Event_Buttons[i].setTextColor(Color.parseColor("#2C2B2B"));
            }
        }
        Button myButton = findViewById(id);
        String buttonText = myButton.getText().toString();
        switch (buttonText){
            case ("Mine"):
                FetchEvents("Mine");
                break;
            case("Public"):
                FetchEvents("1");
                break;
            case("Private"):
                FetchEvents("0");
                break;
            case("All"):
                FetchEvents("All");
                break;
            default:
                FetchEvents("All");
                break;

        }
    }


    @Override
    public void onItemClicked(int recycleViewID, int position) {
        Intent intent = new Intent(this, EventProfile.class);
        startActivity(intent);
    }
}