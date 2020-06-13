package pt.ual.android.bhjencryption.ui.form.cipher.decrypt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import pt.ual.android.bhjencryption.R;
import pt.ual.android.bhjencryption.ui.form.cipher.encrypt.EcraEncriptacaoActivityView;
import pt.ual.android.bhjencryption.ui.form.cipher.result.EcraResultadoActivityView;
import pt.ual.android.bhjencryption.ui.form.cipher.result.EcraResultadoModel;
import pt.ual.android.bhjencryption.ui.graphics.ImageTextMessage;

public class EcraDesencriptacaoActivityView extends AppCompatActivity implements EcraDesencriptacaoContract.View {

    // Atributos de classe
    private static final String TAG = "EcraDesencriptacaoView";
    private static final String CIPHER_IMAGE_RESOURCE_NAME = "cipher_image_";

    // Presenter
    private EcraDesencriptacaoContract.Presenter ecraDesencriptacaoPresenter;

    // UI controlos
    private Button btnDecriptResult;
    private EditText etxtMenInput;
    private EditText etxtPassInput;
    private TextView txtvMenDecript;
    private Spinner spnDropEncriptacoes;

    private LinearLayout llTecaldoImagens;
    private Button btnTecladoQ;
    private Button btnTecladoW;
    private Button btnTecladoE;
    private Button btnTecladoR;
    private Button btnTecladoT;
    private Button btnTecladoY;
    private Button btnTecladoU;
    private Button btnTecladoI;
    private Button btnTecladoO;
    private Button btnTecladoP;
    private Button btnTecladoA;
    private Button btnTecladoS;
    private Button btnTecladoD;
    private Button btnTecladoF;
    private Button btnTecladoG;
    private Button btnTecladoH;
    private Button btnTecladoJ;
    private Button btnTecladoK;
    private Button btnTecladoL;
    private Button btnTecladoZ;
    private Button btnTecladoX;
    private Button btnTecladoC;
    private Button btnTecladoV;
    private Button btnTecladoB;
    private Button btnTecladoN;
    private Button btnTecladoM;
    private Button btnTecladoSpace;

    private LinearLayout llTecladoMorse;
    private Button btnPonto;
    private Button btnTraco;
    private Button btnLetra;
    private Button btnPalavra;


    // Atributos de instância
    private String mensagem;
    private String password;
    private int posCifraSelecionada;
    private String strCifraSelecionada;
    private ArrayAdapter<String> myAdapter;
    private ImageTextMessage imageMessage;

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

    @SuppressLint("SetTextI18n")
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

        this.llTecaldoImagens = findViewById(R.id.tecladoImagens);
        this.btnTecladoQ = findViewById(R.id.button_q);
        this.btnTecladoW = findViewById(R.id.button_w);
        this.btnTecladoE = findViewById(R.id.button_e);
        this.btnTecladoR = findViewById(R.id.button_r);
        this.btnTecladoT = findViewById(R.id.button_t);
        this.btnTecladoY = findViewById(R.id.button_y);
        this.btnTecladoU = findViewById(R.id.button_u);
        this.btnTecladoI = findViewById(R.id.button_i);
        this.btnTecladoO = findViewById(R.id.button_o);
        this.btnTecladoP = findViewById(R.id.button_p);
        this.btnTecladoA = findViewById(R.id.button_a);
        this.btnTecladoS = findViewById(R.id.button_s);
        this.btnTecladoD = findViewById(R.id.button_d);
        this.btnTecladoF = findViewById(R.id.button_f);
        this.btnTecladoG = findViewById(R.id.button_g);
        this.btnTecladoH = findViewById(R.id.button_h);
        this.btnTecladoJ = findViewById(R.id.button_j);
        this.btnTecladoK = findViewById(R.id.button_k);
        this.btnTecladoL = findViewById(R.id.button_l);
        this.btnTecladoZ = findViewById(R.id.button_z);
        this.btnTecladoX = findViewById(R.id.button_x);
        this.btnTecladoC = findViewById(R.id.button_c);
        this.btnTecladoV = findViewById(R.id.button_v);
        this.btnTecladoB = findViewById(R.id.button_b);
        this.btnTecladoN = findViewById(R.id.button_n);
        this.btnTecladoM = findViewById(R.id.button_m);
        this.btnTecladoSpace = findViewById(R.id.button_space);

