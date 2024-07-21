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

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Friends extends AppCompatActivity implements View.OnClickListener, RecyclerViewInterface
{
    private String username;
    private ImageButton Listat;
    private ImageButton Home;
    private ImageButton Profile;
    private ImageButton Events;
    private ImageButton Recommender;
    private PopupWindow popupWindow;
    private List<Friend> list = new ArrayList<Friend>();
    private SearchView searchView;
    private List<Request> req = new ArrayList<Request>();
    private List<Request> req_search = new ArrayList<Request>();
    private RequestAdapter Request_Adapter;
    private FriendAdapter Friend_Adapter;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private RequestAdapter adapter;


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

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            username = bundle.getString("Username");
        }
        Request_Adapter = new RequestAdapter(this,getApplicationContext(),req);
        Request_Adapter.setActivity2(this);
        Friend_Adapter = new FriendAdapter(this,getApplicationContext(),list);
        Friend_Adapter.setActivity(this);

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
        url = "http://10.0.2.2:4000/Fetch_Friends?Reciever_USERNAME="+username;

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                com.android.volley.Request.Method.GET, url, null,
                response ->
                {
                    Log.d("Requester", response.toString());
                    try {
                        int j = 0;
                        for (int i = 0; i < response.length(); i++)
                        {
                            JSONObject friend = response.getJSONObject(i);
                            String Username = friend.getString("Username");
                            int Accepted = friend.getInt("Accepted");
                            String Image = friend.getString("ProfilePic");
                            if(Accepted==1)
                                list.add(new Friend(Username,Image,23,R.id.remove_friend_btn));
                            else
                            {
                                req.add(new Request(Username,100,R.id.remove_friend_btn,R.id.add_friend_btn,Image));
                                req.get(j).setIsRequest(0);
                                j++;
                            }
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


    private void performSearch(String text)
    {
        if (text.isEmpty())
        {
            req_search.clear();
            showDropdown();
            return;
        }
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:4000/Search?Type=Friend&Name=" + text + "&user=" + username;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                com.android.volley.Request.Method.GET, url, null,
                response -> {
                    try {
                        req_search.clear();
                        Log.d("Search", "Search results: " + response.toString());
                        for (int i = 0; i < response.length(); i++)
                        {
                            JSONObject request = response.getJSONObject(i);
                            String Username = request.getString("Username");
                            String Image = request.getString("ProfilePic");
                            if(!Username.equals(username))
                                req_search.add(new Request(Username,20, R.id.add_friend_btn,Image));
                        }
                        showDropdown();
                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                        Toast.makeText(Friends.this, "Search failed", Toast.LENGTH_SHORT).show();
                    }
                },
                error ->
                {
                    error.printStackTrace();
                    Toast.makeText(Friends.this, "Search failed", Toast.LENGTH_SHORT).show();
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    private void showDropdown()
    {
        if (popupWindow == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.dropdown_search_results, null);
            RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new RequestAdapter(this,getApplicationContext(), req_search);
            adapter.setActivity2(this);
            recyclerView.setAdapter(adapter);

            popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, 1000);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setFocusable(false);
        }
        else
        {
            View view = popupWindow.getContentView();
            RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
            recyclerView.getAdapter().notifyDataSetChanged();
        }

        if (!popupWindow.isShowing() && !req_search.isEmpty())
            popupWindow.showAsDropDown(searchView);
    }


    @Override
    public void onClick(View v)
    {
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
        F_L.putExtra("Username", username);
        startActivity(F_L);
    }

    private void Go_Events()
    {
        Intent F_E = new Intent(Friends.this, Events.class);
        F_E.putExtra("Username", username);
        startActivity(F_E);
    }

    private void Go_Home()
    {
        Intent F_H = new Intent(Friends.this, Home.class);
        F_H.putExtra("Username", username);
        startActivity(F_H);
    }

    private void Go_Recommender()
    {
        Intent F_R = new Intent(Friends.this, Recommender.class);
        F_R.putExtra("Username", username);
        startActivity(F_R);
    }

    private void Go_Profile()
    {
        Intent F_P = new Intent(Friends.this, Profile.class);
        F_P.putExtra("Username", username);
        startActivity(F_P);
    }

    @Override
    public void onItemClicked(int recycleViewID, int position)
    {
        if (recycleViewID == 2)
        { // Remove item from list
            list.remove(position);
            RecyclerView recyclerView = findViewById(R.id.friends_recyclerView);
            recyclerView.getAdapter().notifyItemRemoved(position);
            recyclerView.getAdapter().notifyItemRangeChanged(position, list.size());
        }

    }

    public void Accept_Friend(String requester)
    {
        String url;
        url = "http://10.0.2.2:4000/Accept_Friend?Requester_USERNAME=" + requester + "&Reciever_USERNAME=" + username;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Log.e("RequestAdapter", url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                com.android.volley.Request.Method.GET, url, null,
                response -> {
                    FetchFriends();
                    Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                },

                //Throwable::printStackTrace
                error -> {
                    // Handle errors
                    error.printStackTrace();
                    Toast.makeText(this, "Request failed", Toast.LENGTH_SHORT).show();
                }
        );
        requestQueue.add(jsonObjectRequest);

        String url2;
        url2 = "http://10.0.2.2:4000/Insert_Friend?Requester_USERNAME=" + username + "&Reciever_USERNAME=" + requester;
        RequestQueue requestQueue2 = Volley.newRequestQueue(this);
        Log.e("RequestAdapter", url2);
        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(
                com.android.volley.Request.Method.GET, url2, null,
                response -> {
                    Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                },

                //Throwable::printStackTrace
                error -> {
                    // Handle errors
                    error.printStackTrace();
                    Toast.makeText(this, "Request failed", Toast.LENGTH_SHORT).show();

                }
        );
        requestQueue2.add(jsonObjectRequest2);
    }

    public void Remove_Friend(String requester)
    {
        String url;
        url = "http://10.0.2.2:4000/Remove_Friend?Requester_USERNAME=" + requester + "&Reciever_USERNAME=" + username;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Log.e("RequestAdapter", url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                com.android.volley.Request.Method.GET, url, null,
                response -> {
                    Friend_Adapter.notifyDataSetChanged();
                    Request_Adapter.notifyDataSetChanged();
                    Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                },

                //Throwable::printStackTrace
                error -> {
                    // Handle errors
                    error.printStackTrace();
                    Log.d("RequestAdapter", "Request1 failed");
                }
        );
        requestQueue.add(jsonObjectRequest);

        String url2;
        url2 = "http://10.0.2.2:4000/Remove_Friend?Requester_USERNAME=" + username + "&Reciever_USERNAME=" + requester;
        RequestQueue requestQueue2 = Volley.newRequestQueue(this);
        Log.e("RequestAdapter", url2);
        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(
                com.android.volley.Request.Method.GET, url2, null,
                response -> {
                    Friend_Adapter.notifyDataSetChanged();
                    Request_Adapter.notifyDataSetChanged();
                    Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                },

                //Throwable::printStackTrace
                error -> {
                    // Handle errors
                    error.printStackTrace();
                    Log.d("RequestAdapter", "Request2 failed");
                }
        );
        requestQueue2.add(jsonObjectRequest2);
        FetchFriends();
    }

    public void RequestFriend(String Reciever, int position)
    {
        String url;
        url = "http://10.0.2.2:4000/Request_Friend?Requester_Username=" + username + "&Reciever_Username=" + Reciever;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Log.e("RequestAdapter", url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                com.android.volley.Request.Method.GET, url, null,
                response -> {
                    Toast.makeText(this, "Friend Request Sent", Toast.LENGTH_SHORT).show();
                    Friend_Adapter.notifyDataSetChanged();
                    Request_Adapter.notifyDataSetChanged();
                    req_search.remove(position);
                    adapter.notifyItemRemoved(position);
                    adapter.notifyDataSetChanged();
                },

                //Throwable::printStackTrace
                error -> {
                    // Handle errors
                    error.printStackTrace();
                    Toast.makeText(this, "Request failed", Toast.LENGTH_SHORT).show();
                }
        );
        requestQueue.add(jsonObjectRequest);
        FetchFriends();

    }

}