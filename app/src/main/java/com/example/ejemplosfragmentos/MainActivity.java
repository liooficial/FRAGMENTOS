package com.example.ejemplosfragmentos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements mimenu{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void miMenus(int miBoton) {
        Intent intent = new Intent(MainActivity.this,miHerramienta.class);
        intent.putExtra("botonpul", miBoton);
        startActivity(intent);
    }
}