package com.wechat.wechat;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class VerificationCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);


        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this, R.array.first_phone_no, android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);


        Button submit = (Button)findViewById(R.id.button3);
        assert submit != null;
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StringBuffer sb = new StringBuffer();
                for(int i=0;i<6;i++){
                    sb.append(new Random().nextInt(9));
                }

                System.out.println("randomCode : "+sb.toString());
                Log.d("randomCode",sb.toString());

                Spinner sp =  (Spinner)findViewById(R.id.spinner);
                TextView middleNo = (TextView)findViewById(R.id.editText2);
                TextView lastNo = (TextView)findViewById(R.id.editText3);

                String telNo =  sp.getSelectedItem().toString() + middleNo.getText()+ lastNo.getText();
                BufferedWriter bw = null;
                HttpURLConnection con = null;
                try {
                    String query = "telNo="+telNo+"&code="+sb.toString();
                    URL url = new URL("http://192.168.219.122:8080/test.do?"+query);
                    con = (HttpURLConnection)url.openConnection();
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.setRequestMethod("GET");
                    con.connect();
                    Log.i("Status",con.getResponseMessage());
                    //con.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    try{
                        if(con!=null) con.disconnect();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                System.out.println("telNo : "+telNo);
                Log.i("telNo",telNo);

            }
        });

    }



}