        this.llTecladoMorse = findViewById(R.id.tecladoMorse);
        this.btnPonto = findViewById(R.id.button_ponto);
        this.btnTraco = findViewById(R.id.button_traco);
        this.btnLetra = findViewById(R.id.button_letra);
        this.btnPalavra = findViewById(R.id.button_palavra);

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
            public void onClick(View v) { onClickDecryptResultButton(v);
            }
        });

        this.btnTecladoQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonQ(v);
            }
        });
        this.btnTecladoW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonW(v);
            }
        });
        this.btnTecladoE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonE(v);
            }
        });
        this.btnTecladoR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonR(v);
            }
        });
        this.btnTecladoT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonT(v);
            }
        });
        this.btnTecladoY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonY(v);
            }
        });
        this.btnTecladoU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonU(v);
            }
        });
        this.btnTecladoI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonI(v);
            }
        });
        this.btnTecladoO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonO(v);
            }
        });
        this.btnTecladoP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonP(v);
            }
        });
        this.btnTecladoA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonA(v);
            }
        });
        this.btnTecladoS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonS(v);
            }
        });
        this.btnTecladoD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonD(v);
            }
        });
        this.btnTecladoF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonF(v);
            }
        });
        this.btnTecladoG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonG(v);
            }
        });
        this.btnTecladoH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonH(v);
            }
        });
        this.btnTecladoJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonJ(v);
            }
        });
        this.btnTecladoK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonK(v);
            }
        });
        this.btnTecladoL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonL(v);
            }
        });
        this.btnTecladoZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonZ(v);
            }
        });
        this.btnTecladoX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonX(v);
            }
        });
        this.btnTecladoC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonC(v);
            }
        });
        this.btnTecladoV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonV(v);
            }
        });
        this.btnTecladoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonB(v);
            }
        });
        this.btnTecladoN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonN(v);
            }
        });
        this.btnTecladoM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonM(v);
            }
        });
        this.btnTecladoSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {onClickButtonSpace(v);
            }
        });

        this.btnPonto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {onClickButtonPonto(v);
            }
        });
        this.btnTraco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {onClickButtonTraco(v);
            }
        });
        this.btnLetra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {onClickButtonLetra(v);
            }
        });
        this.btnPalavra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {onClickButtonPalavra(v);
            }
        });

    }

    /* Eventos */

    private void onSelectedDropDesencriptacoes(AdapterView<?> parent, View view, int position, long id) {
        this.posCifraSelecionada = position;

        if(position == 0){
            etxtPassInput.setVisibility(view.INVISIBLE);
            etxtMenInput.setVisibility(view.INVISIBLE);
            llTecaldoImagens.setVisibility(view.INVISIBLE);
            llTecladoMorse.setVisibility(view.INVISIBLE);
        }
        else if(isPassCipher()){
            etxtMenInput.setVisibility(view.VISIBLE);
            etxtPassInput.setVisibility(view.VISIBLE);
            llTecaldoImagens.setVisibility(view.INVISIBLE);
            llTecladoMorse.setVisibility(view.INVISIBLE);
        }
        else if(isImageCipher()){
            etxtMenInput.setVisibility(view.VISIBLE);
            etxtPassInput.setVisibility(view.INVISIBLE);

            //this.imageMessage = new ImageTextMessage(getResources().getString(getResources().getIdentifier(getCipherImageName(), "string", getPackageName())));

            if(this.posCifraSelecionada == 19 || this.posCifraSelecionada == 22){
                if(this.posCifraSelecionada == 19){
                    this.btnPonto.setBackgroundResource(R.drawable.morsek_dot);
                    this.btnTraco.setBackgroundResource(R.drawable.morsek_iphen);
                }
                else{
                    this.btnPonto.setBackgroundResource(R.drawable.morsep_dot);
                    this.btnTraco.setBackgroundResource(R.drawable.morsep_iphen);
                }

                llTecladoMorse.setVisibility(view.VISIBLE);
                llTecaldoImagens.setVisibility(view.INVISIBLE);
            }
            else{
                if(this.posCifraSelecionada == 3){
                    this.btnTecladoQ.setBackgroundResource(R.drawable.angular_q);
                    this.btnTecladoW.setBackgroundResource(R.drawable.angular_w);
                    this.btnTecladoE.setBackgroundResource(R.drawable.angular_e);
                    this.btnTecladoR.setBackgroundResource(R.drawable.angular_r);
                    this.btnTecladoT.setBackgroundResource(R.drawable.angular_t);
                    this.btnTecladoY.setBackgroundResource(R.drawable.angular_y);
                    this.btnTecladoU.setBackgroundResource(R.drawable.angular_u);
                    this.btnTecladoI.setBackgroundResource(R.drawable.angular_i);
                    this.btnTecladoO.setBackgroundResource(R.drawable.angular_o);
                    this.btnTecladoP.setBackgroundResource(R.drawable.angular_p);
                    this.btnTecladoA.setBackgroundResource(R.drawable.angular_a);
                    this.btnTecladoS.setBackgroundResource(R.drawable.angular_s);
                    this.btnTecladoD.setBackgroundResource(R.drawable.angular_d);
                    this.btnTecladoF.setBackgroundResource(R.drawable.angular_f);
                    this.btnTecladoG.setBackgroundResource(R.drawable.angular_g);
                    this.btnTecladoH.setBackgroundResource(R.drawable.angular_h);
                    this.btnTecladoJ.setBackgroundResource(R.drawable.angular_j);
                    this.btnTecladoK.setBackgroundResource(R.drawable.angular_k);
                    this.btnTecladoL.setBackgroundResource(R.drawable.angular_l);
                    this.btnTecladoZ.setBackgroundResource(R.drawable.angular_z);
                    this.btnTecladoX.setBackgroundResource(R.drawable.angular_x);
                    this.btnTecladoC.setBackgroundResource(R.drawable.angular_c);
                    this.btnTecladoV.setBackgroundResource(R.drawable.angular_v);
                    this.btnTecladoB.setBackgroundResource(R.drawable.angular_b);
                    this.btnTecladoN.setBackgroundResource(R.drawable.angular_n);
                    this.btnTecladoM.setBackgroundResource(R.drawable.angular_m);
                }
                else if(this.posCifraSelecionada == 8){
                    this.btnTecladoQ.setBackgroundResource(R.drawable.braille_q);
                    this.btnTecladoW.setBackgroundResource(R.drawable.braille_w);
                    this.btnTecladoE.setBackgroundResource(R.drawable.braille_e);
                    this.btnTecladoR.setBackgroundResource(R.drawable.braille_r);
                    this.btnTecladoT.setBackgroundResource(R.drawable.braille_t);
                    this.btnTecladoY.setBackgroundResource(R.drawable.braille_y);
                    this.btnTecladoU.setBackgroundResource(R.drawable.braille_u);
                    this.btnTecladoI.setBackgroundResource(R.drawable.braille_i);
                    this.btnTecladoO.setBackgroundResource(R.drawable.braille_o);
                    this.btnTecladoP.setBackgroundResource(R.drawable.braille_p);
                    this.btnTecladoA.setBackgroundResource(R.drawable.braille_a);
                    this.btnTecladoS.setBackgroundResource(R.drawable.braille_s);
                    this.btnTecladoD.setBackgroundResource(R.drawable.braille_d);
                    this.btnTecladoF.setBackgroundResource(R.drawable.braille_f);
                    this.btnTecladoG.setBackgroundResource(R.drawable.braille_g);
                    this.btnTecladoH.setBackgroundResource(R.drawable.braille_h);
                    this.btnTecladoJ.setBackgroundResource(R.drawable.braille_j);
                    this.btnTecladoK.setBackgroundResource(R.drawable.braille_k);
                    this.btnTecladoL.setBackgroundResource(R.drawable.braille_l);
                    this.btnTecladoZ.setBackgroundResource(R.drawable.braille_z);
                    this.btnTecladoX.setBackgroundResource(R.drawable.braille_x);
                    this.btnTecladoC.setBackgroundResource(R.drawable.braille_c);
                    this.btnTecladoV.setBackgroundResource(R.drawable.braille_v);
                    this.btnTecladoB.setBackgroundResource(R.drawable.braille_b);
                    this.btnTecladoN.setBackgroundResource(R.drawable.braille_n);
                    this.btnTecladoM.setBackgroundResource(R.drawable.braille_m);
                }
                else if(this.posCifraSelecionada == 10){
                    this.btnTecladoQ.setBackgroundResource(R.drawable.chinese1_q);
                    this.btnTecladoW.setBackgroundResource(R.drawable.chinese1_w);
                    this.btnTecladoE.setBackgroundResource(R.drawable.chinese1_e);
                    this.btnTecladoR.setBackgroundResource(R.drawable.chinese1_r);
                    this.btnTecladoT.setBackgroundResource(R.drawable.chinese1_t);
                    this.btnTecladoY.setBackgroundResource(R.drawable.chinese1_y);
                    this.btnTecladoU.setBackgroundResource(R.drawable.chinese1_u);
                    this.btnTecladoI.setBackgroundResource(R.drawable.chinese1_i);
                    this.btnTecladoO.setBackgroundResource(R.drawable.chinese1_o);
                    this.btnTecladoP.setBackgroundResource(R.drawable.chinese1_p);
                    this.btnTecladoA.setBackgroundResource(R.drawable.chinese1_a);
                    this.btnTecladoS.setBackgroundResource(R.drawable.chinese1_s);
                    this.btnTecladoD.setBackgroundResource(R.drawable.chinese1_d);
                    this.btnTecladoF.setBackgroundResource(R.drawable.chinese1_f);
                    this.btnTecladoG.setBackgroundResource(R.drawable.chinese1_g);
                    this.btnTecladoH.setBackgroundResource(R.drawable.chinese1_h);
                    this.btnTecladoJ.setBackgroundResource(R.drawable.chinese1_j);
                    this.btnTecladoK.setBackgroundResource(R.drawable.chinese1_k);
                    this.btnTecladoL.setBackgroundResource(R.drawable.chinese1_l);
                    this.btnTecladoZ.setBackgroundResource(R.drawable.chinese1_z);
                    this.btnTecladoX.setBackgroundResource(R.drawable.chinese1_x);
                    this.btnTecladoC.setBackgroundResource(R.drawable.chinese1_c);
                    this.btnTecladoV.setBackgroundResource(R.drawable.chinese1_v);
                    this.btnTecladoB.setBackgroundResource(R.drawable.chinese1_b);
                    this.btnTecladoN.setBackgroundResource(R.drawable.chinese1_n);
                    this.btnTecladoM.setBackgroundResource(R.drawable.chinese1_m);
                }
                else if(this.posCifraSelecionada == 11){
                    this.btnTecladoQ.setBackgroundResource(R.drawable.chinese2_q);
                    this.btnTecladoW.setBackgroundResource(R.drawable.chinese2_w);
                    this.btnTecladoE.setBackgroundResource(R.drawable.chinese2_e);
                    this.btnTecladoR.setBackgroundResource(R.drawable.chinese2_r);
                    this.btnTecladoT.setBackgroundResource(R.drawable.chinese2_t);
                    this.btnTecladoY.setBackgroundResource(R.drawable.chinese2_y);
                    this.btnTecladoU.setBackgroundResource(R.drawable.chinese2_u);
                    this.btnTecladoI.setBackgroundResource(R.drawable.chinese2_i);
                    this.btnTecladoO.setBackgroundResource(R.drawable.chinese2_o);
                    this.btnTecladoP.setBackgroundResource(R.drawable.chinese2_p);
                    this.btnTecladoA.setBackgroundResource(R.drawable.chinese2_a);
                    this.btnTecladoS.setBackgroundResource(R.drawable.chinese2_s);
                    this.btnTecladoD.setBackgroundResource(R.drawable.chinese2_d);
                    this.btnTecladoF.setBackgroundResource(R.drawable.chinese2_f);
                    this.btnTecladoG.setBackgroundResource(R.drawable.chinese2_g);
                    this.btnTecladoH.setBackgroundResource(R.drawable.chinese2_h);
                    this.btnTecladoJ.setBackgroundResource(R.drawable.chinese2_j);
                    this.btnTecladoK.setBackgroundResource(R.drawable.chinese2_k);
                    this.btnTecladoL.setBackgroundResource(R.drawable.chinese2_l);
                    this.btnTecladoZ.setBackgroundResource(R.drawable.chinese2_z);
                    this.btnTecladoX.setBackgroundResource(R.drawable.chinese2_x);
                    this.btnTecladoC.setBackgroundResource(R.drawable.chinese2_c);
                    this.btnTecladoV.setBackgroundResource(R.drawable.chinese2_v);
                    this.btnTecladoB.setBackgroundResource(R.drawable.chinese2_b);
                    this.btnTecladoN.setBackgroundResource(R.drawable.chinese2_n);
                    this.btnTecladoM.setBackgroundResource(R.drawable.chinese2_m);
                }
                else{
                    this.btnTecladoQ.setBackgroundResource(R.drawable.homograph_q);
                    this.btnTecladoW.setBackgroundResource(R.drawable.homograph_w);
                    this.btnTecladoE.setBackgroundResource(R.drawable.homograph_e);
                    this.btnTecladoR.setBackgroundResource(R.drawable.homograph_r);
                    this.btnTecladoT.setBackgroundResource(R.drawable.homograph_t);
                    this.btnTecladoY.setBackgroundResource(R.drawable.homograph_y);
                    this.btnTecladoU.setBackgroundResource(R.drawable.homograph_u);
                    this.btnTecladoI.setBackgroundResource(R.drawable.homograph_i);
                    this.btnTecladoO.setBackgroundResource(R.drawable.homograph_o);
                    this.btnTecladoP.setBackgroundResource(R.drawable.homograph_p);
                    this.btnTecladoA.setBackgroundResource(R.drawable.homograph_a);
                    this.btnTecladoS.setBackgroundResource(R.drawable.homograph_s);
                    this.btnTecladoD.setBackgroundResource(R.drawable.homograph_d);
                    this.btnTecladoF.setBackgroundResource(R.drawable.homograph_f);
                    this.btnTecladoG.setBackgroundResource(R.drawable.homograph_g);
                    this.btnTecladoH.setBackgroundResource(R.drawable.homograph_h);
                    this.btnTecladoJ.setBackgroundResource(R.drawable.homograph_j);
                    this.btnTecladoK.setBackgroundResource(R.drawable.homograph_k);
                    this.btnTecladoL.setBackgroundResource(R.drawable.homograph_l);
                    this.btnTecladoZ.setBackgroundResource(R.drawable.homograph_z);
                    this.btnTecladoX.setBackgroundResource(R.drawable.homograph_x);
                    this.btnTecladoC.setBackgroundResource(R.drawable.homograph_c);
                    this.btnTecladoV.setBackgroundResource(R.drawable.homograph_v);
                    this.btnTecladoB.setBackgroundResource(R.drawable.homograph_b);
                    this.btnTecladoN.setBackgroundResource(R.drawable.homograph_n);
                    this.btnTecladoM.setBackgroundResource(R.drawable.homograph_m);
                }

                llTecladoMorse.setVisibility(view.INVISIBLE);
                llTecaldoImagens.setVisibility(view.VISIBLE);
            }
        }
        else{
            etxtMenInput.setVisibility(view.VISIBLE);
            etxtPassInput.setVisibility(view.INVISIBLE);
            llTecaldoImagens.setVisibility(view.INVISIBLE);
            llTecladoMorse.setVisibility(view.INVISIBLE);
        }
    }

    private String getCipherImageName() {
        return CIPHER_IMAGE_RESOURCE_NAME + posCifraSelecionada;
    }

    private boolean isPassCipher() {
        if(this.posCifraSelecionada == 2 || this.posCifraSelecionada == 4 || this.posCifraSelecionada == 5 ||
                this.posCifraSelecionada == 7 || this.posCifraSelecionada == 12 || this.posCifraSelecionada == 13 ||
                this.posCifraSelecionada == 14 || this.posCifraSelecionada == 16 || this.posCifraSelecionada == 26 ||
                this.posCifraSelecionada == 28)
            return true;

        return false;
    }

    private boolean isImageCipher() {
        if(this.posCifraSelecionada == 3 || this.posCifraSelecionada == 8 || this.posCifraSelecionada == 10 ||
                this.posCifraSelecionada == 11 || this.posCifraSelecionada == 15 ||
                this.posCifraSelecionada == 19 || this.posCifraSelecionada == 22)
            return true;

        return false;
    }

    private void onClickDecryptResultButton(View view) {
        Log.d(TAG, "onClick: Cliquei no botão de resultado da encriptação");

        if (posCifraSelecionada == 0) {
            showToast("Tem que ser selecionada uma cifra para continuar");
        } else {
            this.mensagem = etxtMenInput.getText().toString();
            this.password = etxtPassInput.getText().toString();
            this.strCifraSelecionada = this.myAdapter.getItem(posCifraSelecionada);
            this.imageMessage = new ImageTextMessage(this.mensagem, getResources().getString(getResources().getIdentifier(getCipherImageName(), "string", getPackageName())));

            triggerDecrypt();
        }
    }

    private void onClickButtonQ(View view){
        this.etxtMenInput.append("Q");
    }
    private void onClickButtonW(View view){
        this.etxtMenInput.append("W");
    }
    private void onClickButtonE(View view){
        this.etxtMenInput.append("E");
    }
    private void onClickButtonR(View view){
        this.etxtMenInput.append("R");
    }
    private void onClickButtonT(View view){
        this.etxtMenInput.append("T");
    }
    private void onClickButtonY(View view){
        this.etxtMenInput.append("Y");
    }
    private void onClickButtonU(View view){
        this.etxtMenInput.append("U");
    }
    private void onClickButtonI(View view){
        this.txtvMenDecript.append("I");
    }
    private void onClickButtonO(View view){
        this.etxtMenInput.append("O");
    }
    private void onClickButtonP(View view){
        this.etxtMenInput.append("P");
    }
    private void onClickButtonA(View view){
        this.etxtMenInput.append("A");
    }
    private void onClickButtonS(View view){
        this.etxtMenInput.append("S");
    }
    private void onClickButtonD(View view){
        this.etxtMenInput.append("D");
    }
    private void onClickButtonF(View view){
        this.etxtMenInput.append("F");
    }
    private void onClickButtonG(View view){
        this.etxtMenInput.append("G");
    }
    private void onClickButtonH(View view){
        this.etxtMenInput.append("H");
    }
    private void onClickButtonJ(View view){
        this.etxtMenInput.append("J");
    }
    private void onClickButtonK(View view){
        this.etxtMenInput.append("K");
    }
    private void onClickButtonL(View view){
        this.etxtMenInput.append("L");
    }
    private void onClickButtonZ(View view){
        this.etxtMenInput.append("Z");
    }
    private void onClickButtonX(View view){
        this.etxtMenInput.append("X");
    }
    private void onClickButtonC(View view){
        this.etxtMenInput.append("C");
    }
    private void onClickButtonV(View view){
        this.etxtMenInput.append("V");
    }
    private void onClickButtonB(View view){
        this.etxtMenInput.append("B");
    }
    private void onClickButtonN(View view){
        this.etxtMenInput.append("N");
    }
    private void onClickButtonM(View view){
        this.etxtMenInput.append("M");
    }
    private void onClickButtonSpace(View view){
        this.etxtMenInput.append(" ");
    }
    private void onClickButtonPalavra(View view){
        this.etxtMenInput.append(" / ");
    }
    private void onClickButtonLetra(View view){
        this.etxtMenInput.append(" ");
    }
    private void onClickButtonPonto(View view){
        this.etxtMenInput.append(".");
    }
    private void onClickButtonTraco(View view){
        this.etxtMenInput.append("-");
    }

    /* Outros métodos de instância */

    private void triggerDecrypt() {
        EcraResultadoModel result = this.ecraDesencriptacaoPresenter.decrypt(this.imageMessage, this.password, this.strCifraSelecionada);

        if (result != null) {
            if(!result.hasErrors()) {
                Intent entrarEcraResultado = new Intent(EcraDesencriptacaoActivityView.this, EcraResultadoActivityView.class);

                Bundle params = new Bundle();
                params.putParcelable("model", result);
                entrarEcraResultado.putExtras(params);

                startActivity(entrarEcraResultado);
            }
            else showToast(result);
        } else showToast("Não foi possível cifrar a mensagem solicitada.");
    }

    private void showToast(EcraResultadoModel result) {
        String text = getResources().getString(getResources().getIdentifier(result.getErrorCode(), "string", getPackageName()));

        if(result.hasErrorParams())
            text = String.format(text, result.getErrorParams());

        Toast.makeText(EcraDesencriptacaoActivityView.this, text, Toast.LENGTH_SHORT).show();
    }

    private void showToast(String text) {
        Toast.makeText(EcraDesencriptacaoActivityView.this, text, Toast.LENGTH_SHORT).show();
    }
}
