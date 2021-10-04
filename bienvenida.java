package com.example.mitienda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mitienda.dao.UsuarioDAOImpl;
import com.example.mitienda.entidad.Usuario;

import java.util.ArrayList;

public class bienvenida extends AppCompatActivity implements View.OnClickListener {
    Button btnInicioUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);
        Button btnInicio;
        btnInicioUsuario=(Button)findViewById(R.id.btnInicioUsuario);
        btnInicioUsuario.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==btnInicioUsuario) {
            Intent  intent=new Intent(this,inicio.class);
            UsuarioDAOImpl dao=new UsuarioDAOImpl(this);
            ArrayList<Usuario> usuario= dao.listaUsuario();
            //intent.putExtra("etDni",usuario.get(0).getUsuario());
            intent.putExtra("etDni",usuario.get(0).getUsuario());
            intent.putExtra("etDniPre",123);
            startActivity(intent);
        }
    }
}
