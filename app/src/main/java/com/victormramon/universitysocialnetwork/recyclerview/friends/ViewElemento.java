package com.victormramon.universitysocialnetwork.recyclerview.friends;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.victormramon.universitysocialnetwork.R;
import com.victormramon.universitysocialnetwork.modelos.Grupos;
import com.victormramon.universitysocialnetwork.modelos.Usuario;

public class ViewElemento extends RecyclerView.ViewHolder {

    private TextView tvNombre;
    private TextView tvFriendPost;
    private Object item;

    public ViewElemento(@NonNull final View itemView) {
        super(itemView);
        this.tvNombre = itemView.findViewById(R.id.tvNombre);
        this.tvFriendPost = itemView.findViewById(R.id.tvFriendPost);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item instanceof Usuario || item instanceof Grupos) {

                    ShowListRecyclerAdapter.onItemClickListener(item);
                } else {
                    Toast.makeText(itemView.getContext(), "algo no ha ido bien al cargar el detalle", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }


    public void setUpHolder(Object item) {
        //comrpobar que estamos pasando (ya que estamos reutilizando el recycler view
        if (item instanceof Usuario) {
            //para manejarlo en el onItemClickListener como parametro
            this.item = (Usuario) item;
            Usuario friend = (Usuario) item;
            String completeName = friend.getNombre() + " " + friend.getApellidos();
            tvNombre.setText(completeName);
            //evitamos mostrar los post si está vacío
            if (!friend.getPostList().isEmpty()) {
                int lastPosition = (friend.getPostList().size()) - 1;
                tvFriendPost.setText(friend.getPostList().get(lastPosition).getContenido());
            } else {
                tvFriendPost.setText("No hay comentario");
            }
        } else {
            this.item = (Grupos) item;
            Grupos grupo = (Grupos) item;
            if (grupo != null) {
                int lastPosition = (grupo.getComentarioGrupoList().size()) - 1;
                tvNombre.setText(grupo.getNombre());
                if (grupo.getComentarioGrupoList().size() > 0) {
                    tvFriendPost.setText(grupo.getComentarioGrupoList().get(lastPosition).getComentario());
                } else {
                    tvFriendPost.setText("No hay comentario");
                }
            }
        }

    }
}
