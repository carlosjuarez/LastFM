package com.juvcarl.batch.mcs.lastfm.view;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.juvcarl.batch.mcs.lastfm.R;
import com.juvcarl.batch.mcs.lastfm.model.Artist;
import com.juvcarl.batch.mcs.lastfm.viewmodel.LastFMViewModel;

import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistListFragment extends Fragment implements SelectArtistInterface {

    private ArtistFragment artistFragment;
    private LastFMViewModel lastFMViewModel;
    private ListArtistRecyclerViewAdapter listArtistRecyclerViewAdapter;
    private List<Artist> listArtists;

    public ArtistListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artist_list, container, false);

        listArtistRecyclerViewAdapter = new ListArtistRecyclerViewAdapter(listArtists,this);
        final RecyclerView recyclerView = view.findViewById(R.id.rv_artist_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(listArtistRecyclerViewAdapter);

        SearchView searchView = view.findViewById(R.id.sv_search_artist);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                lastFMViewModel.searchArtists(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                lastFMViewModel.searchArtists(s);
                return true;
            }
        });

        lastFMViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(LastFMViewModel.class);
        lastFMViewModel.getMutablelistArtists().observe(this, new Observer<List<Artist>>() {
            @Override
            public void onChanged(@Nullable List<Artist> artists) {
                if(artists!=null){
                    listArtistRecyclerViewAdapter.updateList(artists);
                }
            }
        });

        artistFragment = new ArtistFragment();

        return view;
    }

    @Override
    public void OnArtistSelected(Artist artist) {

        lastFMViewModel.searchArtist(artist);
        Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_artist_search,artistFragment)
                .addToBackStack("artist")
                .commit();
        //Check if we need to search more information
        lastFMViewModel.getMutableArtist().setValue(artist);
    }

}
