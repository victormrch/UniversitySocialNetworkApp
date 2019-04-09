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

import com.victormramon.universitysocialnetwork.R;
import com.victormramon.universitysocialnetwork.modelos.Sugerencias;
import com.victormramon.universitysocialnetwork.recyclerview.suggestions.SuggestionRecyclerAdapter;

public class AddFriendFragment extends Fragment {

    private Sugerencias suggestion;
    private Activity activ;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.friends_frag, container, false);
        prepare(rootView);
        return rootView;
    }

    private void prepare(View v) {
        getBundleFromArgument();
        SuggestionRecyclerAdapter rvAdapter = new SuggestionRecyclerAdapter
                (R.layout.item_suggested, suggestion.getFriendsSuggested());
        RecyclerView recView = v.findViewById(R.id.rvSuggested);
        recView.setHasFixedSize(true);
        recView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recView.setAdapter(rvAdapter);
        //PeticionVolleySuggestion volleySuggestion = new PeticionVolleySuggestion(getActivity(), user);
        //volleySuggestion.getUsuarioVolley();
    }

    private void getBundleFromArgument() {
        this.suggestion = (Sugerencias) this.getArguments()
                .getSerializable(getString(R.string.key_suggestion));

    }
}
