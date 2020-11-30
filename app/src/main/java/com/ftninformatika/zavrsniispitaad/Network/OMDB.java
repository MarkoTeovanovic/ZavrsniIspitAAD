package com.ftninformatika.zavrsniispitaad.Network;

import android.telecom.Call;

import com.ftninformatika.zavrsniispitaad.Model.Movie;

import java.util.Map;

import retrofit2.http.GET;

public interface OMDB {

    @GET("/")
    Call<Movie> getMoviesDetails(Map<String, String> options);

    @GET("/")
    Call<Movie> getActorDetails(Map<String, String> options);

}
