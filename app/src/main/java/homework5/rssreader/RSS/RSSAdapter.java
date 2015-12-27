package homework5.rssreader.RSS;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import homework5.rssreader.R;

/**
 * Created by Anstanasia on 14.10.2014.
 */

public class RSSAdapter extends BaseAdapter {
    private ArrayList <TNews> data = new ArrayList<TNews>();

    public void setData(ArrayList<TNews> d) {
        if (d != null && d.size() > 0) {
            Log.d("adapter", d.get(0).getDescription());
        }
        this.data = d;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public TNews getItem(int pos) {
        return data.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        Log.d("RSSAdapter", "!!!!!!!!!!!!!!!!!!!!!!!!!!!!qwerty");

        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        }

        final TNews x = getItem(pos);

        ((TextView) view.findViewById(R.id.title)).setText(x.getTitle());
        ((TextView) view.findViewById(R.id.content)).setText(x.getDescription());
        ((TextView) view.findViewById(R.id.rssDate)).setText(x.getDate());
        ((TextView) view.findViewById(R.id.rssParentTitle)).setText(x.getParent());
        view.findViewById(R.id.title).setBackgroundColor(Color.GRAY);
        view.findViewById(R.id.content).setBackgroundColor(Color.GRAY);
        view.findViewById(R.id.rssDate).setBackgroundColor(Color.GRAY);
        view.findViewById(R.id.rssParentTitle).setBackgroundColor(Color.GRAY);


        return view;
    }
}