package com.mrb.test.ooad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
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
    private int regionorder;
    private int roworder;

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


        //Here to init all database
        MovieControl.InitMovieData();
        Toast.makeText(BookingActivity.this,"init OK", Toast.LENGTH_SHORT).show();

        confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("0".equals(number_input.getText().toString())) {
                    number_input.setText("");
                    Toast.makeText(BookingActivity.this, "張數錯誤，請重新輸入", Toast.LENGTH_SHORT).show();
                }
                if (!"請選擇電影".equals(movies_spinner.getSelectedItem().toString()) &&
                        !"請選擇場次".equals(time_spinner.getSelectedItem().toString()) &&
                        !"".equals(number_input.getText().toString())) {
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
                    Toast.makeText(BookingActivity.this, "棒 可以繼續往下開發囉", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BookingActivity.this, "有問題尚未回答", Toast.LENGTH_SHORT).show();
                }
            }
        });


        final ArrayList<String> movies = MovieControl.getMovieList();
        movies.add(0,"請選擇電影");

        ArrayAdapter<String> movieList = new ArrayAdapter<>(BookingActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                movies);
        movies_spinner.setSelection(0);
        movies_spinner.setAdapter(movieList);

        movies_spinner.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?>parent, View view, int position, long id){
                if (position>0) {
                    movieorder = position-1;
                    MovieControl.setCurrentMovieName(movies.get(position));

                    final ArrayList<String> time = MovieControl.getSession();
                    time.add(0,"請選擇場次");

                    ArrayAdapter<String> timeList = new ArrayAdapter<>(BookingActivity.this,
                            android.R.layout.simple_spinner_dropdown_item,
                            time);
                    time_spinner.setSelection(0);
                    time_spinner.setAdapter(timeList);
                    //setting time spinner
                    time_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if(position>0){
                                timeorder = position-1;
                                MovieControl.setCurrentSession(time.get(position));
                                if(MovieControl.getisBigHall()) {
                                    row_region.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                        @Override
                                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                            if (isChecked) {
                                                // The region mode
                                                //set to region mode
                                                ArrayList<String> region = MovieControl.ShowRegionState();
                                                region.add(0,"請選擇區域");
                                                ArrayAdapter<String> regionList = new ArrayAdapter<>(BookingActivity.this,
                                                        android.R.layout.simple_spinner_dropdown_item,region);
                                                row_region_spinner.setSelection(0);
                                                row_region_spinner.setAdapter(regionList);

                                                //add the region spinner setting

                                            } else {
                                                // The row mode
                                                //set to row mode
                                                ArrayList<String> row = MovieControl.ShowRowState();
                                                row.add(0,"請選擇排數");
                                                ArrayAdapter<String> rowList = new ArrayAdapter<>(BookingActivity.this,
                                                        android.R.layout.simple_spinner_dropdown_item,
                                                        row);
                                                row_region_spinner.setSelection(0);
                                                row_region_spinner.setAdapter(rowList);

                                                //add the region spinner setting
                                            }
                                        }
                                    });
                                }
                                else{
                                    ArrayList<String> row_region = new ArrayList<String>();
                                    row_region.add("小廳無法選擇排數或區域");
                                    ArrayAdapter<String> row_region_List = new ArrayAdapter<>(BookingActivity.this,
                                            android.R.layout.simple_spinner_dropdown_item,row_region);
                                    row_region_spinner.setSelection(0);
                                    row_region_spinner.setAdapter(row_region_List);
                                }

                            }else{
                                ArrayList<String> row_region = new ArrayList<String>();
                                row_region.add("");
                                ArrayAdapter<String> row_region_List = new ArrayAdapter<>(BookingActivity.this,
                                        android.R.layout.simple_spinner_dropdown_item,
                                        row_region);
                                row_region_spinner.setSelection(0);
                                row_region_spinner.setAdapter(row_region_List);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> arg0) {
                            ArrayList<String> row_region = new ArrayList<String>();
                            row_region.add("");
                            ArrayAdapter<String> row_region_List = new ArrayAdapter<>(BookingActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item, row_region);
                            row_region_spinner.setSelection(0);
                            row_region_spinner.setAdapter(row_region_List);
                        }
                    });
                }
                else{
                    ArrayList<String> time = new ArrayList<String>();
                    time.add("");
                    ArrayAdapter<String> timeList = new ArrayAdapter<>(BookingActivity.this,
                            android.R.layout.simple_spinner_dropdown_item,
                            time);
                    time_spinner.setSelection(0);
                    time_spinner.setAdapter(timeList);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                ArrayList<String> time = new ArrayList<String>();
                time.add("");
                ArrayAdapter<String> timeList = new ArrayAdapter<>(BookingActivity.this,
                        android.R.layout.simple_spinner_dropdown_item,
                        time);
                time_spinner.setSelection(0);
                time_spinner.setAdapter(timeList);
            }
        }));

    }
}
