package com.victormramon.universitysocialnetwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.victormramon.universitysocialnetwork.callback.Callback;
import com.victormramon.universitysocialnetwork.modelos.Grupos;
import com.victormramon.universitysocialnetwork.modelos.Usuario;
import com.victormramon.universitysocialnetwork.recyclerview.friends.ShowListRecyclerAdapter;

public class Groups extends AppCompatActivity implements Callback {

    private Gson gson;
    private Usuario user;

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
        this.user = (Usuario) getIntent().getExtras()
                .getSerializable(getString(R.string.key_userLogged));

        //gson = new GsonBuilder().create();
        //getUser();
        chargeRV(this.user, R.layout.groups_item, R.id.rvGroups);
        preparedAddGroupBtn();
    }

    public void getUser() {
        this.user = (Usuario) getIntent().getExtras().getSerializable(getString(R.string.key_userLogged));
    }

    public void cargarJson(String json) {

        chargeRV(user, R.layout.friends_item, R.id.rvGroups);
    }

    public void chargeRV(Usuario userLogged, int idLayoutItem, int idRecyclerView) {
        //R.layout.friends_item
        //R.id.rvFriends
        ShowListRecyclerAdapter rvAdapter = new ShowListRecyclerAdapter(idLayoutItem, userLogged.getGruposList(), this);
        RecyclerView recView = findViewById(idRecyclerView);
        recView.setHasFixedSize(true);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(rvAdapter);
    }

    @Override
    public void onItemClick(Object item) {
        Bundle bundle = new Bundle();
        Grupos friend = (Grupos) item;
        bundle.putSerializable(getString(R.string.key_groupSelected), friend);
        Intent intent = new Intent(this, GroupDetailed.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    private void preparedAddGroupBtn() {
        FloatingActionButton btnAddFriend = findViewById(R.id.floatingFollowFriends);
        btnAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putSerializable(getString(R.string.key_userLogged), user);
                Intent intent = new Intent(Groups.this, AddRelationships.class);
                intent.putExtras(args);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            Intent intent = new Intent();
            setResult(Activity.RESULT_OK);
            finish();
        }
    }

}
