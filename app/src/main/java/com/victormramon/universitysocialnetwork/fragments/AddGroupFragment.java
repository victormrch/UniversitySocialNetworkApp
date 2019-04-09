package com.victormramon.universitysocialnetwork.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.victormramon.universitysocialnetwork.R;
import com.victormramon.universitysocialnetwork.modelos.EncapsularInfoPost;
import com.victormramon.universitysocialnetwork.modelos.Grupos;
import com.victormramon.universitysocialnetwork.modelos.Sugerencias;
import com.victormramon.universitysocialnetwork.modelos.Usuario;
import com.victormramon.universitysocialnetwork.peticionvolley.PeticionVolleyCreateGroups;
import com.victormramon.universitysocialnetwork.recyclerview.suggestions.SuggestionRecyclerAdapter;

import java.io.Serializable;

public class AddGroupFragment extends Fragment implements Serializable {

    private EditText nombreGrupo;
    private Button btn;
    private Sugerencias suggestion;
    private Activity actividad;
    private Usuario user;
    //private Activity activ;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.groups_frag, container, false);
        prepare(view);
        return view;

    }

    private void prepare(View v) {
        getBundleFromArgument();
        SuggestionRecyclerAdapter rvAdapter = new SuggestionRecyclerAdapter
                (R.layout.item_suggested, suggestion.getGroupsSuggested());
        RecyclerView recView = v.findViewById(R.id.rvSuggested);
        recView.setHasFixedSize(true);
        recView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recView.setAdapter(rvAdapter);
        //PeticionVolleySuggestion volleySuggestion = new PeticionVolleySuggestion(getActivity(), user);
        //volleySuggestion.getUsuarioVolley();

        btn = v.findViewById(R.id.crear_grupo);
        nombreGrupo = v.findViewById(R.id.etSearchFriends);
        actividad = getActivity();
        user = (Usuario) getArguments().getSerializable(getString(R.string.key_userLogged));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Grupos g = new Grupos();
                EncapsularInfoPost grupoCap = new EncapsularInfoPost();
                g.setNombre(nombreGrupo.getText().toString());
                grupoCap.setGrupo(g);

                PeticionVolleyCreateGroups post = new PeticionVolleyCreateGroups(actividad, grupoCap, user);
                post.doPostRequestToSave();


            }
        });
    }

    private void getBundleFromArgument() {
        this.suggestion = (Sugerencias) this.getArguments()
                .getSerializable(getString(R.string.key_suggestion));

    }

}
