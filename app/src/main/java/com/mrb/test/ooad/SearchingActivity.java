package com.mrb.test.ooad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class SearchingActivity extends AppCompatActivity {
    private ImageButton rating;
    private ImageButton time;
    private ImageButton seat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);

        rating = (ImageButton) findViewById(R.id.ratingButton);
        time = (ImageButton) findViewById(R.id.timeButton);
        seat = (ImageButton) findViewById(R.id.seatButton);

        rating.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                Intent intent = new Intent();
                intent.setClass(SearchingActivity.this, SearchingRatingActivity.class);
                startActivity(intent);
            }
        });
        time.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                Intent intent = new Intent();
                intent.setClass(SearchingActivity.this, SearchingTimeActivity.class);
                startActivity(intent);
            }
        });

        seat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                    /*
                    Intent intent = new Intent();
                    intent.setClass(SearchingActivity.this, RefundingActivity.class);
                    startActivity(intent);
                    */
                Toast.makeText(SearchingActivity.this, "功能尚未開放", Toast.LENGTH_LONG).show();
            }
        });
    }
}
