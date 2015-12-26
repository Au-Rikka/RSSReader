package homework5.rssreader;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Anstanasia on 26.12.2015.
 */
public class ShowChannels extends Activity {
    ChannelAdapter adapter;
    ListView channelView;
    List<TChannel> res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("ShowChannels", "123");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.channel_list);

        channelView = (ListView) findViewById(R.id.channelListView);

        updateChannelList();
    }

    public void updateChannelList() {
        //засунуть список каналов в рез

        if (res == null) {
            Toast toast = Toast.makeText(getApplicationContext(), "List of channels is empty", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            adapter = new ChannelAdapter();
            adapter.setData((java.util.ArrayList<TChannel>) res);
            adapter.notifyDataSetChanged();
            channelView.setAdapter(adapter);
        }
    }

}
