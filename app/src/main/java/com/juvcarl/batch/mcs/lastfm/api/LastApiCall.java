package com.juvcarl.batch.mcs.lastfm.api;

import com.juvcarl.batch.mcs.lastfm.model.LastFMResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LastApiCall {

    @GET("/2.0/")
    Call<LastFMResult> searchArtist(@Query("method") String type,
                                    @Query("artist") String searchterm,
                                    @Query("api_key") String apikey,
                                    @Query("format") String format);

}
