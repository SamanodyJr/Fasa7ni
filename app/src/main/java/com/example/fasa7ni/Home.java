package com.example.fasa7ni;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.SearchView;
import android.widget.Toast;

import com.bumptech.glide.Glide;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity implements View.OnClickListener,RecyclerViewInterface
{
    private String username;
    private String area;
    private ImageButton Listat;
    private ImageButton Events;
    private ImageButton Friends;
    private ImageButton Recommender;
    private ImageButton Profile;
    private SearchView searchView;
    private List<Event> list = new ArrayList<Event>();
    private List<Place> places_list = new ArrayList<Place>();
    private List<Object> combinedList = new ArrayList<>();
    private PopupWindow popupWindow;
    private EventAdapter Event_Adapter;
    private PlaceAdapter placeAdapter;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;



    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
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
        FetchArea();
        Event_Adapter = new EventAdapter(getApplicationContext(),list,this);
        placeAdapter = new PlaceAdapter(getApplicationContext(),places_list,this);

        recyclerView = findViewById(R.id.upcoming_fosa7_recyclerView);
        recyclerView2 = findViewById(R.id.intrests_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(Event_Adapter);
        recyclerView2.setAdapter(placeAdapter);

        searchView = findViewById(R.id.searchView);


        Listat = findViewById(R.id.small_list_btn);
        Events = findViewById(R.id.small_event_btn);
        Friends = findViewById(R.id.small_friends_btn);
        Recommender = findViewById(R.id.small_recommender_btn);
        Profile = findViewById(R.id.profile_btn);

        Listat.setOnClickListener(this);
        Events.setOnClickListener(this);
        Friends.setOnClickListener(this);
        Recommender.setOnClickListener(this);
        Profile.setOnClickListener(this);

        FetchUpcoming();

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
    private void FetchUpcoming()
    {
        list.clear();
        String url;

        url = "http://10.0.2.2:4000/Fetch_My_Fosa7?Username="+username; //add type

        RequestQueue requestQueue = Volley.newRequestQueue(this);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                response ->
                {
                    Log.d("Home",response.toString());
                    try {
                        for (int i = 0; i < response.length(); i++)
                        {
                            JSONObject place = response.getJSONObject(i);
                            String name = place.getString("Fos7a_Name");
                            String Host = place.getString("Host_Username");
                            String description = place.getString("Description");
                            int cap = place.getInt("Capacity");
                            String Fos7a_Date = place.getString("Fos7a_Date").substring(0, Math.min(place.getString("Fos7a_Date").length(), 10));;
                            String Fos7a_Time = place.getString("Fos7a_Time");
                            int Is_Public = place.getInt("Is_Public");
                            String Place_Name = place.getString("Place_Name");
                            String Image = place.getString("Pic");
                            list.add(new Event(name, Host, description,Fos7a_Time, Fos7a_Date,cap, Image, Is_Public, Place_Name));
                        }
                        Event_Adapter.notifyDataSetChanged();
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
    private void FetchPlaces()
    {
        places_list.clear();

        if(area == null)
            area = "All";
        String url = "http://10.0.2.2:4000/Fetch_Places?Cat="+area; //add type
        RequestQueue requestQueue = Volley.newRequestQueue(this);

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


    private void FetchArea()
    {
        String url = "http://10.0.2.2:4000/Fetch_User_Area?username="+username; //add type
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                response ->
                {
                    try {
                        JSONObject place = response.getJSONObject(0);
                        area = place.getString("Area");
                        FetchPlaces();
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
            combinedList.clear();
            showDropdown();
            return;
        }
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:4000/Search?Type=Home&Name=" + text;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                response -> {
                    try {
                        combinedList.clear();
                        for (int i = 0; i < response.length(); i++)
                        {

                            JSONArray jsonArray = response.getJSONArray(i);
                            for (int j = 0; j < jsonArray.length(); j++)
                            {
                                JSONObject obj = jsonArray.getJSONObject(j);
                                if (i == 0)
                                { // Places
                                    String name = obj.getString("Place_Name");
                                    String location = obj.getString("Address");
                                    String description = obj.getString("Description");
                                    String phone = obj.getString("Phone");
                                    String openingTime = obj.getString("OpeningTime");
                                    String closingTime = obj.getString("ClosingTime");
                                    String workingDays = obj.getString("WorkingDays");
                                    String Image = obj.getString("PlacePic");
                                    combinedList.add(new Place(name, location, description, phone, openingTime, closingTime, workingDays, Image));
                                }
                                else
                                { // Events
                                    String name = obj.getString("Fos7a_Name");
                                    String Host = obj.getString("Host_Username");
                                    String description = obj.getString("Description");
                                    int cap = obj.getInt("Capacity");
                                    String Fos7a_Date = obj.getString("Fos7a_Date");
                                    String Fos7a_Time = obj.getString("Fos7a_Time");
                                    int Is_Public = obj.getInt("Is_Public");
                                    String Place_Name = obj.getString("Place_Name");
                                    String Image = obj.getString("Pic");
                                    combinedList.add(new Event(name, Host, description, Fos7a_Time, Fos7a_Date, cap, Image, Is_Public, Place_Name));
                                }
                            }
                        }
                        showDropdown();
                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                        Toast.makeText(Home.this, "Search failed", Toast.LENGTH_SHORT).show();
                    }
                },
                error ->
                {
                    error.printStackTrace();
                    Toast.makeText(Home.this, "Search failed", Toast.LENGTH_SHORT).show();
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    private void showDropdown() {
        if (popupWindow == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.dropdown_search_results, null);
            RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            CombinedAdapter adapter = new CombinedAdapter(getApplicationContext(), combinedList, new RecyclerViewInterface() {
                @Override
                public void onItemClicked(int recycleViewID, int position) {
                    handleItemClick(2, position);
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

        if (!popupWindow.isShowing() && !combinedList.isEmpty())
            popupWindow.showAsDropDown(searchView);
    }


    @Override
    public void onClick(View v)
    {
        if(v.getId()==Listat.getId())
            Go_List();

        else if (v.getId()==Events.getId())
            Go_Events();

        else if (v.getId()==Friends.getId())
            Go_Friends();

        else if (v.getId()==Recommender.getId())
            Go_Recommender();

        else if (v.getId()==Profile.getId())
            Go_Profile();

        else
            return;

    }

    private void Go_List()
    {
        Intent H_L = new Intent(Home.this, Listat.class);
        H_L.putExtra("Username", username);
        startActivity(H_L);
    }

    private void Go_Events()
    {
        Intent H_E = new Intent(Home.this, Events.class);
        H_E.putExtra("Username", username);
        startActivity(H_E);
    }

    private void Go_Friends()
    {
        Intent H_F = new Intent(Home.this, Friends.class);
        H_F.putExtra("Username", username);
        startActivity(H_F);
    }

    private void Go_Recommender()
    {
        Intent H_R = new Intent(Home.this, Recommender.class);
        H_R.putExtra("Username", username);
        startActivity(H_R);
    }

    private void Go_Profile()
    {
        Intent H_P = new Intent(Home.this, Profile.class);
        H_P.putExtra("Username", username);
        startActivity(H_P);
    }



    @Override
    public void onItemClicked(int recycleViewID, int position) {
        handleItemClick(recycleViewID, position);
    }

    private void handleItemClick(int recycleViewID, int position) {
        if (recycleViewID == 0) {
            Intent intent = new Intent(this, PlaceProfile.class);
            Place place = places_list.get(position);
            intent.putExtra("page", "home");
            intent.putExtra("Username", username);
            intent.putExtra("Location", place.getLocation());
            intent.putExtra("Description", place.getDescription());
            intent.putExtra("OpeningTime", place.getOpeningTime());
            intent.putExtra("ClosingTime", place.getClosingTime());
            intent.putExtra("Phone", place.getPhone());
            intent.putExtra("Name", place.getName());
            intent.putExtra("WorkingDays", place.getWorkingDays());
            intent.putExtra("Image", place.getImage());
            startActivity(intent);
        } else if (recycleViewID == 1) {
            Intent intent = new Intent(this, EventProfile.class);
            Event event = list.get(position);
            intent.putExtra("Username", username);
            intent.putExtra("page", "home");
            intent.putExtra("Fos7a_Name", event.getName());
            intent.putExtra("Host_Username", event.getHostName());
            intent.putExtra("Description", event.getDescription());
            intent.putExtra("Capacity", event.getCapacity());
            intent.putExtra("Fos7a_Date", event.getDate());
            intent.putExtra("Fos7a_Time", event.getFos7a_Time());
            intent.putExtra("Is_Public", event.getIs_Public());
            intent.putExtra("Place_Name", event.getLocation());
            intent.putExtra("Image", event.getImage());
            startActivity(intent);
        } else if (recycleViewID == 2) {
            if(combinedList.get(position) instanceof Place){
                Intent intent = new Intent(this, PlaceProfile.class);
                Place place = (Place) combinedList.get(position);
                intent.putExtra("page", "home");
                intent.putExtra("Username", username);
                intent.putExtra("Location", place.getLocation());
                intent.putExtra("Description", place.getDescription());
                intent.putExtra("OpeningTime", place.getOpeningTime());
                intent.putExtra("ClosingTime", place.getClosingTime());
                intent.putExtra("Phone", place.getPhone());
                intent.putExtra("Name", place.getName());
                intent.putExtra("WorkingDays", place.getWorkingDays());
                intent.putExtra("Image", place.getImage());
                startActivity(intent);
            }
            else if (combinedList.get(position) instanceof Event ) {
                Intent intent = new Intent(this, EventProfile.class);
                Event event = (Event) combinedList.get(position);
                intent.putExtra("page", "home");
                intent.putExtra("Username", username);
                intent.putExtra("Fos7a_Name", event.getName());
                intent.putExtra("Host_Username", event.getHostName());
                intent.putExtra("Description", event.getDescription());
                intent.putExtra("Capacity", event.getCapacity());
                intent.putExtra("Fos7a_Date", event.getDate());
                intent.putExtra("Fos7a_Time", event.getFos7a_Time());
                intent.putExtra("Is_Public", event.getIs_Public());
                intent.putExtra("Place_Name", event.getLocation());
                intent.putExtra("Image", event.getImage());
                startActivity(intent);

            }

        }
    }
}