package com.example.cristianocalicchia.run2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RaceListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Race> mRaceList;

    //Constructor

    public RaceListAdapter(Context mContext, List<Race> mRaceList) {
        this.mContext = mContext;
        this.mRaceList = mRaceList;
    }

    @Override
    public int getCount() {
        return mRaceList.size();
    }

    @Override
    public Object getItem(int position) {
        return mRaceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.race_list, null);
        ImageView ivRace= (ImageView)v.findViewById(R.id.iv_race);
        TextView tvName= (TextView)v.findViewById(R.id.tv_name);
        TextView tvLocation= (TextView)v.findViewById(R.id.tv_location);
        TextView tvDate= (TextView)v.findViewById(R.id.tv_date);
        TextView tvDistance= (TextView)v.findViewById(R.id.tv_distance);

        //Set Image
        Picasso.with(mContext).load(mRaceList.get(position).getImage()).into(ivRace);

        //Set text for TextView
        tvName.setText(mRaceList.get(position).getName());
        tvLocation.setText(mRaceList.get(position).getLocation());
        tvDate.setText(mRaceList.get(position).getDate());
        tvDistance.setText(String.valueOf(mRaceList.get(position).getDistance()) + "m");

        //Save product name to tag
        v.setTag(mRaceList.get(position).getId());

        return v;
    }
}
