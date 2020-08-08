package com.example.cristianocalicchia.run2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RaceDetailActivity extends AppCompatActivity {

    String passedVarRace=null;
    String passedVarUser=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);


        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest request=new JsonArrayRequest("https://my-json-server.typicode.com/lufth/MP_Project/races", getListener, errorListener);
        mRequestQueue.add(request);
    }

    public void onClick2(View view) {
        passedVarRace=getIntent().getStringExtra(NextRacesActivity.ID_RACE);
        int idRace = Integer.parseInt(passedVarRace);
        passedVarUser=getIntent().getStringExtra(LoginActivity.NAME);
        JSONObject newRequest=new JSONObject();
        try {
            newRequest.put("name", passedVarUser);
            newRequest.put("idrace", idRace);
        } catch (JSONException e) {
        }
        RequestQueue mRequestQueue2 = Volley.newRequestQueue(this);
        JsonObjectRequest request=new JsonObjectRequest("https://my-json-server.typicode.com/lufth/MP_Project/booking", newRequest, postListener, errorListener);
        mRequestQueue2.add(request);
    }

    private Response.ErrorListener errorListener=new Response.ErrorListener()
    {
        @Override
        public void onErrorResponse(VolleyError err)
        {
            Toast.makeText(RaceDetailActivity.this, "Errore di rete", Toast.LENGTH_SHORT).show();
        }
    };

    private Response.Listener<JSONArray> getListener = new Response.Listener<JSONArray>()
    {
        @Override
        public void onResponse(JSONArray response) {

            passedVarRace=getIntent().getStringExtra(NextRacesActivity.ID_RACE);
            int id = Integer.parseInt(passedVarRace);
            for (int i=0; i<response.length(); i++)
            {
                try {
                    JSONObject j=response.getJSONObject(i);
                    if (j.getInt("idrace")==id) {

                        //Get
                        ImageView ivRace= (ImageView) findViewById(R.id.iv_race);
                        TextView tvUrl= (TextView) findViewById(R.id.tv_url);
                        TextView tvExp= (TextView) findViewById(R.id.tv_exp);
                        TextView tvNote= (TextView) findViewById(R.id.tv_note);

                        //Set
                        Picasso.with(getApplicationContext()).load(j.getString("picture")).into(ivRace);
                        tvUrl.setText(j.getString("url"));
                        tvExp.setText(j.getString("Before"));
                        tvNote.setText(j.getString("note"));
                    }
                }
                catch (JSONException e) {
                }
            }

        }

    };

    private Response.Listener<JSONObject> postListener= new Response.Listener<JSONObject>()
    {
        @Override
        public void onResponse(JSONObject response) {
            Toast.makeText(RaceDetailActivity.this, "Registrazione effettuata!", Toast.LENGTH_SHORT).show();
        }
    };
}
