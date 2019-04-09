package com.victormramon.universitysocialnetwork.peticionvolley.suggestion;

import android.app.Activity;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.victormramon.universitysocialnetwork.AddRelationships;
import com.victormramon.universitysocialnetwork.R;
import com.victormramon.universitysocialnetwork.modelos.Usuario;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PeticionVolleySuggestion {

    private Activity context;
    private String url;
    private JSONObject userLogin;

    public PeticionVolleySuggestion(Activity context, Usuario user) {
        this.context = context;
        this.url = context.getString(R.string.ws_suggestion);

        userLogin = this.crearJsonObjectUsuario(user.getId());

        //userLogin = this.crearJsonObjectUsuario("jespana@uma.es", "22222");

    }

    public void getUsuarioVolley(/*Usuario userToLogin*/) {

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.POST, url, userLogin,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //4-04 -> pinta al main activity con el json del usuario que viene del servidor
                                // activity = (Groups) context;
                                //activity.cargarJson(response.toString());
                                AddRelationships activity = (AddRelationships) context;
                                activity.getSuggestionFromResponse(response.toString());
                                Toast.makeText(context, "Petición sugerencias realizada", Toast.LENGTH_LONG)
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
     * necesitamos un Map para crear el JSON object de manera correcta
     *
     * @param id id del usuario
     * @return JSONObject ya creado con los datos
     */
    private JSONObject crearJsonObjectUsuario(Integer id) {
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("id", id);
        return new JSONObject(params);
    }

}
