package com.pepegames.duckhunt.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.pepegames.duckhunt.R;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    TextView tvContador, tvTimer, tvNick;
    ImageView zombie;
    int contador=0;
    int anchoPantalla, altoPantalla;
    Random aleatorio;
    boolean GameOver = false;
    String id, nick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        initViewCompont();
        initPantalla();
        eventos();
        initCuentaAtras();
    }




    // Metodos de inicializacion y eventos



    private void initCuentaAtras() {
        new CountDownTimer(60000,1000){
            public void onTick(long millis){
                long segundos = millis/1000;
                tvTimer.setText(segundos + " s");
            }

            public void onFinish() {
                tvTimer.setText("0 s");
                GameOver = true;
                dialogoGameOver();
            }
        }.start();
    }



    private void dialogoGameOver() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Lograste matar " + contador + " zombies")
                .setTitle("GAME OVER");

        builder.setPositiveButton("Jugar de nuevo", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                contador = 0;
                tvContador.setText("0");
                GameOver = false;
                initCuentaAtras();
            }
        });
        builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void initPantalla() {
        //obtenemos medidas de la pantalla del dispostivo
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        anchoPantalla = size.x;
        altoPantalla = size.y-10;
        //generamos numeros aleatorios
        aleatorio = new Random();
    }


    private void eventos() {
        zombie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!GameOver) {
                    contador++;
                    tvContador.setText(String.valueOf(contador));

                    zombie.setImageResource(R.drawable.bloodsplash);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            zombie.setImageResource(R.drawable.zombie2);
                            moveZombie();
                        }
                    }, 200);

                }
            }
        });
    }


    private void moveZombie() {
        int min=0, maxAncho= anchoPantalla-zombie.getWidth(),
                maxAlto= altoPantalla-zombie.getHeight();

        //generar dos numeros x,y respectivamente

        int ranX = aleatorio.nextInt(((maxAncho - min)+1)+min);
        int ranY = aleatorio.nextInt(((maxAlto - min)+1)+min);

        //numeros aleatorios para mover zombie
        zombie.setX(ranX);
        zombie.setY(ranY);

    }


    private void initViewCompont() {
        tvContador = findViewById(R.id.textViewContador);
        tvTimer = findViewById(R.id.textViewTimer);
        tvNick = findViewById(R.id.textViewNick);
        zombie = findViewById(R.id.imageViewZombie);

        Typeface tpface = Typeface.createFromAsset(getAssets(), "zombieLetter.ttf");

        tvNick.setTypeface(tpface);
        tvContador.setTypeface(tpface);
        tvNick.setTypeface(tpface);

        //Extra: nick del usuario

        Bundle extra = getIntent().getExtras();
        nick = extra.getString("nickname");
        tvNick.setText(nick);
    }
}