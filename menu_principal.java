package com.example.mitienda;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class menu_principal extends AppCompatActivity implements View.OnClickListener {
    LottieAnimationView lavInformacionTotal;
    LottieAnimationView lavCalendarioTotal;
    LottieAnimationView lavCartillaTotal;
    LottieAnimationView lavTutoriales;

    public TextView tvHora,tvUsuarioMenu;
    Date d=new Date();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        tvHora=(TextView) findViewById(R.id.tvHora);
        tvUsuarioMenu=(TextView) findViewById(R.id.tvUsuarioMenu);
        lavInformacionTotal=(LottieAnimationView) findViewById(R.id.lavInformacionTotal);
        lavCalendarioTotal=(LottieAnimationView) findViewById(R.id.lavCalendarioTotal);
        lavCartillaTotal=(LottieAnimationView) findViewById(R.id.lavCartillaTotal);
        lavTutoriales=(LottieAnimationView) findViewById(R.id.lavTutoriales);
        lavInformacionTotal.setOnClickListener(this);
        lavCalendarioTotal.setOnClickListener(this);
        lavCartillaTotal.setOnClickListener(this);
        lavTutoriales.setOnClickListener(this);
        tvHora.setText("00:00:00");
        cargarPreferencias();
        //LEER LA SESSION DESDE MI SHARE PREFERENCE

        //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        /*int name = sharedPreferences.getInt("id", -1);
        SharedPreferences preferencias = getPreferences(MODE_PRIVATE);


        int id2 = sharedPreferences.getInt("id",-1);
        int usuario2 = sharedPreferences.getInt("usuario", -1);
        String contra2 =sharedPreferences.getString("contra", "user");

         */
        //Toast.makeText(this,"id la firme: "+id2+"usuario: "+usuario2+"contra: "+contra2,Toast.LENGTH_LONG).show();

        //System.out.println("Current time => "+c.getTime());
        // formattedDate have current date/time
        miHilo miHilo=new miHilo();
        miHilo.start();

    }
    @Override
    public void onClick(View v) {
        if (v==lavCartillaTotal){
            //Este es el la implementación de Bluetooth
            Intent intent_andBlu=new Intent(this,AndroidBluetooth.class);
            startActivity(intent_andBlu);
        }
        else if (v==lavCalendarioTotal){
            Intent intent_informacion=new Intent(this,Main3Activity.class);
            startActivity(intent_informacion);
        }
        /*MediaPlayer mp = MediaPlayer.create(this,R.raw.blom);
        mp.start();
        miHilo miHilo=new miHilo();
        miHilo.start();
         */
        }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menup,menu);
        return true;
    }
    public void hora(String hora){
        Calendar c = Calendar.getInstance();
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        //Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();
        tvHora.setText(formattedDate);
        if (formattedDate.equals(hora)){
            MediaPlayer mp = MediaPlayer.create(this,R.raw.siete);
            mp.start();
        }
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.nav_acerca:
                Intent intent_acerca=new Intent(this,acerca.class);
                startActivity(intent_acerca);
                return true;
            case R.id.nav_ayuda:
                Intent intent_ayuda=new Intent(this,ayuda.class);
                startActivity(intent_ayuda);
                return true;
            case R.id.nav_paises:
                Intent intent_paises=new Intent(this,paises.class);
                startActivity(intent_paises);
                return true;
            case R.id.nav_servicios:
                Intent intent_servicios=new Intent(this,Registrar_vacuna.class);
                startActivity(intent_servicios);
                return true;
            case R.id.nav_contactanos:
                Intent intent_contactanos=new Intent(this,contactanos.class);
                startActivity(intent_contactanos);
                return true;
            case R.id.nav_calendario:
                Intent intent_calendario=new Intent(this,calendario.class);
                startActivity(intent_calendario);
                return true;
            case R.id.nav_informacion:
                Intent intent_informacion=new Intent(this,informacion.class);
                startActivity(intent_informacion);
                return true;
            case R.id.nav_session:
                SharedPreferences preferences=getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
                int usuario=111;
                String contra="No hay nada";
                SharedPreferences.Editor editor=preferences.edit();
                editor.putInt("usuario", usuario);
                editor.putString("contra", contra);
                editor.commit();
                Intent intent_inicio=new Intent(this,splash.class);
                startActivity(intent_inicio);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
        //hilo creado
    public class miHilo extends Thread{
        public void run(){
            for (int i=1; i<=10;i++){
                try {
                    Thread.sleep(1000);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            hora("18:48:00");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i==10){

                    i=0;
                }
            }
        }
    }
    private void cargarPreferencias() {
        SharedPreferences preferences=getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        int usuario2=preferences.getInt("usuario",111);
        String contra2=preferences.getString("contra","No hay nada");
        tvUsuarioMenu.setText(""+usuario2);

    }


}
