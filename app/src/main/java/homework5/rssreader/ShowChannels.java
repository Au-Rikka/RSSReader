package homework5.rssreader;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.nio.channels.Channel;

import homework5.rssreader.Channels.ChannelAdapter;
import homework5.rssreader.Channels.ChannelDialog;
import homework5.rssreader.Channels.ChannelsStuff;
import homework5.rssreader.Channels.TChannel;
import homework5.rssreader.RSS.RSSAdapter;
import homework5.rssreader.RSS.TNews;

/**
 * Created by Anstanasia on 26.12.2015.
 */
public class ShowChannels extends Activity {
    ChannelAdapter adapter;
    ListView channelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("ShowChannels", "123");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.channel_list);

        channelView = (ListView) findViewById(R.id.channelListView);

        updateChannelList();

        channelView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View view, int pos, long l) {
                ChannelDialog dialog = new ChannelDialog();
                Bundle args = new Bundle();
                args.putString(ChannelDialog.EXTRA_TITLE, adapter.getItem(pos).getTitle());
                args.putString(ChannelDialog.EXTRA_URL, adapter.getItem(pos).getUrl());
                dialog.setArguments(args);
                dialog.show(getFragmentManager(), "dialog");
            }
        });

        Button button = (Button) findViewById(R.id.new_channel_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChannelDialog dialog = new ChannelDialog();
                dialog.show(getFragmentManager(), "dialog");
            }
        });
    }

    public void updateChannelList() {
        if (ChannelsStuff.res == null) {
            Toast toast = Toast.makeText(getApplicationContext(), "List of channels is empty", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            adapter = new ChannelAdapter();
            adapter.setData((java.util.ArrayList<TChannel>) ChannelsStuff.res);
            adapter.notifyDataSetChanged();
            channelView.setAdapter(adapter);
        }
    }


    static final String TAG = "ShowChannels Activity";
}
