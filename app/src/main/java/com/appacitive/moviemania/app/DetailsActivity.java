package com.appacitive.moviemania.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.appacitive.core.AppacitiveConnection;
import com.appacitive.core.AppacitiveObject;
import com.appacitive.core.model.Callback;
import com.appacitive.core.model.ConnectedObject;
import com.appacitive.core.model.ConnectedObjectsResponse;

import java.util.ArrayList;
import java.util.List;


public class DetailsActivity extends ActionBarActivity {

    long mMovieId;
    String mMovieName;
    String mMoviePosterUrl;
    List<Actor> mActors;
    ListView mActorsList;
    ActorsAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mMovieId = this.getIntent().getLongExtra("movie_id", 0);
        mMovieName = this.getIntent().getStringExtra("movie_name");
        mMoviePosterUrl = this.getIntent().getStringExtra("movie_url");
        mActorsList = (ListView)findViewById(R.id.actors_list);
        this.setTitle(mMovieName);
        mActors = new ArrayList<Actor>();
        AppacitiveObject.getConnectedObjectsInBackground("acted", "movie", mMovieId, null, null, new Callback<ConnectedObjectsResponse>() {
            @Override
            public void success(ConnectedObjectsResponse result) {
                for(ConnectedObject connectedObject: result.results)
                {
                    String name = connectedObject.object.getPropertyAsString("name");
                    String url = connectedObject.object.getPropertyAsString("picurl");
                    Actor actor = new Actor(name, url);
                    mActors.add(actor);
                }

                mAdapter = new ActorsAdapter(getBaseContext(), mActors);
                mActorsList.setAdapter(mAdapter);
            }

            @Override
            public void failure(ConnectedObjectsResponse result, Exception e) {
                Log.e("APPACITIVE", e.getMessage());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
