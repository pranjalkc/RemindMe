package com.example.studentapp;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ShowGallery extends AppCompatActivity {

    GridView gridView;
    private Db appDatabase;
    private PictureAdapter adapter;
    private List<PictureClass> temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_gallery);

        startconf();
        appDatabase = Db.getDb(ShowGallery.this);
        //Fetch pictureAdapter File Step - 1
        RoomDAO dao = appDatabase.getRoomDAO();
        temp = dao.getAllPics();
        adapter = new PictureAdapter(temp);
        try
        {
            gridView.setAdapter(adapter);
        }
        catch (Exception ex)
        {

        }
    }
    public void startconf()
    {
        gridView=(GridView) findViewById(R.id.gridView);
    }
}
