package com.victormramon.universitysocialnetwork.recyclerview.recyclerviewcomment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.victormramon.universitysocialnetwork.modelos.ComentarioGrupo;
import com.victormramon.universitysocialnetwork.modelos.Post;

import org.w3c.dom.Comment;

import java.util.List;


public class CommentRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ComentarioGrupo> elementos;
    private int resourceId;

    public CommentRecyclerAdapter(int resourceId, List<ComentarioGrupo> elementos) {
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
