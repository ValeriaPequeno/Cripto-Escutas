package com.example.bhjencryption;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //chamar pelo elemento TextView no xml
        TextView menBoasVindas = (TextView) findViewById(R.id.boasVindas);

        //meter texto na TextView
        menBoasVindas.setText("Bem-vindo ao Encriptador e Desencriptador de Mensagens");

        //chamar pelo elemento Button no xml
        Button btnEncript = (Button) findViewById(R.id.btMainEncript);
        Button btnDecript = (Button) findViewById(R.id.btMainDecript);

        //criar atividade de entrar noutro ecrã ao clicar no botão
        btnEncript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Cliquei no botão de encriptação");

                Intent entrarEcraEncript = new Intent(MainActivity.this, EcraEncriptacao.class);
                startActivity(entrarEcraEncript);
            }
        });

        btnDecript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Cliquei no botão de desencriptação");

                Intent entrarEcraDecript = new Intent(MainActivity.this, EcraDesencriptacao.class);
                startActivity(entrarEcraDecript);
            }
        });
    }
}


//extrair texto da TextView
//String stringFromTextView = theTextView.getText().toString();