package com.example.sumon.firstaid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sumon.firstaid.models.IdName;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public List<IdName> getCategory() {
        List<IdName> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT category_id, category_name FROM tbl_category", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            list.add(new IdName(id, name));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public List<IdName> getSubCategory(int categoryId) {
        List<IdName> list = new ArrayList<>();
        String sql = "select sc.sub_category_id, sc.sub_category_name from tbl_relation r\n" +
                "join tbl_sub_category sc on sc.sub_category_id = r.sub_category_id\n" +
                "where r.category_id = " + categoryId;
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            list.add(new IdName(id, name));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}