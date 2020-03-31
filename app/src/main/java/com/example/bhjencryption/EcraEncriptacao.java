package com.example.bhjencryption;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EcraEncriptacao extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecra_encriptacao);

        TextView theTextView = (TextView) findViewById(R.id.menEncript);
        theTextView.setText("Este é o ecrã de encriptação");
    }
}
