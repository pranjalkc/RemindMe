package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task")

public class Tasks {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    String name;

    public String getName() {
        return name;
    }

    public int getTaskId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTaskId(int id) {
        this.id = id;
    }

}
