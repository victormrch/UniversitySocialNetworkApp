package com.victormramon.universitysocialnetwork.peticionvolley;

import android.app.Activity;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.victormramon.universitysocialnetwork.AddRelationships;
import com.victormramon.universitysocialnetwork.MainActivity;
import com.victormramon.universitysocialnetwork.R;
import com.victormramon.universitysocialnetwork.modelos.EncapsularInfoPost;
import com.victormramon.universitysocialnetwork.modelos.Grupos;
import com.victormramon.universitysocialnetwork.modelos.Post;
import com.victormramon.universitysocialnetwork.modelos.Usuario;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PeticionVolleyCreateGroups {

    private Activity context;
    private String url;
    private JSONObject grup;
    private SimpleDateFormat sdt;

    public PeticionVolleyCreateGroups(Activity context, EncapsularInfoPost groups,Usuario usuario) {
        this.context = context;
        this.url = context.getString(R.string.ws_create_group);
        grup = this.crearJsonObjectGrupo(groups,usuario);

    }
    public void doPostRequestToSave() {

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
                        Toast.makeText(context, "Ha ocurrido un error en la petición",
                                Toast.LENGTH_LONG);
                    }


                }
                );

        queue.add(jsonObjectRequest);
    }

    private JSONObject crearJsonObjectGrupo(EncapsularInfoPost grupos,Usuario usuario) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("nombre", grupos.getGrupo().getNombre());

        Map<String,Object> paramsUsuario = new HashMap<String, Object>();
        paramsUsuario.put("id", usuario.getId());

        params.put("usuario", paramsUsuario);

        return new JSONObject(params);
    }


    private String selectDateFormat(Date date) {
        sdt = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        return sdt.format(date);
    }


}


