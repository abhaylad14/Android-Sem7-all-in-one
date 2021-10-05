package com.example.activitylifecycledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    TextView lblEmail;
    Button btnLogout;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        ControlInitialization();
        ButtonClicks();
    }

    private void ControlInitialization()
    {
        lblEmail = findViewById(R.id.lblEmail);
        btnLogout = findViewById(R.id.btnLogout);

        sh = getSharedPreferences("MyPreference",MODE_PRIVATE);
        if(sh.contains("email"))
        {
            lblEmail.setText("Welcome " + sh.getString("email",""));
        }
    }

    private void ButtonClicks()
    {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sh.edit();
                editor.putString("email","");
                editor.commit();
                finish();
            }
        });
    }
}