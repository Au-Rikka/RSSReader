package homework5.rssreader;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

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
