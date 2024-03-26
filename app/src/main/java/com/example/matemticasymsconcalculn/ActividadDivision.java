package com.example.matemticasymsconcalculn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ActividadDivision extends AppCompatActivity {

    TextView tvNum1, tvNum2;
    RatingBar ratingBar;
    EditText etRespuesta;
    Button btn1, btn2, btn3;
    ImageView btnVolver, btnNext;

    String sresultado;

    MediaPlayer mediaPlayer;

    int pasadas;
    int ganadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_division);
        mediaPlayer = MediaPlayer.create(this, R.raw.worldcolor);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        tvNum1 = findViewById(R.id.tvNum1D);
        tvNum2 = findViewById(R.id.tvNum2D);

        ratingBar = findViewById(R.id.ratingBar4);
        ratingBar.setEnabled(false);
        ratingBar.setVisibility(View.INVISIBLE);

        etRespuesta = findViewById(R.id.etRespuesta4);

        btn1 = findViewById(R.id.btnOp1D);
        btn2 = findViewById(R.id.btnOp2D);
        btn3 = findViewById(R.id.btnOp3D);



        btnVolver = findViewById(R.id.btnVolver4);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
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
        });
        btnNext = findViewById(R.id.btnNext4);
        //
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pasadas < 5) {

                    if (isCorrect()) {
                        ganadas++;
                        ratingBar.setVisibility(View.VISIBLE);
                        ratingBar.setNumStars(ganadas);
                        Toast.makeText(v.getContext(), "BIEN HECHO, TEN UNA ESTRELLITA",Toast.LENGTH_SHORT).show();
                        MediaPlayer bienhecho = MediaPlayer.create(v.getContext(), R.raw.bienhecho);
                        bienhecho.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                mp.stop();
                                mp.release();
                            }
                        });
                        bienhecho.start();
                    }else{
                        Toast.makeText(v.getContext(), "UPS, esa no fue la respuesta",Toast.LENGTH_SHORT).show();
                    }
                    etRespuesta.setText("");
                    crearOperacion();
                }else{
                    //Terminó el juego
                    if(isCorrect()){
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
                                    etRespuesta.setText("");
                                    crearOperacion();
                                    ratingBar.setNumStars(0);

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
        crearOperacion();
    }
    public void crearOperacion(){
        pasadas++;
        Random r = new Random();
        int divisor = r.nextInt((10 - 1) + 1) + 1;

        int cociente = r.nextInt((10 - 0) + 1) + 0;

        int dividendo = divisor * cociente;

        tvNum1.setText(dividendo+"");
        tvNum2.setText(divisor+"");
        sresultado = cociente+"";

        int lugar = r.nextInt((3 - 1) + 1) + 1;
        if(lugar == 1){
            btn1.setText(cociente+"");
            btn2.setText(randomNumber(0,20,cociente)+"");
            btn3.setText(randomNumber(0,20,cociente)+"");
        }else if(lugar == 2){
            btn1.setText(randomNumber(0,20,cociente)+"");
            btn2.setText(cociente+"");
            btn3.setText(randomNumber(0,20,cociente)+"");
        }else if(lugar == 3){
            btn1.setText(randomNumber(0,20,cociente)+"");
            btn2.setText(randomNumber(0,20,cociente)+"");
            btn3.setText(cociente +"");
        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etRespuesta.setText(btn1.getText());
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etRespuesta.setText(btn2.getText());
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etRespuesta.setText(btn3.getText());
            }
        });
    }
    public boolean isCorrect(){

        return sresultado.equals(etRespuesta.getText().toString());

    }

    public int randomNumber(int min, int max, int exception){
        int numero;
        Random r = new Random();
        do{
            numero = r.nextInt((max - min) + 1) + min;

        }while(numero == exception);
        return numero;
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
    @Override
    protected void onStop(){
        super.onStop();
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}