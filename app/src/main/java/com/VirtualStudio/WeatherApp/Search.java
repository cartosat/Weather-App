package com.VirtualStudio.WeatherApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3);


        find_wheather();
    }


    public void find_wheather(){

        String header="https://openweathermap.org/data/2.5/weather?q=";
        String last="&appid=b6907d289e10d714a6e88b30761fae22";
        String city="pune";

        String url=header+city+last;
        JsonObjectRequest jbr=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONObject main_object = response.getJSONObject("main");
                    JSONObject wind_object = response.getJSONObject("wind");
                    JSONArray array = response.getJSONArray("weather");
                    JSONObject object = array.getJSONObject(0);

                    String temp = String.valueOf(main_object.getDouble("temp"));
                    String city = response.getString("name");
                    String pressure = String.valueOf(main_object.getDouble("pressure"));
                    String speed = String.valueOf(wind_object.getDouble("speed"));






                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }


        );

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jbr);
    }

}
