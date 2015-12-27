package homework5.rssreader;

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
    static List<TChannel> res = new ArrayList<TChannel>();

    public static void loadChannelsFromDB(Context context) {
        ChannelsDBHelper dbHelper = new ChannelsDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Log.d(TAG, "--- Rows in mytable: ---");
        // ������ ������ ���� ������ �� ������� mytable, �������� Cursor
        Cursor c = db.query("channels", null, null, null, null, null, null);

        // ������ ������� ������� �� ������ ������ �������
        // ���� � ������� ��� �����, �������� false
        if (c.moveToFirst()) {
            // ���������� ������ �������� �� ����� � �������
            int idColIndex = c.getColumnIndex("id");
            int nameColIndex = c.getColumnIndex("title");
            int emailColIndex = c.getColumnIndex("url");

            do {
                res.add(new TChannel(c.getString(nameColIndex), c.getString(emailColIndex)));

                // �������� �������� �� ������� �������� � ����� ��� � ���
                Log.d(TAG, "ID = " + c.getInt(idColIndex) +
                        ", title = " + c.getString(nameColIndex) +
                        ", url = " + c.getString(emailColIndex));
                // ������� �� ��������� ������
                // � ���� ��������� ��� (������� - ���������), �� false - ������� �� �����
            } while (c.moveToNext());
        } else
            Log.d(TAG, "0 rows");
        c.close();

        dbHelper.close();
    }


    void addNewChannel(String title, String url, Context context) {
        res.add(new TChannel(title, url));

        ContentValues cv = new ContentValues();
        ChannelsDBHelper dbHelper = new ChannelsDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        Log.d(TAG, "--- Insert in channels: ---");

        // ���������� ������ ��� ������� � ���� ���: ������������ ������� - ��������
        cv.put("title", title);
        cv.put("url", url);
        // ��������� ������ � �������� �� ID
        long rowID = db.insert("channels", null, cv);
        Log.d(TAG, "row inserted, ID = " + rowID);

        dbHelper.close();
    }

    static final String TAG = "ChannelsStuff";
}

