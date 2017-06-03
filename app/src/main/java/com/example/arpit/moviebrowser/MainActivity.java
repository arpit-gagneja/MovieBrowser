package com.example.arpit.moviebrowser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.arpit.moviebrowser.api.HttpClient;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView mTestView;
    private Button mTestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTestView = (TextView) findViewById(R.id.testTextView);
        mTestButton = (Button) findViewById(R.id.button);

        mTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeHttpCall();
            }
        });
    }

    private void makeHttpCall() {
        String url = "https://api.themoviedb.org/3/search/movie?query=big&api_key=5b2b06134bae31ee8534bd84fcf59eea&page=1";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        mTestView.setText(response.toString());
                        Log.i("MainActivity", "Inside Main Activity");
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mTestView.setText(error.getMessage());

                    }
                });
        HttpClient.getInstance(this.getApplicationContext()).addToRequestQueue(jsObjRequest);
    }
}
