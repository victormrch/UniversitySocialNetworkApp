package com.victormramon.universitysocialnetwork.recyclerview.recyclerviewpost;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.victormramon.universitysocialnetwork.R;
import com.victormramon.universitysocialnetwork.modelos.Post;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewElemento extends RecyclerView.ViewHolder {

    private TextView texto;
    private TextView fecha;

    public ViewElemento(View v) {
        super(v);
        this.texto = v.findViewById(R.id.tvPost);
        this.fecha = v.findViewById(R.id.tvFechaPost);

    }

    public void setUpHolder(Post item) {

        texto.setText(item.getContenido());
        String fecha = parsearFecha(item.getFecha());
        this.fecha.setText(fecha);
    }

    public static String parsearFecha(Date fecha) {
        try {
            //Cambio de formato
            String fechaParseada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(fecha);

            return fechaParseada;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;

    }

}
