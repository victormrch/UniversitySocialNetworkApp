package com.victormramon.universitysocialnetwork.peticionvolley.deletefriend;

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
import com.victormramon.universitysocialnetwork.FriendDetailed;
import com.victormramon.universitysocialnetwork.R;
import com.victormramon.universitysocialnetwork.modelos.Usuario;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class PeticionVolleyUnfollowFriend {

    private Activity context;
    private String url;
    private JSONObject infoPackage;
    private SimpleDateFormat sdt;

    public PeticionVolleyUnfollowFriend(Activity context, Usuario user, Usuario friendToAdd) {
        this.context = context;
        this.url = context.getString(R.string.ws_deleteFriend);

        infoPackage = this.crearJsonObjectUsuario(user, friendToAdd);

    }

    public void doDeleteFriendRequest(/*Usuario userToLogin*/) {

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.POST, url, infoPackage,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                //utilizamos esto para el método que construye el intent result
                                FriendDetailed activity = (FriendDetailed) context;
                                Toast.makeText(context, "La peticion ha ido bien", Toast.LENGTH_LONG)
                                        .show();
                                activity.backToMenu();
                                //activity.onSavePostServerResult();
                                //activity.cargarJson(response.toString());

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

        queue.add(jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )));
    }

    /**
     * Crear un JSONObject para enviarlo
     * @param usuario
     * @param friendToAdd
     * @return
     */
    private JSONObject crearJsonObjectUsuario(Usuario usuario, Usuario friendToAdd) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("idUsuario", usuario.getId());
        if (friendToAdd.getId() != null) {
            params.put("idAmigo", friendToAdd.getId());
        } else {
            params.put("emailAmigo", friendToAdd.getEmail());
        }

        return new JSONObject(params);
    }





}
