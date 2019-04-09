package com.victormramon.universitysocialnetwork;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.victormramon.universitysocialnetwork.fragments.AddFriendFragment;
import com.victormramon.universitysocialnetwork.fragments.AddGroupFragment;
import com.victormramon.universitysocialnetwork.modelos.Sugerencias;
import com.victormramon.universitysocialnetwork.modelos.Usuario;
import com.victormramon.universitysocialnetwork.peticionvolley.PeticionVolley;
import com.victormramon.universitysocialnetwork.peticionvolley.suggestion.PeticionVolleySuggestion;

public class AddRelationships extends AppCompatActivity {

    private Gson gson;
    private Usuario user;
    private Sugerencias suggestion;

    private Button btnFriends;
    private Button btnGroups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_main);
        prepareActivity();

        btnFriends = findViewById(R.id.btnAmigos);
        btnFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFriendFragment fragmentToCharge = new AddFriendFragment();
                fragmentToCharge.setArguments(generateBundle(suggestion));
                FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                t.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                t.replace(R.id.flPlaceToFragment, fragmentToCharge);
                t.addToBackStack(null);
                t.commit();
            }
        });

        btnGroups = findViewById(R.id.btnGrupos);
        btnGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddGroupFragment fragmentToCharge = new AddGroupFragment();
                fragmentToCharge.setArguments(generateBundle(suggestion));
                FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                t.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                t.replace(R.id.flPlaceToFragment, fragmentToCharge);
                t.addToBackStack(null);
                t.commit();
            }
        });
    }

    private Bundle generateBundle(Sugerencias suggestion) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(getString(R.string.key_suggestion), suggestion);
        return bundle;
    }

    /**
     * hacer la petición volley oara traer al usuario con el que vamos a buscar las sugerencias
     */
    private void prepareActivity() {
        this.user = (Usuario) getIntent().getExtras().getSerializable(getString(R.string.key_userLogged));

    }

    public void getSuggestion(Usuario user) {
        PeticionVolleySuggestion volleySuggestion = new PeticionVolleySuggestion(this, user);
        volleySuggestion.getUsuarioVolley();
    }

    /**
     * transformar el json usuario a un Usuario
     * @param response
     */
    public Usuario getUserFromResponse(String response) {
        gson = new GsonBuilder().create();
        return gson.fromJson(response, Usuario.class);
    }

    /**
     * transformar el json sugerencias a el objeto Sugerencias
     * @param response
     */
    public void getSuggestionFromResponse(String response) {

        Sugerencias sug = gson.fromJson(response, Sugerencias.class);
        this.suggestion = sug;
    }

}
