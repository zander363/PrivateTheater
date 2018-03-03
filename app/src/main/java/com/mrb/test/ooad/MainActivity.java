package com.mrb.test.ooad;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.mrb.test.MOVIE.MovieControl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
//import com.mrb.test.Class.MovieControl;

public class MainActivity extends RootActivity {
    private ViewPager viewPager;
    private ArrayList<ImageView> viewList;
    private ImageView thirdImage;
    private String[] urls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            MovieControl.InitMovieData(getApplicationContext());

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            CurrentMenuItem = 0;
            NV.getMenu().getItem(CurrentMenuItem).setChecked(true);

            viewList=new ArrayList<>();
            //ViewGroup group = (ViewGroup)findViewById(R.id.viewGroup);
            viewPager = (ViewPager) findViewById(R.id.view_pager);

            LayoutInflater inflater = getLayoutInflater();
            urls = new String[]{"https://movies.tw.campaign.yahoo.net/i/o/production/movies/February2018/DkILFQciRbkKpKsnkrkJ-1956x2793.jpg",
                    "https://movies.tw.campaign.yahoo.net/i/o/production/movies/December2017/YnIWe5i29EANr4EV797j-2037x2910.jpg",
                    "https://movies.tw.campaign.yahoo.net/i/o/production/movies/February2018/KiJW5uM9f9rSrx6GKYdU-4075x5817.jpg"};

            PagerAdapter pagerAdapter = new PagerAdapter() {
                @Override
                public boolean isViewFromObject(View arg0, Object arg1) {
                    // TODO Auto-generated method stub
                    return arg0 == arg1;
                }

                @Override
                public int getCount() {
                    // TODO Auto-generated method stub
                    return viewList.size();
                }

                @Override
                public void destroyItem(ViewGroup container, int position,
                                        Object object) {
                    // TODO Auto-generated method stub
                    container.removeView(viewList.get(position));
                }

                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                    // TODO Auto-generated method stub
                    container.addView(viewList.get(position));

                    return viewList.get(position);
                }
            };
            //建立一個AsyncTask執行緒進行圖片讀取動作，並帶入圖片連結網址路徑
            for(String url: urls) {
                final ImageView imageView = new ImageView(this);
                new AsyncTask<String, Void, Bitmap>() {
                    @Override
                    protected Bitmap doInBackground(String... params) {
                        String url = params[0];
                        return getBitmapFromURL(url);
                    }

                    @Override
                    protected void onPostExecute(Bitmap result) {
                        imageView.setImageBitmap(result);
                        super.onPostExecute(result);
                    }
                }.execute(url);
                viewList.add(imageView);
                viewPager.setAdapter(pagerAdapter);
            }
        }catch (Exception e){
            Toast.makeText(com.mrb.test.ooad.MainActivity.this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
    }


    private Drawable loadImageFromURL(String url){
        try{
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable draw = Drawable.createFromStream(is, "src");
            return draw;
        }catch (Exception e) {
            Toast.makeText(com.mrb.test.ooad.MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }
    private static Bitmap getBitmapFromURL(String imageUrl)
    {
        try
        {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
