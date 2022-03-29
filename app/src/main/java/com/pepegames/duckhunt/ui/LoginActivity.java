package com.pepegames.duckhunt.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.pepegames.duckhunt.R;
import com.pepegames.duckhunt.modelos.Usuarios;

public class LoginActivity extends AppCompatActivity {

    EditText etNick;
    Button btnStart;
    String nick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        etNick = findViewById(R.id.editTextNick);
        btnStart = findViewById(R.id.buttonStart);

        //cambiar fuente
        Typeface tpface = Typeface.createFromAsset(getAssets(), "zombieLetter.ttf");
        etNick.setTypeface(tpface);
        btnStart.setTypeface(tpface);

        //Evento clic

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nick = etNick.getText().toString();

                if(nick.isEmpty()){
                    etNick.setError("El nombre de usuario es obligatorio");
                }
                Intent i = new Intent(LoginActivity.this, GameActivity.class);
                i.putExtra("nickname", nick);
                startActivity(i);
            }
        });

    }



}