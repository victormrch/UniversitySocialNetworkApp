package com.victormramon.universitysocialnetwork.peticionvolley.newfriendsuggested;

import android.app.Activity;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.victormramon.universitysocialnetwork.AddPublicationActivity;
import com.victormramon.universitysocialnetwork.AddRelationships;
import com.victormramon.universitysocialnetwork.R;
import com.victormramon.universitysocialnetwork.modelos.Post;
import com.victormramon.universitysocialnetwork.modelos.Usuario;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PeticionVolleyFriend {

    private Activity context;
    private String url;
    private JSONObject infoPackage;
    private SimpleDateFormat sdt;

    public PeticionVolleyFriend(Activity context, Usuario user, Usuario friendToAdd) {
        this.context = context;
        if (friendToAdd.getId() != null) {
            this.url = context.getString(R.string.ws_addFriend);
        } else {
            this.url = context.getString(R.string.ws_addFriendByEmail);
        }
        infoPackage = this.crearJsonObjectUsuario(user, friendToAdd);

    }

    public void doPostRequestToSave(/*Usuario userToLogin*/) {

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.POST, url, infoPackage,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                //utilizamos esto para el método que construye el intent result
                                AddRelationships activity = (AddRelationships) context;
                                Toast.makeText(context, "¡Nuevo amigo de aventuras!", Toast.LENGTH_LONG)
                                        .show();
                                activity.backToMenu();

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(context, "Mufasa estaba bien solo...",
                                Toast.LENGTH_LONG).show();
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
