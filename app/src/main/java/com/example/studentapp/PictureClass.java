package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "picture")

public class PictureClass
{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int gid;
    String gname;
    byte[] gimage;
    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public byte[] getGimage() {
        return gimage;
    }

    public void setGimage(byte[] gimage) {
        this.gimage = gimage;
    }
}
