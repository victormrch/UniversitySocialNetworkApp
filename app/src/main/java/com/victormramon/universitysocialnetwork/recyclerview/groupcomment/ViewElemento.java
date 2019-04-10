package com.victormramon.universitysocialnetwork.recyclerview.groupcomment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.victormramon.universitysocialnetwork.R;
import com.victormramon.universitysocialnetwork.modelos.ComentarioGrupo;

import java.text.SimpleDateFormat;

public class ViewElemento extends RecyclerView.ViewHolder {

    private TextView tvContent;
    private TextView tvDate;
    private TextView tvPublisher;
    private Object item;

    public ViewElemento(@NonNull final View itemView) {
        super(itemView);
        this.tvContent = itemView.findViewById(R.id.tvGroupComment);
        this.tvDate = itemView.findViewById(R.id.tvDateComment);
        this.tvPublisher = itemView.findViewById(R.id.tvUserComment);

    }


    public void setUpHolder(ComentarioGrupo item) {
        tvContent.setText(item.getComentario());
        SimpleDateFormat sdt = new SimpleDateFormat("dd-MM-YYYY");
        tvDate.setText(sdt.format(item.getFecha()));

        // tvPublisher.setText(item.getIdAutor().getEmail());

    }
}
