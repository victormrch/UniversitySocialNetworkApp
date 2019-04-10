package com.victormramon.universitysocialnetwork.peticionvolley;

import android.app.Activity;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.victormramon.universitysocialnetwork.AddRelationships;
import com.victormramon.universitysocialnetwork.R;
import com.victormramon.universitysocialnetwork.modelos.EncapsularInfoPost;
import com.victormramon.universitysocialnetwork.modelos.Usuario;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PeticionVolleyFollowGroup {

    private Activity context;
    private String url;
    private JSONObject grup;
    private SimpleDateFormat sdt;

    public PeticionVolleyFollowGroup(Activity context, EncapsularInfoPost groups, Usuario usuario) {
        this.context = context;
        this.url = context.getString(R.string.ws_create_group);
        grup = this.crearJsonObjectGrupo(groups,usuario);

    }

    public void doPostRequestToSaveFollow() {

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.POST, url, grup,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //4-04 -> pinta al main activity con el json del usuario que viene del servidor
                                AddRelationships activity = (AddRelationships) context;
                                //activity.cargarJson(response.toString());
                                Toast.makeText(context, "La peticion ha ido bien", Toast.LENGTH_LONG)
                                        .show();
                                activity.onSavePostServerResult();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(context, "Ha ocurrido un error en la petición",
                                Toast.LENGTH_LONG);
                    }


                }
                );

        //queue.add(jsonObjectRequest);
        queue.add(jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )));

    }

    private JSONObject crearJsonObjectGrupo(EncapsularInfoPost grupos, Usuario usuario) {
        Map<String, Object> paramsGroup = new HashMap<String, Object>();
        paramsGroup.put("nombre", grupos.getGrupo().getNombre());

        Map<String,Object> paramsEncapsular = new HashMap<String, Object>();
        paramsEncapsular.put("idUsuario", usuario.getId());

        paramsEncapsular.put("grupo", paramsGroup);

        return new JSONObject(paramsEncapsular);
    }


    private String selectDateFormat(Date date) {
        sdt = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        return sdt.format(date);
    }

}
