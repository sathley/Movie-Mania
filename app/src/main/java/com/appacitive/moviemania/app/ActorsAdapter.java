package com.appacitive.moviemania.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.appacitive.core.AppacitiveObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sathley on 4/25/2014.
 */
public class ActorsAdapter extends BaseAdapter {

    List<Actor> mActorList = new ArrayList<Actor>();
    Context mContext;
    public ActorsAdapter(Context context, List<Actor> actors)
    {
        this.mActorList = actors;
        this.mContext = context;
    }
    @Override
    public int getCount() {
        return mActorList.size();
    }

    @Override
    public Object getItem(int i) {
        return mActorList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater li = LayoutInflater.from(mContext);
        View actorItem = li.inflate(R.layout.actors_list_item, null, false);
        ImageView posterView = (ImageView) actorItem.findViewById(R.id.image_actor);
        TextView nameView = (TextView) actorItem.findViewById(R.id.actor_name);

        Actor actor = (Actor)getItem(i);
        nameView.setText(actor.mName);
        Picasso.with(mContext)
                .load(actor.mUrl)
//                .placeholder(R.drawable.placeholder)
                .into(posterView);

        return actorItem;
    }
}
