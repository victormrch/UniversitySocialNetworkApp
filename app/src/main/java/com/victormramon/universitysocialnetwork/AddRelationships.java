package com.victormramon.universitysocialnetwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.victormramon.universitysocialnetwork.callback.CallbackRelationship;
import com.victormramon.universitysocialnetwork.fragments.AddFriendFragment;
import com.victormramon.universitysocialnetwork.fragments.AddGroupFragment;
import com.victormramon.universitysocialnetwork.modelos.Grupos;
import com.victormramon.universitysocialnetwork.modelos.Sugerencias;
import com.victormramon.universitysocialnetwork.modelos.Usuario;
import com.victormramon.universitysocialnetwork.peticionvolley.PeticionVolleyCreateGroups;
import com.victormramon.universitysocialnetwork.peticionvolley.newfriendsuggested.PeticionVolleyFriend;
import com.victormramon.universitysocialnetwork.peticionvolley.suggestion.PeticionVolleySuggestion;

public class AddRelationships extends AppCompatActivity  implements CallbackRelationship {

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
                findViewById(R.id.l_parent_relationship).setBackgroundColor(getResources().getColor(R.color.colorFondoIcono));
                AddFriendFragment fragmentToCharge = new AddFriendFragment();
                fragmentToCharge.setArguments(generateBundle(suggestion));
                FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                t.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                t.replace(R.id.flPlaceToFragment, fragmentToCharge);
                t.disallowAddToBackStack();
                t.commit();
            }
        });

        btnGroups = findViewById(R.id.btnGrupos);
        btnGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.l_parent_relationship).setBackgroundColor(getResources().getColor(R.color.colorFondoIcono));
                AddGroupFragment fragmentToCharge = new AddGroupFragment();
                fragmentToCharge.setArguments(generateBundle(suggestion));
                FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                t.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                t.replace(R.id.flPlaceToFragment, fragmentToCharge);
                t.disallowAddToBackStack();
                t.commit();
            }
        });
    }

    private Bundle generateBundle(Sugerencias suggestion) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(getString(R.string.key_suggestion), suggestion);
        bundle.putSerializable(getString(R.string.key_userLogged), user);
        return bundle;
    }

    /**
     * hacer la petición volley oara traer al usuario con el que vamos a buscar las sugerencias
     */
    private void prepareActivity() {
        this.user = (Usuario) getIntent().getExtras().getSerializable(getString(R.string.key_userLogged));
        getSuggestion(user);
    }

    /**
     * Peticiona al servidor las sugerencias para el usuario
     * @param user
     */
    private void getSuggestion(Usuario user) {
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
        Gson gson = new GsonBuilder().create();
        Sugerencias sug = gson.fromJson(response, Sugerencias.class);
        this.suggestion = sug;
    }


    @Override
    public void onFriendClick(Usuario friendSuggestedClicked) {
        Usuario newFriend = new Usuario();
        //si el id no es null es que va a seguir a un amigo sugerido
        if (friendSuggestedClicked.getId() != null) {
            newFriend.setId(friendSuggestedClicked.getId());
        } else {
            //si es null, es que va a seguir a un usuario buscado por email
            newFriend.setEmail(friendSuggestedClicked.getEmail());
        }
        PeticionVolleyFriend volley =
                new PeticionVolleyFriend(this, user, newFriend);
        volley.doPostRequestToSave();

    }

    @Override
    public void onGroupClick(Grupos item) {
        Grupos newGroup = new Grupos();

        if (item.getIdGrupo() != null) {
            newGroup.setIdGrupo(item.getIdGrupo());
        } else {
            newGroup.setNombre(item.getNombre());
        }
        PeticionVolleyCreateGroups volley = new PeticionVolleyCreateGroups(this, newGroup, user);
        volley.doPostRequestToSave();


    }

    public void backToMenu() {
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK);
        finish();
    }
}
