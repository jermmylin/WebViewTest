package com.apress.gerber.webviewtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

/**
 * search the web with webview
 */
public class MainActivity extends AppCompatActivity {
    private WebView webview;
    private Button httpUrlConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webview = (WebView)findViewById(R.id.web_view);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("http://www.baidu.com");
        httpUrlConnection = (Button)findViewById(R.id.HttpURLConnnetction);
        httpUrlConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HttpURLConnnection.class);
                startActivity(intent);
            }
        });
    }
}
