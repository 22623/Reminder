package com.example.reminder;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User")
    List<User> getAll();

    @Query("SELECT * FROM User WHERE id = :id")
    User getById(long id);

    @Delete
    void delete(User user);

    @Update
    void update(User user);

    @Insert
    void insert(User user);
}
