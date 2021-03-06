package com.juvcarl.batch.mcs.lastfm.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artistmatches {

    @SerializedName("artist")
    @Expose
    private List<Artist> artist = null;

    public List<Artist> getArtist() {
        return artist;
    }

    public void setArtist(List<Artist> artist) {
        this.artist = artist;
    }

    public Artistmatches withArtist(List<Artist> artist) {
        this.artist = artist;
        return this;
    }

}