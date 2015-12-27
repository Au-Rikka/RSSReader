package homework5.rssreader.Channels;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anstanasia on 27.12.2015.
 */
public class ChannelsStuff {
    static public List<TChannel> res;

    public static void loadChannelsFromDB(Context context) {
        res = new ArrayList<TChannel>();

        ChannelsDBHelper dbHelper = new ChannelsDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Log.d(TAG, "--- Rows in mytable: ---");

        Cursor c = db.query("channels", null, null, null, null, null, null);

        if (c.moveToFirst()) {
            int idColIndex = c.getColumnIndex("id");
            int titleColIndex = c.getColumnIndex("title");
            int urlColIndex = c.getColumnIndex("url");

            do {
                res.add(new TChannel(c.getString(titleColIndex), c.getString(urlColIndex), c.getLong(idColIndex)));
                Log.d(TAG, "ID = " + c.getInt(idColIndex) +
                        ", title = " + c.getString(titleColIndex) +
                        ", url = " + c.getString(urlColIndex));
            } while (c.moveToNext());
        } else
            Log.d(TAG, "0 rows");
        c.close();

        dbHelper.close();
    }


    public static void addNewChannel(String title, String url, Context context) {
        ContentValues cv = new ContentValues();
        ChannelsDBHelper dbHelper = new ChannelsDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        Log.d(TAG, "--- Insert in channels: ---");

        cv.put("title", title);
        cv.put("url", url);

        long rowID = db.insert("channels", null, cv);
        Log.d(TAG, "row inserted, ID = " + rowID);

        res.add(new TChannel(title, url, rowID));

        dbHelper.close();
    }

    public static void changeChannel(String title, String url, int i, Context context) {
        ContentValues cv = new ContentValues();
        ChannelsDBHelper dbHelper = new ChannelsDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Long id = res.get(i).getId();

        Log.d(TAG, "--- Update channels: ---");

        cv.put("title", title);
        cv.put("url", url);

        long updCount = db.update("channels", cv, "id = ?", new String[] { id.toString() });

        res.remove(i);
        res.add(new TChannel(title, url, id));

        Log.d(TAG, "updated rows count = " + updCount);

        //loadChannelsFromDB(context);
        dbHelper.close();
    }

    public static void deleteChannel(int i, Context context) {
        ChannelsDBHelper dbHelper = new ChannelsDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        long id = res.get(i).getId();

        Log.d(TAG, "--- Delete from mytabe: ---");
        int delCount = db.delete("channels", "id = " + id, null);

        Log.d(TAG, "deleted rows count = " + delCount);

        res.remove(i);

        dbHelper.close();
    }

    static final String TAG = "ChannelsStuff";
}

