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
import android.widget.PopupWindow;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;


public class Listat extends AppCompatActivity implements View.OnClickListener, RecyclerViewInterface
{
    private String username;
    private ImageButton Event;
    private ImageButton Home;
    private ImageButton Friends;
    private ImageButton Recommender;
    private PopupWindow popupWindow;
    private ImageButton Profile;
    private Button[] List_Buttons = new android.widget.Button[5];
    private boolean[] List_Status= new boolean[5];
    private SearchView searchView;
    private List<Place> places_list = new ArrayList<Place>();
    private List<Place> Searchlist = new ArrayList<Place>();
    private PlaceAdapter placeAdapter;
    private RecyclerView recyclerView;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
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

        placeAdapter = new PlaceAdapter(getApplicationContext(),places_list,this);

        Event = findViewById(R.id.small_event_btn);
        Home = findViewById(R.id.small_home_btn);
        Friends = findViewById(R.id.small_friends_btn);
        Recommender = findViewById(R.id.small_recommender_btn);
        Profile = findViewById(R.id.profile_btn);
        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.places_recyclerview);

        Event.setOnClickListener(this);
        Home.setOnClickListener(this);
        Friends.setOnClickListener(this);
        Recommender.setOnClickListener(this);
        Profile.setOnClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(placeAdapter);

        FetchPlaces("All");

        int[] ListbuttonIDs =
                {
                        R.id.list_filter_btn, R.id.sports_filter_btn, R.id.food_filter_btn, R.id.activity_filter_btn,
                        R.id.sightseeing_filter_btn
                };

        for (int i = 0; i < 5; i++)
        {
            List_Buttons[i] = findViewById(ListbuttonIDs[i]);
            List_Buttons[i].setOnClickListener(this);
        }

        List_Buttons[0].setBackgroundColor(Color.parseColor("#2C2B2B"));
        List_Buttons[0].setTextColor(Color.parseColor("#41F2F8"));
        List_Status[0] = true;

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                performSearch(newText);
                return true;
            }
        });

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (hasFocus)
                    showDropdown();

                else
                {
                    if (popupWindow != null && popupWindow.isShowing())
                        popupWindow.dismiss();
                }
            }
        });
    }

    private void FetchPlaces(String Type)
    {
        places_list.clear();
        Log.d("Listat", Type);
        String url = "http://10.0.2.2:4000/Fetch_Places?Cat=" + Type; //add type
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        int ID[]={R.drawable.adrenaline_park_laser_tag,R.drawable.bibliotecha,R.drawable.b,R.drawable.befit_360,R.drawable.bounce_egypt};

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                response ->
                {
                    try {
                        for (int i = 0; i < response.length(); i++)
                        {
                            JSONObject place = response.getJSONObject(i);
                            String name = place.getString("Place_Name");
                            String location = place.getString("Address");
                            String description = place.getString("Description");
                            String phone = place.getString("Phone");
                            String openingTime = place.getString("OpeningTime");
                            String closingTime = place.getString("ClosingTime");
                            String workingDays = place.getString("WorkingDays");
                            String Image = place.getString("PlacePic");
                            places_list.add(new Place(name, location, description, phone, openingTime, closingTime, workingDays, Image));
                        }
                        placeAdapter.notifyDataSetChanged();
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


    private void performSearch(String text)
    {
        if (text.isEmpty())
        {
            Searchlist.clear();
            showDropdown();
            return;
        }
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:4000/Search?Type=Listat&Name=" + text;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                response ->
                {
                    try {
                        Searchlist.clear();
                        for (int i = 0; i < response.length(); i++)
                        {

                            JSONObject place = response.getJSONObject(i);
                            String name = place.getString("Place_Name");
                            String location = place.getString("Address");
                            String description = place.getString("Description");
                            String phone = place.getString("Phone");
                            String openingTime = place.getString("OpeningTime");
                            String closingTime = place.getString("ClosingTime");
                            String workingDays = place.getString("WorkingDays");
                            String Image = place.getString("PlacePic");
                            Searchlist.add(new Place(name, location, description, phone, openingTime, closingTime, workingDays, Image));
                        }
                        showDropdown();
                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                        Toast.makeText(Listat.this, "Search failed", Toast.LENGTH_SHORT).show();
                    }
                },
                error ->
                {
                    error.printStackTrace();
                    Toast.makeText(Listat.this, "Search failed", Toast.LENGTH_SHORT).show();
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    private void showDropdown() {
        if (popupWindow == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.dropdown_search_results, null);
            RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            PlaceAdapter adapter = new PlaceAdapter(getApplicationContext(), Searchlist, new RecyclerViewInterface() {
                @Override
                public void onItemClicked(int recycleViewID, int position) {
                    handleItemClick(1, position);
                }
            });
            recyclerView.setAdapter(adapter);

            popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, 1000);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setFocusable(false);
        } else {
            View view = popupWindow.getContentView();
            RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
            recyclerView.getAdapter().notifyDataSetChanged();
        }

        if (!popupWindow.isShowing() && !Searchlist.isEmpty())
            popupWindow.showAsDropDown(searchView);
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId()==Event.getId())
            Go_Event();

        else if (v.getId()==Home.getId())
            Go_Home();

        else if (v.getId()==Friends.getId())
            Go_Friends();

        else if (v.getId()==Recommender.getId())
            Go_Recommender();

        else if (v.getId()==Profile.getId())
            Go_Profile();

        else
            Go_Filter(v.getId());

    }

    private void Go_Home()
    {
        Intent L_H = new Intent(Listat.this, Home.class);
        L_H.putExtra("Username", username);
        startActivity(L_H);
    }

    private void Go_Friends()
    {
        Intent L_F = new Intent(Listat.this, Friends.class);
        L_F.putExtra("Username", username);
        startActivity(L_F);
    }

    private void Go_Profile()
    {
        Intent L_P = new Intent(Listat.this, Profile.class);
        L_P.putExtra("Username", username);
        startActivity(L_P);
    }

    private void Go_Recommender()
    {
        Intent L_R = new Intent(Listat.this, Recommender.class);
        L_R.putExtra("Username", username);
        startActivity(L_R);
    }

    private void Go_Event()
    {
        Intent L_E = new Intent(Listat.this, Events.class);
        L_E.putExtra("Username", username);
        startActivity(L_E);
    }

    public void Go_Filter(int id)
    {
        for (int i = 0; i < 5; i++)
        {
            if (id == List_Buttons[i].getId())
                List_Status[i] = true;
            else
                List_Status[i] = false;

            if (List_Status[i])
            {
                List_Buttons[i].setBackgroundColor(Color.parseColor("#2C2B2B"));
                List_Buttons[i].setTextColor(Color.parseColor("#41F2F8"));
            }
            else
            {
                List_Buttons[i].setBackgroundColor(Color.parseColor("#41F2F8"));
                List_Buttons[i].setTextColor(Color.parseColor("#2C2B2B"));
            }
        }
        Button myButton = findViewById(id);
        String buttonText = myButton.getText().toString();
        FetchPlaces(buttonText);
    }
    @Override
    public void onItemClicked(int recycleViewID, int position) {
        handleItemClick(recycleViewID, position);
    }

    private void handleItemClick(int recycleViewID, int position) {
        if (recycleViewID == 1) {
            Intent intent = new Intent(this, PlaceProfile.class);
            Place place = Searchlist.get(position);
            intent.putExtra("page", "Listat");
            intent.putExtra("Username", username);
            intent.putExtra("Location", place.location);
            intent.putExtra("Description", place.description);
            intent.putExtra("OpeningTime", place.OpeningTime);
            intent.putExtra("ClosingTime",place.ClosingTime);
            intent.putExtra("Phone", place.phone);
            intent.putExtra("Name", place.name);
            intent.putExtra("WorkingDays", place.WorkingDays);
            intent.putExtra("Image", place.image);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, PlaceProfile.class);
            intent.putExtra("page", "Listat");
            intent.putExtra("Username", username);
            intent.putExtra("Location", places_list.get(position).location);
            intent.putExtra("Description", places_list.get(position).description);
            intent.putExtra("OpeningTime", places_list.get(position).OpeningTime);
            intent.putExtra("ClosingTime", places_list.get(position).ClosingTime);
            intent.putExtra("Phone", places_list.get(position).phone);
            intent.putExtra("Name", places_list.get(position).name);
            intent.putExtra("WorkingDays", places_list.get(position).WorkingDays);
            intent.putExtra("Image", places_list.get(position).image);
            startActivity(intent);
        }
    }

}