package com.victormramon.universitysocialnetwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.victormramon.universitysocialnetwork.callback.Callback;
import com.victormramon.universitysocialnetwork.fragments.AddPostFragment;
import com.victormramon.universitysocialnetwork.modelos.Post;
import com.victormramon.universitysocialnetwork.modelos.Usuario;
import com.victormramon.universitysocialnetwork.peticionvolley.PeticionVolleyPublications;

public class AddPublicationActivity extends AppCompatActivity implements Callback {

    private Usuario userLogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_publication);
        getUserFromIntent();
        chargeFragment();
    }

    public void getUserFromIntent() {
        this.userLogged = (Usuario) getIntent().getExtras().getSerializable(getString(R.string.key_userLogged));
    }


    @Override
    public void onItemClick(Object item) {
        if (item instanceof Post) {
            Post postToSave = (Post) item;
            sendToServer(postToSave);

        }

    }

    /**
     * manda a la activity que está esperando el resultado (para actualizar el usuario desde la base de datos)
     */
    public void onSavePostServerResult() {
        Intent result = new Intent();
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    /**
     * se encarga de instanciar la peticion volley y de ejecutar el método para hacerla
     *
     * @param item objeto que se va a mandar
     */
    private void sendToServer(Object item) {
        PeticionVolleyPublications volleyPost = null;
        if (item instanceof Post) {
            volleyPost = new PeticionVolleyPublications(this, userLogged, (Post) item);
            volleyPost.doPostRequestToSave();
        }
    }

    private void chargeFragment() {
        AddPostFragment fragmentToCharge = new AddPostFragment();
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        t.replace(R.id.flNewPublication, fragmentToCharge);
        t.disallowAddToBackStack();
        t.commit();
    }


}
