package com.remijonathan.virtualelaine.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.remijonathan.virtualelaine.model.Label;
import com.remijonathan.virtualelaine.model.Task;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "VirtualElaine.db";
    public static final String TABLE_NAME = "LABEL";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "TITLE";
    public static final String COL_3 = "COLOR";
    public static final String COL_4 = "DESCRIPTION";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 3);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_2 + " VARCHAR(70), " + COL_3 + " INTEGER, " + COL_4 + " TEXT DEFAULT NULL); ");
        db.execSQL("CREATE TABLE TASK ( TASKID INTEGER PRIMARY KEY AUTOINCREMENT, TASKTITLE TEXT DEFAULT NULL, TASKDUEDATE TEXT DEFAULT NULL, TASKLABEL INTEGER DEFAULT NULL, TASKDESCRIPTION TEXT DEFAULT NULL, ISACTIVE BOOLEAN DEFAULT 1);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_NAME));
        db.execSQL("DROP TABLE IF EXISTS TASK");
        onCreate(db);
    }

    public void createLabel(String title, int color, @Nullable String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(String.format("INSERT INTO %s  (%s, %s, %s) VALUES ('%s', %d, '%s');", TABLE_NAME, COL_2, COL_3, COL_4, title, color, description));
    }

    public List<Label> getLabels() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Label> labels = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM LABEL", null);

        if (cursor.moveToFirst()) {
            do {
                labels.add(new Label(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)), cursor.getString(3)));
            } while (cursor.moveToNext());
        }
        return labels;
    }

    public void putTask(String taskTitle, @Nullable String taskDueDate, @Nullable String taskLabel, @Nullable String taskDescription) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(String.format("INSERT INTO TASK (TASKTITLE, TASKDUEDATE, TASKLABEL, TASKDESCRIPTION) VALUES ('%s', '%s', '%s', %s)", taskTitle, taskDueDate, taskLabel, taskDescription));
    }

    public void updateTaskTitle(int id, String title) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("UPDATE TASK SET TASKTITLE = '" + title + "' WHERE TASKID = " + id + ";");
    }

    public void updateTaskDueDate(int id, String dueDate) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("UPDATE TASK SET TASKDueDate = '" + dueDate + "' WHERE TASKID = " + id + ";");
    }

    public void updateTaskLabel(int id, String label) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("UPDATE TASK SET TASKLABEL = '" + label + "' WHERE TASKID = " + id + ";");
    }

    public void updateTaskDescription(int id, String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("UPDATE TASK SET TASKDESCRIPTION = '" + description + "' WHERE TASKID = " + id + ";");
    }

    public List<Task> getTasks() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Task> tasks = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM TASK WHERE ISACTIVE = 1;", null);

        if (cursor.moveToFirst()) {
            do {
                Task task = new Task(Integer.parseInt(cursor.getString(0)),//id
                        cursor.getString(1),//title
                        cursor.getString(2),//duedate
                        cursor.getString(3),//label
                        cursor.getString(4),//description
                        Boolean.parseBoolean(cursor.getString(5))//isActive
                );

                tasks.add(task);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return tasks;
    }

    public Task getTask(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM TASK WHERE TASKID = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            Task task = new Task(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), Boolean.parseBoolean(cursor.getString(5)));
            return task;
        } else {
            return null;
        }
    }

    public void deleteTask(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(String.format("UPDATE TASK SET ISACTIVE = 0 WHERE TASKID = %d", id));
    }


    //Run this method in MainActivity in order to fill the application with dummy Task
    public void addTestDummyData() {
        createLabel("Fun", -26266, "Fun activities");
        createLabel("Travel", -10027111, "Travelling objectives");
        createLabel("Health and Fitness", -6723841, "Keep in shape, keep happy!");
        createLabel("Personal Development", -3342490, null);
        createLabel("Cool", -10040065, null);
        createLabel("Health and Fitness", -39220, null);
        createLabel("Creative", 0, "This is a label with no color...");

        putTask("Drive a Formula 1 Car", "1/1/2020 0:00", "1", null);
        putTask("Watch Fire Dancers in Hawaii", "1/1/2020 0:00", "1", null);
        putTask("Ski Whistler", "1/1/2020 0:00", "1", null);
        putTask("Compete in a 3 Point Basketball Contest", "12/1/2020 0:00", "1", null);
        putTask("Find a Cause That Will Be in my Heart Forever", "13/1/2020 0:00", "2", null);
        putTask("Be in Time Square on New Years Eve", "18/1/2020 0:00", "2", null);
        putTask("Give Free Hugs in a Public Place", "1/1/2020 0:00", "3", null);
        putTask("Watch 1000 Movies", "15/1/2020 0:00", "4", null);
        putTask("Achieve Six-Pack Abs", "1/1/2020 0:00", "5", null);
        putTask("Dance all Around the World", "18/1/2020 0:00", "5", null);
        putTask("Become a Bone Marrow Potential Donor", "19/1/2020 0:00", "6", null);
        putTask("Build a Computer", "23/1/2020 0:00", "1", null);
        putTask("Complete Android Project", "15/1/2020 0:00", "0", null);
    }
}
