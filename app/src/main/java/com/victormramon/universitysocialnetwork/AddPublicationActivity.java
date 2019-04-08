package com.victormramon.universitysocialnetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.victormramon.universitysocialnetwork.Callback.CallbackPost;
import com.victormramon.universitysocialnetwork.modelos.Post;
import com.victormramon.universitysocialnetwork.modelos.Usuario;
import com.victormramon.universitysocialnetwork.peticionvolley.PeticionVolleyPublications;

import java.util.Date;

public class AddPublicationActivity extends AppCompatActivity implements CallbackPost {

    private Usuario userLogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_publication);
    }


    @Override
    public void onItemClick(Object item) {
        if (item instanceof Post) {
            Post postToSave = (Post) item;
            postToSave.setFecha(new Date());
            postToSave.setIdPublicador(userLogged);
            sendToServer(postToSave);
        }
    }

    private void sendToServer(Object item) {
        PeticionVolleyPublications volleyPost = null;
        if (item instanceof Post) {
            new PeticionVolleyPublications(this, userLogged,(Post) item);
            volleyPost.doPostRequestToSave();
        }
    }
}
