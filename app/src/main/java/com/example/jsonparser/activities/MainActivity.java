package com.example.jsonparser.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.example.jsonparser.R;
import com.example.jsonparser.asynctasks.MyAsyncTask;
import com.example.jsonparser.interfaces.ResultantFeed;
import com.example.jsonparser.models.PropertyAndValue;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ResultantFeed
{
    private static final String TAG = "MainActivity";
    private static String FEED_URL = "http://services.hanselandpetal.com/feeds/flowers.json";
    private TextView json_feed;

   @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        json_feed = (TextView) findViewById(R.id.json_feed);
        json_feed.setMovementMethod(new ScrollingMovementMethod());

        new MyAsyncTask(MainActivity.this).execute(FEED_URL);
    }

    @Override
    public void getResultantFeed(List<PropertyAndValue> feedList)
    {
        if (feedList!=null)
        {
            String feed = "";
            for (PropertyAndValue propertyAndValue :feedList)
            {
                if(json_feed!=null)
                {
                    feed += propertyAndValue.category+"\n";
                    feed += propertyAndValue.price+"\n";
                    feed += propertyAndValue.instructions+"\n";
                    feed += propertyAndValue.photo+"\n";
                    feed += propertyAndValue.name+"\n";
                    feed += propertyAndValue.product_id+"\n---------------------\n";
                }
            }
            if (feed!=null)
                json_feed.setText(feed);
        }
    }
}
