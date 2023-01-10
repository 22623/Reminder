package com.example.reminder.data;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task {
    @PrimaryKey(autoGenerate = true)
    private long taskId;
    private String taskName;
    private String taskText;


    public Task(long taskId, String taskName, String taskText) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskText = taskText;
    }

    public long getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskText() {
        return taskText;
    }
}
