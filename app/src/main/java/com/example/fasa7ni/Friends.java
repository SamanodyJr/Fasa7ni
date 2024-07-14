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

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Friends extends AppCompatActivity implements View.OnClickListener, RecyclerViewInterface
{
    private String email;
    private ImageButton Listat;
    private ImageButton Home;
    private ImageButton Profile;
    private ImageButton Events;
    private ImageButton Recommender;
    private PopupWindow popupWindow;
    private List<Friend> list = new ArrayList<Friend>();
    private SearchView searchView;
    private List<Request> req = new ArrayList<Request>();
    private RequestAdapter Request_Adapter;
    private FriendAdapter Friend_Adapter;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;




    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Start();
    }

    private void Start()
    {

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

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            email = bundle.getString("Email");
        }
        Request_Adapter = new RequestAdapter(this,getApplicationContext(),req);
        Friend_Adapter = new FriendAdapter(this,getApplicationContext(),list);

        recyclerView = findViewById(R.id.friends_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(Friend_Adapter);

        recyclerView2 = findViewById(R.id.requests_recyclerView);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setAdapter(Request_Adapter);

        Listat = findViewById(R.id.small_list_btn);
        Events = findViewById(R.id.small_event_btn);
        Home = findViewById(R.id.small_home_btn);
        Recommender = findViewById(R.id.small_recommender_btn);
        Profile = findViewById(R.id.profile_btn);

        Listat.setOnClickListener(this);
        Events.setOnClickListener(this);
        Home.setOnClickListener(this);
        Recommender.setOnClickListener(this);
        Profile.setOnClickListener(this);

        FetchFriends();

    }
    private void FetchFriends()
    {
        list.clear();
        req.clear();
        String url;
        url = "http://10.0.2.2:4000/Fetch_Friends?Reciever_Email="+email; //add Email

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        int ID[]={R.drawable.heggo_image,R.drawable.heggo_image,R.drawable.sarah};

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                com.android.volley.Request.Method.GET, url, null,
                response ->
                {
                    Log.d("Requester", Integer.toString(response.length()));
                    try {
                        for (int i = 0; i < response.length(); i++)
                        {
                            JSONObject friend = response.getJSONObject(i);
                            String Username = friend.getString("Username");
                            int Accepted = friend.getInt("Accepted");
                            int Image=ID[i%3];
                            if(Accepted==1)
                                list.add(new Friend(Username,Image,23,R.id.remove_friend_btn));
                            else
                                req.add(new Request(Username,100,R.id.remove_friend_btn,R.id.add_friend_btn,Image));
                        }
                        Friend_Adapter.notifyDataSetChanged();
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
            recyclerView.setAdapter(new RequestAdapter(this,getApplicationContext(),req)); // Assuming EventAdapter constructor accepts List<Event>

            popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, 1000);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setFocusable(false);

        }

        if (!popupWindow.isShowing() && !list.isEmpty()) {
            popupWindow.showAsDropDown(searchView);
        }
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==Listat.getId())
            Go_List();

        else if (v.getId()==Events.getId())
            Go_Events();

        else if (v.getId()==Home.getId())
            Go_Home();

        else if (v.getId()==Recommender.getId())
            Go_Recommender();

        else if (v.getId()==Profile.getId())
            Go_Profile();

        else
            return;

    }

    private void Go_List()
    {
        Intent F_L = new Intent(Friends.this, Listat.class);
        F_L.putExtra("Email", email);
        startActivity(F_L);
    }

    private void Go_Events()
    {
        Intent F_E = new Intent(Friends.this, Events.class);
        F_E.putExtra("Email", email);
        startActivity(F_E);
    }

    private void Go_Home()
    {
        Intent F_H = new Intent(Friends.this, Home.class);
        F_H.putExtra("Email", email);
        startActivity(F_H);
    }

    private void Go_Recommender()
    {
        Intent F_R = new Intent(Friends.this, Recommender.class);
        F_R.putExtra("Email", email);
        startActivity(F_R);
    }

    private void Go_Profile()
    {
        Intent F_P = new Intent(Friends.this, Profile.class);
        F_P.putExtra("Email", email);
        startActivity(F_P);
    }

    @Override
    public void onItemClicked(int recycleViewID, int position) {
        if (recycleViewID == 2) { // Remove item from list
            list.remove(position);
            RecyclerView recyclerView = findViewById(R.id.friends_recyclerView);
            recyclerView.getAdapter().notifyItemRemoved(position);
            recyclerView.getAdapter().notifyItemRangeChanged(position, list.size());
        } else if (recycleViewID == 1) { // View profile
            Intent intent = new Intent(this, Profile.class);
            startActivity(intent);
        } else if (recycleViewID == 3) { //Request
            Intent intent = new Intent(this, Profile.class);
            startActivity(intent);
        }
    }
}