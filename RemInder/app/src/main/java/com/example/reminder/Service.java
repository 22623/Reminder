package com.example.reminder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("Reminder/db")
    Call<List<Task>> getTaskService();
}
