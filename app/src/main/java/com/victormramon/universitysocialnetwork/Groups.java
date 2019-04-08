package com.victormramon.universitysocialnetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.victormramon.universitysocialnetwork.modelos.Usuario;
import com.victormramon.universitysocialnetwork.peticionvolley.PeticionVolley;
import com.victormramon.universitysocialnetwork.recyclerview.friends.FriendRecyclerAdapter;

public class Groups extends AppCompatActivity {

    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groups_activity_main);
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
        chargeRV(user, R.layout.friends_item, R.id.rvGroups);
    }

    public void chargeRV(Usuario userLogged, int idLayoutItem, int idRecyclerView) {
        //R.layout.friends_item
        //R.id.rvFriends
        FriendRecyclerAdapter rvAdapter = new FriendRecyclerAdapter(idLayoutItem, userLogged.getGruposList());
        RecyclerView recView = findViewById(idRecyclerView);
        recView.setHasFixedSize(true);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(rvAdapter);
    }

}
