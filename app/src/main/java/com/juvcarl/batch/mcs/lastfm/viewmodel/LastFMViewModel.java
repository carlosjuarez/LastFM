package com.juvcarl.batch.mcs.lastfm.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.juvcarl.batch.mcs.lastfm.api.ApiClient;
import com.juvcarl.batch.mcs.lastfm.model.Artist;

import java.util.List;

public class LastFMViewModel extends ViewModel {

    ApiClient apiClient;
    MutableLiveData<List<Artist>> mutablelistArtists = new MutableLiveData<>();
    MutableLiveData<Artist> mutableArtist = new MutableLiveData<>();

    public void init(){
        apiClient = new ApiClient();
        apiClient.createApiClient();
    }

    public void searchArtists(String artist){
        apiClient.searchArtist(mutablelistArtists,artist);
    }

    public MutableLiveData<Artist> getMutableArtist() {
        return mutableArtist;
    }

    public MutableLiveData<List<Artist>> getMutablelistArtists() {
        return mutablelistArtists;
    }

    public void searchArtist(Artist artist) {

    }
}
