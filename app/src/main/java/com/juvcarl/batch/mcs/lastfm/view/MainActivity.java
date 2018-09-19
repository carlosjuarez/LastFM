package com.juvcarl.batch.mcs.lastfm.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.juvcarl.batch.mcs.lastfm.R;
import com.juvcarl.batch.mcs.lastfm.model.Artist;
import com.juvcarl.batch.mcs.lastfm.model.LastFMResult;
import com.juvcarl.batch.mcs.lastfm.viewmodel.LastFMViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    FragmentManager fm;
    ArtistListFragment artistListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LastFMViewModel lastFMViewModel = ViewModelProviders.of(this).get(LastFMViewModel.class);
        lastFMViewModel.init();

        fm = getSupportFragmentManager();
        artistListFragment = new ArtistListFragment();
        fm.beginTransaction().replace(R.id.fr_artist_search,artistListFragment).commit();
    }
}
