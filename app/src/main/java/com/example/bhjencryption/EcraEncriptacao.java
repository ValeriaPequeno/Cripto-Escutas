package com.example.bhjencryption;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EcraEncriptacao extends AppCompatActivity {

    private static final String TAG = "EcraEncriptacao";

    String mensagem, passString;
    char passChar;
    int passInt;

    TextView theTextView;

    EditText menInput, passInput;

    Spinner dropEncriptacoes;

    Button btnEncriptResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecra_encriptacao);

        theTextView = (TextView) findViewById(R.id.menEncript);
        theTextView.setText("Este é o ecrã de encriptação");

        menInput = (EditText) findViewById(R.id.encriptCaixaMen);
        passInput = (EditText) findViewById(R.id.encriptCaixaPass);

        dropEncriptacoes = (Spinner) findViewById(R.id.encriptDrop);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(EcraEncriptacao.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.encriptacoes));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropEncriptacoes.setAdapter(myAdapter);

        btnEncriptResult = (Button) findViewById(R.id.btEncriptResult);
        btnEncriptResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Cliquei no botão de resultado da encriptação");

                /*mensagem = menInput.getText().toString();
                passString = passInput.getText().toString();
                passInt = Integer.valueOf(passInput.getText().toString());*/

                Intent entrarEcraResultado = new Intent(EcraEncriptacao.this, EcraResultado.class);
                startActivity(entrarEcraResultado);
            }
        });
    }

    private void showToast(String text){
        Toast.makeText(EcraEncriptacao.this, text, Toast.LENGTH_SHORT).show();
    }
}
