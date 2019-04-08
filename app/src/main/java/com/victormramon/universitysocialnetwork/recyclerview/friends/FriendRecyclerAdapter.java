package com.victormramon.universitysocialnetwork.recyclerview.friends;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.victormramon.universitysocialnetwork.modelos.Post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FriendRecyclerAdapter extends RecyclerView.Adapter<ViewElemento> {

    private List elementos;
    private int resourceId;

    public FriendRecyclerAdapter(int resourceId, List elementos) {
        this.elementos = elementos;
        this.resourceId = resourceId;
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

}

