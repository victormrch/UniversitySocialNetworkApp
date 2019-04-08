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

public class RegistroActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etApellidos;
    private EditText etNombre;
    private EditText etTelefono;
    private EditText etContraseña;
   // private EditText etContraseña2;
   // private Button btni;
    private Button btnr;
    private TextView tvVolver;
    private ProgressBar progressBar;
    private Bundle args;
    private Gson gson;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_main);

        gson = new GsonBuilder().create();

        etEmail = findViewById(R.id.etEmail);
        etApellidos = findViewById(R.id.etApellidos);
        etNombre = findViewById(R.id.etNombre);
        etTelefono = findViewById(R.id.etTelefono);
        etContraseña = findViewById(R.id.etContraseña);
       // etContraseña2 = findViewById(R.id.etContraseña2);
        // btni = findViewById(R.id.btnImagen);
        btnr = findViewById(R.id.btnRegistro);
        tvVolver = findViewById(R.id.tvVolver);

        btnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario();
                usuario.setEmail(etEmail.getText().toString());
                usuario.setApellidos(etApellidos.getText().toString());
                usuario.setNombre(etNombre.getText().toString());
                usuario.setTelefono(etTelefono.getText().toString());
                usuario.setPassword(etContraseña.getText().toString());
                //usuario.setPassword(etContraseña2.getText().toString());
                //if(etContraseña = etContraseña2){
                PeticionVolley get = new PeticionVolley(RegistroActivity.this, usuario);
                get.getUsuarioVolley();//}

                //Ocultar progressBar
                progressBar.setVisibility(View.GONE);

            }
        });

        tvVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    public void volverActivity(Usuario usuario){
        //Recuperar como objeto
        args = new Bundle();
        args.putSerializable("usuario",usuario);
        //Iniciar activityMain pasando los argumentos
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        intent.putExtras(args);
        startActivity(intent);
    }

}
