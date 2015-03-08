package com.simplenotes.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NoteSQLiteHelper extends SQLiteOpenHelper {


    public static final String TABLE = "notes";
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String NOTE = "note";
    public static final String[] COLUMNS = { ID,TITLE ,NOTE};


    private static final String DATABASE_NAME = "NOTES.db";
    private static final int DATABASE_VERSION = 1;


    private static final String DATABASE_DROP = "DROP TABLE IF EXISTS " + TABLE;


    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE
            + "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TITLE + " TEXT, "
            + NOTE + " TEXT "
            + ")";


    public NoteSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DATABASE_DROP);
        onCreate(db);
    }
}
