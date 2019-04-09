package com.victormramon.universitysocialnetwork.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.victormramon.universitysocialnetwork.AddPublicationActivity;
import com.victormramon.universitysocialnetwork.R;
import com.victormramon.universitysocialnetwork.callback.Callback;
import com.victormramon.universitysocialnetwork.modelos.Post;
import com.victormramon.universitysocialnetwork.modelos.Usuario;

public class AddPostFragment extends Fragment {

    private Post newPost;
    private Button btnNewPost;
    private Usuario userLogged;
    private AddPublicationActivity activity;
    private Callback callback;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.new_publication_frag, container, false);
        prepare(rootView);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        callback = (Callback) context;
    }

    private void prepare(final View view) {

        btnNewPost = view.findViewById(R.id.btnNewPublication);
        btnNewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText publication = (EditText) view.findViewById(R.id.etPublication);

                Post post = new Post();
                post.setContenido(publication.getText().toString());

                callback.onItemClick(post);


            }
        });

    }


}


