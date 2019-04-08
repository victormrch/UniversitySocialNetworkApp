package com.victormramon.universitysocialnetwork.recyclerview.suggestions;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.victormramon.universitysocialnetwork.R;
import com.victormramon.universitysocialnetwork.modelos.Grupos;
import com.victormramon.universitysocialnetwork.modelos.Usuario;

//view elemento para los amigos sugeridos
public class ViewElemento extends RecyclerView.ViewHolder {

    private TextView tvNombre;

    public ViewElemento(@NonNull View itemView) {
        super(itemView);
        tvNombre = itemView.findViewById(R.id.tvNameSuggested);

    }

    public void setUpHolder(Object item) {
        if (item instanceof Usuario) {
            Usuario user = (Usuario) item;
            tvNombre.setText(user.getNombre());
        } else {
            Grupos group = (Grupos) item;
            tvNombre.setText(group.getNombre());
        }
    }
}
