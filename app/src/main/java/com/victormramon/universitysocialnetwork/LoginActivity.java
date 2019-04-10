package com.victormramon.universitysocialnetwork;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.victormramon.universitysocialnetwork.modelos.Usuario;
import com.victormramon.universitysocialnetwork.peticionvolley.PeticionVolley;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsuario;
    private EditText etContraseña;
    private Button btn;
    private TextView tvBoton;
    private Bundle args;
    private ProgressBar progressBar;
    private Gson gson;
    private SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        prefs = getSharedPreferences(getString(R.string.key_sharedPref), Context.MODE_PRIVATE);

        gson = new GsonBuilder().create();

        prepareView();
    }

    /**
     * recogemos los valores de los editText y gestionamos los eventos de los botones
     */
    private void prepareView() {
        etUsuario = findViewById(R.id.etUsuario);
        etContraseña = findViewById(R.id.etContraseña);
        //progressBar = findViewById(R.id.progressBar);
        btn = findViewById(R.id.btnSession);
        tvBoton = findViewById(R.id.btnRegistro);

        checkSharedPreferences();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserTryToServer();

                //Ocultar progressBar
                findViewById(R.id.progressBar).setVisibility(View.GONE);

            }
        });

        tvBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistroActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * construye el usuario con los editText y lo manda a peticion volley para intentar
     * ver si esta en la base de datos
     */
    private void sendUserTryToServer() {
        Usuario usuario = new Usuario();
        usuario.setEmail(etUsuario.getText().toString());
        usuario.setPassword(etContraseña.getText().toString());

        PeticionVolley get = new PeticionVolley(LoginActivity.this, usuario);
        get.getUsuarioVolley();
    }

    /**
     * comprueba las sharedPreferences e introduce el usuario recibido de la petición al servidor
     * en el bundle para mandarlo a la siguiente activity
     * @param usuario usuario registrado
     */
    public void cargarSiguienteActivity(Usuario usuario) {
        saveUserInSharedPref(usuario);
        //Recuperar como objeto
        args = new Bundle();
        args.putSerializable(getString(R.string.key_userLogged), usuario);
        //Iniciar activityMain pasando los argumentos
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtras(args);
        startActivity(intent);
    }

    /**
     * comprueba si el usuario se ha registrado antes y está en las sharedPreferences
     */
    private void checkSharedPreferences() {
        String email = prefs.getString(getString(R.string.key_emailUserLogged), null);
        if (email != null) {
            String password = prefs.getString(getString(R.string.key_passwordUserLogged), null);
            etUsuario.setText(email);
            etContraseña.setText(password);
        }
    }

    /**
     * comprueba si están las preferences y si no están las guarda
     * @param usuario
     */
    private void saveUserInSharedPref(Usuario usuario) {
        String email = prefs.getString(getString(R.string.key_emailUserLogged), null);
        if (email == null) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(getString(R.string.key_emailUserLogged), usuario.getEmail());
            editor.putString(getString(R.string.key_passwordUserLogged), usuario.getPassword());
            editor.commit();
        }
    }
}
