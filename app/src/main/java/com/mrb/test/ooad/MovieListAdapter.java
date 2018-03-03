package com.mrb.test.ooad;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mr.B on 2018/2/25.
 */

public class MovieListAdapter extends BaseAdapter {
    private LayoutInflater myInflater;
    private List<MovieOnList> movies;

    public MovieListAdapter(Context context, List<MovieOnList> movie){
        myInflater = LayoutInflater.from(context);
        this.movies = movie;
    }
    @Override
    public int getCount() {
        return movies.size();
    }
    @Override
    public Object getItem(int arg0) {
        return movies.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView title;
        TextView subTitle;
        TextView time;
        TextView classification;
        public ViewHolder(TextView title, TextView subTitle, TextView time, TextView classification) {
            this.title = title;
            this.subTitle = subTitle;
            this.time = time;
            this.classification = classification;
        }
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            convertView = myInflater.inflate(R.layout.movie_list, null);
            holder = new ViewHolder(
                    (TextView) convertView.findViewById(R.id.textTitle),
                    (TextView) convertView.findViewById(R.id.textSubTitle),
                    (TextView) convertView.findViewById(R.id.textTime),
                    (TextView) convertView.findViewById(R.id.textClassification)
            );
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        MovieOnList movie = (MovieOnList) getItem(position);
        String text_class[] = {"普遍級 0+","保護級 6+","輔12級 12+","輔15級","限制級18+"};
        int color_class[] = {0xFF3A8F2F,0xFF1179AE,0xFFF2B320,0xFFFF5A1E,0xFFEB252C};

        int type_num = movie.getClassification();
        holder.classification.setBackgroundColor(color_class[type_num]);
        holder.classification.setText(text_class[type_num]);
        holder.title.setText(movie.getName());
        holder.subTitle.setText(movie.getSubTitle());
        holder.time.setText(movie.getTime());

        return convertView;
    }
}
