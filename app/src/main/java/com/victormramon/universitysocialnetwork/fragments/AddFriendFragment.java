package com.victormramon.universitysocialnetwork.fragments;

import android.app.Activity;
import android.content.Context;
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
import com.victormramon.universitysocialnetwork.callback.CallbackRelationship;
import com.victormramon.universitysocialnetwork.callback.CallbackRelationship;
import com.victormramon.universitysocialnetwork.modelos.Sugerencias;
import com.victormramon.universitysocialnetwork.modelos.Usuario;
import com.victormramon.universitysocialnetwork.recyclerview.suggestions.SuggestionRecyclerAdapter;

public class AddFriendFragment extends Fragment {

    private Sugerencias suggestion;
    private Activity activ;
    private static CallbackRelationship callback;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.friends_frag, container, false);
        prepare(rootView);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        callback = (CallbackRelationship) context;
    }

    /**
     * Ejecuta métodos necesarios al mostrar la vista (cargar rv, establecer listener etc)
     * @param v
     */
    private void prepare(View v) {
        getBundleFromArgument();
        prepareBtnSendFriend(v);

        SuggestionRecyclerAdapter rvAdapter = new SuggestionRecyclerAdapter
                (R.layout.item_suggested, suggestion.getFriendsSuggested());
        RecyclerView recView = v.findViewById(R.id.rvSuggested);
        recView.setHasFixedSize(true);
        recView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recView.setAdapter(rvAdapter);

    }

    /**
     * establece el listener al botón de enviar email de usuario a añadir
     * @param view
     */
    private void prepareBtnSendFriend(final View view) {
        Button btnSend = view.findViewById(R.id.btnSendFriendEmail);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etEmail = view.findViewById(R.id.etSearchFriends);
                Usuario friendToSearch = new Usuario();
                friendToSearch.setEmail(etEmail.getText().toString());
                callback.onFriendClick(friendToSearch);
            }
        });
    }

    /**
     * recoge las sugerencias del bundle
     */
    private void getBundleFromArgument() {
        this.suggestion = (Sugerencias) this.getArguments()
                .getSerializable(getString(R.string.key_suggestion));

    }

    /**
     * llama a la función callback implementada en el activity que carga en fragment
     * @param itemClicked
     */
    public static void onFriendClickListener(Usuario itemClicked) {
        callback.onFriendClick(itemClicked);

    }
}
