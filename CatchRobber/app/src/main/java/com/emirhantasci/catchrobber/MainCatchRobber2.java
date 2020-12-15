package com.emirhantasci.catchrobber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class MainCatchRobber2 extends AppCompatActivity {
    Button buttonBasic;
    WebView webView;
    Button buttonHard;
    int seviye;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_catch_robber2);
        buttonBasic=findViewById(R.id.buttonBasic);
        buttonHard=findViewById(R.id.buttonHard);
        webView= findViewById(R.id.web_view);

        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        String file="file:android_asset/hirsiz.gif";
        webView.loadUrl(file);


        seviye=0;
    }

    public void basic(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        seviye=650;
        intent.putExtra("seviyes", seviye);
        startActivity(intent);

    }

    public void hard(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        seviye=450;
        intent.putExtra("seviyes", seviye);
        startActivity(intent);
    }
}