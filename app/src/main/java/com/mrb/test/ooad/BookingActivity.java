package com.mrb.test.ooad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.mrb.test.MOVIE.MovieControl;

import java.util.ArrayList;

public class BookingActivity extends AppCompatActivity {
    private Button confirmation;
    private Spinner movies_spinner;
    private Spinner time_spinner;
    private EditText number_input;
    private Switch row_region;
    private Spinner row_region_spinner;
    private int movieorder;
    private int timeorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        confirmation = (Button) findViewById(R.id.confirmation);
        number_input = (EditText) findViewById(R.id.numberInput);
        movies_spinner = (Spinner)findViewById(R.id.movieSpinner);
        time_spinner = (Spinner)findViewById(R.id.timeSpinner);
        row_region = (Switch) findViewById(R.id.row_region);
        row_region_spinner = (Spinner) findViewById(R.id.row_region_spinner);

        MovieControl.InitMovieData();

        confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("0".equals(number_input.getText().toString())){
                    number_input.setText( "" );
                    Toast.makeText(BookingActivity.this,"張數錯誤，請重新輸入", Toast.LENGTH_SHORT).show();
                }
                if(!"請選擇電影".equals(movies_spinner.getSelectedItem().toString())&&
                        !"請選擇場次".equals(time_spinner.getSelectedItem().toString())&&
                        !"".equals(number_input.getText().toString())){
                    /*
                    Intent intent = new Intent();
                    intent.setClass(BookingActivity.this, next.class);
                    intent.putExtra("number_sheets", number_input.getText().toString());
                    intent.putExtra("movie", movies_spinner.getSelectedItem().toString());
                    intent.putExtra("time", time_spinner.getSelectedItem().toString());
                    intent.putExtra("movieorder", Integer.toString(movieorder));
                    //把字串傳到第二個Activity
                    startActivity(intent);
                    */
                    Toast.makeText(BookingActivity.this,"棒 可以繼續往下開發囉", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(BookingActivity.this,"有問題尚未回答", Toast.LENGTH_SHORT).show();
                }

                ArrayList<String> movies = MovieControl.getMovieList();
                movies.add(0,"請選擇電影");

                ArrayAdapter<String> movieList = new ArrayAdapter<>(BookingActivity.this,
                        android.R.layout.simple_spinner_dropdown_item,
                        movies);
                movies_spinner.setSelection(0);
                movies_spinner.setAdapter(movieList);
/*
                movies_spinner.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener(){
                    @Override
                    public void onItemSelected(AdapterView<?>parent, View view, int position, long id){
                        if (position>0) {
                            movieorder = position-1;
                            String[] time = new String[Base.moviesList.get( movieorder ).time.length+1];
                            time[0]="請選擇場次";
                            for (int i = 0; i < Base.moviesList.get( movieorder ).time.length; i++) {
                                time[i+1] = new String( Base.moviesList.get( movieorder ).time[i].getinfo());
                            }
                            ArrayAdapter<String> timeList = new ArrayAdapter<>(BookingActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item,
                                    time);
                            time_spinner.setSelection(0);
                            time_spinner.setAdapter(timeList);

                            time_spinner.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    if(position>0){
                                        timeorder = position-1;
                                        row_region.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
                                            public void onCheckedChanged(Switch buttonView, boolean isChecked) {
                                                if (isChecked) {
                                                    // The region mode
                                                    String[] time = new String[Base.moviesList.get(movieorder).time.length + 1];
                                                    time[0] = "請選擇區域";
                                                    for (int i = 0; i < Base.moviesList.get(movieorder).time.length; i++) {
                                                        time[i + 1] = new String(Base.moviesList.get(movieorder).time[i].getinfo());
                                                    }
                                                    ArrayAdapter<String> timeList = new ArrayAdapter<>(BookingActivity.this,
                                                            android.R.layout.simple_spinner_dropdown_item,
                                                            time);
                                                    time_spinner.setSelection(0);
                                                    time_spinner.setAdapter(timeList);

                                                } else {
                                                    // The row mode
                                                    String[] time = new String[Base.moviesList.get(movieorder).time.length + 1];
                                                    time[0] = "請選擇排數";
                                                    for (int i = 0; i < Base.moviesList.get(movieorder).time.length; i++) {
                                                        time[i + 1] = new String(Base.moviesList.get(movieorder).time[i].getinfo());
                                                    }
                                                    ArrayAdapter<String> timeList = new ArrayAdapter<>(BookingActivity.this,
                                                            android.R.layout.simple_spinner_dropdown_item,
                                                            time);
                                                    time_spinner.setSelection(0);
                                                    time_spinner.setAdapter(timeList);
                                                }
                                            }
                                        });


                                    }else{

                                    }
                                });
                                @Override
                                public void onNothingSelected(AdapterView<?> arg0) {
                                    String[] time = new String[1];
                                    time[0] = "";
                                    ArrayAdapter<String> timeList = new ArrayAdapter<>(BookingActivity.this,
                                            android.R.layout.simple_spinner_dropdown_item,
                                            time);
                                    time_spinner.setSelection(0);
                                    time_spinner.setAdapter(timeList);
                                }
                            });
                        }
                        else{
                            String[] time = new String[1];
                            time[0]="";
                            ArrayAdapter<String> timeList = new ArrayAdapter<>(BookingActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item,
                                    time);
                            time_spinner.setSelection(0);
                            time_spinner.setAdapter(timeList);
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        String[] time = new String[1];
                        time[0]="";
                        ArrayAdapter<String> timeList = new ArrayAdapter<>(BookingActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                time);
                        time_spinner.setSelection(0);
                        time_spinner.setAdapter(timeList);

                    }
                }));
                */
            }
        });
    }
}
