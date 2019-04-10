package com.victormramon.universitysocialnetwork;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.victormramon.universitysocialnetwork.modelos.Usuario;
import com.victormramon.universitysocialnetwork.peticionvolley.PeticionVolley;
import com.victormramon.universitysocialnetwork.peticionvolley.deletefriend.PeticionVolleyUnfollowFriend;
import com.victormramon.universitysocialnetwork.recyclerview.recyclerviewpost.PostRecyclerAdapter;

public class FriendDetailed extends AppCompatActivity {

    private Usuario userLogged;
    private Usuario friendSelected;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        prepareLayout();
    }

    private void prepareLayout() {
        getExtrasFromBundle();
        TextView tvNombre = findViewById(R.id.tvName);
        tvNombre.setText(friendSelected.getNombre());
        TextView tvApellido = findViewById(R.id.tvSurname);
        tvApellido.setText(friendSelected.getApellidos());
        TextView tvEmail = findViewById(R.id.tvEmail);
        tvEmail.setText(friendSelected.getEmail());

        findViewById(R.id.tvTitleLastComment).setVisibility(View.GONE);
        findViewById(R.id.btnNewPubliMain).setVisibility(View.GONE);
        chargeRV(friendSelected);
    }

    private void chargeRV(Usuario friend) {

        PostRecyclerAdapter recAdapter =
                new PostRecyclerAdapter(R.layout.item_post, friend.getPostList());

        RecyclerView recView = (RecyclerView) findViewById(R.id.rvUltimosPost);

        recView.setHasFixedSize(true);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(recAdapter);

    }


    private void getExtrasFromBundle() {
        friendSelected = (Usuario) getIntent().getExtras()
                .getSerializable(getString(R.string.key_friendSelected));
        userLogged = (Usuario) getIntent().getExtras()
                .getSerializable(getString(R.string.key_userLogged));
    }

    private void unfollowFriend() {
        Button btnUnfollow = findViewById(R.id.btnAmigos);
        btnUnfollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PeticionVolleyUnfollowFriend volley =
                        new PeticionVolleyUnfollowFriend(FriendDetailed.this, userLogged, friendSelected);
                volley.doDeleteFriendRequest();
            }
        });
    }
}
