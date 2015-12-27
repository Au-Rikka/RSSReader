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
    Button button;
    ListView rssList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("main", "start");

        button = (Button) findViewById(R.id.okButton);
        rssList = (ListView) findViewById(R.id.rssListView);

        if (savedInstanceState == null) {
            ChannelsStuff.loadChannelsFromDB(this);
            download();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(Main.this, ShowChannels.class);
                startActivity(intent);
            }
        });

        final Intent intent = new Intent(Main.this, ShowWeb.class);
        rssList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View view, int pos, long l) {
                RSSAdapter a = (RSSAdapter) av.getAdapter();
                TNews n = a.getItem(pos);
                intent.putExtra("url", n.getLink());
                startActivity(intent);
            }
        });
    }

    public void download() {
       new UpdateRSSList(this, rssList).execute();
        //.execute("http://feeds.bbci.co.uk/news/rss.xml");
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
