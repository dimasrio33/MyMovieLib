package com.example.user.mymovielib.Rest;

import com.example.user.mymovielib.Model.MvResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("movie/now_playing")
    Call<MvResponse> getNowPlaying(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MvResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}