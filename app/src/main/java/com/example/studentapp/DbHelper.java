package com.example.studentapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_Name="newDB";
    private static final int DB_Ver=1;

    public static final String DB_TABLE="ToDoList";
    public static final String DB_COLUM="TaskName";

    public DbHelper(@Nullable Context context) {
        super(context, DB_Name, null, DB_Ver);
    }
    //public DbHelper(@Nullable Context context) { super(context, "e-zeeDB", null, 1); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query=String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL);",DB_TABLE,DB_COLUM);
        db.execSQL(query);
        db.execSQL("create table gallery (gid INTEGER PRIMARY KEY AUTOINCREMENT, gname TEXT, gimage BLOB)");
        db.execSQL("create table registration(uid INTEGER PRIMARY KEY AUTOINCREMENT, ufullname TEXT,  uemail TEXT, upassword TEXT)");
        db.execSQL("create table reminders (rid INTEGER PRIMARY KEY AUTOINCREMENT, ReminderName TEXT,  date TEXT, time TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query=String.format("DELETE TABLE IF EXISTS %s",DB_TABLE);
        db.execSQL(query);
    }

    //New Task
    public void insertNewTask(String task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_COLUM,task);
        db.insertWithOnConflict(DB_TABLE,null,values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void deleteTask(String task){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_TABLE,DB_COLUM + " = ?", new String[]{task});
        db.close();
    }

    public ArrayList<String> getTaskList(){
        ArrayList<String> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.query(DB_TABLE, new String[]{DB_COLUM},null,null,null,null,null);
        while(cursor.moveToNext())
        {
            int index=cursor.getColumnIndex(DB_COLUM);
            taskList.add(cursor.getString(index));
        }
        cursor.close();
        db.close();
        return taskList;
    }

    //Registration
    public boolean addrecord(String f1, String f2, String f3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ufullname",f1);
        contentValues.put("uemail",f2);
        contentValues.put("upassword",f3);

        long result = db.insert("registration",null,contentValues);
        if(result > 0)
        {
            return true;
        }
        return  false;
    }

    //Duplicate Email
    public Cursor dupliregistration(String uemail) {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM registration WHERE uemail='"+uemail+"'",null);
    }

    //Change / Edit Password
    /*public Cursor selectprofile(String e) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM registration WHERE uemail='"+e+"'", null);
    }*/
    public boolean updatepwd(String uemail, String upassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("upassword",upassword);

        long res = db.update("registration", contentValues, "uemail"+"=?", new String[]{String.valueOf(uemail)});
        if (res > 0) {
            return true;
        } else {
            return false;
        }
    }

    //User Login
    public Cursor loginuser(String uemail, String upassword) {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM registration WHERE uemail='"+uemail+"' and upassword='"+upassword+"'",null);
    }

    //Gallery
    public void addgallery(String gname, byte[] image){
        SQLiteDatabase db=this.getWritableDatabase();
        String q="insert into gallery values(NULL,?,?)";
        SQLiteStatement statement=db.compileStatement(q);
        statement.clearBindings();
        statement.bindString(1,gname);
        statement.bindBlob(2,image);
        statement.executeInsert();
    }
    public Cursor showgallery(String q) {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.rawQuery(q,null);
    }
}
