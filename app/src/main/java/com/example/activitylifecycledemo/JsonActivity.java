package com.example.activitylifecycledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class JsonActivity extends AppCompatActivity {
    ListView lstPlayer;
    String url = "https://demonuts.com/Demonuts/JsonTest/Tennis/json_parsing.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        ControlInitialization();
    }

    private void ControlInitialization()
    {
        lstPlayer = findViewById(R.id.lstPlayers);
        JsonRequest();
    }

    private void JsonRequest()
    {
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    ArrayList<PlayerModel> PlayerModelList = new ArrayList<PlayerModel>();
                    if(object.optString("status").equals("true"))
                    {
                        JSONArray dataArray = object.getJSONArray("data");
                        for(int i=0;i<dataArray.length();i++)
                        {
                            JSONObject dataObject = dataArray.getJSONObject(i);
                           /* PlayerModelList.add(dataObject.getString("id") + "," +
                                    dataObject.getString("name") + "," +
                                    dataObject.getString("country") + "," +
                                    dataObject.getString("city") + "," +
                                    dataObject.getString("imgURL"));*/

                            PlayerModelList.add(new PlayerModel(dataObject.getString("id"),dataObject.getString("name"),
                                    dataObject.getString("country"),dataObject.getString("city"),dataObject.getString("imgURL")));
                        }

                      /*  ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                                android.R.layout.simple_list_item_1,PlayerModelList);
                        lstPlayer.setAdapter(adapter);*/

                      JsonCustomAdapter adapter = new JsonCustomAdapter(getApplicationContext(),PlayerModelList);
                      lstPlayer.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

}