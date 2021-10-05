package com.example.activitylifecycledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;

public class seekbarActivity extends AppCompatActivity {
    SeekBar svProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);
        ControlInitialization();
        EventListeners();

    }

    private void ControlInitialization()
    {
        svProgress = findViewById(R.id.sbProgress);
    }

    private void EventListeners()
    {
        svProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressVal = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                progressVal = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(),"start tracking",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(),"User stopped at :"
                                + progressVal,
                        Toast.LENGTH_LONG).show();
            }
        });
    }


}