package com.example.activitylifecycledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class webviewDemo extends AppCompatActivity {
    WebView wv;
    Button btnOpen;
    String URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_demo);
        ControlInitialization();
        ButtonClicks();
    }

    //Initialization of controls
    private void ControlInitialization()
    {
        wv = findViewById(R.id.wv);
        wv.setWebViewClient(new MyBrowser());
        btnOpen = findViewById(R.id.btnOpen);
    }

    //Button Clicks
    private void ButtonClicks()
    {
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                URL = "https://www.google.com";
                wv.getSettings().setJavaScriptEnabled(true);
                wv.getSettings().setLoadsImagesAutomatically(true);
                wv.loadUrl(URL);
            }
        });
    }

    private class MyBrowser extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(URL);
            return true;
        }
    }


}

