package com.victormramon.universitysocialnetwork.recyclerview.friendsuggested;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.victormramon.universitysocialnetwork.R;
import com.victormramon.universitysocialnetwork.modelos.Usuario;

//view elemento para los amigos sugeridos
public class ViewElemento extends RecyclerView.ViewHolder {

    private TextView tvNombre;

    public ViewElemento(@NonNull View itemView) {
        super(itemView);
        tvNombre = itemView.findViewById(R.id.tvNameSuggested);

    }

    public void setUpHolder(Usuario item) {
        tvNombre.setText(item.getNombre());
    }
}
