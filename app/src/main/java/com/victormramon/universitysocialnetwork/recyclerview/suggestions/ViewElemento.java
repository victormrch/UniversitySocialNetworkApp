package com.victormramon.universitysocialnetwork.recyclerview.suggestions;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.victormramon.universitysocialnetwork.R;
import com.victormramon.universitysocialnetwork.fragments.AddFriendFragment;
import com.victormramon.universitysocialnetwork.fragments.AddGroupFragment;
import com.victormramon.universitysocialnetwork.modelos.Grupos;
import com.victormramon.universitysocialnetwork.modelos.Usuario;

//view elemento para los amigos sugeridos
public class ViewElemento extends RecyclerView.ViewHolder {

    private TextView tvNombre;
    private Button btnFollow;
    private Usuario userClicked;
    private Grupos groupClicked;

    public ViewElemento(@NonNull View itemView) {
        super(itemView);
        tvNombre = itemView.findViewById(R.id.tvNameSuggested);
        btnFollow = itemView.findViewById(R.id.btnFollow);
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userClicked != null) {
                    AddFriendFragment.onFriendClickListener(userClicked);

                } else if (groupClicked != null) {
                    AddGroupFragment.onGroupSuggestedClick(groupClicked);
                }
            }
        });

    }

    public void setUpHolder(Object item) {
        if (item instanceof Usuario) {

            userClicked = (Usuario) item;
            tvNombre.setText(userClicked.getNombre());
        } else {
            groupClicked = (Grupos) item;
            tvNombre.setText(groupClicked.getNombre());
        }
    }
}
