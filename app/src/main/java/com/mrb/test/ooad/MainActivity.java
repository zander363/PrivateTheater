package com.mrb.test.ooad;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mrb.test.MOVIE.MovieControl;
//import com.mrb.test.Class.MovieControl;

public class MainActivity extends RootActivity {
    private ImageButton movie;
    private ImageButton booking;
    private ImageButton searching;
    private ImageButton user;

    //判斷離開的flag，設定成全域變數
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            //MovieControl.InitMovieData();
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            movie = (ImageButton) findViewById(R.id.movieButton);
            booking = (ImageButton) findViewById(R.id.bookingButton);
            searching = (ImageButton) findViewById(R.id.searchingButton);
            user = (ImageButton) findViewById(R.id.userButton);
            CurrentMenuItem = 0;
            NV.getMenu().getItem(CurrentMenuItem).setChecked(true);


            if (this.getIntent().getExtras() != null) {
                Bundle bundle = this.getIntent().getExtras();
                doubleBackToExitPressedOnce = Boolean.parseBoolean(bundle.getString("directgoback"));
            }

            movie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doubleBackToExitPressedOnce = false;
                    // TODO    make a ListView to show the movie List

                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, MovieListActivity.class);
                    startActivity(intent);

                    //Toast.makeText(MainActivity.this, "功能尚未開放", Toast.LENGTH_LONG).show();
                }
            });
            booking.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doubleBackToExitPressedOnce = false;
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, BookingActivity.class);
                    startActivity(intent);
                }
            });

            searching.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doubleBackToExitPressedOnce = false;
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, SearchingActivity.class);
                    startActivity(intent);
                }
            });

            user.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doubleBackToExitPressedOnce = false;

                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, LoggingActivity.class);
                    startActivity(intent);

                    //Toast.makeText(MainActivity.this, "功能尚未開放", Toast.LENGTH_LONG).show();
                }
            });

        } catch (Exception e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        SharedPreferences setting;
        setting = getSharedPreferences("user", MODE_PRIVATE);
        if(setting.getBoolean("LogIn", false)){
            menu.findItem(R.id.action_logging).setTitle("Log Out");
        }
        else{
            menu.findItem(R.id.action_logging).setTitle("Log In");
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_help:
                Help();
                return true;
            case R.id.action_settings:
                return true;
            case R.id.action_logging:
                SharedPreferences setting;
                setting = getSharedPreferences("user", MODE_PRIVATE);
                if(setting.getBoolean("LogIn", false)){
                    LogOut();
                }
                else{
                    Login();
                }
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void Help() {
        Toast.makeText(this, "關掉比較快", Toast.LENGTH_SHORT).show();
    }
    private void Login(){
        LayoutInflater factory = LayoutInflater.from(MainActivity.this);
        final View dialogView = factory.inflate(R.layout.login_alertdialog, null);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setView(dialogView);
        alertDialog.setPositiveButton("登入", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText user = (EditText)dialogView.findViewById(R.id.username);
                EditText passwd = (EditText)dialogView.findViewById(R.id.password);
                String userStr = user.getText().toString();
                String pwStr = passwd.getText().toString();
                boolean valid = true;
                if(valid){
                    SharedPreferences setting;
                    setting = getSharedPreferences("user", MODE_PRIVATE);
                    //Preference have NAME & PW
                    setting.edit()
                            .putString("NAME", String.valueOf(userStr))
                            .putString("PW", String.valueOf(pwStr))
                            .putBoolean("LogIn",true)
                            .apply();
                    Toast.makeText(MainActivity.this, "登入成功", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "登入失敗", Toast.LENGTH_LONG).show();
                }
            }
        });
        alertDialog.setNegativeButton("註冊", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                // TODO Intent to Register Page
            }
        });
        alertDialog.create().show();
    }
    private void LogOut(){
        SharedPreferences setting;
        setting = getSharedPreferences("user", MODE_PRIVATE);
        setting.edit()
                .putString("NAME", null)
                .putString("PW", null)
                .putBoolean("LogIn",false)
                .apply();
        Toast.makeText(MainActivity.this, "登出成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "再按一下退出程式", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}

