package com.example.studentapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Login.class,Reminders.class,Tasks.class,PictureClass.class},version = 3)
@TypeConverters({DateTypeConverter.class})

public abstract class Db extends RoomDatabase {

    private static Db INSTANCE = null;

    public abstract RoomDAO getRoomDAO();

    public static Db getDb(Context context){

        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),Db.class,"users")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return INSTANCE;

    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

}
