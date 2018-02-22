package com.mrb.test.ooad;

import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
//import com.mrb.test.Class.MovieControl;

public class MainActivity extends AppCompatActivity {
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
            //oopuser = new OOPUser(this);
            //helper = new UserDBHelper(this, "oop.movie", null, 1);
            //Toast.makeText(MainActivity.this, Integer.toString(  oopuser.getCount()), Toast.LENGTH_LONG).show();
            //if (oopuser.getCount() == 0) {
            //    oopuser.connect();
            //}

            if (this.getIntent().getExtras() != null) {
                Bundle bundle = this.getIntent().getExtras();
                doubleBackToExitPressedOnce = Boolean.parseBoolean(bundle.getString("directgoback"));
            }

            movie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doubleBackToExitPressedOnce = false;
                    /*
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, RefundingActivity.class);
                    startActivity(intent);
                    */
                    Toast.makeText(MainActivity.this, "功能尚未開放", Toast.LENGTH_LONG).show();
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
                    /*
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, SearchingActivity.class);
                    startActivity(intent);
                    */
                    Toast.makeText(MainActivity.this, "功能尚未開放", Toast.LENGTH_LONG).show();
                }
            });

        } catch (Exception e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
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

