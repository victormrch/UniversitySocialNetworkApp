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
import com.victormramon.universitysocialnetwork.Friends;
import com.victormramon.universitysocialnetwork.Groups;
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

    public PeticionVolley(Activity context) {
        this.context = context;
        this.url = context.getString(R.string.ws_login);

        userLogin = this.crearJsonObjectUsuario("jespana@uma.es", "22222");

    }

    public void getUsuarioVolley(/*Usuario userToLogin*/) {

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest =
            new JsonObjectRequest(Request.Method.POST, url, userLogin,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Groups activity = (Groups) context;
                            activity.cargarJson(response.toString());
                            Toast.makeText(context, "Usuario logeado", Toast.LENGTH_LONG)
                                    .show();
                            /*
                            //8-04 -> hace login y con el id de usuario pide las sugerencias
                            AddRelationships activity = (AddRelationships) context;
                            Usuario user = activity.getUserFromResponse(response.toString());
                            if (activity instanceof AddRelationships) {
                                activity.getSuggestion(user);
                            }
                            Toast.makeText(context, "Usuario logeado", Toast.LENGTH_LONG)
                                    .show();
                        }
                        */
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

}
