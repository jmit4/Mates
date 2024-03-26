package com.example.matemticasymsconcalculn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.Fade;
import androidx.transition.Scene;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MenuPrincipal extends AppCompatActivity {

    Scene escena1, escena2;
    TextView tvGrado;

    ImageView btnVolver, btnSuma, btnResta, btnMultiplicacion, btnDivision, btnDuelo, btnUser, btnLetras;
    MediaPlayer mediaPlayer;
    String[] edades = {"1°", "2°","3°","4°"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mediaPlayer = MediaPlayer.create(this,R.raw.happyday);
        reproducir();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal2);
        View inflatedView = getLayoutInflater().inflate(R.layout.menu_principal, null);
        ViewGroup sceneRoot = (ViewGroup) inflatedView.findViewById(R.id.escenaroot);

        escena1 = Scene.getSceneForLayout(sceneRoot, R.layout.menu_principal1, this);
        escena2 = Scene.getSceneForLayout(sceneRoot, R.layout.menu_principal2 , this);

        escena1.enter();

        TransitionManager.go(escena2);

        setContentView(R.layout.menu_principal2);


        SharedPreferences miCompartido = getSharedPreferences("edad", Context.MODE_PRIVATE);

        singleToneClass edad = com.example.matemticasymsconcalculn.singleToneClass.getInstance();

        if(miCompartido.getString("laEdad","").equals("")) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("¿En qué grado estás?");
            builder.setItems(edades, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SharedPreferences preferencias = getSharedPreferences("edad", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferencias.edit();
                    editor.putString("laEdad", edades[which]);
                    editor.commit();
                    String grado = miCompartido.getString("laEdad","");
                    tvGrado.setText(grado);
                }
            });
            builder.show();

        }
        edad.setData(miCompartido.getString("laEdad",""));
        tvGrado = findViewById(R.id.tvGrado);
        tvGrado.setText(edad.getData());

        btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cambiar al menú de operaciones y juegos

                Intent intencion = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intencion);
            }
        });

        btnSuma = findViewById(R.id.btnSuma);
        btnSuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intencion = new Intent(getApplicationContext(), ActividadSuma.class);
                startActivity(intencion);
            }
        });

        btnResta = findViewById(R.id.btnResta);

        btnResta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvGrado.getText().equals("2°") || tvGrado.getText().equals("3°") || tvGrado.getText().equals("4°")) {
                    Intent intencion = new Intent(getApplicationContext(), ActividadResta.class);
                    startActivity(intencion);
                }else{
                    Toast.makeText(v.getContext(), "Eres muy chico para restar", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnMultiplicacion = findViewById(R.id.btnMultiplicacion);
        btnMultiplicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvGrado.getText().equals("3°") || tvGrado.getText().equals("4°")) {
                    Intent intencion = new Intent(getApplicationContext(), ActividadMultiplicacion.class);
                    startActivity(intencion);
                }else{
                    Toast.makeText(v.getContext(), "Eres muy chico para multiplicar", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDivision = findViewById(R.id.btnDivision);
        btnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvGrado.getText().equals("4°")) {
                    Intent intencion = new Intent(getApplicationContext(), ActividadDivision.class);
                    startActivity(intencion);
                }else{
                    Toast.makeText(v.getContext(), "Eres muy chico para dividir", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDuelo = findViewById(R.id.btnDuelo);
        btnDuelo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), ActividadDuelo.class);
                startActivity(intencion);
            }
        });
        btnLetras = findViewById(R.id.btnLetras);
        btnLetras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), ActividadLetras.class);
                startActivity(intencion);
            }
        });
        btnUser = findViewById(R.id.btnUser);
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("¿En qué grado estás?");
                builder.setItems(edades, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences preferencias = getSharedPreferences("edad", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferencias.edit();
                        editor.putString("laEdad", edades[which]);
                        editor.commit();
                        String grado = miCompartido.getString("laEdad","");
                        tvGrado.setText(grado);
                    }
                });
                builder.show();
            }
        });



    }
    public void reproducir(){

            mediaPlayer.setLooping(true);
            mediaPlayer.start();
    }

    @Override
    protected void onStop(){
        super.onStop();
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    public void onBackPressed() {
        Intent intencion = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intencion);

    }

}