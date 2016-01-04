package lxfeng.dbtest.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 *
 */
public class BaseDB {

    private DBHelper dbHelper;
    public SQLiteDatabase db;

    public BaseDB(Context contex) {
        dbHelper = new DBHelper(contex);
        db = dbHelper.getWritableDatabase();
    }

    public void closeDB() {
        if (db.isOpen()) {
            db.close();
        }
    }

    public void openDB() {
        if (null == db) {
            db = dbHelper.getReadableDatabase();
        }
    }
}
