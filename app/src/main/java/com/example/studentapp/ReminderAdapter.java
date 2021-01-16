package com.example.studentapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.MyViewHolder>{

    private List<Reminders> allReminders;
    private TextView message,time;
    private Button delete;
    private Db appDatabase;

    public ReminderAdapter(List<Reminders> allReminders) {
        this.allReminders = allReminders;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.reminder_item,viewGroup,false);
        appDatabase = Db.getDb(viewGroup.getContext());
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        final Reminders reminder = allReminders.get(i);
        final RoomDAO roomDAO = appDatabase.getRoomDAO();
        if(!reminder.getMessage().equals(""))
            message.setText(reminder.getMessage());
        else
            message.setHint("No Message");
        time.setText(reminder.getRemindDate().toString());
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roomDAO.Delete(reminder);
                allReminders.remove(i);
                notifyItemRemoved(i);
                //notifyDataSetChanged();
                //notifyItemRangeChanged(i, getItemCount());
            }
        });

    }

    @Override
    public int getItemCount() {
        return allReminders.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.textView1);
            time = itemView.findViewById(R.id.textView2);
            delete = itemView.findViewById(R.id.deleteReminderBtn);
        }
    }



}
