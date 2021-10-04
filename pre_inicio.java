package com.example.mitienda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class pre_inicio extends AppCompatActivity implements View.OnClickListener{
    RequestQueue requestQueue;
    EditText etDni, etContra1, etContra2;
    String contraFinal;
    String contra1;
    String contra2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_inicio);
        etDni=(EditText)findViewById(R.id.etDni);
        etContra1=(EditText)findViewById(R.id.etContra1);
        etContra2=(EditText)findViewById(R.id.etContra2);
        Button btnRegresar;
        btnRegresar=(Button)findViewById(R.id.btnGuardar);
        btnRegresar.setOnClickListener(this);
        cargarDatosDni();


    }

    @Override
    public void onClick(View v){
        contra1=etContra1.getText().toString();
        contra2=etContra2.getText().toString();
        if (contra1.equals(contra2)){
            contraFinal=etContra1.getText().toString();
                    ejecutarServicio1("https://vacunachild.000webhostapp.com/usuarioinsert.php");
            //ejecutarAlmacenUsuario("http://192.168.1.8:8080/vacunaChild/insertar_Usuario.php");
            //ejecutarAlmacenUsuario("http://gredisflex.byethost7.com/php/insertar_Byte.php");
            // este es de forma remota ejecutarAlmacenUsuario("https://vacunachild.000webhostapp.com/insertar_Byte.php");
            Intent obj=new Intent(this,inicio.class);
            obj.putExtra("etDni",123);
            obj.putExtra("etDniPre",Integer.parseInt(etDni.getText().toString()));
            startActivity(obj);
            Toast.makeText(getApplicationContext(), "Inicie session ahora", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "LAS CONTRASEÑAS NO SON IGUALES", Toast.LENGTH_LONG).show();
        }

    }
    private void ejecutarServicio1(String URL){
        StringRequest stringRequest= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "operación exitosa", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Hay un error", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros= new HashMap<String, String>();
                String dniusuario = etDni.getText().toString();
                String contrausuario = contraFinal;
                parametros.put("dni", dniusuario);
                parametros.put("contra", contrausuario);

                return parametros;
            }
        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void cargarDatosDni() {
        Bundle datos=this.getIntent().getExtras();
        etDni.setText(datos.getString("etDniRegistro"));

    }
}
