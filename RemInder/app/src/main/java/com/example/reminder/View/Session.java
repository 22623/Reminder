package com.example.reminder.View;

public class Session {

    private String username;
    private long sessionDate;

    public Session(String username, long sessionDate) {
        this.username = username;
        this.sessionDate = sessionDate;
    }

    public String getUsername() {
        return username;
    }

    public long getSessionDate() {
        return sessionDate;
    }
}
