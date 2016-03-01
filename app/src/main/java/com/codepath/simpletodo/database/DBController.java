package com.codepath.simpletodo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.codepath.simpletodo.constant.Const;
import com.codepath.simpletodo.model.Item;
import com.codepath.simpletodo.model.ItemDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mazino on 3/1/2016.
 */
public class DBController {
    private DBItem mDBItem;
    private Context ourContext;
    private SQLiteDatabase db;

    public DBController(Context c) {
        ourContext = c;
    }

    public DBController open() {
        mDBItem = new DBItem(ourContext);
        db = mDBItem.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public long addItem(Item item) {
        ContentValues values = new ContentValues();
        values.put(Const.ITEM_NAME, item.getItemName());
        long id = db.insert(Const.TBL_NAME, null, values);
        close();
        return id;
    }

    public int updateItem(long itemID, String itemName) {
        ContentValues values = new ContentValues();
        values.put(Const.ITEM_NAME, itemName);
        int rs = db.update(Const.TBL_NAME, values, Const.KEY_ID + "=" + itemID , null);
        close();
        return rs;
    }

    public void deleteItem(long itemID) {
        db.delete(Const.TBL_NAME, Const.KEY_ID + "=" + itemID, null);
    }

    public List<Item> loadAll() {
        ItemDAO dao = new ItemDAO();
        db = mDBItem.getReadableDatabase();
        Cursor cursor = db.query(Const.TBL_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Item item = new Item();
            item.setItemId(cursor.getLong(0));
            item.setItemName(cursor.getString(1));
            dao.getItemList().add(item);
            cursor.moveToNext();
        }
        close();
        return dao.getItemList();
    }


}
