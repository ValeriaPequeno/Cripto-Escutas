package pt.ual.android.bhjencryption.ui.form.cipher.encrypt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import pt.ual.android.bhjencryption.R;
import pt.ual.android.bhjencryption.ui.form.cipher.result.EcraResultadoActivityView;
import pt.ual.android.bhjencryption.ui.form.cipher.result.EcraResultadoModel;
import pt.ual.android.bhjencryption.ui.graphics.ImageTextMessage;

public class EcraEncriptacaoActivityView extends AppCompatActivity implements EcraEncriptacaoContract.View {

    // Atributos de classe
    private static final String TAG = "EcraEncriptacaoView";
    private static final String CIPHER_IMAGE_RESOURCE_NAME = "cipher_image_";

    // Presenter
    private EcraEncriptacaoContract.Presenter ecraEncriptacaoPresenter;

    // UI controlos
    private TextView txtvMenEncript;
    private EditText etxtMenInput;
    private EditText etxtPassInput;
    private Spinner spnDropEncriptacoes;
    private Button btnEncriptResult;

    // Atributos de instância
    private String mensagem;
    private String password;
    private int posCifraSelecionada;
    private String strCifraSelecionada;
    private ArrayAdapter<String> myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecra_encriptacao);
        loadModel();
        initView();
    }

    @Override
    public void initView() {
        // Inicializar o Presenter da View
        this.ecraEncriptacaoPresenter = new EcraEncriptacaoPresenter(this);

        // Guardar a referência dos elementos/controlos do xml do layout da View (servirá para mais fácil manipulação do controlo)
        this.txtvMenEncript = findViewById(R.id.menEncript);
        this.etxtMenInput = findViewById(R.id.encriptCaixaMen);
        this.etxtPassInput = findViewById(R.id.encriptCaixaPass);
        this.btnEncriptResult = findViewById(R.id.btEncriptResult);
        this.spnDropEncriptacoes = findViewById(R.id.encriptDrop);

        // Inicializar o controlo menEcript com a mensagem desejada
        this.txtvMenEncript.setText("Este é o ecrã de encriptação");

        // Inicializar a drop down de escolha do tipo de cifra
        this.myAdapter = new ArrayAdapter<String>(
                EcraEncriptacaoActivityView.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.cifras)
        );
        this.myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.spnDropEncriptacoes.setAdapter(this.myAdapter);
        this.spnDropEncriptacoes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onSelectedDropEncriptacoes(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        this.btnEncriptResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickEncriptResultButton(v);
            }
        });
    }

    @Override
    public void loadModel() {

    }

    /* Eventos */

    private void onSelectedDropEncriptacoes(AdapterView<?> parent, View view, int position, long id) {
        this.posCifraSelecionada = position;

        //Como habilitar a visibilidade de uma view
        if(position == 2 || position == 4 || position == 5 || position == 7 || position == 12 || position == 13 ||
                position == 14 || position == 16 || position == 26 || position == 28){
            etxtPassInput.setVisibility(view.VISIBLE);
            etxtMenInput.setVisibility(view.VISIBLE);
        }
        else if(position == 0){
            etxtPassInput.setVisibility(view.INVISIBLE);
            etxtMenInput.setVisibility(view.INVISIBLE);
        }
        else{
            etxtPassInput.setVisibility(view.INVISIBLE);
            etxtMenInput.setVisibility(view.VISIBLE);
        }
    }

    private boolean isImageCipher() {
        if(this.posCifraSelecionada == 3 || this.posCifraSelecionada == 8 || this.posCifraSelecionada == 10 ||
                this.posCifraSelecionada == 11 || this.posCifraSelecionada == 15 ||
                this.posCifraSelecionada == 19 || this.posCifraSelecionada == 22)
            return true;

        return false;
    }

    private void onClickEncriptResultButton(View view) {
        Log.d(TAG, "onClick: Cliquei no botão de resultado da encriptação");

        if (posCifraSelecionada == 0) {
            showToast("Tem que ser selecionada uma cifra para continuar");
        } else {
            this.mensagem = etxtMenInput.getText().toString();
            this.password = etxtPassInput.getText().toString();
            this.strCifraSelecionada = this.myAdapter.getItem(posCifraSelecionada);

            triggerEncrypt();
        }
    }

    /* Outros métodos de instância */

    private void triggerEncrypt() {
        EcraResultadoModel result = null;

        if(isImageCipher())
            result = this.ecraEncriptacaoPresenter.encrypt(new ImageTextMessage(this.mensagem,
                    getResources().getString(getResources().getIdentifier(getCipherImageName(), "string", getPackageName()))),
                    this.password,
                    this.strCifraSelecionada);
        else result = this.ecraEncriptacaoPresenter.encrypt(new ImageTextMessage(this.mensagem,null), this.password, this.strCifraSelecionada);

        if (result != null) {
            if(!result.hasErrors()) {
                Intent entrarEcraResultado = new Intent(EcraEncriptacaoActivityView.this, EcraResultadoActivityView.class);

                Bundle params = new Bundle();
                //params.putSerializable("model", result);
                params.putParcelable("model", result);
                entrarEcraResultado.putExtras(params);

                startActivity(entrarEcraResultado);
            }
            else showToast(getResources().getString(getResources().getIdentifier(result.getErrorCode(), "string", getPackageName())));
        } else showToast("Não foi possível cifrar a mensagem solicitada.");
    }

    private void showToast(String text) {
        Toast.makeText(EcraEncriptacaoActivityView.this, text, Toast.LENGTH_SHORT).show();
    }

    private String getCipherImageName() {
        return CIPHER_IMAGE_RESOURCE_NAME + posCifraSelecionada;
    }
}


