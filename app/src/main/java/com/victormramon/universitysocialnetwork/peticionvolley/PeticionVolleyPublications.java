package com.victormramon.universitysocialnetwork.peticionvolley;

import android.app.Activity;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.victormramon.universitysocialnetwork.MainActivity;
import com.victormramon.universitysocialnetwork.R;
import com.victormramon.universitysocialnetwork.modelos.Post;
import com.victormramon.universitysocialnetwork.modelos.Usuario;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PeticionVolleyPublications {

    private Activity context;
    private String url;
    private JSONObject userLogin;
    private SimpleDateFormat sdt;

    public PeticionVolleyPublications(Activity context, Usuario user, Post post) {
        this.context = context;
        this.url = context.getString(R.string.ws_safe_post);
        userLogin = this.crearJsonObjectUsuario(user, post);

    }

    public void doPostRequestToSave(/*Usuario userToLogin*/) {

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.POST, url, userLogin,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //4-04 -> pinta al main activity con el json del usuario que viene del servidor
                                MainActivity activity = (MainActivity) context;
                                //activity.cargarJson(response.toString());
                                Toast.makeText(context, "La peticion ha ido bien", Toast.LENGTH_LONG)
                                        .show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Ha ocurrido un error en la petición",
                                Toast.LENGTH_LONG);
                    }


                }
                );

        queue.add(jsonObjectRequest);
    }

    /**
     *
     * @param usuario
     * @param post
     * @return
     */
    private JSONObject crearJsonObjectUsuario(Usuario usuario, Post post) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("idUsuario", usuario.getId());

        Map<String,Object> paramsPost = new HashMap<String, Object>();
        paramsPost.put("contenido", post.getContenido());
        paramsPost.put("fecha", selectDateFormat(post.getFecha()));
        paramsPost.put("idPublicador", post.getIdPublicador().getId());

        params.put("post", paramsPost);

        return new JSONObject(params);
    }

    private String selectDateFormat(Date date) {
        sdt = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        return sdt.format(date);
    }



}
