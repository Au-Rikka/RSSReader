package homework5.rssreader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;



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

        download();

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
                MyAdapter a = (MyAdapter) av.getAdapter();
                TNews n = a.getItem(pos);
                intent.putExtra("url", n.getLink());
                startActivity(intent);
            }
        });
    }

    public void download() {
       new Update(this, rssList).execute("http://feeds.bbci.co.uk/news/rss.xml");
    }
}
