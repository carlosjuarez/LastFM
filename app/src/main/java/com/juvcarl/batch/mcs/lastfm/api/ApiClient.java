package com.juvcarl.batch.mcs.lastfm.api;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.juvcarl.batch.mcs.lastfm.model.Artist;
import com.juvcarl.batch.mcs.lastfm.model.LastFMResult;
import com.juvcarl.batch.mcs.lastfm.utils.ConstantString;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    String baseUrl = ConstantString.BASE_URL;
    String apiKey = ConstantString.API_KEY;
    String format = ConstantString.FORMAT;
    String searchType = ConstantString.SEARCH_TYPE;

    LastApiCall lastApiCall;

    public void createApiClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        lastApiCall = retrofit.create(LastApiCall.class);
    }

    public MutableLiveData<List<Artist>> searchArtist(final MutableLiveData<List<Artist>> liveArtists, String search){

        lastApiCall.searchArtist(searchType,search,apiKey,format).enqueue(new Callback<LastFMResult>() {
            @Override
            public void onResponse(Call<LastFMResult> call, Response<LastFMResult> response) {
                if(response.isSuccessful()){
                    LastFMResult lastFMResult = response.body();
                    if (lastFMResult != null) {
                        List<Artist> artists = lastFMResult.getResults().getArtistmatches().getArtist();
                        if(artists.size()>0){
                            liveArtists.postValue(artists);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<LastFMResult> call, Throwable t) {
                liveArtists.postValue(null);
            }
        });

        return liveArtists;
    }

}
