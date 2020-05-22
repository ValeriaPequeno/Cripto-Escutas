package pt.ual.android.bhjencryption.ui.form.cipher.decrypt;

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

public class EcraDesencriptacaoActivityView extends AppCompatActivity implements EcraDesencriptacaoContract.View {

    // Atributos de classe
    private static final String TAG = "EcraDesencriptacaoView";

    // Presenter
    private EcraDesencriptacaoContract.Presenter ecraDesencriptacaoPresenter;

    // UI controlos
    private Button btnDecriptResult;
    private EditText etxtMenInput;
    private EditText etxtPassInput;
    private TextView txtvMenDecript;
    private Spinner spnDropEncriptacoes;

    // Atributos de instância
    private String mensagem;
    private String password;
    private int posCifraSelecionada;
    private String strCifraSelecionada;
    private ArrayAdapter<String> myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecra_desencriptacao);
        loadModel();
        initView();
    }

    @Override
    public void loadModel() {

    }

    @Override
    public void initView() {
        // Inicializar o Presenter da View
        this.ecraDesencriptacaoPresenter = new EcraDesencriptacaoPresenter(this);

        // Guardar a referência dos elementos/controlos do xml do layout da View (servirá para mais fácil manipulação do controlo)
        this.txtvMenDecript = findViewById(R.id.menDecript);
        this.etxtMenInput = findViewById(R.id.decriptCaixaMen);
        this.etxtPassInput = findViewById(R.id.decriptCaixaPass);
        this.btnDecriptResult = findViewById(R.id.btDecriptResult);
        this.spnDropEncriptacoes = findViewById(R.id.decriptDrop);

        // Inicializar o controlo menEcript com a mensagem desejada
        this.txtvMenDecript.setText("Este é o ecrã de desencriptação");

        // Inicializar a drop down de escolha do tipo de cifra
        this.myAdapter = new ArrayAdapter<String>(
                EcraDesencriptacaoActivityView.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.cifras)
        );
        this.myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnDropEncriptacoes.setAdapter(myAdapter);
        spnDropEncriptacoes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onSelectedDropDesencriptacoes(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        this.btnDecriptResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDecriptResultButton(v);
            }
        });
    }

    /* Eventos */

    private void onSelectedDropDesencriptacoes(AdapterView<?> parent, View view, int position, long id) {
        this.posCifraSelecionada = position;

        if(position == 2 || position == 4 || position == 5 || position == 7 || position == 12 || position == 13 ||
                position == 14 || position == 16 || position == 24 || position == 26 || position == 28){
            etxtPassInput.setVisibility(view.VISIBLE);
        }
        else{
            etxtPassInput.setVisibility(view.INVISIBLE);
        }
    }

    private void onClickDecriptResultButton(View view) {
        Log.d(TAG, "onClick: Cliquei no botão de resultado da encriptação");

        if (posCifraSelecionada == 0) {
            showToast("Tem que ser selecionada uma cifra para continuar");
        } else {
            this.mensagem = etxtMenInput.getText().toString();
            this.password = etxtPassInput.getText().toString();
            this.strCifraSelecionada = this.myAdapter.getItem(posCifraSelecionada);

            triggerDecrypt();
        }
    }

    /* Outros métodos de instância */

    private void triggerDecrypt() {
        EcraResultadoModel result = this.ecraDesencriptacaoPresenter.decrypt(this.mensagem, this.password, this.strCifraSelecionada, this.posCifraSelecionada);

        if (result != null) {
            if(!result.hasErrors()) {
                Intent entrarEcraResultado = new Intent(EcraDesencriptacaoActivityView.this, EcraResultadoActivityView.class);

                Bundle params = new Bundle();
                params.putSerializable("model", result);
                entrarEcraResultado.putExtras(params);

                startActivity(entrarEcraResultado);
            }
            else showToast(getResources().getString(getResources().getIdentifier(result.getErrorCode(), "string", getPackageName())));
        } else showToast("Não foi possível cifrar a mensagem solicitada.");
    }

    private void showToast(String text) {
        Toast.makeText(EcraDesencriptacaoActivityView.this, text, Toast.LENGTH_SHORT).show();
    }
}
