package com.example.reminder;

public class RetrofitClient {
    private static final String BASE_URL="https://my-json-server.typicode.com/carlossancho-pt/";
    private static Retrofit retrofit=null;



    public static Retrofit getRetrofitClient(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static PlanetsService planetsService(){
        return getRetrofitClient().create(PlanetsService.class);
    }
}
