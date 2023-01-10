package com.example.reminder.data.database;


import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.reminder.data.Cal;
import com.example.reminder.data.Task;
import com.example.reminder.data.User;

@androidx.room.Database(entities = {Task.class, Cal.class, User.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract TaskDao getTaskDao();
    public abstract CalDao getCalDao();
    public abstract UserDao getUserDao();



    private static Database INSTANCE;

    public static Database getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Database.class, "Database")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
