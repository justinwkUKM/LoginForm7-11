package com.waqas.myxlab.loginform1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etName, etEmail, etPassword;
    Button btLogin, btNext;
    TextView tvResult;
    String stName, stEmail, stPassword;

    //define as a global variable
    SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initialising the variables/finding the views
        etName=(EditText)findViewById(R.id.editTextName);
        etEmail=(EditText)findViewById(R.id.editTextEmail);
        etPassword=(EditText)findViewById(R.id.editTextPassword);
        btLogin=(Button)findViewById(R.id.buttonLogin);
        btNext=(Button)findViewById(R.id.buttonNext);
        tvResult=(TextView) findViewById(R.id.textViewResult);

        pref  = getApplicationContext().getSharedPreferences("MyData",0);



        if (pref.contains("stName")){

            String s = pref.getString("stName", "N/A");
            etName.setText(s);

            String u = pref.getString("stEmail", "N/A");
            etEmail.setText(u);

            String p = pref.getString("stPassword", "N/A");
            etPassword.setText(p);

            tvResult.setText(s +"\n"+u+"\n"+p);
        }

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do something here
                stName = etName.getText().toString();
                stEmail = etEmail.getText().toString();
                stPassword = etPassword.getText().toString();
                if(!stName.isEmpty() && !stEmail.isEmpty() && !stPassword.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    tvResult.setText(stName +"\n"+stEmail+"\n"+stPassword);
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                }


            }
        });





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                if (tvResult.getText().toString().isEmpty()){
                    Snackbar.make(view, "Enter the value first",
                            Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                }else{
                    SharedPreferences.Editor editor = pref.edit();

                        editor.putString("stName", stName);
                        editor.putString("stEmail", stEmail);
                        editor.putString("stPassword", stPassword);

                        editor.commit();

                        Intent i = new Intent(LoginActivity.this,
                                SecondActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("stName", stName);
                        bundle.putString("stEmail", stEmail);
                        bundle.putString("stPassword", stPassword);
                        i.putExtras(bundle); ///key-value-pair
                        startActivity(i);

                }
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
