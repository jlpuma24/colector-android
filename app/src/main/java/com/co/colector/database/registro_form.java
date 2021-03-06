package com.co.colector.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by User on 30/08/2015.
 */
public class registro_form {

    public static final String TABLE_NAME = "registro_form";
    public static final String ID = "id";
    public static final String UPDATED = "updated";
    public static final String TIMESTAMP = "timestamp";

    private ContentValues cv;

    public registro_form(){

    }

    public void insert(SQLiteDatabase db){
        cv = new ContentValues();
        cv.put(UPDATED, "0");
        cv.put(TIMESTAMP, System.currentTimeMillis());
        db.insert(TABLE_NAME, "registro_form", cv);
    }

    public Cursor consulta(SQLiteDatabase db){
        return db.rawQuery("select * from registro_form", null);
    }

    public void updateValues(SQLiteDatabase db, String id){

        ContentValues values = new ContentValues();
        values.put(UPDATED, "1");
        values.put(TIMESTAMP, System.currentTimeMillis());

        db.update(TABLE_NAME,
                values,
                ID +" = ?",
                new String[] { id });

        db.close();
    }

    public void delete(SQLiteDatabase db){ db.delete(TABLE_NAME, null, null); }

    public void deleteRecord(SQLiteDatabase db, String id){
        String whereClause = "id" + "=?";
        String[] whereArgs = new String[] { String.valueOf(id) };
        db.delete(TABLE_NAME, whereClause, whereArgs);
        db.close();
    }
}