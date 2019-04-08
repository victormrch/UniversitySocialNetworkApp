package com.victormramon.universitysocialnetwork.recyclerview.friends;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.victormramon.universitysocialnetwork.R;
import com.victormramon.universitysocialnetwork.modelos.Grupos;
import com.victormramon.universitysocialnetwork.modelos.Usuario;

public class ViewElemento extends RecyclerView.ViewHolder {

    private TextView tvNombre;
    private TextView tvFriendPost;

    public ViewElemento(@NonNull View itemView) {
        super(itemView);
        this.tvNombre = itemView.findViewById(R.id.tvNombre);
        this.tvFriendPost = itemView.findViewById(R.id.tvFriendPost);
    }


    public void setUpHolder(Object item) {
        if(item instanceof Usuario) {
            Usuario friend = (Usuario) item;
            if (!friend.getPostList().isEmpty()) {
                int lastPosition = (friend.getPostList().size()) - 1;
                String completeName = friend.getNombre() + " " + friend.getApellidos();
                tvNombre.setText(completeName);
                tvFriendPost.setText(friend.getPostList().get(lastPosition).getContenido());
            }
        } else {
            Grupos grupo = (Grupos) item;
            if (grupo != null ) {
                int lastPosition = (grupo.getComentarioGrupoList().size()) - 1;
                tvNombre.setText(grupo.getNombre());
                if ( grupo.getComentarioGrupoList().size() > 0) {
                    tvFriendPost.setText(grupo.getComentarioGrupoList().get(lastPosition).getComentario());
                } else {
                    tvFriendPost.setText("No hay comentario");
                }
            }
        }

    }
}
