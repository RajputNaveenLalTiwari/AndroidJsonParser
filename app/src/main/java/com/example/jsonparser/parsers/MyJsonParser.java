package com.example.jsonparser.parsers;

import android.util.Log;

import com.example.jsonparser.models.PropertyAndValue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 2114 on 23-06-2017.
 */

public class MyJsonParser
{
    private static final String TAG = "MyJsonParser";
    public List<PropertyAndValue> parseJson(String feed_url)
    {
        URL url = null;
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        List<PropertyAndValue> feedList = null;
        String feedInString = "";
        JSONArray jsonArray = null;

        try
        {
            url = new URL(feed_url);

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            inputStream = httpURLConnection.getInputStream();

            if (inputStream!=null)
            {
                int byte_character;
                while ((byte_character = inputStream.read()) != -1)
                {
                    feedInString += (char) byte_character;
                }

                if (feedInString!=null)
                {
                    feedList = new ArrayList<>();
                    jsonArray = new JSONArray(feedInString);

                    if (jsonArray!=null)
                    {
                        for (int i=0; i<jsonArray.length(); i++)
                        {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            PropertyAndValue propertyAndValue = new PropertyAndValue();
                            propertyAndValue.category = jsonObject.getString("category");
                            propertyAndValue.price = jsonObject.getDouble("price");
                            propertyAndValue.instructions = jsonObject.getString("instructions");
                            propertyAndValue.photo = jsonObject.getString("photo");
                            propertyAndValue.name = jsonObject.getString("name");
                            propertyAndValue.product_id = jsonObject.getInt("productId");

                            feedList.add(propertyAndValue);
                        }
                    }
                    return feedList;
                }
                else
                {
                    return null;
                }
            }
            else
            {
                return null;
            }

        }
        catch (MalformedURLException e)
        {
            Log.e(TAG,"Error In URL "+e.getMessage());
        }
        catch (IOException e)
        {
            Log.e(TAG,"Error In HttpUrlConnection "+e.getMessage());
        }
        catch (JSONException e)
        {
            Log.e(TAG,"Error In Json "+e.getMessage());
        }
        finally
        {
            try
            {
                if (inputStream!=null)
                    inputStream.close();

                if (httpURLConnection!=null)
                    httpURLConnection.disconnect();
            }
            catch (IOException e)
            {
                Log.e(TAG,"Error in closing input stream "+e.getMessage());
            }
        }

        return null;
    }
}
