package com.example.fasa7ni;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EventProfile extends AppCompatActivity implements View.OnClickListener
{

    private TextView fos7aImage, fos7aTitle,fos7aDescription,fos7aDate,Fos7a_Location,fos7aCapacity,fos7aPublic,fos7aHost;

    private TextView ReadMore;
    private ArrayList<TextView> Tags = new ArrayList<TextView>();
    private ImageButton BackButton;
    private Button Join;
    private boolean pending;
    private boolean status = false;
    private boolean isExpanded=false;
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
        Tags.add(findViewById(R.id.tag1));
        Tags.add(findViewById(R.id.tag2));
        for(int i=0;i<2;i++)
        {
            Tags.get(i).setVisibility(View.INVISIBLE);
        }

        BackButton = findViewById(R.id.backButton);
        BackButton.setOnClickListener(this);
        ReadMore = findViewById(R.id.read_more);


        //fos7aImage = findViewById(R.id.imageView3);
        fos7aTitle = findViewById(R.id.Fos7aTitle);
        fos7aDescription = findViewById(R.id.Fos7aDescription);
        fos7aCapacity = findViewById(R.id.Fos7aCapacity);
        fos7aDate = findViewById(R.id.Fos7aDateTime);
        fos7aPublic = findViewById(R.id.Fos7aType);
        fos7aHost = findViewById(R.id.Fos7aHost);
        Fos7a_Location = findViewById(R.id.Fos7aLocation);

        ReadMore.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (isExpanded) {
                    fos7aDescription.setMaxLines(3);
                    fos7aDescription.setEllipsize(android.text.TextUtils.TruncateAt.END);
                    ReadMore.setText("Read More");
                } else {
                    fos7aDescription.setMaxLines(Integer.MAX_VALUE);
                    fos7aDescription.setEllipsize(null);
                    ReadMore.setText("Read Less");
                }
                isExpanded = !isExpanded;

            }
        });


        Join = findViewById(R.id.Join_btn);
        Join.setOnClickListener(this);

        Bundle Bundle = getIntent().getExtras();
        if(Bundle!=null){
            String Name = Bundle.getString("Fos7a_Name");
            String Host_Username = Bundle.getString("Host_Username");
            String Description = Bundle.getString("Description");
            String Capacity = Integer.toString(Bundle.getInt("Capacity"));
            String Fos7a_Date = Bundle.getString("Fos7a_Date");
            String Fos7a_Time = Bundle.getString("Fos7a_Time");
            int Is_Public = Bundle.getInt("Is_Public");
            String Place_Name = Bundle.getString("Place_Name");
            int Image = Bundle.getInt("Image");

            //setting views
            //fos7aImage.setImageResource(Image);
            fos7aTitle.setText(Name);
            fos7aDescription.setText(Description);
            fos7aCapacity.setText(Capacity);
            fos7aDate.setText(Fos7a_Date);
            fos7aPublic.setText(Is_Public==1?"Public":"Private");
            fos7aHost.setText(Host_Username);
            Fos7a_Location.setText(Place_Name);
            //FetchTags(Name);

        }
        


    }



    @Override
    public void onClick(View v)
    {
        if(v.getId()==BackButton.getId())
            finish();

        else if(v.getId()==Join.getId())
        {
            if(!status && !pending)
            {
                Join.setText("Pending");
                Join.setBackgroundColor(getResources().getColor(R.color.support_grey));;
                Join.setTextColor(getResources().getColor(R.color.light_grey));
                pending = true;
                //add request
            }

            else if (status || (!status && pending))
            {
                Join.setText("Join Fos7a");
                Join.setBackgroundColor(getResources().getColor(R.color.blue));
                Join.setTextColor(getResources().getColor(R.color.black));
                status = false;
                pending = false;
                //Delete request
            }
        }


    }
}