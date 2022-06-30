package com.example.reminder;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM Task")
    List<Task> getAll();

    @Query("SELECT * FROM Task WHERE taskId = :taskId")
    Task getById(long taskId);

    @Delete
    void delete(Task task);

    @Update
    void update(Task task);

    @Insert
    void insert(Task task);
}
