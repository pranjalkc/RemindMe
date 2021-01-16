package com.example.studentapp;

/*

Created By: Pranjal Chauhan, 000731577

 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void tdlist(View view) {
        Intent intent = new Intent(getApplicationContext(), ToDoListActivity.class);
        startActivity(intent);
    }

    public void galleryclick(View view) {
        Intent intent = new Intent(getApplicationContext(),GalleryActivity.class);
        startActivity(intent);
    }

    public void reminderClick(View view) {
        Intent intent = new Intent(getApplicationContext(),ReminderActivity.class);
        startActivity(intent);
    }
}
