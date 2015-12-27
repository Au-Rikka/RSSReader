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
        // делаем запрос всех данных из таблицы mytable, получаем Cursor
        Cursor c = db.query("channels", null, null, null, null, null, null);

        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернетс€ false
        if (c.moveToFirst()) {
            // определ€ем номера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex("id");
            int nameColIndex = c.getColumnIndex("title");
            int emailColIndex = c.getColumnIndex("url");

            do {
                res.add(new TChannel(c.getString(nameColIndex), c.getString(emailColIndex)));

                // получаем значени€ по номерам столбцов и пишем все в лог
                Log.d(TAG, "ID = " + c.getInt(idColIndex) +
                        ", title = " + c.getString(nameColIndex) +
                        ", url = " + c.getString(emailColIndex));
                // переход на следующую строку
                // а если следующей нет (текуща€ - последн€€), то false - выходим из цикла
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

        // подготовим данные дл€ вставки в виде пар: наименование столбца - значение
        cv.put("title", title);
        cv.put("url", url);
        // вставл€ем запись и получаем ее ID
        long rowID = db.insert("channels", null, cv);
        Log.d(TAG, "row inserted, ID = " + rowID);

        dbHelper.close();
    }

    static final String TAG = "ChannelsStuff";
}

