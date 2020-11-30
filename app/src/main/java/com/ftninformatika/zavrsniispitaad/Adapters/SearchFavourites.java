package com.ftninformatika.zavrsniispitaad.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ftninformatika.zavrsniispitaad.Model.Movie;
import com.ftninformatika.zavrsniispitaad.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchFavourites extends BaseAdapter {

    private List<Movie> movies = null;
    private Activity activity;

    public SearchFavourites(List<Movie> movies, Activity activity) {
        this.movies = movies;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Movie getItem(int i) {
        return movies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.listview_single_item, null);
        }

        ImageView imageView = convertView.findViewById(R.id.image_Movie);

        TextView textView = convertView.findViewById(R.id.textView_Title);
        TextView textView1 = convertView.findViewById(R.id.textView_Year);
        TextView textView2 = convertView.findViewById(R.id.textView_Type);

        textView.setText(movies.get(i).getTitle());
        textView1.setText(movies.get(i).getYear());
        textView2.setText(movies.get(i).getType());

        Picasso.get().load(movies.get(i).getPoster()).into(imageView);

        return convertView;
    }
}
