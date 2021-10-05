package com.example.activitylifecycledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudActivity extends AppCompatActivity {
    String url="http://192.168.56.1/StudentAPI.php";
    String Addurl = "http://192.168.56.1/AddStudent.php";
    String deleteurl = "http://192.168.56.1/DeleteStudent.php";
    String updateurl = "http://192.168.56.1/UpdateStudent.php";
    ListView lstStud;
    EditText txtStudentName, txtCity, txtContactNo;
    Button btnAdd;
    int StudentID;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud);
        ControlInitialization();

    }

    private void ControlInitialization() {
        lstStud = findViewById(R.id.lstStud);
        txtStudentName = findViewById(R.id.txtName);
        txtCity = findViewById(R.id.txtCity);
        txtContactNo = findViewById(R.id.txtContactNo);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //AddRequest();

                //RequestJSON();
                UpdateRequest();
            }
        });

        lstStud.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = adapter.getItem(i);
                String[] SelectedColumn = selectedItem.split(",");
                StudentID = Integer.parseInt(SelectedColumn[0]);
                txtStudentName.setText(SelectedColumn[1]);
                txtContactNo.setText((SelectedColumn[2]));
                txtCity.setText(SelectedColumn[3]);
               // DeleteRequest(StudentID);

            }
        });
        RequestJSON();

    }
    private void UpdateRequest()
    {
        StringRequest updateRequest = new StringRequest(Request.Method.POST, updateurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                RequestJSON();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String,String>();
                params.put("StudentName",txtStudentName.getText().toString());
                params.put("City",txtCity.getText().toString());
                params.put("ContactNo",txtContactNo.getText().toString());
                params.put("StudentID",String.valueOf(StudentID));
                return params;
            }
        };
        RequestQueue updateQueue = Volley.newRequestQueue(getApplicationContext());
        updateQueue.add(updateRequest);
    }
    private void DeleteRequest(final int studID)
    {
        StringRequest deleteRequest = new StringRequest(Request.Method.POST, deleteurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                RequestJSON();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<String,String>();
                param.put("StudentID",String.valueOf(studID));
                return param;
            }
        };

        RequestQueue deleteQueue = Volley.newRequestQueue(getApplicationContext());
        deleteQueue.add(deleteRequest);

    }
    private void  AddRequest()
    {
        StringRequest addRequest = new StringRequest(Request.Method.POST, Addurl,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                txtStudentName.setText("");
                txtCity.setText("");
                txtContactNo.setText("");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("StudentName",txtStudentName.getText().toString());
                params.put("City",txtCity.getText().toString());
                params.put("ContactNo",txtContactNo.getText().toString());
                return params;
            }
        };

        RequestQueue addqueue = Volley.newRequestQueue(getApplicationContext());
        addqueue.add(addRequest);
    }

    
    private void RequestJSON()
    {
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("Stringobject",response);
                    JSONObject object = new JSONObject(response);
                    JSONArray dataArray = object.getJSONArray("stud");
                    ArrayList<String> StudentArrayList = new ArrayList<String>();
                    for(int i=0;i<dataArray.length();i++)
                    {
                        JSONObject dataObject = dataArray.getJSONObject(i);
                        StudentArrayList.add(dataObject.getString("StudentID") + "," +
                                dataObject.getString("StudentName") + "," +
                                dataObject.getString("City") + "," +
                                dataObject.getString("ContactNo"));
                    }

                   adapter = new ArrayAdapter<String>(getApplicationContext(),
                            android.R.layout.simple_list_item_1,StudentArrayList);
                    lstStud.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            public Priority getPriority() {
                return Priority.LOW;
            }
        };

        JsonObjectRequest jsonRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Json response","JSOn REsponse");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Priority getPriority() {
                return Priority.IMMEDIATE;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
        queue.add(jsonRequest);
    }
}