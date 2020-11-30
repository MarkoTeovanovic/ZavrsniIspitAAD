package com.ftninformatika.zavrsniispitaad.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RESTservice {

    public static final String BASE_URL = "https://www.omdbapi.com";
    public static final String API_KEY = "d1b46367";

    public static Retrofit getRetrofitInstance(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static OMDB apiInterface(){
        OMDB apiService = getRetrofitInstance().create(OMDB.class);

        return apiService;
    }
}
