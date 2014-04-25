package com.appacitive.moviemania.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.appacitive.android.AppacitiveContext;
import com.appacitive.core.model.Environment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    ListView mMoviesList;
    MovieAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Movie Mania");
        AppacitiveContext.initialize("UV7ltYy1L0WCEvRkPTU9Lw==", Environment.sandbox, this);
        mMoviesList = (ListView) findViewById(R.id.movies_list);
        List<String> fields = new ArrayList<String>(){{
            add("name");
            add("posterurl");
        }};
        adapter = new MovieAdapter(this, "movie", fields, null);
        mMoviesList.setAdapter(adapter);


       mMoviesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Movie movieDetails = (Movie)view.getTag();
               Intent detailsIntent = new Intent(MainActivity.this, DetailsActivity.class);
               detailsIntent.putExtra("movie_id", movieDetails.mId);
               detailsIntent.putExtra("movie_name", movieDetails.mName);
               detailsIntent.putExtra("movie_url", movieDetails.mPosterUrl);
               startActivity(detailsIntent);
           }
       });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
