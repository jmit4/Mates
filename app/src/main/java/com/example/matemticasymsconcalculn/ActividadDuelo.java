package com.example.matemticasymsconcalculn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ActividadDuelo extends AppCompatActivity implements View.OnClickListener{

    Button btn1J1, btn2J1, btn3J1, btn1J2, btn2J2, btn3J2;

    TextView tvContador1, tvContador2;

    TextView operacion1, operacion2;

    int pasadas, ganadas1, ganadas2;

    String grado, sresultado1, sresultado2;

    Boolean flag;

    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_duelo);
        mediaPlayer = MediaPlayer.create(this, R.raw.worldcolor);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        flag = false;
        btn1J1 = findViewById(R.id.btn1J1);
        btn2J1 = findViewById(R.id.btn2J1);
        btn3J1 = findViewById(R.id.btn3J1);
        btn1J2 = findViewById(R.id.btn1J2);
        btn2J2 = findViewById(R.id.btn2J2);
        btn3J2 = findViewById(R.id.btn3J2);

        btn1J1.setOnClickListener(this);
        btn2J1.setOnClickListener(this);
        btn3J1.setOnClickListener(this);
        btn1J2.setOnClickListener(this);
        btn2J2.setOnClickListener(this);
        btn3J2.setOnClickListener(this);


        operacion1 = findViewById(R.id.tvOperacion1);
        operacion2 = findViewById(R.id.tvOperacion2);

        tvContador1 = findViewById(R.id.tvContador1);
        tvContador2 = findViewById(R.id.tvContador2);

        pasadas = ganadas1 = ganadas2 = 0;

        setGrado();

        crearOperaciones();

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
    public void crearOperaciones(){
        btn1J1.setBackgroundColor(Color.parseColor("#04D9B2"));
        btn2J1.setBackgroundColor(Color.parseColor("#04D9B2"));
        btn3J1.setBackgroundColor(Color.parseColor("#04D9B2"));
        btn1J2.setBackgroundColor(Color.parseColor("#04D9B2"));
        btn2J2.setBackgroundColor(Color.parseColor("#04D9B2"));
        btn3J2.setBackgroundColor(Color.parseColor("#04D9B2"));
        Random r = new Random();
        int lugar;
        if(grado.equals("1°")){
            crearSuma(btn1J1, btn2J1, btn3J1, 1, operacion1);
            crearSuma(btn1J2, btn2J2, btn3J2, 2, operacion2);
        } else if (grado.equals("2°")) {
            lugar = r.nextInt((2 - 1) + 1) + 1;
            switch (lugar){
                case 1:
                    crearSuma(btn1J1, btn2J1, btn3J1, 1, operacion1);
                    crearSuma(btn1J2, btn2J2, btn3J2, 2, operacion2);
                    break;
                case 2:
                    crearResta(btn1J1, btn2J1, btn3J1, 1, operacion1);
                    crearResta(btn1J2, btn2J2, btn3J2, 2, operacion2);
                    break;
                default:
                    crearSuma(btn1J1, btn2J1, btn3J1, 1, operacion1);
                    crearSuma(btn1J2, btn2J2, btn3J2, 2, operacion2);
            }
        } else if (grado.equals("3°")) {
            lugar = r.nextInt((3 - 1) + 1) + 1;
            switch (lugar){
                case 1:
                    crearSuma(btn1J1, btn2J1, btn3J1, 1, operacion1);
                    crearSuma(btn1J2, btn2J2, btn3J2, 2, operacion2);
                    break;
                case 2:
                    crearResta(btn1J1, btn2J1, btn3J1, 1, operacion1);
                    crearResta(btn1J2, btn2J2, btn3J2, 2, operacion2);
                    break;
                case 3:
                    crearMultiplicacion(btn1J1, btn2J1, btn3J1, 1, operacion1);
                    crearMultiplicacion(btn1J2, btn2J2, btn3J2, 2, operacion2);
                    break;
                default:
                    crearSuma(btn1J1, btn2J1, btn3J1, 1, operacion1);
                    crearSuma(btn1J2, btn2J2, btn3J2, 2, operacion2);
            }
        } else if (grado.equals("4°")) {
            lugar = r.nextInt((4 - 1) + 1) + 1;
            switch (lugar){
                case 1:
                    crearSuma(btn1J1, btn2J1, btn3J1, 1, operacion1);
                    crearSuma(btn1J2, btn2J2, btn3J2, 2, operacion2);
                    break;
                case 2:
                    crearResta(btn1J1, btn2J1, btn3J1, 1, operacion1);
                    crearResta(btn1J2, btn2J2, btn3J2, 2, operacion2);
                    break;
                case 3:
                    crearMultiplicacion(btn1J1, btn2J1, btn3J1, 1, operacion1);
                    crearMultiplicacion(btn1J2, btn2J2, btn3J2, 2, operacion2);
                    break;
                case 4:
                    crearDivision(btn1J1, btn2J1, btn3J1, 1, operacion1);
                    crearDivision(btn1J2, btn2J2, btn3J2, 2, operacion2);
                    break;
                default:
                    crearSuma(btn1J1, btn2J1, btn3J1, 1, operacion1);
                    crearSuma(btn1J2, btn2J2, btn3J2, 2, operacion2);
            }
        }
    }
    public void crearSuma(Button btn1, Button btn2, Button btn3, int jugador, TextView operacion){
        Random r = new Random();
        int num1 = r.nextInt((9 - 0) + 1) + 0;
        int num2 = r.nextInt((9 - 0) + 1) + 0;

        int resultado = num1 + num2;
        operacion.setText(num1+" + "+num2+" = ");
        if(jugador == 1){
            sresultado1 = resultado+"";
        }else{
            sresultado2 = resultado+"";
        }

        int lugar = r.nextInt((3 - 1) + 1) + 1;
        if(lugar == 1){
            btn1.setText(resultado+"");
            btn2.setText(randomNumber(0,20,resultado)+"");
            btn3.setText(randomNumber(0,20,resultado)+"");
        }else if(lugar == 2){
            btn1.setText(randomNumber(0,20,resultado)+"");
            btn2.setText(resultado+"");
            btn3.setText(randomNumber(0,20,resultado)+"");
        }else if(lugar == 3){
            btn1.setText(randomNumber(0,20,resultado)+"");
            btn2.setText(randomNumber(0,20,resultado)+"");
            btn3.setText(resultado +"");
        }

    }

    public void crearResta(Button btn1, Button btn2, Button btn3, int jugador, TextView operacion){
        Random r = new Random();
        int num1 = r.nextInt((20 - 0) + 1) + 0;

        int num2 = r.nextInt((num1 - 0) + 1) + 0;

        int resultado = num1 - num2;

        operacion.setText(num1+" - "+num2+" =");
        if(jugador == 1){
            sresultado1 = resultado+"";
        }else{
            sresultado2 = resultado+"";
        }

        int lugar = r.nextInt((3 - 1) + 1) + 1;
        if(lugar == 1){
            btn1.setText(resultado+"");
            btn2.setText(randomNumber(0,20,resultado)+"");
            btn3.setText(randomNumber(0,20,resultado)+"");
        }else if(lugar == 2){
            btn1.setText(randomNumber(0,20,resultado)+"");
            btn2.setText(resultado+"");
            btn3.setText(randomNumber(0,20,resultado)+"");
        }else if(lugar == 3){
            btn1.setText(randomNumber(0,20,resultado)+"");
            btn2.setText(randomNumber(0,20,resultado)+"");
            btn3.setText(resultado +"");
        }
    }
    public void crearMultiplicacion(Button btn1, Button btn2, Button btn3, int jugador, TextView operacion){
        Random r = new Random();
        int num1 = r.nextInt((10 - 0) + 1) + 0;

        int num2 = r.nextInt((10 - 0) + 1) + 0;

        int resultado = num1 * num2;
        operacion.setText(num1+" x "+num2+" =");

        if(jugador == 1){
            sresultado1 = resultado+"";
        }else{
            sresultado2 = resultado+"";
        }

        int lugar = r.nextInt((3 - 1) + 1) + 1;
        if(lugar == 1){
            btn1.setText(resultado+"");
            btn2.setText(randomNumber(0,20,resultado)+"");
            btn3.setText(randomNumber(0,20,resultado)+"");
        }else if(lugar == 2){
            btn1.setText(randomNumber(0,20,resultado)+"");
            btn2.setText(resultado+"");
            btn3.setText(randomNumber(0,20,resultado)+"");
        }else if(lugar == 3){
            btn1.setText(randomNumber(0,20,resultado)+"");
            btn2.setText(randomNumber(0,20,resultado)+"");
            btn3.setText(resultado +"");
        }
    }
    public void crearDivision(Button btn1, Button btn2, Button btn3, int jugador, TextView operacion){
        Random r = new Random();
        int divisor = r.nextInt((10 - 1) + 1) + 1;

        int cociente = r.nextInt((10 - 0) + 1) + 0;

        int dividendo = divisor * cociente;

        operacion.setText(dividendo+" ÷ "+divisor+" =");
        if(jugador == 1){
            sresultado1 = cociente+"";
        }else{
            sresultado2 = cociente+"";
        }

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
    }
    public void setGrado(){
        SharedPreferences miCompartido = getSharedPreferences("edad", Context.MODE_PRIVATE);
        grado = miCompartido.getString("laEdad","");
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
    public void onClick(View v) {
        if (pasadas < 6) {
            if (v.getId() == R.id.btn1J1 || v.getId() == R.id.btn2J1 || v.getId() == R.id.btn3J1) {
                //Si fue uno de los botones del jugador 1, entonces checar si la respuesta fue correcta
                Button boton = findViewById(v.getId());
                if (boton.getText().equals(sresultado1)) {
                    if (!flag) {
                        reproducirBienHecho();
                        ganadas1++;
                        pasadas++;
                        tvContador1.setText(ganadas1+"");

                            crearOperaciones();

                    }
                }else{
                    boton.setBackgroundColor(Color.parseColor("#ff3867"));
                }
            } else if (v.getId() == R.id.btn1J2 || v.getId() == R.id.btn2J2 || v.getId() == R.id.btn3J2) {
                Button boton = findViewById(v.getId());
                if (boton.getText().equals(sresultado2)) {
                    if (!flag) {
                        reproducirBienHecho();
                        ganadas2++;
                        pasadas++;
                        tvContador2.setText(ganadas2+"");
                        crearOperaciones();
                    }
                }else{
                    boton.setBackgroundColor(Color.parseColor("#ff3867"));
                }
            }
        }else{
            if(v.getId() == R.id.btn1J1 || v.getId() == R.id.btn2J1 || v.getId() == R.id.btn3J1){
                Button boton = findViewById(v.getId());
                if(boton.getText().equals(sresultado1)){
                    ganadas1++;
                }
            }else{
                Button boton = findViewById(v.getId());
                if(boton.getText().equals(sresultado2)){
                    ganadas2++;
                }
            }
            Toast.makeText(this,"Jugador 1: "+ganadas1+"Jugador2:"+ganadas2, Toast.LENGTH_SHORT).show();
            SharedPreferences preferencias = getSharedPreferences("jugadores", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencias.edit();
            editor.putString("Jugador1", ganadas1+"");
            editor.commit();
            editor.putString("Jugador2", ganadas2+"");
            editor.commit();
            //Irse a la actividad ganadores
            Intent intencion = new Intent(getApplicationContext(),ActividadGanadores.class);
            startActivity(intencion);
        }
    }
    @Override
    protected void onStop(){
        super.onStop();
        mediaPlayer.stop();
        mediaPlayer.release();
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
}