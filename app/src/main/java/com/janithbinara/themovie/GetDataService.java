package com.janithbinara.themovie;

import com.janithbinara.themovie.models.Films;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetDataService {

    @POST("/3/movie/popular")
    Call<Films> getPopularList(@Query("api_key") String api_key, @Query("page") String page);

    @POST("/3/movie/upcoming")
    Call<Films> getUpcomingList(@Query("api_key") String api_key, @Query("page") String page);
}
