package com.example.cristianocalicchia.run2;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

public class NextRacesActivity extends Activity {

    public final static String ID_RACE="";

    private ListView lvRace;
    private RaceListAdapter adapter;
    private List<Race> mRaceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_races);

        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest request=new JsonArrayRequest("https://my-json-server.typicode.com/lufth/MP_Project/races", getListener, errorListener);
        mRequestQueue.add(request);
    }

    private Response.ErrorListener errorListener=new Response.ErrorListener()
    {
        @Override
        public void onErrorResponse(VolleyError err)
        {
            Toast.makeText(NextRacesActivity.this, "Errore di rete", Toast.LENGTH_SHORT).show();
        }
    };

    private Response.Listener<JSONArray> getListener = new Response.Listener<JSONArray>()
    {
        @Override
        public void onResponse(JSONArray response) {

            lvRace =(ListView)findViewById(R.id.listview_product);
            mRaceList = new ArrayList<>();
            for (int i=0; i<response.length(); i++)
            {
                try {
                    JSONObject j=response.getJSONObject(i);
                    mRaceList.add(new Race(j.getInt("idrace"), j.getString("picture"), j.getString("Description"),
                            j.getString("Location"),j.getString( "Date"),j.getLong("Distance")));
                }
                catch (JSONException e) {
                }
            }
            //Init Adapter
            adapter = new RaceListAdapter(getApplicationContext(), mRaceList);
            lvRace.setAdapter(adapter);

            lvRace.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
                    //Do Something
                    Intent i = new Intent(NextRacesActivity.this, RaceDetailActivity.class); // from -> to
                    String index = ""+view.getTag();
                    int trueIndex = Integer.parseInt(index)-1;
                    Race mRace=mRaceList.get(trueIndex);
                    i.putExtra(ID_RACE,  String.valueOf(mRace.getId())); // extra arguments: the item ID
                    startActivity(i);
                }
            });
        }

    };
}
