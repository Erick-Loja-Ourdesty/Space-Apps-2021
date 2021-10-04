package com.example.mitienda;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mitienda.dao.UsuarioDAOImpl;
import com.example.mitienda.entidad.Usuario;

import org.json.JSONArray;
import org.json.JSONException;


public class inicio extends AppCompatActivity implements View.OnClickListener {
    RequestQueue requestQueue;
    EditText etDni, etcontra1;
    Button btnGuardar;
    int datoDni=123;
    int datoDniPre=123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        etDni = (EditText) findViewById(R.id.etDni);
        etcontra1 = (EditText) findViewById(R.id.etContra1);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(this);
        cargarDatosDni();
        cargarPreferencias();
    }



    @Override
    public void onClick(View v) {
        if (v==btnGuardar){
            encontrarUsuarioServer();
        }
        else{
            Toast.makeText(this, "Acción no admitida", Toast.LENGTH_SHORT).show();
        }

        // TRABAJO REMOTO encontrarUsuario("http://192.168.1.8:8080/vacunaChild/encontrar_usuario.php?dni="+etDni.getText());
    }

    private void encontrarUsuarioServer() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        //String url ="https://vacunachild.000webhostapp.com/visitasinsert.php?nombres=rara&correoelectronico=ra@gmail.com&comentario=ra&edad=25";
        String url ="https://vacunachild.000webhostapp.com/usuariosselect.php";


        // Request a string response from the provided URL.
        //StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("Datos",response);
                        try {
                            corroborar(response);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Datos", "error " );
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
    UsuarioDAOImpl dao=new UsuarioDAOImpl(this);
    private void corroborar(String response) throws JSONException {
        JSONArray ja=new JSONArray(response);
        String[] dni = new String[ja.length()];
        String[] contra = new String[ja.length()];

        for (int i = 0; i < ja.length(); i++) {
            if (ja.getJSONObject(i).getString("dni").equals(etDni.getText().toString())&&ja.getJSONObject(i).getString("contra").equals(etcontra1.getText().toString())){
                //tvCorrectos.setText("Click nuevamente");
                Intent menu = new Intent(this, menu_principal.class);

                Usuario bean=new Usuario();
                bean.setId(Integer.parseInt("0"));
                bean.setUsuario(Integer.parseInt(etDni.getText().toString()));
                bean.setPassword(etcontra1.getText().toString());
                dao.actualizar(bean);
                Toast.makeText(this, "ID: "+bean.getId(), Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "usuario: "+bean.getUsuario(), Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "contra: "+bean.getPassword(), Toast.LENGTH_SHORT).show();
                guardarPreferencias();
                //ALMACENAR SESSION EN EL SHARE PREFERENCE
                /*SharedPreferences preferencias= getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencias.edit();
                editor.putInt("id", 0);
                editor.putInt("usuario", Integer.parseInt(etDni.getText().toString()));
                editor.putString("contra", etcontra1.getText().toString());
                editor.commit();


                Toast.makeText(this,""+preferencias.getInt("usuario",999),Toast.LENGTH_SHORT).show();
                //menu.putExtra("m",bean);
                 */
                startActivity(menu);
                Toast.makeText(this, "Bienvenido: "+etDni.getText().toString(), Toast.LENGTH_SHORT).show();
            }
            else{
            }
        }
        /*for (int i = 0; i < ja.length(); i++) {
            contra[i] = ja.getJSONObject(i).getString("contra");
        }
         */
    }
    private void cargarPreferencias() {
        SharedPreferences preferences=getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        int usuario2=preferences.getInt("usuario",111);
        String contra2=preferences.getString("contra","No hay nada");
    }
    private void guardarPreferencias() {
        SharedPreferences preferences=getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        int usuario=Integer.parseInt(etDni.getText().toString());
        String contra=etcontra1.getText().toString();
        SharedPreferences.Editor editor=preferences.edit();
        editor.putInt("usuario", usuario);
        editor.putString("contra", contra);
        editor.commit();
    }

    public void registro(View v) {
        Intent obj = new Intent(this, registro.class);
        startActivity(obj);
    }

    private void cargarDatosDni() {

        Bundle datos=this.getIntent().getExtras();
        datoDni=datos.getInt("etDni");
        datoDniPre=datos.getInt("etDniPre");
        if (datoDni==123){
            Toast.makeText(this,"Usuario no registrado en celular",Toast.LENGTH_LONG).show();
        }
        if(datoDniPre==123){
            Toast.makeText(this,"No funciona",Toast.LENGTH_LONG).show();
        }
        else{
            etDni.setText(String.valueOf(datoDniPre));
        }

    }



}
