package com.example.ejemplosfragmentos;

import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.ejemplosfragmentos.R;


public class musica extends Fragment {
    private ImageView imageView;
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;

    public musica() {
        // Constructor público requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View view = inflater.inflate(R.layout.fragment_musica, container, false);

        // Obtener referencia de la ImageView
        imageView = view.findViewById(R.id.musica);

        // Agregar listener de clic a la ImageView
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    pausarMusica();
                } else {
                    reproducirMusica();
                }
            }
        });

        return view;
    }

    private void reproducirMusica() {
        // Crear instancia de MediaPlayer y asignar el archivo de música
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.oye);

        // Iniciar reproducción de música
        mediaPlayer.start();

        // Cambiar la imagen a pausa
        imageView.setImageResource(R.drawable.musica2);

        isPlaying = true;
    }

    private void pausarMusica() {
        // Pausar reproducción de música
        mediaPlayer.pause();

        // Cambiar la imagen a reproducir
        imageView.setImageResource(R.drawable.musica);

        isPlaying = false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // Liberar los recursos del MediaPlayer
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    }
