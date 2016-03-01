package com.codepath.simpletodo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.codepath.simpletodo.constant.Const;

/**
 * Created by Mazino on 3/1/2016.
 */
public class DBItem extends SQLiteOpenHelper{


    public DBItem(Context context) {
        super(context, Const.DATABASE_NAME, null, Const.DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder CREATE_ITEM_TABLE = new StringBuilder();
        CREATE_ITEM_TABLE.append("CREATE TABLE ").append(Const.TBL_NAME)
                .append("(").append(Const.KEY_ID)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT,")
                .append(Const.ITEM_NAME).append(" TEXT NOT NULL").append(")");
        db.execSQL(CREATE_ITEM_TABLE.toString());
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + Const.TBL_NAME);
        onCreate(db);
    }

}
