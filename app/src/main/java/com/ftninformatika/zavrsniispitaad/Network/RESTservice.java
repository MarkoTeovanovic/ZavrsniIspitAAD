package com.ftninformatika.zavrsniispitaad.Network;

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

    public static OMDBApiEndpointInterface apiInterface(){
        OMDBApiEndpointInterface apiService = getRetrofitInstance().create(OMDBApiEndpointInterface.class);

        return apiService;
    }
}
