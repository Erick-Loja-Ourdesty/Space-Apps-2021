package com.example.mitienda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mitienda.dao.UsuarioDAOImpl;
import com.example.mitienda.entidad.Usuario;


public class DatosActivity extends AppCompatActivity implements View.OnClickListener {
Button btnRegresarD,btnActualizarD,btnEliminarD;
TextView tvCodigoD;
EditText edtUsuarioD,edtContraD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
        tvCodigoD=(TextView) findViewById(R.id.tvIdAlarmaD);
        edtContraD=(EditText) findViewById(R.id.edtContraD);
        edtUsuarioD=(EditText) findViewById(R.id.edtUsuarioD);
        tvCodigoD=(TextView) findViewById(R.id.tvIdAlarmaD);

        btnRegresarD=(Button)findViewById(R.id.btnRegresarD);
        btnActualizarD=(Button)findViewById(R.id.btnActualizarD);
        btnEliminarD=(Button)findViewById(R.id.btnEliminarD);
        btnRegresarD.setOnClickListener(this);
        btnActualizarD.setOnClickListener(this);
        btnEliminarD.setOnClickListener(this);
        cargarD();
    }

    private void cargarD() {
        Usuario us=(Usuario)getIntent().getSerializableExtra("m");
        tvCodigoD.setText(""+us.getId());
        edtUsuarioD.setText(us.getUsuario());
        edtContraD.setText(us.getPassword());

    }

    @Override
    public void onClick(View v) {
        UsuarioDAOImpl dao=new UsuarioDAOImpl(this);
        if (v==btnRegresarD){
            Intent intent=new Intent(this,Main2Activity.class);
            startActivity(intent);
        }
        if (v==btnActualizarD){
            Usuario bean=new Usuario();
            bean.setId(Integer.parseInt(tvCodigoD.getText().toString()));
            bean.setUsuario(Integer.parseInt(edtUsuarioD.getText().toString()));
            bean.setPassword(edtContraD.getText().toString());
            dao.actualizar(bean);
        }
        if (v==btnEliminarD){
            int codigo;
            codigo=Integer.parseInt(tvCodigoD.getText().toString());
            dao.eliminar(codigo);
        }

    }
}
