package com.ftninformatika.zavrsniispitaad.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ftninformatika.zavrsniispitaad.Model.NavigationItem;
import com.ftninformatika.zavrsniispitaad.R;

import java.util.List;

public class DrawerListAdapter extends BaseAdapter {

    private List <NavigationItem> items = null;
    private Activity activity;

    public DrawerListAdapter(List<NavigationItem> items, Activity activity) {
        this.items = items;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public NavigationItem getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.drawer_single_list, null);
        }
        ImageView imageView = convertView.findViewById(R.id.imageView_Icon);
        TextView textView = convertView.findViewById(R.id.textView_Title_Drawer);
        TextView textView1 = convertView.findViewById(R.id.textView_Subtitle_Drawer);

        textView.setText(items.get(i).getTitle());
        textView1.setText(items.get(i).getSubtitle());
        imageView.setImageResource(items.get(i).getIcon());

        return convertView;
    }
}
