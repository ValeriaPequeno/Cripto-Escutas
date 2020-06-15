package pt.ual.android.bhjencryption.ui.form.cipher.encrypt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
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
    private Button btnDescription;

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

    @SuppressLint("SetTextI18n")
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
        this.btnDescription = findViewById(R.id.btEncriptDescription);

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

        this.btnDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openLink = new Intent();
                openLink.setAction(Intent.ACTION_VIEW);
                openLink.addCategory(Intent.CATEGORY_BROWSABLE);
                switch (posCifraSelecionada){
                    case 1:
                        openLink.setData(Uri.parse("http://cne-escutismo.pt/recursos/cscodigoscifras/codigo03.htm"));
                        break;
                    case 2:
                        openLink.setData(Uri.parse("http://cne-escutismo.pt/recursos/cscodigoscifras/codigo08.htm"));
                        break;
                    case 3:
                        openLink.setData(Uri.parse("http://cne-escutismo.pt/recursos/cscodigoscifras/codigo20.htm"));
                        break;
                    case 4:
                        openLink.setData(Uri.parse("http://cne-escutismo.pt/recursos/cscodigoscifras/codigo24.htm"));
                        break;
                    case 5:
                        openLink.setData(Uri.parse("http://cne-escutismo.pt/recursos/cscodigoscifras/codigo29.htm"));
                        break;
                    case 6:
                        openLink.setData(Uri.parse("http://cne-escutismo.pt/recursos/cscodigoscifras/codigo13.htm"));
                        break;
                    case 7:
                        //openLink.setData(Uri.parse(""));
                        break;
                    case 8:
                        openLink.setData(Uri.parse("http://cne-escutismo.pt/recursos/cscodigoscifras/codigo01.htm"));
                        break;
                    case 9:
                        openLink.setData(Uri.parse("http://cne-escutismo.pt/recursos/cscodigoscifras/codigo17.htm"));
                        break;
                    case 10:
                        openLink.setData(Uri.parse("http://cne-escutismo.pt/recursos/cscodigoscifras/codigo18.htm"));
                        break;
                    case 11:
                        openLink.setData(Uri.parse("http://cne-escutismo.pt/recursos/cscodigoscifras/codigo19.htm"));
                        break;
                    case 12:
                        openLink.setData(Uri.parse("http://cne-escutismo.pt/recursos/cscodigoscifras/codigo02.htm"));
                        break;
                    case 13:
                        openLink.setData(Uri.parse("http://cne-escutismo.pt/recursos/cscodigoscifras/codigo14.htm"));
                        break;
                    case 14:
                        openLink.setData(Uri.parse("http://cne-escutismo.pt/recursos/cscodigoscifras/codigo15.htm"));
                        break;
                    case 15:
                        openLink.setData(Uri.parse("http://cne-escutismo.pt/recursos/cscodigoscifras/codigo22.htm"));
                        break;
                    case 16:
                        openLink.setData(Uri.parse("http://cne-escutismo.pt/recursos/cscodigoscifras/codigo28.htm"));
                        break;
                    case 17:
                        openLink.setData(Uri.parse("http://cne-escutismo.pt/recursos/cscodigoscifras/codigo10.htm"));
                        break;
                    case 18:
                        //openLink.setData(Uri.parse(""));
                        break;
                    case 19:
                        openLink.setData(Uri.parse("http://cne-escutismo.pt/recursos/cscodigoscifras/codigo23.htm"));
                        break;
                    case 20:
                        openLink.setData(Uri.parse("http://cne-escutismo.pt/recursos/cscodigoscifras/codigo06.htm"));
                        break;
                    case 21:
                        openLink.setData(Uri.parse("http://cne-escutismo.pt/recursos/cscodigoscifras/codigo07.htm"));
                        break;
                    case 22:
                        openLink.setData(Uri.parse("http://cne-escutismo.pt/recursos/cscodigoscifras/codigo05.htm"));
                        break;
                    case 23:
                        openLink.setData(Uri.parse("http://cne-escutismo.pt/recursos/cscodigoscifras/codigo30.htm"));
                        break;
                    case 24:
                        openLink.setData(Uri.parse("http://cne-escutismo.pt/recursos/cscodigoscifras/codigo09.htm"));
                        break;
                    case 25:
                        //openLink.setData(Uri.parse(""));
                        break;
                    case 26:
                        openLink.setData(Uri.parse("http://cne-escutismo.pt/recursos/cscodigoscifras/codigo04.htm"));
                        break;
                    case 27:
                        openLink.setData(Uri.parse("http://cne-escutismo.pt/recursos/cscodigoscifras/codigo21.htm"));
                        break;
                    case 28:
                        openLink.setData(Uri.parse("http://cne-escutismo.pt/recursos/cscodigoscifras/codigo27.htm"));
                        break;
                    case 29:
                        openLink.setData(Uri.parse("http://cne-escutismo.pt/recursos/cscodigoscifras/codigo12.htm"));
                        break;
                    default:
                        break;
                }
                startActivity(openLink);
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
        if(position == 0){
            etxtMenInput.setVisibility(View.INVISIBLE);
            etxtPassInput.setVisibility(View.INVISIBLE);
            btnDescription.setVisibility(View.INVISIBLE);
        }
        else if(position == 2 || position == 4 || position == 5 || position == 7 || position == 12 || position == 13 ||
                position == 14 || position == 16 || position == 26 || position == 28){
            etxtMenInput.setVisibility(View.VISIBLE);
            etxtPassInput.setVisibility(View.VISIBLE);
            btnDescription.setVisibility(View.VISIBLE);
        }
        else{
            etxtMenInput.setVisibility(View.VISIBLE);
            etxtPassInput.setVisibility(View.INVISIBLE);
            btnDescription.setVisibility(View.VISIBLE);
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


