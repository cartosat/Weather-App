package com.VirtualStudio.WeatherApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    TextView t1_tempa,t1_citee,t1_speed,t1_pressure,t1_time;
    private Button button1;
    private Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.buttonSearch);
        button2 = (Button) findViewById(R.id.button5Day);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

        String time = format.format(cal.getTime());



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFive_Day();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearch();
            }
        });


        t1_tempa=(TextView)findViewById(R.id.textView3);
        t1_citee=(TextView)findViewById(R.id.textView4);
        t1_speed=(TextView)findViewById(R.id.windspeed);
        t1_pressure=(TextView)findViewById(R.id.pressure);



        find_whe();



    }

    public void openFive_Day(){
        Intent intent = new Intent(this , Five_Day.class);
        startActivity(intent);
    }
    public void openSearch(){
        Intent intent2=new Intent(this,Search.class);
        startActivity(intent2);
    }
    public void find_whe(){
        String url="https://openweathermap.org/data/2.5/weather?q=pune&appid=b6907d289e10d714a6e88b30761fae22";
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



                    t1_tempa.setText(temp);
                    t1_citee.setText(city);
                    t1_pressure.setText(pressure);
                    t1_speed.setText(speed);


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

