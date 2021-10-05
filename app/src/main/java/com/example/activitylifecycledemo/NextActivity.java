package com.example.activitylifecycledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NextActivity extends AppCompatActivity {
    EditText txtName;
    Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        ButtonClicks();
        ControlInitialization();

    }

    private void ControlInitialization()
    {
        txtName = findViewById(R.id.txtName);
        btnNext = findViewById(R.id.btnNext);
    }

    private void ButtonClicks()
    {

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = txtName.getText().toString();
                Intent objIntent = new Intent(getApplicationContext(),MainActivity.class);
                objIntent.putExtra("name",Name);
                startActivity(objIntent);

            }
        });
    }
}