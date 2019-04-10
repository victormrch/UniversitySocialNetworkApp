package com.victormramon.universitysocialnetwork;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.victormramon.universitysocialnetwork.modelos.Usuario;
import com.victormramon.universitysocialnetwork.peticionvolley.PeticionVolley;
import com.victormramon.universitysocialnetwork.recyclerview.recyclerviewcomment.CommentRecyclerAdapter;
import com.victormramon.universitysocialnetwork.recyclerview.recyclerviewpost.PostRecyclerAdapter;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private TextView tvName;
    private TextView tvSurname;
    private TextView tvEmail;
    private Usuario usuario = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        prepareView();

    }

    private void prepareView() {
        Bundle args = getIntent().getExtras();
        this.tvName = (TextView) findViewById(R.id.tvName);
        this.tvSurname = (TextView) findViewById(R.id.tvSurname);
        this.tvEmail = (TextView) findViewById(R.id.tvEmail);

        findViewById(R.id.btnNewPubliMain).setVisibility(View.GONE);

        usuario = (Usuario) args.getSerializable(getString(R.string.key_userLogged));
        chargeTextView(usuario);
        if (usuario.getComentarioGrupoList() != null){
            chargeComment(usuario);
        }

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
        if (id == R.id.action_profile) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            // Handle the camera action
        } else if (id == R.id.nav_friends) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(getString(R.string.key_userLogged), usuario);

            Intent intent = new Intent(getApplicationContext(), Friends.class);
            intent.putExtras(bundle);
            startActivityForResult(intent, 1);

        } else if (id == R.id.nav_groups) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(getString(R.string.key_userLogged), usuario);

            Intent intent = new Intent(getApplicationContext(), Groups.class);
            intent.putExtras(bundle);
            startActivity(intent);

        } else if (id == R.id.nav_post) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(getString(R.string.key_userLogged), usuario);

            Intent intent = new Intent(getApplicationContext(), AddPublicationActivity.class);
            intent.putExtras(bundle);
            startActivityForResult(intent, 1);

        } else if (id == R.id.nav_new_friend) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(getString(R.string.key_userLogged), usuario);

            Intent intent = new Intent(this, AddRelationships.class);
            intent.putExtras(bundle);
            startActivityForResult(intent, 1);

        } else if (id == R.id.nav_logout) {
            //borrar las preferences
            SharedPreferences prefs = getSharedPreferences(getString(R.string.key_sharedPref), Context.MODE_PRIVATE);
            prefs.edit().clear();
            
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                //pedir el usuario logeado de nuevo para recibir los cambios
                PeticionVolley volley = new PeticionVolley(this, usuario);
                volley.getUsuarioVolley();

            }
        }
    }

    /**
     * recoge el usuario del servidor con los cambios realizados (post añadidos o borrados(aun sin hacer))
     * y pinta d enuevo los recycler view
     *
     * @param user
     */
    public void getUserRefreshed(Usuario user) {
        this.usuario = user;
        chargePost(this.usuario);
        chargeComment(this.usuario);
    }

    public void chargeTextView(Usuario userLogged) {

        tvName.setText(userLogged.getNombre());
        tvSurname.setText(userLogged.getApellidos());
        tvEmail.setText(userLogged.getEmail());

        if (userLogged.getPostList() != null){

            chargePost(userLogged);
        }



    }

    /**
     * pinta los recycler view con los post del usuario
     *
     * @param userLogged
     */
    private void chargePost(Usuario userLogged) {
        PostRecyclerAdapter recAdapter =
                new PostRecyclerAdapter(R.layout.item_post, userLogged.getPostList());

        RecyclerView recView = (RecyclerView) findViewById(R.id.rvUltimosPost);

        recView.setHasFixedSize(true);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(recAdapter);
    }

    /**
     * pinta los recycler view con los comentarios
     *
     * @param userLogged
     */
    public void chargeComment(Usuario userLogged) {

        CommentRecyclerAdapter recAdapter =
                new CommentRecyclerAdapter(R.layout.comment_item, userLogged.getComentarioGrupoList());

        RecyclerView recView = (RecyclerView) findViewById(R.id.rvLastComment);

        recView.setHasFixedSize(true);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(recAdapter);

    }
}
