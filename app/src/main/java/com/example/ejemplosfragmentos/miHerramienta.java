package com.example.ejemplosfragmentos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class miHerramienta extends AppCompatActivity implements mimenu {

    Fragment [] misFragmentos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_herramienta);
        misFragmentos=new Fragment[3];
        misFragmentos[0]=new interna();
        misFragmentos[1]=new musica();
        misFragmentos[2]=new nivel();

        Bundle extra=getIntent().getExtras();
        miMenus(extra.getInt("botonpul"));
    }

    @Override
    public void miMenus(int miBoton) {
        FragmentManager mimanejador=getSupportFragmentManager();
        FragmentTransaction mitransaccion=mimanejador.beginTransaction();
        Fragment menuIluminado=new Menu();
        Bundle datos = new Bundle();
        datos.putInt("botonpulsado2",miBoton);
        menuIluminado.setArguments(datos);
        mitransaccion.replace(R.id.menu2,menuIluminado);
        mitransaccion.replace(R.id.contenedor,misFragmentos[miBoton]);
        mitransaccion.commit();
    }
}