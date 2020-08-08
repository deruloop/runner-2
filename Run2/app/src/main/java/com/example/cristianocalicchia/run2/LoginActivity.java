package com.example.cristianocalicchia.run2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    public final static String NAME="";
    private String Name;
    private String Pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void onLoginClick(View v) {
        EditText ptName= (EditText) this.findViewById(R.id.pt_name);
        EditText ptPass= (EditText) this.findViewById(R.id.pw_password);

        Name=ptName.getText().toString();
        Pass=ptPass.getText().toString();

        RequestQueue mRequestQueueUL = Volley.newRequestQueue(this);
        JsonArrayRequest requestUL=new JsonArrayRequest("https://my-json-server.typicode.com/lufth/MP_Project/users", getListener, errorListener);
        mRequestQueueUL.add(requestUL);

    }

    public void onSignUpClick(View v) {
        EditText ptName= (EditText) this.findViewById(R.id.pt_name);
        EditText ptPass= (EditText) this.findViewById(R.id.pw_password);

        Name=ptName.getText().toString();
        Pass=ptPass.getText().toString();

        JSONObject newUser=new JSONObject();
        try {
            newUser.put("name", Name);
            newUser.put("password", Pass);
        } catch (JSONException e) {
        }
        RequestQueue mRequestQueueUR = Volley.newRequestQueue(this);
        JsonObjectRequest requestUR=new JsonObjectRequest("https://my-json-server.typicode.com/lufth/MP_Project/users", newUser, postListener, errorListener);
        mRequestQueueUR.add(requestUR);
    }

    private Response.ErrorListener errorListener=new Response.ErrorListener()
    {
        @Override
        public void onErrorResponse(VolleyError err)
        {
            Toast.makeText(LoginActivity.this, "Errore di rete", Toast.LENGTH_SHORT).show();
        }
    };

    private Response.Listener<JSONArray> getListener = new Response.Listener<JSONArray>()
    {
        @Override
        public void onResponse(JSONArray response) {

            boolean flag=false;
            for (int i=0; i<response.length(); i++)
            {
                try {
                    JSONObject j=response.getJSONObject(i);
                    if (Name.equals(j.getString("name"))) {
                        flag=true;
                        if (Pass.equals(j.getString("password"))) {
                            Intent u = new Intent(LoginActivity.this, MainActivity.class); // from -> to
                            u.putExtra(NAME, j.getString("name")); // extra arguments: the name
                            startActivity(u);
                        } else {
                            Toast.makeText(LoginActivity.this, "Password errata", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                }
                catch (JSONException e) {
                }
            }
            if (!flag) {
                Toast.makeText(LoginActivity.this, "Username non trovato", Toast.LENGTH_SHORT).show();
            }

        }

    };

    private Response.Listener<JSONObject> postListener= new Response.Listener<JSONObject>()
    {
        @Override
        public void onResponse(JSONObject response) {
            Toast.makeText(LoginActivity.this, "Utente registrato!", Toast.LENGTH_SHORT).show();
        }
    };
}
