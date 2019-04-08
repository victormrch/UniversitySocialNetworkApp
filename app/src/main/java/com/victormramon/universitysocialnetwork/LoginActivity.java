package com.victormramon.universitysocialnetwork;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        gson = new GsonBuilder().create();

        etUsuario = findViewById(R.id.etUsuario);
        etContraseña = findViewById(R.id.etContraseña);
        progressBar = findViewById(R.id.progressBar);
        btn = findViewById(R.id.btnSession);
        tvBoton = findViewById(R.id.btnRegistro);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario();
                usuario.setEmail(etUsuario.getText().toString());
                usuario.setPassword(etContraseña.getText().toString());

                PeticionVolley get = new PeticionVolley(LoginActivity.this, usuario);
                get.getUsuarioVolley();

                //Ocultar progressBar
                 progressBar.setVisibility(View.GONE);

            }
        });

        tvBoton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegistroActivity.class);
                startActivity(intent);
            }
        });
    }

    public void cargarSiguienteActivity(Usuario usuario){
        //Recuperar como objeto
        args = new Bundle();
        args.putSerializable("usuario",usuario);
        //Iniciar activityMain pasando los argumentos
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.putExtras(args);
        startActivity(intent);
    }

}
