package com.example.reminder;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CalDao {
    @Query("SELECT * FROM Cal")
    List<Cal> getAll();

    @Query("SELECT * FROM Cal WHERE calId = :calId")
    Cal getById(long calId);

    @Delete
    void delete(Cal cal);

    @Update
    void update(Cal cal);

    @Insert
    void insert(Cal cal);
}
