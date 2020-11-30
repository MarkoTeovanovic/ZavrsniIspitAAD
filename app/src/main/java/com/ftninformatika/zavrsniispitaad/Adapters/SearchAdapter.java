package com.ftninformatika.zavrsniispitaad.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ftninformatika.zavrsniispitaad.Model.Search;
import com.ftninformatika.zavrsniispitaad.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchAdapter extends BaseAdapter {

    private List<Search> movies = null;
    private Activity activity;

    public SearchAdapter(List<Search> movies, Activity activity) {
        this.movies = movies;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Search getItem(int i) {
        return movies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View converView, ViewGroup parent) {
        if (converView == null) {
            converView = activity.getLayoutInflater().inflate(R.layout.listview_single_item, null);
        }

        ImageView imageView = converView.findViewById(R.id.image_Movie);

        TextView textView = converView.findViewById(R.id.textView_Title);
        TextView textView1 = converView.findViewById(R.id.textView_Year);
        TextView textView2 = converView.findViewById(R.id.textView_Type);

        textView.setText(movies.get(i).getTitle());
        textView1.setText(movies.get(i).getYear());
        textView2.setText(movies.get(i).getType());

        Picasso.get().load(movies.get(i).getPoster()).into(imageView);


        return converView;
    }
}
