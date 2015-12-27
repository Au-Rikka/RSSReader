package homework5.rssreader;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import homework5.rssreader.Channels.ChannelAdapter;
import homework5.rssreader.Channels.ChannelDialog;
import homework5.rssreader.Channels.ChannelsStuff;
import homework5.rssreader.Channels.TChannel;

/**
 * Created by Anstanasia on 26.12.2015.
 */
public class ShowChannels extends Activity implements ChannelDialog.OnCompleteListener {
    private static final int REQUEST_ADD_CHANNEL = 1;
    private static final int REQUEST_CHANGE_CHANNEL = 2;

    ChannelAdapter adapter;
    ListView channelView;
    int lastItem;
    int currCode;

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
                lastItem = pos;
                currCode = REQUEST_CHANGE_CHANNEL;
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
                currCode = REQUEST_ADD_CHANNEL;
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

    @Override
    public void onComplete(String title, String url) {
        if (currCode == REQUEST_ADD_CHANNEL) {
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://" + url;
            }
            ChannelsStuff.addNewChannel(title, url, this);
            adapter.notifyDataSetChanged();
            Main.download();
        } else if (currCode == REQUEST_CHANGE_CHANNEL) {
            if (title == null && url == null) {
                ChannelsStuff.deleteChannel(lastItem, this);
            } else {
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "http://" + url;
                }
                ChannelsStuff.changeChannel(title, url, lastItem, this);
            }
            Main.download();
            adapter.notifyDataSetChanged();
        }
    }

    static final String TAG = "ShowChannels Activity";

}
