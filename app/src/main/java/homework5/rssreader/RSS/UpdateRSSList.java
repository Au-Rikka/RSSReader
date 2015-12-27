package homework5.rssreader.RSS;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import homework5.rssreader.RSS.RSSAdapter;
import homework5.rssreader.RSS.RSSParser;
import homework5.rssreader.RSS.TNews;

/**
 * Created by Anstanasia on 21.10.2014.
 */
public class UpdateRSSList extends AsyncTask<String, Void, List<TNews>> {
    Context context;
    RSSAdapter adapter;
    ListView rssList;

    public UpdateRSSList(Context c, ListView l) {
        context = c;
        rssList = l;
    }

    @Override
    protected List<TNews> doInBackground(String... urls) {
       // Log.d("Updater: ", Thread.currentThread().getName());

        try {
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
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

            return RSSParser.getNews();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<TNews> res) {
        if (res == null) {
            Log.d("UpdateRSSList", "null pointer!!!!!!!!!!!!!!");
        } else {
            Log.d("UpdateRSSList", res.get(0).getDescription());
        }

        adapter = new RSSAdapter();
        adapter.setData((java.util.ArrayList<TNews>) res);
        adapter.notifyDataSetChanged();
        rssList.setAdapter(adapter);
    }
}
