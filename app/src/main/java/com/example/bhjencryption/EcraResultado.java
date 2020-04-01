package com.example.bhjencryption;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EcraResultado extends AppCompatActivity {

    private static final String TAG = "EcraResultado";

    TextView theTextView;

    ImageView imagemResultado;

    Button btnPartilhar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecra_resultado);

        theTextView = (TextView) findViewById(R.id.menResultado);
        theTextView.setText("Este é o ecrã do resultado da operação");

        imagemResultado = (ImageView) findViewById(R.id.imgResultado);
        int imageResource = getResources().getIdentifier("@drawable/icon", null, this.getPackageName());
        imagemResultado.setImageResource(imageResource);

        btnPartilhar = (Button) findViewById(R.id.btPartilha);
        btnPartilhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Cliquei no botão de partilha do resultado");
            }
        });
    }
}
