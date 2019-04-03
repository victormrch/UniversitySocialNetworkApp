//package com.victormramon.universitysocialnetwork.RecyclerViewPost;
//
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import java.util.ArrayList;
//
//public class PostRecyclerAdapter extends RecyclerView.Adapter<ViewElemento> {
//    private ArrayList<Post> elementos;
//    private int resourceId;
//
//    public PostRecyclerAdapter(int resourceId, ArrayList<Post> elementos) {
//        this.elementos = elementos;
//        this.resourceId = resourceId;
//    }
//
//    @Override
//    public ViewElemento onCreateViewHolder(ViewGroup v, int i) {
//        View view = LayoutInflater.from(
//                v.getContext()).inflate(resourceId, v, false);
//        ViewElemento viewHolder = new ViewElemento(view);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(ViewElemento viewElemento, int i) {
//        viewElemento.setUpHolder(elementos.get(i));
//    }
//
//    @Override
//    public int getItemCount() {
//        return elementos.size();
//    }
//
//}
