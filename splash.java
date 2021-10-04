package com.example.mitienda;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class splash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                cargarPreferencias();
            }
        }, 4000);
    }
    private void cargarPreferencias() {
        SharedPreferences preferences=getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        int usuario2=preferences.getInt("usuario",111);
        String contra2=preferences.getString("contra","No hay nada");
        if (usuario2==111){
            Intent intent=new Intent(this,bienvenida.class);
            startActivity(intent);
        }
        else{
            Intent intent=new Intent(this,menu_principal.class);
            startActivity(intent);
            Toast.makeText(this,"Session iniciada con: "+usuario2,Toast.LENGTH_LONG).show();
        }
    }
}
