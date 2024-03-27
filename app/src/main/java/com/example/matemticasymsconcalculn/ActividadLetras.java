package com.example.matemticasymsconcalculn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import kotlin.collections.ArrayDeque;
//Hare un comentario de prueba
//prueba merge
public class ActividadLetras extends AppCompatActivity{

    HashMap<Integer,String> mapa=new HashMap<Integer,String>();
    String respuesta;
    ImageView imagen, btnNext;

    RatingBar ratingBar;

    MediaPlayer mediaPlayer;

    Button btnListo;

    EditText et;
    TextView tvrespuesta;

    Integer[] claves;
    List<String> clavesList;

    int pasadas, ganadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_letras);
        mediaPlayer = MediaPlayer.create(this, R.raw.worldcolor);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        mapa.put(R.drawable.arana, "araña");
        mapa.put(R.drawable.burro, "burro");
        mapa.put(R.drawable.casa, "casa");
        mapa.put(R.drawable.dado, "dado");
        mapa.put(R.drawable.elefante, "elefante");
        mapa.put(R.drawable.flor, "flor");
        mapa.put(R.drawable.gato, "gato");
        mapa.put(R.drawable.hoja,"hoja");
        mapa.put(R.drawable.iglu, "iglu");
        mapa.put(R.drawable.jirafa,"jirafa");
        mapa.put(R.drawable.planeta, "planeta");
        mapa.put(R.drawable.koalas, "koala");
        mapa.put(R.drawable.lobo, "lobo");
        et = findViewById(R.id.editTextText);
        et.setText("");

        ratingBar = findViewById(R.id.ratingBar5);
        ratingBar.setVisibility(View.INVISIBLE);
        ratingBar.setEnabled(false);
        imagen = findViewById(R.id.imagenJuego);
        tvrespuesta = findViewById(R.id.tvRespuesta);
        tvrespuesta.setText("");
        setClaves();
        desordenarClaves();
        elegirPalabra();
        btnNext = findViewById(R.id.btnNextL);
        btnNext.setVisibility(View.INVISIBLE);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                et.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.rounded_edittext, null));
                pasadas++;
                elegirPalabra();
                tvrespuesta.setText("");
                et.setText("");
                btnNext.setVisibility(View.INVISIBLE);
                btnListo.setVisibility(View.VISIBLE);
            }
        });
        btnListo = findViewById(R.id.button);
        btnListo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (pasadas < 4) {
                    if (et.getText().toString().equals(respuesta)) {
                        ganadas++;
                        ratingBar.setVisibility(View.VISIBLE);
                        et.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.forma_verde, null));
                        ratingBar.setNumStars(ganadas);
                        reproducirBienHecho();
                    } else {
                        et.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.forma_roja, null));
                    }

                    tvrespuesta.setText(respuesta);
                    btnNext.setVisibility(View.VISIBLE);
                    btnListo.setVisibility(View.INVISIBLE);
                }else{
                    if(et.getText().toString().equals(respuesta)){
                        ganadas++;
                    }
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = MediaPlayer.create(v.getContext(), R.raw.finalnivel);
                    mediaPlayer.start();
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setMessage("Muy bien, has ganado "+ganadas+" juegos\n¿Quieres seguir jugando?")
                            .setTitle("Salir")
                            .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    mediaPlayer.stop();
                                    mediaPlayer.release();
                                    mediaPlayer = MediaPlayer.create(v.getContext(), R.raw.worldcolor);
                                    mediaPlayer.setLooping(true);
                                    mediaPlayer.start();
                                    pasadas = 0;
                                    ganadas = 0;
                                    et.setText("");
                                    tvrespuesta.setText("");
                                    ratingBar.setNumStars(0);
                                    desordenarClaves();
                                    elegirPalabra();

                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intencion = new Intent(getApplicationContext(), MenuPrincipal.class);
                                    startActivity(intencion);
                                }
                            });
                    // Create the AlertDialog object and return it
                    builder.show();
                }
            }
        });



    }
    public void elegirPalabra(){
        imagen.setImageResource(elegirImagenMapa());
    }
    public Integer elegirImagenMapa(){
        Integer clave = claves[pasadas];
        respuesta = mapa.get(clave);
        return clave;
    }
    public void setClaves(){
        claves = mapa.keySet().stream().toArray(Integer[]:: new);

    }
    @Override
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
    public void desordenarClaves(){
        Random r = new Random();
        for (int i = claves.length; i > 0; i--) {
            int posicion = r.nextInt(i);
            int tmp = claves[i-1];
            claves[i - 1] = claves[posicion];
            claves[posicion] = tmp;
        }
    }
    public void reproducirBienHecho(){
        MediaPlayer bienhecho = MediaPlayer.create(this, R.raw.bienhecho);
        bienhecho.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                mp.release();
                mp = null;
            }
        });
        bienhecho.start();
    }
    @Override
    protected void onStop(){
        super.onStop();
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}