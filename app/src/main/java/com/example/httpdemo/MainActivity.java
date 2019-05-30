package com.example.httpdemo;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {


    private void ConnectInternet() {
        try {

            //對目標的ＵＲＬ轉址
            URL url = new URL("https://udn.com/invoice/index");
            //針對拿到的ＵＲＬ連線
            URLConnection conn = url.openConnection();
            //取得串流
            InputStream  streamIn = conn.getInputStream();

            //準備開始解碼，首先，把剛剛的串流讀進來，製作一個串流讀取器(BufferReader)
            BufferedReader r = new BufferedReader(new InputStreamReader(streamIn));
            //做一個StringBuilder,接著不斷地去讀取串流，讀到他是NULL為止，在這之前則把每一行 append  到 StringBuilder 裡面
            StringBuilder html  = new StringBuilder();

            String line ;
            while ( (line = r.readLine()) != null){
                html.append(line);
            }

            String key="特別獎</label><b class=\"sp\">";

            int start = html.toString().indexOf(key)+key.length() ;


            lblHttp.setText("特！別獎 ："+html.toString().substring(start,start+8));





        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitialComponent();
        StrictMode.ThreadPolicy l_policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(l_policy);
        ConnectInternet();
    }


    private void InitialComponent() {
    lblHttp = findViewById(R.id.lblHttp);

    }

    TextView lblHttp;
}
