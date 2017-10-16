package com.spacetravel.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spacetravel.Models.PlanetTime;
import com.spacetravel.R;

import java.util.ArrayList;

/**
 * Created by matt on 2017-10-15.
 */

public class TimetableAdapter extends RecyclerView.Adapter<TimetableAdapter.TimetableViewHolder>
{

    private static final String TAG = TimetableAdapter.class.getSimpleName();
    private Context context;
    private int numberOfItems;
    private static int viewHolderCount;
    private ArrayList<PlanetTime> planets = new ArrayList();
    final private TimetableAdapter.ListItemClickListener mOnClickListener;
    private boolean isArrivals = true;

    public TimetableAdapter(Context context, ArrayList<PlanetTime> planets, TimetableAdapter.ListItemClickListener listener)
    {
        mOnClickListener = listener;
        this.planets = planets;
        this.context = context;
    }

    public void add(PlanetTime p)
    {
        planets.add(p);
    }


    public int getLength()
    {
        return planets.size();
    }

    public void setArrivals(boolean arrivals) {
        isArrivals = arrivals;
    }

    @Override
    public TimetableAdapter.TimetableViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(R.layout.timetable_row, parent, shouldAttachToParentImmediately);

        return  new TimetableAdapter.TimetableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TimetableAdapter.TimetableViewHolder holder, int position)
    {
        if(isArrivals) {
            String title = planets.get(position).getOrigin();
            String time = planets.get(position).getArrival();
            holder.tvPlanetTitle.setText(title);
            holder.tvTime.setText(time);
            holder.tvTo.setText("From");
        }
        else{
            String title = planets.get(position).getDestination();
            String time = planets.get(position).getDeparture();
            holder.tvPlanetTitle.setText(title);
            holder.tvTime.setText(time);
            holder.tvTo.setText("to");
        }


    }

    @Override
    public int getItemCount()
    {
        return planets.size();
    }

    public void changeTimetable(ArrayList<PlanetTime> timetable, boolean isArrivals) {
        this.isArrivals = isArrivals;
        planets = timetable;

    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex, PlanetTime pt);
    }


    /* *************************************
     *          ViewHolder Class           *
     ***************************************/
    public class TimetableViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        TextView tvPlanetTitle;
        TextView tvTime;
        TextView tvTo;

        public TimetableViewHolder(View itemView) {
            super(itemView);

            tvPlanetTitle = (TextView) itemView.findViewById(R.id.planet_title);
            tvTime = (TextView) itemView.findViewById(R.id.planet_time);
            tvTo = (TextView) itemView.findViewById(R.id.tv_to);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            int clickedPos = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPos, planets.get(clickedPos));
            Log.d(TAG, "onClick: " + clickedPos);
        }
    }
}

