package com.example.jsonparser.asynctasks;

import android.app.Activity;
import android.os.AsyncTask;

import com.example.jsonparser.interfaces.ResultantFeed;
import com.example.jsonparser.models.PropertyAndValue;
import com.example.jsonparser.parsers.MyJsonParser;

import java.util.List;

/**
 * Created by 2114 on 23-06-2017.
 */

public class MyAsyncTask extends AsyncTask<String,Void,List<PropertyAndValue>>
{
    private final ResultantFeed resultantFeed;

    public MyAsyncTask(Activity activity)
    {
        resultantFeed = (ResultantFeed) activity;
    }

    @Override
    protected void onPreExecute()
    {

    }

    @Override
    protected List<PropertyAndValue> doInBackground(String... params)
    {
        return new MyJsonParser().parseJson(params[0]);
    }

    @Override
    protected void onPostExecute(List<PropertyAndValue> propertyAndValues)
    {
        resultantFeed.getResultantFeed(propertyAndValues);
    }
}
