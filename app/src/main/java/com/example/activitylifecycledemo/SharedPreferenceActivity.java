package com.example.activitylifecycledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SharedPreferenceActivity extends AppCompatActivity {
    EditText txtName, txtPassword;
    Button btnSave, btnClear, btnRetrieve;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);
        ControlInitialization();
        ButtonClicks();
    }

    private void ControlInitialization()
    {
        txtName = findViewById(R.id.txtName);
        txtPassword = findViewById(R.id.txtPassword);
        btnSave = findViewById(R.id.btnSave);
        btnClear = findViewById(R.id.btnClear);
        btnRetrieve = findViewById(R.id.btnRetrieve);

        sharedPreferences = getSharedPreferences("MyPreference",MODE_PRIVATE);
    }

    private void ButtonClicks()
    {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name",txtName.getText().toString());
                editor.putString("password", txtPassword.getText().toString());
                editor.commit();
                Toast.makeText(getApplicationContext(),"Data Saved",Toast.LENGTH_LONG).show();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtName.setText("");
                txtPassword.setText("");
            }
        });

        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sharedPreferences.contains("name"))
                    txtName.setText(sharedPreferences.getString("name",""));

                if(sharedPreferences.contains("password"))
                {
                    txtPassword.setText(sharedPreferences.getString("password",""));
                }
            }
        });
    }
}