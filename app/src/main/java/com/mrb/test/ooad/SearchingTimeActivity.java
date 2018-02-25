package com.mrb.test.ooad;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import com.mrb.test.MOVIE.Time;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SearchingTimeActivity extends AppCompatActivity {
    private Button start_time;
    private Button end_time;

    private TimePickerDialog timePickerDialog2;
    private TimePickerDialog timePickerDialog;
    private Button confirmation;
    private Time start_ourtime;
    private Time end_ourtime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching_time);
        start_time = (Button) findViewById(R.id.timestart);
        end_time = (Button) findViewById(R.id.timeend);
        confirmation = (Button) findViewById(R.id.confirmation);

        GregorianCalendar calendar = new GregorianCalendar();


        confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent intent = new Intent();
                intent.setClass(SearchingTimeActivity.this, next.class);
                intent.putExtra("starttime",start_ourtime.toString());
                intent.putExtra("endtime",end_ourtime.toString());
                startActivity(intent);
        */
            }
        });

    // 實作TimePickerDialog的onTimeSet方法，設定時間後將所設定的時間show在textTime上
    timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
        //將時間轉為12小時製顯示出來
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            start_time.setText((hourOfDay > 12 ? hourOfDay - 12 : hourOfDay)
                    + ":" + (minute>9 ? "":"0") + minute + " " + (hourOfDay > 12 ? "PM" : "AM"));
            start_ourtime = new Time(Integer.toString(hourOfDay), Integer.toString(minute));

        }
    }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(calendar.MINUTE),
            false);
    // 實作TimePickerDialog的onTimeSet方法，設定時間後將所設定的時間show在textTime上
    timePickerDialog2 = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
        //將時間轉為12小時製顯示出來
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            end_time.setText((hourOfDay > 12 ? hourOfDay - 12 : hourOfDay)
                    + ":" + (minute>9 ? "":"0") + minute + " " + (hourOfDay > 12 ? "PM" : "AM"));
            end_ourtime = new Time(Integer.toString(hourOfDay), Integer.toString(minute));
        }
    }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(calendar.MINUTE),
            false);
}



    // setTime Button onClick 顯示時間設定視窗
    public void setTime(View v) {
        timePickerDialog.show();
    }

    // setTime Button onClick 顯示時間設定視窗
    public void setTime2(View v) {
        timePickerDialog2.show();
    }

    public void reset(View v) {
        start_time.setText("-- : --");
    }

    public void reset2(View v) {
        end_time.setText("-- : --");
    }
}
