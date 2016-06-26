package com.wechat.wechat;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class AgreementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
        final Button b1 = (Button)findViewById(R.id.button2);
        b1.setClickable(false);

        final CheckBox box = (CheckBox)findViewById(R.id.checkBox);
        if(box != null){
            box.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                   if(box.isChecked()){
                        b1.setClickable(true);
                       b1.setTextColor(Color.parseColor("#ff000000"));
                       b1.setOnClickListener(new View.OnClickListener() {
                           public void onClick(View view) {
                               startActivity(new Intent(AgreementActivity.this, VerificationCodeActivity.class));
                           }
                       });

                   }else{
                       b1.setClickable(false);
                       b1.setTextColor(Color.parseColor("#FFAAAAAA"));
                   }
                }
            });
        }

    }
}
