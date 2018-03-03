package com.mrb.test.ooad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class SearchingRatingActivity extends RootActivity {
    private Button confirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_searching_rating);

            confirmation = (Button) findViewById(R.id.confirm);
            CurrentMenuItem = 3;
            NV.getMenu().getItem(CurrentMenuItem).setChecked(true);

            final List<MovieOnList> movie_list = new ArrayList<>();
            movie_list.add(new MovieOnList("亞瑟：王者之劍", "King Arthur: Legend of the Sword", "片長：127分", 1));
            movie_list.add(new MovieOnList("逃出絕命鎮", "Get Out", " 片長：104分 ", 4));
            movie_list.add(new MovieOnList("攻殼機動隊1995", "GHOST IN THE SHELL", " 片長：83分 ", 1));
            movie_list.add(new MovieOnList("我和我的冠軍女兒", "Dangal", " 片長：161分 ", 0));
            movie_list.add(new MovieOnList("異形：聖約", "Alien: Covenant ", " 片長：122分 ", 2));


            confirmation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doubleBackToExitPressedOnce=false;


                    //Bundle bundle = new Bundle();

                    Intent intent = new Intent();
                    intent.setClass(SearchingRatingActivity.this, MovieListActivity.class);
                    /*
                    for(int i=0;i<movie_list.size();i++) {
                        bundle.putSerializable("movies"+i,movie_list.get(i));
                    }

                    intent.putExtras(bundle);*/

                    startActivity(intent);
                }
            });
        }catch (Exception e){
            Toast.makeText(SearchingRatingActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
