package com.ftninformatika.zavrsniispitaad.Network;

import com.ftninformatika.zavrsniispitaad.Model.Movie;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OMDB {

   @GET
    Call<Movie> getMovieDetails (Map <String, String> options);

    @GET
    Call<Movie> getMovieByActor  (Map <String, String> options);
}
