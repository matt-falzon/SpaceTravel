package com.spacetravel;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.spacetravel.Adapters.PlanetAdapter;
import com.spacetravel.Adapters.TimetableAdapter;
import com.spacetravel.Models.PlanetTime;
import com.spacetravel.Network.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;


public class TimetableFragment extends Fragment
        implements TimetableAdapter.ListItemClickListener{
    Button BtnCancel, BtnDepartures, BtnArrivals;
    TextView tvTitle;
    private static final String TAG = "TimetableFragment";
    private static final String FLIGHTS_URL = "https://api.vortilla.net/issb/?action=flights&authkey=1a0f423312e24ef9246afed7d2a18a06";
    private String key;

    TimetableAdapter timetableAdapter;
    RecyclerView recyclerView;

    private ArrayList<PlanetTime> timetable = new ArrayList();
    private ArrayList<PlanetTime> flights = new ArrayList();
    public TimetableFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);
        // Inflate the layout for this fragment
        BtnCancel = (Button) view.findViewById(R.id.btn_cancel);
        BtnArrivals = (Button) view.findViewById(R.id.btn_arrivals);
        BtnDepartures = (Button) view.findViewById(R.id.btn_departures);
        tvTitle = (TextView) view.findViewById(R.id.toolbar_title);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_timetable_view);

        tvTitle.setText(key);
        dataRequest(FLIGHTS_URL);

        BtnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                (getActivity()).onBackPressed();
            }
        });

        BtnArrivals.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                newTimetable(key, true);
                timetableAdapter.changeTimetable(timetable, true);
                timetableAdapter.notifyDataSetChanged();
                BtnArrivals.setBackgroundColor(0xFF103A59);
                BtnDepartures.setBackgroundColor(0x70D1D1D5);
                Log.d(TAG, "onClick: Arrivals clicked");
            }
        });

        BtnDepartures.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Departures clicked");
                newTimetable(key, false);
                timetableAdapter.notifyDataSetChanged();
                BtnArrivals.setBackgroundColor(0x70D1D1D5);
                BtnDepartures.setBackgroundColor(0xFF103A59);
            }
        });

        timetableAdapter = new TimetableAdapter(getContext(), timetable, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(timetableAdapter);

        return view;
    }

    public void newTimetable(String key, boolean isArrivals)
    {
        timetable.clear();
        if(isArrivals)
            for(int i=0;i<flights.size();i++){
                if(key.equals(flights.get(i).getDestination()))
                    timetable.add(flights.get(i));

            }
        else if(!isArrivals)
            for(int i=0;i<flights.size();i++){
                if(key.equals(flights.get(i).getOrigin()))
                    timetable.add(flights.get(i));
            }
        timetableAdapter.changeTimetable(timetable, isArrivals);
        timetableAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        key = getArguments().getString("key");
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    private void dataRequest(String uri)
    {
        Log.d(TAG, "dataRequest: start request");
        RequestQueue queue = VolleySingleton.getsInstance().getmRequestQueue();

        JsonArrayRequest request = new JsonArrayRequest
                (Request.Method.GET, uri, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            for(int i = 0; i < response.length(); i++)
                                flights.add(new PlanetTime(response.getJSONObject(i).getString("origin"), response.getJSONObject(i).getString("destination"), (response.getJSONObject(i).getString("departure")).substring(0, 5), (response.getJSONObject(i).getString("arrival")).substring(0, 5), response.getJSONObject(i).getInt("trip_duration")));

                            newTimetable(key, true);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: " + error);

                    }
                });

        queue.add(request);
    }

    @Override
    public void onListItemClick(int clickedItemIndex, PlanetTime pt) {
        Toast.makeText(getContext(), pt.getTrip_duration(), Toast.LENGTH_SHORT).show();
    }

}
