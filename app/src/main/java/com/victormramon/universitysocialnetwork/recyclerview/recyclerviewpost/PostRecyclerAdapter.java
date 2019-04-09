package com.victormramon.universitysocialnetwork.recyclerview.recyclerviewpost;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

import com.victormramon.universitysocialnetwork.modelos.Post;



public class PostRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Post> elementos;
    private int resourceId;

    public PostRecyclerAdapter(int resourceId, List<Post> elementos) {
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewElemento v = (ViewElemento) viewHolder;
        v.setUpHolder(elementos.get(i));
    }


    @Override
    public int getItemCount() {
        return elementos.size();
    }

}
