package homework5.rssreader.Channels;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Anstanasia on 27.12.2015.
 */

public class ChannelsDBHelper extends SQLiteOpenHelper {

    public ChannelsDBHelper(Context context) {
        super(context, "ChannelDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "--- onCreate database ---");
        db.execSQL("create table channels ("
                + "id integer primary key autoincrement,"
                + "title text,"
                + "url text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    final String TAG = "ChannelsDBHelper";
}
