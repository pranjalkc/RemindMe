package com.example.studentapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RoomDAO {

    @Insert
    public void Insert(Login... usernamePasswords);

    @Update
    public void Update(Login... usernamePasswords);

    @Delete
    public void Delete(Login usernamePassword);

    @Query("Select * from login where usename = :username")
    public Login getUserwithUsername(String username);

    @Query("Select * from login where isloggedIn = 1")
    public Login getLoggedInUser();


    @Insert
    public void Insert(Reminders... reminders);

    @Update
    public void Update(Reminders... reminders);

    @Delete
    public void Delete(Reminders reminders);

    @Query("Select * from reminder order by remindDate")
    public List<Reminders> orderThetable();

    @Query("Select * from reminder Limit 1")
    public Reminders getRecentEnteredData();

    @Query("Select * from reminder")
    public List<Reminders> getAll();

    @Insert
    public void Insert(Tasks... tasks);

    @Update
    public void Update(Tasks... tasks);

    @Delete
    public void Delete(Tasks tasks);

    @Query("Select name from task")
    public List<String> getAllTasks();

    @Query("Select * from task where name = :selTask")
    public Tasks getSelectedTask(String selTask);

    @Insert
    public void Insert(PictureClass... pictures);

    @Update
    public void Update(PictureClass... pictures);

    @Delete
    public void Delete(PictureClass pictures);

    @Query("Select * from picture")
    public List<PictureClass> getAllPics();

}
