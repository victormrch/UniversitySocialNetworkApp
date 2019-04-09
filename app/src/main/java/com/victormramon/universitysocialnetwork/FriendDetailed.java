package com.victormramon.universitysocialnetwork;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.victormramon.universitysocialnetwork.modelos.Usuario;
import com.victormramon.universitysocialnetwork.recyclerview.recyclerviewpost.PostRecyclerAdapter;

public class FriendDetailed extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        prepareLayout();
    }

    private void prepareLayout() {
        Usuario friend = getFriendSelected();
        TextView tvNombre = findViewById(R.id.tvName);
        tvNombre.setText(friend.getNombre());
        TextView tvApellido = findViewById(R.id.tvSurname);
        tvApellido.setText(friend.getApellidos());
        TextView tvEmail = findViewById(R.id.tvEmail);
        tvEmail.setText(friend.getEmail());
        findViewById(R.id.tvTitleLastComment).setVisibility(View.GONE);
        chargeRV(friend);
    }
   private void chargeRV (Usuario friend){


       PostRecyclerAdapter recAdapter =
               new PostRecyclerAdapter(R.layout.item_post, friend.getPostList());

       RecyclerView recView = (RecyclerView) findViewById(R.id.rvUltimosPost);

       // Mejora el rendimiento
       recView.setHasFixedSize(true);
       recView.setLayoutManager(new LinearLayoutManager(this));
       recView.setAdapter(recAdapter);

   }


    private Usuario getFriendSelected() {
        return (Usuario) getIntent().getExtras()
                .getSerializable(getString(R.string.key_friendSelected));

    }
}
