package com.example.fasa7ni;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlaceProfile extends AppCompatActivity implements View.OnClickListener
{
    private String username;
    private String place_Name;
    private String description;
    private String openingTime;
    private String closingTime;
    private String phone;
    private String location;
    private String workingDays;
    private String facebook_account="temp";

    private String image;


    private  class Social{
        private String type;
        private String name;
        private String link;

        public Social(String accName, String accType) {
            this.name = accName;
            this.type = accType;
        }
    }

    private ArrayList<Social> social_list = new ArrayList<Social>();


    private ImageButton BackButton;
    private Button Show_Events;
    private Button directionsBtn;
    private ImageView Place_Image;
    private TextView Place_Name;
    private TextView Description;
    private TextView Timings;
    private TextView Location;
    private TextView Facebook;
    private TextView Phone;
    private TextView ReadMore;
    private boolean  isExpanded = false;
    private ArrayList<TextView> Tags = new ArrayList<TextView>();

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Start();
    }

    private void Start()
    {

        Tags.add(findViewById(R.id.tag1));
        Tags.add(findViewById(R.id.tag2));
        Tags.add(findViewById(R.id.tag3));
        for(int i=0;i<3;i++)
        {
            Tags.get(i).setVisibility(View.INVISIBLE);
        }

        BackButton = findViewById(R.id.backButton);
        BackButton.setOnClickListener(this);

        Show_Events = findViewById(R.id.show_fosa7_btn);
        Show_Events.setOnClickListener(this);
        Place_Image = findViewById(R.id.placeImageView);
        Place_Name = findViewById(R.id.placeName);
        Description = findViewById(R.id.placeDescription);
        Timings = findViewById(R.id.placeTimings);
        Location = findViewById(R.id.placeLocation);
        Phone = findViewById(R.id.phone);
        Facebook = findViewById(R.id.facebook);

        ReadMore=findViewById(R.id.read_more);
        ReadMore.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (isExpanded) {
                    Description.setMaxLines(3);
                    Description.setEllipsize(android.text.TextUtils.TruncateAt.END);
                    ReadMore.setText("Read More");
                } else {
                    Description.setMaxLines(Integer.MAX_VALUE);
                    Description.setEllipsize(null);
                    ReadMore.setText("Read Less");
                }
                isExpanded = !isExpanded;

            }
        });


        Bundle Bundle = getIntent().getExtras();
        if(Bundle!=null){
            username = Bundle.getString("Username");
            location=Bundle.getString("Location");
            description=Bundle.getString("Description");
            openingTime=Bundle.getString("OpeningTime");
            closingTime=Bundle.getString("ClosingTime");
            phone=Bundle.getString("Phone");
            place_Name=Bundle.getString("Name");
            workingDays=Bundle.getString("WorkingDays");
            image=Bundle.getString("Image");
            FetchSocials(place_Name);
            FetchTags(place_Name);
            setting_view();

        }

        directionsBtn=findViewById(R.id.button2);
        directionsBtn.setOnClickListener(v ->
        {
            String source="AUC";

            Uri uri = Uri.parse("https://www.google.com/maps/dir/"+source+"/"+location);

            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }


    private void setting_view()
    {
        Place_Name.setText(place_Name);
        Description.setText(description);
        Timings.setText(workingDays+" [ "+openingTime + " - " + closingTime+" ]");
        Location.setText(location);
        Phone.setText(phone);
        Facebook.setText(facebook_account);
        String imagePath = "file:///android_asset/" + image;
        Glide.with(getApplicationContext())
                .load(imagePath)
                .into(Place_Image);
    }

    private void FetchSocials(String name)
    {
        String url = "http://10.0.2.2:4000/Fetch_Places_Socials?name=" + name; //add type
        RequestQueue requestQueue = Volley.newRequestQueue(this);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                response ->
                {

                    try {
                        for (int i = 0; i < response.length(); i++)
                        {
                            JSONObject place = response.getJSONObject(i);
                            String acc_name = place.getString("Account_Name");
                            String acc_type = place.getString("SM_Type");
                            //we can get  the link here
                            if(acc_type.equals("Facebook"))
                            {
                                facebook_account = acc_name;
                                Facebook.setText(facebook_account);

                            }

                            social_list.add(new Social(acc_name, acc_type));
                        }
                        //set
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

    private void FetchTags(String name)
    {

        String url = "http://10.0.2.2:4000/Fetch_Places_Tags?name=" + name; //add type
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                response ->
                {

                    try {
                        for (int i = 0; i < response.length(); i++)
                        {
                            JSONObject place = response.getJSONObject(i);
                            String tag = place.getString("Tag_Name");
                            Tags.get(i).setText(tag);
                            Tags.get(i).setVisibility(View.VISIBLE);
                        }

                        for(int j=0;j<3;j++){

                        }
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

    @Override
    public void onClick(View v)
    {
        if(v.getId() == BackButton.getId())
            finish();

        else if (v.getId() == Show_Events.getId())
            Go_Events();

        else
            return;
    }

    private void Go_Events()
    {
        Intent P_E = new Intent(PlaceProfile.this, Events.class);
        P_E.putExtra("Place_Name", place_Name);
        P_E.putExtra("Username", username);
        startActivity(P_E);
    }

}
