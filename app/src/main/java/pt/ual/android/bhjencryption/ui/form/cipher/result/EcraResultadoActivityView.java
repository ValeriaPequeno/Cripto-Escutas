package pt.ual.android.bhjencryption.ui.form.cipher.result;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

import pt.ual.android.bhjencryption.R;

public class EcraResultadoActivityView extends AppCompatActivity implements EcraResultadoContrat.View {

    private static final String TAG = "EcraResultadoView";

    // Presenter
    EcraResultadoContrat.Presenter ecraResultadoPresenter;

    // Model
    private EcraResultadoModel model;

    // UI controls
    private TextView txtvMenResultado;
    private ImageView imgResultado;
    private Button btnPartilhar;

    public static Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888); // Define a bitmap with the same size as the view

        Canvas canvas = new Canvas(returnedBitmap); // Ligar um canvas ao bitmap
        Drawable bgDrawable = view.getBackground(); // Obter o background da imageview

        if (bgDrawable != null)
            bgDrawable.draw(canvas); // se o background for drawable, então faz o seu draw no canvas
        else
            canvas.drawColor(Color.WHITE); // não tem background drawable? Então faz draw de branco

        view.draw(canvas); // fazer o draw do canvas

        return returnedBitmap;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecra_resultado);
        loadModel();
        initView();
    }

    public void loadModel() {
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        this.model = (EcraResultadoModel) bundle.getSerializable("model");

        if (this.model == null)
            this.model = new EcraResultadoModel();
    }

    @Override
    public void initView() {
        // Inicializar o presenter
        this.ecraResultadoPresenter = new EcraResultadoPresenter(this);

        // Referenciar os controlos de UI
        this.txtvMenResultado = findViewById(R.id.menResultado);
        this.imgResultado = findViewById(R.id.imgResultado);
        this.btnPartilhar = findViewById(R.id.btPartilha);

        // Iniciar os controlos
        this.txtvMenResultado.setText("Este é o ecrã do resultado da operação");
        renderResult();

        // Definir os event handlers
        this.btnPartilhar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                onClickShareButton(v);
            }
        });
    }

    /* Eventos */

    private void renderResult() {
        imgResultado.setImageBitmap(this.model.getResultAsBitmap());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void onClickShareButton(View view) {
        Log.d(TAG, "onClick: Cliquei no botão de partilha do resultado");

        String permit = android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
        int checkVal = getApplicationContext().checkCallingOrSelfPermission(permit);

        if(checkVal == PackageManager.PERMISSION_GRANTED){
            Intent share = new Intent(Intent.ACTION_SEND);
            share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            share.setType("image/jpeg");
            share.putExtra(Intent.EXTRA_STREAM, getImageUri(this.getApplicationContext(), getBitmapFromView(this.imgResultado)));

            try {
                startActivity(Intent.createChooser(share, "My Profile ..."));
            } catch (android.content.ActivityNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        else{
            requestPermissions(new String[] {permit},1024);
        }
    }

    public Uri getImageUri(Context inContext, Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), bitmap, "Title", null);

        return Uri.parse(path);
    }
}
