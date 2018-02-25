package com.mrb.test.ooad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mrb.test.MOVIE.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieListActivity extends AppCompatActivity {
    private ListView listV;
    private MovieListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_movie_list);

            //------
            //This Block is for test
            //The real one should transfer by bundle
            List<MovieOnList> movie_list = new ArrayList<>();
            movie_list.add(new MovieOnList("亞瑟：王者之劍", "King Arthur: Legend of the Sword", "片長：127分", 1));
            movie_list.add(new MovieOnList("逃出絕命鎮", "Get Out", " 片長：104分 ", 3));
            movie_list.add(new MovieOnList("攻殼機動隊1995", "GHOST IN THE SHELL", " 片長：83分 ", 1));
            movie_list.add(new MovieOnList("我和我的冠軍女兒", "Dangal", " 片長：161分 ", 0));
            movie_list.add(new MovieOnList("異形：聖約", "Alien: Covenant ", " 片長：122分 ", 2));
            //
            //------

            listV = (ListView) findViewById(R.id.movie_list);

            adapter = new MovieListAdapter(MovieListActivity.this, movie_list);
            listV.setAdapter(adapter);
        }catch (Exception e){
            Toast.makeText(MovieListActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
