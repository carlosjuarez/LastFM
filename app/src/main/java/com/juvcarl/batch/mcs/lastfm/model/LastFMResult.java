package com.juvcarl.batch.mcs.lastfm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LastFMResult {

    @SerializedName("results")
    @Expose
    private Results results;

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public LastFMResult withResults(Results results) {
        this.results = results;
        return this;
    }

}
