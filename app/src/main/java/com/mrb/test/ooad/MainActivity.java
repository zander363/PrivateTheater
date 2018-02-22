package com.mrb.test.ooad;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import com.mrb.test.Class.MOVIE.MovieControl;

public class MainActivity extends AppCompatActivity {
    private ImageButton booking;
    private ImageButton refunding;
    private ImageButton searching;

    //判斷離開的flag，設定成全域變數
    private boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            //Base.loadMovie();
            MovieControl.InitMovieData();
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            booking = (ImageButton) findViewById(R.id.bookingButton);
            refunding = (ImageButton) findViewById(R.id.refundingButton);
            searching = (ImageButton) findViewById(R.id.searchingButton);
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

            booking.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doubleBackToExitPressedOnce = false;
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, BookingActivity.class);
                    startActivity(intent);
                }
            });
            refunding.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doubleBackToExitPressedOnce = false;
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, RefundingActivity.class);
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

