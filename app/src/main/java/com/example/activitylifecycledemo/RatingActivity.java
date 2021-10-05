package com.example.activitylifecycledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Rating;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;

public class RatingActivity extends AppCompatActivity {
    RatingBar rbRating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        ControlInitialization();
        EventListeners();
    }

    private void ControlInitialization(){
        rbRating = findViewById(R.id.rbrating);

    }

    private void EventListeners()
    {
        rbRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
                Toast.makeText(getApplicationContext(),
                        "Current rating is : " + rating,Toast.LENGTH_LONG).show();
            }
        });
    }


}