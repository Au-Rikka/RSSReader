package homework5.rssreader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import homework5.rssreader.Channels.ChannelsStuff;
import homework5.rssreader.RSS.RSSAdapter;
import homework5.rssreader.RSS.TNews;
import homework5.rssreader.RSS.UpdateRSSList;


public class Main extends Activity {
    Button rss, channels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_very_main);

        Log.d("main", "start");

        ChannelsStuff.loadChannelsFromDB(this);

        rss = (Button) findViewById(R.id.go_to_rss_feed);
        channels = (Button) findViewById(R.id.go_to_channels);

        rss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(Main.this, ShowRss.class);
                startActivity(intent);
            }
        });

        channels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(Main.this, ShowChannels.class);
                startActivity(intent);
            }
        });


    }
}
