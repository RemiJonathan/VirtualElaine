package com.remijonathan.virtualelaine.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "VirtualElaine.db";
    public static final String TABLE_NAME = "LABEL";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "TITLE";
    public static final String COL_3 = "COLOR";
    public static final String COL_4 = "DESCRIPTION";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 2);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_2 + " VARCHAR(70), " + COL_3 + " INTEGER, " + COL_4 + " TEXT DEFAULT NULL); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_NAME));
        onCreate(db);
    }

    public void createLabel(String title, int color) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_NAME + "  (" + COL_2 + ", " + COL_3 + ") VALUES ('" + title + "', " + color + ");");
    }

    public void createLabel(String title, int color, String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT INTO " + TABLE_NAME + "  (" + COL_2 + ", " + COL_3 + ", " + COL_4 + ") VALUES ('" + title + "', " + color + ", " + description + ");");
    }
}