package com.example.reminder.View;

import com.example.reminder.data.remote.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL="https://my-json-server.typicode.com/22623/";
    private static Retrofit retrofit=null;



    public static Retrofit getRetrofitClient(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Service service(){
        return getRetrofitClient().create(Service.class);
    }
}
