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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.victormramon.universitysocialnetwork.R;
import com.victormramon.universitysocialnetwork.RegistroActivity;
import com.victormramon.universitysocialnetwork.modelos.Usuario;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistroPeticionVolley {

    private Activity context;
    private String url;
    private JSONObject userLogin;

    public RegistroPeticionVolley(Activity context, Usuario user) {
        this.context = context;
        this.url = context.getString(R.string.ws_register);

        userLogin = this.crearJsonObjectUsuario(user);

    }

    public void getUsuarioVolley() {

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.POST, url, userLogin,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //4-04 -> pinta al main activity con el json del usuario que viene del servidor
                                Usuario user = fromJsonToUsuario(response.toString());
                                RegistroActivity acitivity = (RegistroActivity) context;
                                Toast.makeText(context, "Welcome to the jungle!",
                                        Toast.LENGTH_LONG).show();
                                acitivity.volverActivity(user);

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Ha ocurrido un error en la petición",
                                Toast.LENGTH_LONG).show();
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

    /**
     * necesitamos un Map para crear el JSON object de manera correcta
     *
     * @param user usuario
     * @return JSONObject ya creado con los datos
     */
    private JSONObject crearJsonObjectUsuario(Usuario user) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", user.getEmail());
        params.put("apellidos", user.getApellidos());
        params.put("nombre", user.getNombre());
        params.put("telefono", user.getTelefono());
        params.put("password", user.getPassword());
        return new JSONObject(params);
    }

    private Usuario fromJsonToUsuario(String userJson) {

        Gson gson = new GsonBuilder().create();
        Usuario userLogget = gson.fromJson(userJson, Usuario.class);
        return userLogget;
    }

}

