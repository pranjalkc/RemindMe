package com.example.studentapp;

import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ToDoListActivity extends AppCompatActivity {
    private Db appDatabase;
    private List<String> temp;
    ArrayAdapter<String> mAdapter;
    ListView lstTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        appDatabase = Db.getDb(ToDoListActivity.this);
        lstTask = (ListView)findViewById(R.id.lstTask);

        loadTaskList();
    }

    private void loadTaskList(){
        RoomDAO dao = appDatabase.getRoomDAO();
        temp = dao.getAllTasks();
        if(temp.size()>0){
            if(mAdapter==null){
                mAdapter = new ArrayAdapter<String>(this,R.layout.t_row,R.id.task_title, temp);
                lstTask.setAdapter(mAdapter);
            }
            else{
                mAdapter.clear();
                mAdapter.addAll(temp);
                mAdapter.notifyDataSetChanged();
            }
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        //Change menu icon color
        Drawable icon = menu.getItem(0).getIcon();
        icon.mutate();
        icon.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                //Log.d(TAG, "Add a new task");
                //return true;

                final EditText taskEditText = new EditText(this);
                final RoomDAO roomDAO = appDatabase.getRoomDAO();
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add New Task")
                        .setMessage("What do you want to do next?")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskEditText.getText());
                                Tasks newTask = new Tasks();
                                newTask.setName(task);
                                roomDAO.Insert(newTask);
                                loadTaskList();
                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .create();
                        dialog.show();
                Db.destroyInstance();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void deleteTask(View view){
        RoomDAO dao = appDatabase.getRoomDAO();
        View parent = (View)view.getParent();
        TextView taskTextView =(TextView)findViewById(R.id.task_title);
        String task = String.valueOf(taskTextView.getText());
        Tasks selectedTask = dao.getSelectedTask(task);
        if(selectedTask != null){
            dao.Delete(selectedTask);
        }
        loadTaskList();
    }
}
