package com.spacetravel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;

import com.spacetravel.Adapters.PlanetAdapter;

import java.util.ArrayList;

/**
 * Created by matt on 2017-10-14.
 */

public class PlanetFragment extends Fragment implements PlanetAdapter.ListItemClickListener{

    private static final String TAG = "PlanetFragment";
    RecyclerView recyclerView;
    PlanetAdapter planetAdapter;
    private ArrayList<String> planets = new ArrayList();

    public PlanetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_planet, container, false);
        // Inflate the layout for this fragment
        Toolbar myToolbar = (Toolbar) view.findViewById(R.id.my_toolbar);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_planet_view);

        ((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);
        myToolbar.setTitle("");

        getPlanets();

        planetAdapter = new PlanetAdapter(getContext(), planets, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(planetAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onListItemClick(int clickedItemIndex, String name) {
        ((MainActivity)getActivity()).timetableFragment(name);
    }

    private void getPlanets()
    {
        String planetNames [] =  {"Earth", "Jupiter", "Mars", "Mercury", "Neptune", "Saturn"};
        for (String planetName : planetNames) {
            planets.add(planetName);
        }
    }
}
