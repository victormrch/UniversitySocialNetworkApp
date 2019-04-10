package com.victormramon.universitysocialnetwork;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.gson.Gson;
import com.victormramon.universitysocialnetwork.callback.Callback;
import com.victormramon.universitysocialnetwork.modelos.Usuario;
import com.victormramon.universitysocialnetwork.recyclerview.friends.ShowListRecyclerAdapter;

public class Friends extends AppCompatActivity implements Callback {

    private Usuario user;
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
        this.user = (Usuario) getIntent().getExtras()
                .getSerializable(getString(R.string.key_userLogged));
        //gson = new GsonBuilder().create();
        //getUser();
        chargeRV(this.user, R.layout.friends_item, R.id.rvFriends);
    }

    public void getUser() {
        this.user = (Usuario) getIntent().getExtras().getSerializable(getString(R.string.key_userLogged));
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
        bundle.putSerializable(getString(R.string.key_userLogged), this.user);
        Intent intent = new Intent(this, FriendDetailed.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

}



