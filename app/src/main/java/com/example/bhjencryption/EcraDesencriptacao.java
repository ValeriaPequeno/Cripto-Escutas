package com.example.bhjencryption;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EcraDesencriptacao extends AppCompatActivity {

    private static final String TAG = "EcraDesencriptacao";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecra_desencriptacao);


        TextView theTextView = (TextView) findViewById(R.id.menDecript);
        theTextView.setText("Este é o ecrã de desencriptação");

        Button btnDecriptResult = (Button) findViewById(R.id.btDecriptResult);
        btnDecriptResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Cliquei no botão de resultado da desencriptação");

                Intent entrarEcraResultado = new Intent(EcraDesencriptacao.this, EcraResultado.class);
                startActivity(entrarEcraResultado);
            }
        });
    }
}
