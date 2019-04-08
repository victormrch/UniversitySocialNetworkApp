package com.victormramon.universitysocialnetwork.peticionvolley;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.victormramon.universitysocialnetwork.LoginActivity;
import com.victormramon.universitysocialnetwork.MainActivity;
import com.victormramon.universitysocialnetwork.R;
import com.victormramon.universitysocialnetwork.modelos.Grupos;
import com.victormramon.universitysocialnetwork.modelos.Usuario;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PeticionVolley {

    private Activity context;
    private String url;
    private JSONObject userLogin;

    public PeticionVolley(Activity context, Usuario user) {
        this.context = context;
        this.url = context.getString(R.string.ws_login);

        userLogin = this.crearJsonObjectUsuario(user.getEmail(), user.getPassword());

    }

    public void getUsuarioVolley(/*Usuario userToLogin*/) {

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.POST, url, userLogin,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //4-04 -> pinta al main activity con el json del usuario que viene del servidor
                                Usuario user = fromJsonToUsuario(response.toString());
                                LoginActivity acitivity = (LoginActivity) context;
                                acitivity.cargarSiguienteActivity(user);
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
     * @param email email del usuario
     * @param password password del usuario
     * @return JSONObject ya creado con los datos
     */
    private JSONObject crearJsonObjectUsuario(String email, String password) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);
        params.put("password", password);
        return new JSONObject(params);
    }

    private Usuario fromJsonToUsuario(String userJson) {

        Gson gson = new GsonBuilder().create();
        Usuario userLogget = gson.fromJson(userJson, Usuario.class);
        return userLogget;
    }

}
