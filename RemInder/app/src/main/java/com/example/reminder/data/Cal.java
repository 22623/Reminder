package com.example.reminder.data;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Cal {

    @PrimaryKey(autoGenerate = true)
    private long calId;
    private String calName;
    private String calText;
    private String calDate;

    public Cal(long calId, String calText, String calDate,String calName) {
        this.calId = calId;
        this.calText = calText;
        this.calDate = calDate;
        this.calName = calName;
    }

    public String getCalName() {
        return calName;
    }

    public long getCalId() {
        return calId;
    }

    public String getCalText() {
        return calText;
    }

    public String getCalDate() {
        return calDate;
    }
}
