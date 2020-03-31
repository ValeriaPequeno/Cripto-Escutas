package com.example.bhjencryption;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EcraEncriptacao extends AppCompatActivity {

    private static final String TAG = "EcraEncriptacao";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecra_encriptacao);

        TextView theTextView = (TextView) findViewById(R.id.menEncript);
        theTextView.setText("Este é o ecrã de encriptação");

        Button btnEncriptResult = (Button) findViewById(R.id.btEncriptResult);
        btnEncriptResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Cliquei no botão de resultado da encriptação");

                Intent entrarEcraResultado = new Intent(EcraEncriptacao.this, EcraResultado.class);
                startActivity(entrarEcraResultado);
            }
        });
    }
}
