package com.example.mitienda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
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

public class registro extends AppCompatActivity implements View.OnClickListener {
    RequestQueue requestQueue;
    private SeekBar sbCantHijos;
    private TextView tvCanHijos;
    private EditText etNombres, etApellidos, etDni, etDniH, etnCelular, etCorreo,etNombresH, etEdadH;
    private Spinner mySpinner,mySpinner2;
    private Button btnGuardar;
    FrameLayout flHijo1,flHijo2,flHijo3,flHijo4,flHijo5;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_prueba);

        etNombres =(EditText)findViewById(R.id.etNombres);
        etNombresH =(EditText)findViewById(R.id.etNombresH);
        etApellidos =(EditText)findViewById(R.id.etApellidos);
        etDni =(EditText)findViewById(R.id.etDni);
        etDniH =(EditText)findViewById(R.id.etDniH);
        etnCelular =(EditText)findViewById(R.id.etnCelular);
        etCorreo =(EditText)findViewById(R.id.etCorreo);
        etEdadH=(EditText)findViewById(R.id.etEdadH);
        flHijo1=(FrameLayout) findViewById(R.id.flHijo1);
        flHijo2=(FrameLayout) findViewById(R.id.flHijo2);
        flHijo3=(FrameLayout) findViewById(R.id.flHijo3);
        flHijo4=(FrameLayout) findViewById(R.id.flHijo4);
        flHijo5=(FrameLayout) findViewById(R.id.flHijo5);
        btnGuardar =(Button)findViewById(R.id.btnGuardar) ;
        btnGuardar.setOnClickListener(this);


        mySpinner=(Spinner)findViewById(R.id.spinner1);
        mySpinner2=(Spinner)findViewById(R.id.spinner2);
        sbCantHijos =(SeekBar)findViewById(R.id.sbCantHijos);
        tvCanHijos =(TextView)findViewById(R.id.tvCantHijos);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.sexo));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner2.setAdapter(myAdapter2);

        sbCantHijos.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                tvCanHijos.setText(progress+"");
                if (progress==0){
                    flHijo1.setVisibility(View.INVISIBLE);
                    flHijo2.setVisibility(View.INVISIBLE);
                    flHijo3.setVisibility(View.INVISIBLE);
                    flHijo4.setVisibility(View.INVISIBLE);
                    flHijo5.setVisibility(View.INVISIBLE);
                }
                else if (progress==1){
                    flHijo1.setVisibility(View.VISIBLE);
                    flHijo2.setVisibility(View.INVISIBLE);
                    flHijo3.setVisibility(View.INVISIBLE);
                    flHijo4.setVisibility(View.INVISIBLE);
                    flHijo5.setVisibility(View.INVISIBLE);
                }
                else if (progress==2){
                    flHijo1.setVisibility(View.VISIBLE);
                    flHijo2.setVisibility(View.VISIBLE);
                    flHijo3.setVisibility(View.INVISIBLE);
                    flHijo4.setVisibility(View.INVISIBLE);
                    flHijo5.setVisibility(View.INVISIBLE);
                }
                else if (progress==3){
                    flHijo1.setVisibility(View.VISIBLE);
                    flHijo2.setVisibility(View.VISIBLE);
                    flHijo3.setVisibility(View.VISIBLE);
                    flHijo4.setVisibility(View.INVISIBLE);
                    flHijo5.setVisibility(View.INVISIBLE);
                }
                else if (progress==4){
                    flHijo1.setVisibility(View.VISIBLE);
                    flHijo2.setVisibility(View.VISIBLE);
                    flHijo3.setVisibility(View.VISIBLE);
                    flHijo4.setVisibility(View.VISIBLE);
                    flHijo5.setVisibility(View.INVISIBLE);
                }
                else if (progress==5){
                    flHijo1.setVisibility(View.VISIBLE);
                    flHijo2.setVisibility(View.VISIBLE);
                    flHijo3.setVisibility(View.VISIBLE);
                    flHijo4.setVisibility(View.VISIBLE);
                    flHijo5.setVisibility(View.VISIBLE);
                }
                else{

                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    public void onClick(View v){
        ejecutarServicio1("https://vacunachild.000webhostapp.com/papainsert.php");
        ejecutarServicio2("https://vacunachild.000webhostapp.com/hijoinsert.php");
        //registrarHijo();
        //ejecutarServicio("http://192.168.1.8:8080/vacunaChild/insertar_productoDemo.php");
        //ejecutarServicioDatosMenor("http://192.168.1.8:8080/vacunaChild/insertar_productoDemoH.php");
        Intent obj=new Intent(this,pre_inicio.class);
        obj.putExtra("etDniRegistro",etDni.getText().toString());
        startActivity(obj);
        //switch (v.getId()){
        //case R.id.btnGuardar:
        //Intent intent=new Intent(this,Imprimir.class);
        //intent.putExtra("etNombresI",etNombres.getText().toString());
        //intent.putExtra("etApellidosI",etApellidos.getText().toString());
        //intent.putExtra("etnDniI",etDni.getText().toString());
        //intent.putExtra("etnCelularI",etnCelular.getText().toString());
        //intent.putExtra("etCorreoI",etCorreo.getText().toString());
        //intent.putExtra("etEstudiosI",mySpinner.getSelectedItem().toString());
        //intent.putExtra("etnCantidadHijosI",tvCanHijos.getText().toString());
        //startActivity(intent);
        //break;
        //}
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
                Toast.makeText(getApplicationContext(), "Hay error", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros= new HashMap<String, String>();
                String nombrepadre = etNombres.getText().toString();
                String apellidopadre = etApellidos.getText().toString();
                String dnipadre = etDni.getText().toString();
                String celularpadre = etnCelular.getText().toString();
                String correopadre = etCorreo.getText().toString();
                String estudiospadre = mySpinner.getSelectedItem().toString();
                String canthijos = tvCanHijos.getText().toString();

                parametros.put("nombres", nombrepadre);
                parametros.put("apellidos", apellidopadre);
                parametros.put("dni", dnipadre);
                parametros.put("celular", celularpadre);
                parametros.put("correo", correopadre);
                parametros.put("estudios", estudiospadre);
                parametros.put("cantidadhijos", canthijos);

                return parametros;
            }
        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void ejecutarServicio2(String URL){
        StringRequest stringRequest= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "operación exitosa", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Hay error", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros= new HashMap<String, String>();
                String nombrehijo = etNombresH.getText().toString();
                String dnihijo = etDniH.getText().toString();
                String sexohijo = mySpinner2.getSelectedItem().toString();
                String edadhijo = etEdadH.getText().toString();
                String dnipadre = etDni.getText().toString();

                parametros.put("nombres", nombrehijo);
                parametros.put("dni", dnihijo);
                parametros.put("sexo", sexohijo);
                parametros.put("edad", edadhijo);
                parametros.put("dnipadre", dnipadre);

                return parametros;
            }
        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    /*
    String dniH = etDniH.getText().toString();
    String sexoH = mySpinner2.getSelectedItem().toString();
    String nombresH = etNombresH.getText().toString();
    String edadH = etEdadH.getText().toString();
    String dni = etDni.getText().toString();

     */


}
