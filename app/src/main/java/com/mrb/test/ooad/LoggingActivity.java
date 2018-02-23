package com.mrb.test.ooad;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoggingActivity extends AppCompatActivity {
    private EditText name;
    private EditText pw;
    private Button register;
    private Button login;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        try {
            register = (Button) findViewById(R.id.register);
            login = (Button) findViewById(R.id.Login);

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_logging);
            name = (EditText) findViewById(R.id.Name);
            pw = (EditText) findViewById(R.id.pw);
            SharedPreferences setting =
                    getSharedPreferences("user", MODE_PRIVATE);
            name.setText(setting.getString("NAME", ""));

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doubleBackToExitPressedOnce = false;
                    Intent intent = new Intent();
                    //go back to previous page
                    intent.setClass(LoggingActivity.this, LoggingActivity.this.getClass());
                    startActivity(intent);
                }
            });
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doubleBackToExitPressedOnce = false;
                    //if log not success, toast.
                    if(1==1){
                        Toast.makeText(LoggingActivity.this,"The input information have error", Toast.LENGTH_LONG).show();
                    }else {
                        Intent intent = new Intent();
                        //go back to previous page
                        SharedPreferences setting;
                        setting = getSharedPreferences("user", MODE_PRIVATE);
                        //Preference have NAME & PW
                        setting.edit()
                                .putString("NAME", String.valueOf(name))
                                .putString("PW", String.valueOf(pw))
                                .apply();
                        intent.setClass(LoggingActivity.this, LoggingActivity.this.getClass());
                        startActivity(intent);
                    }
                }
            });

        }catch (Exception e){
            Toast.makeText(LoggingActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
