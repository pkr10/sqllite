package com.example.rok.a0607;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by rok on 2017. 6. 8..
 */

public class MyManageDB {
    private static Main2Activity database = null;
    private static SQLiteDatabase myDB2 = null;
    private static MyManageDB mInstance = null;

    public final static MyManageDB getInstance(Context context) {
        if (mInstance == null) mInstance = new MyManageDB(context);
        return mInstance;
    }

    MyManageDB(Context context) {
        database = new Main2Activity(context, "myDB2", null, 1);
        myDB2 = database.getWritableDatabase();
    }

    public Cursor execSELECTStudent(String sql) {
        Cursor cursor = myDB2.rawQuery(sql, null);
        return cursor;
    }
}
