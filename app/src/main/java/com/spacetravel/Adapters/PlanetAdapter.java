package com.spacetravel.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.spacetravel.MainActivity;
import com.spacetravel.R;

import java.util.ArrayList;

/**
 * Created by matt on 2017-10-15.
 */

public class PlanetAdapter extends RecyclerView.Adapter<PlanetAdapter.PlanetViewHolder>
{

    private static final String TAG = PlanetAdapter.class.getSimpleName();
    private ArrayList<String> planets;
    final private ListItemClickListener mOnClickListener;

    public PlanetAdapter(Context context, ArrayList<String> planets, ListItemClickListener listener)
    {
        mOnClickListener = listener;
        this.planets = planets;
    }

    public void add(String p)
    {
        planets.add(p);
    }

    public int getLength()
    {
        return planets.size();
    }

    @Override
    public PlanetViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflator = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflator.inflate(R.layout.planet_row, parent, shouldAttachToParentImmediately);

        return  new PlanetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlanetViewHolder holder, int position)
    {
        String title = planets.get(position);
        holder.tvPlanetTitle.setText(title);
    }

    @Override
    public int getItemCount()
    {
        return planets.size();
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex, String name);
    }


    /* *************************************
     *          ViewHolder Class           *
     ***************************************/
    public class PlanetViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        TextView tvPlanetTitle;
        ImageView ivPlanetImage;

        public PlanetViewHolder(View itemView) {
            super(itemView);

            tvPlanetTitle = (TextView) itemView.findViewById(R.id.planet_title);
            ivPlanetImage = (ImageView) itemView.findViewById(R.id.planet_image);

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
