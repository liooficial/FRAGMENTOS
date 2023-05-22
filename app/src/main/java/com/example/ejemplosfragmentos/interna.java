package com.example.ejemplosfragmentos;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.content.Context;
import android.content.pm.PackageManager;


public class interna extends Fragment {


        private ImageView imageView;
        private boolean isFlashOn = false;
        private CameraManager cameraManager;
        private String cameraId;

        public interna() {
            // Constructor vacío requerido
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_interna, container, false);

            imageView = view.findViewById(R.id.linterna);
            cameraManager = (CameraManager) getActivity().getSystemService(Context.CAMERA_SERVICE);

            // Verificar si el dispositivo tiene flash
            boolean hasFlash = getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

            // Ocultar la imagen si el dispositivo no tiene flash
            if (!hasFlash) {
                imageView.setVisibility(View.GONE);
            } else {
                // Obtener el ID de la cámara trasera (flash)
                try {
                    cameraId = cameraManager.getCameraIdList()[0];
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }

                // Asignar el controlador de clics a la imagen
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toggleFlashlight();
                    }
                });
            }

            return view;
        }

        private void toggleFlashlight() {
            try {
                if (isFlashOn) {
                    turnOffFlashlight();
                } else {
                    turnOnFlashlight();
                }
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

        private void turnOnFlashlight() throws CameraAccessException {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, true);
                isFlashOn = true;
                imageView.setImageResource(R.drawable.linterna2);
            }
        }

        private void turnOffFlashlight() throws CameraAccessException {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, false);
                isFlashOn = false;
                imageView.setImageResource(R.drawable.linterna);
            }
        }
    @Override
    public void onStop() {
        super.onStop();

        // Apagar la linterna al cambiar de fragmento
        try {
            if (isFlashOn) {
                turnOffFlashlight();
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}
