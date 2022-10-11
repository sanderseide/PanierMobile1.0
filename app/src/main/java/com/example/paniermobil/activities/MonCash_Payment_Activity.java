package com.example.paniermobil.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.os.Bundle;

import com.example.paniermobil.R;

public class MonCash_Payment_Activity extends AppCompatActivity {
    private WebView WebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_cash_payment);

        String url = getIntent().getStringExtra("url");
        WebView = (WebView) findViewById(R.id.WebView_Moncash);
        WebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = WebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        WebView.loadUrl(url);
    }
}