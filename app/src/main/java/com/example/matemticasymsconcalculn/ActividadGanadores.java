package com.example.matemticasymsconcalculn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActividadGanadores extends AppCompatActivity {

    TextView tvGanador, puntaje1, puntaje2;

    Button btnOk;

    int puntajej1, puntajej2;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_ganadores);

        tvGanador = findViewById(R.id.tvGanador);
        puntaje1 = findViewById(R.id.tvPuntaje1);
        puntaje2 = findViewById(R.id.tvPuntaje2);
        btnOk = findViewById(R.id.btnOk);
        setPuntajes();
        setGanador();
        mediaPlayer = MediaPlayer.create(this, R.raw.finalnivel);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                mp.release();
                mp=null;
            }
        });
        mediaPlayer.start();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(),MenuPrincipal.class);
                startActivity(intencion);
            }
        });


    }
    public void setPuntajes(){
        SharedPreferences preferencias = getSharedPreferences("jugadores", Context.MODE_PRIVATE);
        puntajej1 = Integer.parseInt(preferencias.getString("Jugador1", ""));
        puntajej2 = Integer.parseInt(preferencias.getString("Jugador2", ""));

        puntaje1.setText(puntajej1+" puntos");
        puntaje2.setText(puntajej2+" puntos");
    }
    public void setGanador(){
        if(puntajej1 > puntajej2){
            tvGanador.setText("El ganador fue el jugador 1");
        } else if (puntajej2 > puntajej1) {
            tvGanador.setText("El ganador fue el jugador 2");
        }else{
            tvGanador.setText("Fue un empate");
        }
    }
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Deseas salir?")
                .setTitle("Salir")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intencion = new Intent(getApplicationContext(), MenuPrincipal.class);
                        startActivity(intencion);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        // Create the AlertDialog object and return it
        builder.show();

    }
}