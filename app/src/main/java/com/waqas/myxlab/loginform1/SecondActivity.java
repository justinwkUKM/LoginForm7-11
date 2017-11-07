package com.waqas.myxlab.loginform1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView tvRValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvRValue = (TextView) findViewById(R.id.tvRValue);
        Intent receiver = getIntent();

//        Bundle bundle = receiver.getExtras();
//        String name = bundle.getString("stName");
//        String email = bundle.getString("stEmail");
//        String password = bundle.getString("stPassword");

        SharedPreferences pref  = getApplicationContext().getSharedPreferences("MyData",0);
        String name = pref.getString("stName","N/A");
        String email = pref.getString("stEmail","N/A");
        String password = pref.getString("stPassword","N/A");
        tvRValue.setText(name+"\n"+email+"\n"+password);












    }
}
