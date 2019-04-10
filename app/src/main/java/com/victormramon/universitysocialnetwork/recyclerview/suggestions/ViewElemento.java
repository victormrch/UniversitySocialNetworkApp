package com.victormramon.universitysocialnetwork.recyclerview.suggestions;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.victormramon.universitysocialnetwork.R;
import com.victormramon.universitysocialnetwork.modelos.Grupos;
import com.victormramon.universitysocialnetwork.modelos.Usuario;

//view elemento para los amigos sugeridos
public class ViewElemento extends RecyclerView.ViewHolder {

    private TextView tvNombre;
    private Button followBtn;

    public ViewElemento(@NonNull View itemView) {
        super(itemView);
        tvNombre = itemView.findViewById(R.id.tvNameSuggested);
        followBtn = itemView.findViewById(R.id.btnFollow);

    }

    public void setUpHolder(Object item) {
        if (item instanceof Usuario) {
            Usuario user = (Usuario) item;
            tvNombre.setText(user.getNombre());
        } else {
            final Grupos group = (Grupos) item;
            tvNombre.setText(group.getNombre());
           

        }
    }
}
