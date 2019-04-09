package com.victormramon.universitysocialnetwork.recyclerview.friends;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.victormramon.universitysocialnetwork.Friends;
import com.victormramon.universitysocialnetwork.Groups;
import com.victormramon.universitysocialnetwork.modelos.Grupos;
import com.victormramon.universitysocialnetwork.modelos.Usuario;

import java.util.List;

public class ShowListRecyclerAdapter extends RecyclerView.Adapter<ViewElemento> {

    private List elementos;
    private int resourceId;
    private static Activity activity;

    public ShowListRecyclerAdapter(int resourceId, List elementos, Activity activity) {
        this.elementos = elementos;
        this.resourceId = resourceId;
        if (activity instanceof Friends) {
            this.activity = (Friends) activity;
        } else {
            ShowListRecyclerAdapter.activity = (Groups) activity;
        }
    }

    @Override
    public ViewElemento onCreateViewHolder(ViewGroup v, int i) {
        View view = LayoutInflater.from(
                v.getContext()).inflate(resourceId, v, false);
        ViewElemento viewHolder = new ViewElemento(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewElemento viewElemento, int i) {
        viewElemento.setUpHolder(elementos.get(i));
    }

    @Override
    public int getItemCount() {
        return elementos.size();
    }

    public static void onItemClickListener(Object item) {
        if (item instanceof Usuario) {
            Friends friendActivity = (Friends) activity;
            friendActivity.onItemClick(item);
        } else if (item instanceof Grupos) {
            Groups groupsActivity = (Groups) activity;
            groupsActivity.onItemClick(item);
        }
    }

}

