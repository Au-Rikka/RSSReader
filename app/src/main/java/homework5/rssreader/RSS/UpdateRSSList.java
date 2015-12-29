package homework5.rssreader.RSS;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import homework5.rssreader.Channels.ChannelsStuff;
import homework5.rssreader.Channels.TChannel;
import homework5.rssreader.RSS.RSSAdapter;
import homework5.rssreader.RSS.RSSParser;
import homework5.rssreader.RSS.TNews;
import homework5.rssreader.ShowChannels;
import homework5.rssreader.ShowRss;

/**
 * Created by Anstanasia on 21.10.2014.
 */
public class UpdateRSSList extends AsyncTask<Void, Void, List<TNews>> {
    Context context;
    ListView rssList;

    public UpdateRSSList(Context c, ListView l) {
        context = c;
        rssList = l;
    }

    @Override
    protected List<TNews> doInBackground(Void... ignore) {
        List<TNews> res = new ArrayList<TNews>();

        for (int i = 0; i < ChannelsStuff.res.size(); i++) {
            List<TNews> curList;
            TChannel channel = ChannelsStuff.res.get(i);
            try {
                URL url = new URL(channel.getUrl());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                InputStream stream = connection.getInputStream();

                Log.d("Updater", "OK 1");

                Reader reader = new InputStreamReader(stream, "windows-1251");
                InputSource is = new InputSource(reader);
                is.setEncoding("windows-1251");

                Log.d("Updater", "OK 2");

                SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
                RSSParser RSSParser = new RSSParser();
                saxParser.parse(is, RSSParser);

                Log.d("Updater", "OK 3");

                curList = RSSParser.getNews();

                if (curList != null) {
                    for (int j = 0; j < curList.size(); j++) {
                        curList.get(j).setParent(channel.getTitle());
                        res.add(curList.get(j));
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
        }

        return res;
    }

    @Override
    protected void onPostExecute(List<TNews> res) {
        if (res == null || res.size() == 0) {
            Log.d("UpdateRSSList", "null pointer!!!!!!!!!!!!!!");
        } else {
            Log.d("UpdateRSSList", res.get(0).getDescription());
        }


        Collections.sort(res, new Comparator<TNews>() {
            public int compare(TNews o1, TNews o2) {
                if (o1.getDate2() == null) {
                    return 1;
                }
                if (o2.getDate2() == null) {
                    return -1;
                }
                return -o1.getDate2().compareTo(o2.getDate2());
            }
        });

        RSSAdapter adapter = new RSSAdapter();
        adapter.setData(res);
        adapter.notifyDataSetChanged();
        rssList.setAdapter(adapter);
    }
}
