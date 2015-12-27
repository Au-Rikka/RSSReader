package homework5.rssreader.Channels;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import homework5.rssreader.R;

/**
 * Created by Anstanasia on 26.12.2015.
 */
public class ChannelAdapter extends BaseAdapter {
    private ArrayList<TChannel> data = new ArrayList<TChannel>();

    public void setData(ArrayList<TChannel> d) {this.data = d;}

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public TChannel getItem(int pos) {
        return data.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        Log.d("ChannelAdapter", "!!!!!!!!!!!!!!!!!!!!!!!!!!!!qwerty");

        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.channel_item, parent, false);
        }

        final TChannel x = getItem(pos);

        ((TextView) view.findViewById(R.id.channel_title)).setText(x.getTitle());
        ((TextView) view.findViewById(R.id.channel_url)).setText(x.getUrl());
        view.findViewById(R.id.channel_title).setBackgroundColor(Color.GRAY);
        view.findViewById(R.id.channel_url).setBackgroundColor(Color.GRAY);

        return view;
    }
}
