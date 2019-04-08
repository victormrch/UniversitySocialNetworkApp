package com.victormramon.universitysocialnetwork;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.victormramon.universitysocialnetwork.modelos.ComentarioGrupo;
import com.victormramon.universitysocialnetwork.modelos.Usuario;
import com.victormramon.universitysocialnetwork.peticionvolley.PeticionVolley;
import com.victormramon.universitysocialnetwork.recyclerview.recyclerviewcomment.CommentRecyclerAdapter;
import com.victormramon.universitysocialnetwork.recyclerview.recyclerviewpost.PostRecyclerAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Gson gson;
    private TextView tvName;
    private TextView tvSurname;
    private TextView tvEmail;
    private Usuario userLogged;
    public static final String KEY_BUNDLE_USER = "usuario_logueado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        inicializar();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    private void inicializar() {
        gson = new GsonBuilder().create();

        tvName = findViewById(R.id.tvName);
        tvSurname = findViewById(R.id.tvSurname);
        tvEmail = findViewById(R.id.tvEmail);


        PeticionVolley get = new PeticionVolley(this);
        get.getUsuarioVolley();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            // Handle the camera action
        } else if (id == R.id.nav_friends) {

        } else if (id == R.id.nav_groups) {

        } else if (id == R.id.nav_post) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(KEY_BUNDLE_USER, userLogged);

            Intent intent = new Intent(getApplicationContext(),AddPublicationActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);

        } else if (id == R.id.nav_new_friend) {

        } else if (id == R.id.nav_new_group) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void cargarJson(String json) {
        // TextView tv = (TextView) this.findViewById(R.id.tvName);
        // tv.setText(json);
        Usuario user = gson.fromJson(json, Usuario.class);
        this.userLogged = user;

        cargarTextView(userLogged);
        cargarComentario(userLogged);
    }

    public void cargarTextView(Usuario userLogged) {

        tvName.setText(userLogged.getNombre());
        tvSurname.setText(userLogged.getApellidos());
        tvEmail.setText(userLogged.getEmail());

        PostRecyclerAdapter recAdapter =
                new PostRecyclerAdapter(R.layout.item_post, userLogged.getPostList());

        RecyclerView recView = (RecyclerView) findViewById(R.id.rvUltimosPost);

        // Mejora el rendimiento
        recView.setHasFixedSize(true);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(recAdapter);
    }

    public void cargarComentario (Usuario userLogged){

        CommentRecyclerAdapter recAdapter =
                new CommentRecyclerAdapter(R.layout.comment_item, userLogged.getComentarioGrupoList());

        RecyclerView recView = (RecyclerView) findViewById(R.id.rvLastComment);

        // Mejora el rendimiento
        recView.setHasFixedSize(true);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(recAdapter);

    }


}
