package com.victormramon.universitysocialnetwork;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.victormramon.universitysocialnetwork.callback.Callback;
import com.victormramon.universitysocialnetwork.modelos.Usuario;
import com.victormramon.universitysocialnetwork.peticionvolley.PeticionVolley;
import com.victormramon.universitysocialnetwork.recyclerview.friends.ShowListRecyclerAdapter;

public class Friends extends AppCompatActivity implements Callback {

    private Usuario userLogged;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_activity_main);

        start();
    }

    /**
     * cargar rv con amigos
     */
    public void start() {

        gson = new GsonBuilder().create();
        getUser();
    }

    public void getUser() {
        PeticionVolley get = new PeticionVolley(this);
        get.getUsuarioVolley();
    }

    public void cargarJson(String json) {
        Usuario user = gson.fromJson(json, Usuario.class);
        chargeRV(user, R.layout.friends_item, R.id.rvFriends);
    }

    public void chargeRV(Usuario userLogged, int idLayoutItem, int idRecyclerView) {
        //R.layout.friends_item
        //R.id.rvFriends
        ShowListRecyclerAdapter rvAdapter = new ShowListRecyclerAdapter(idLayoutItem, userLogged.getAmigosList(), this);
        RecyclerView recView = findViewById(idRecyclerView);
        recView.setHasFixedSize(true);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(rvAdapter);
    }


    @Override
    public void onItemClick(Object item) {
        Bundle bundle = new Bundle();
        Usuario friend = (Usuario) item;
        bundle.putSerializable(getString(R.string.key_friendSelected), friend);
        Intent intent = new Intent(this, FriendDetailed.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

}



