package com.apress.gerber.webviewtest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * this is the activity use HttpURLConnection
 */
public class HttpURLConnnection extends AppCompatActivity {
    private static final int SHOW_RESPONSE = 0;
    private Button sendRequest;
    private TextView responseText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_urlconnnection);
        sendRequest = (Button)findViewById(R.id.send_request);
        responseText = (TextView)findViewById(R.id.response_text);
        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequestWithHttpURLConnection();
            }
        });
    }

    private void sendRequestWithHttpURLConnection(){
    new Thread(new Runnable() {
        @Override
        public void run() {
            HttpURLConnection connnection = null;
            try{
                URL url = new URL("http://www.baidu.com");
                //cast URLconnection to HttpURLConnection
                connnection = (HttpURLConnection)url.openConnection();
                connnection.setRequestMethod("GET");
                connnection.setConnectTimeout(8000);
                connnection.setReadTimeout(8000);
                InputStream in = connnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder response = new StringBuilder();
                String line;
                while((line = reader.readLine())!=null){
                    response.append(line);
                }
                Message message = new Message();
                message.what = SHOW_RESPONSE;
                //将服务器返回的结果存放在Message中
                message.obj = response.toString();
                handler.sendMessage(message);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(connnection != null){
                    connnection.disconnect();
                }
            }
        }
    }).start();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SHOW_RESPONSE:
                    String response = (String)msg.obj;
                    responseText.setText(response);
            }

        }
    };
}
