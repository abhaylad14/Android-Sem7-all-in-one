package com.example.activitylifecycledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText txtEmail, txtPassword;
    Button btnLogin;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ControlInitialization();
        ButtonClicks();

    }

    private void ControlInitialization()
    {
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        sharedPreferences = getSharedPreferences("MyPreference",MODE_PRIVATE);
    }

    private void ButtonClicks()
    {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = txtEmail.getText().toString();
                String Password = txtPassword.getText().toString();

                if(Email.equalsIgnoreCase("admin@gmail.com") && Password.equalsIgnoreCase("12345"))
                {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email",Email);
                    editor.commit();
                    txtEmail.setText("");
                    txtPassword.setText("");
                    Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(i);

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Wrong Input",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}