package com.example.mitienda;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.mitienda.fragments.MisionFragment;
import com.example.mitienda.fragments.QuinesSomosFragment;
import com.example.mitienda.fragments.VisionFragment;

public class acerca extends AppCompatActivity implements View.OnClickListener {
    RadioButton rbQuienesSomos;
    RadioButton rbMision;
    RadioButton rbVision;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca);


        rbQuienesSomos=(RadioButton)findViewById(R.id.rbQuienesSomos);
        rbMision=(RadioButton)findViewById(R.id.rbMision);
        rbVision=(RadioButton)findViewById(R.id.rbVision);

        rbQuienesSomos.setOnClickListener(this);
        rbMision.setOnClickListener(this);
        rbVision.setOnClickListener(this);

        mostrarQuienesSomos();
        rbQuienesSomos.isChecked();
    }


    @Override
    public void onClick(View v) {
        RadioButton rb = (RadioButton) v;
        switch (rb.getId()){
            case R.id.rbQuienesSomos:
                mostrarQuienesSomos();
                break;
            case R.id.rbMision:
                mostrarMision();
                break;
            case R.id.rbVision:
                mostrarVision();
                break;
        }
    }
    private void mostrarQuienesSomos() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        QuinesSomosFragment fragment = new QuinesSomosFragment();
        fragmentManager.beginTransaction().replace(R.id.contenedor_acerca_de,fragment).commit();
    }

    private void mostrarMision() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        MisionFragment fragment = new MisionFragment();
        fragmentManager.beginTransaction().replace(R.id.contenedor_acerca_de,fragment)
                .commit();
    }


    private void mostrarVision() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        VisionFragment fragment = new VisionFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.contenedor_acerca_de,fragment)
                .commit();
    }
}
