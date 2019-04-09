package com.victormramon.universitysocialnetwork;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.victormramon.universitysocialnetwork.modelos.Grupos;
import com.victormramon.universitysocialnetwork.recyclerview.groupcomment.GroupDetailedCommentRecyclerAdapter;

public class GroupDetailed extends AppCompatActivity {

    private TextView tvNombre;
    private TextView tvIntegrantes;
    private Grupos groupSelected;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_detail);

        prepareView();
    }

    private void prepareView() {
        getGroupFromIntent();
        tvNombre = findViewById(R.id.tvNameGroupDetail);
        tvNombre.setText(groupSelected.getNombre());
        tvIntegrantes = findViewById(R.id.tvNumberUsersGroupDetail);
        tvIntegrantes.setText(Integer.toString(groupSelected.getGroupSize()));
        chargeRv();

    }

    private void chargeRv() {
        GroupDetailedCommentRecyclerAdapter rvAdapter = new GroupDetailedCommentRecyclerAdapter
                (R.layout.item_new_publication, groupSelected.getComentarioGrupoList());
        RecyclerView recView = findViewById(R.id.rvGroupsComment);
        recView.setHasFixedSize(true);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(rvAdapter);


    }

    private void getGroupFromIntent() {
        this.groupSelected = (Grupos) getIntent().getExtras()
                .getSerializable(getString(R.string.key_groupSelected));
    }


}
