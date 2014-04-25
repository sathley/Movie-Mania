package com.appacitive.moviemania.app;

/**
 * Created by sathley on 4/25/2014.
 */
public class Movie {

    public String mName;

    public String mPosterUrl;

    public long mId;

    public Movie(long id, String name, String url)
    {
        this.mId = id;
        this.mName = name;
        this.mPosterUrl = url;
    }
}
