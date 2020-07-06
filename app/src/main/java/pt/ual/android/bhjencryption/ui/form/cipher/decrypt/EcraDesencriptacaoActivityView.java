package pt.ual.android.bhjencryption.ui.form.cipher.decrypt;

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
import android.widget.ImageButton;
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
    private Button btnDescription;

    private LinearLayout llTecaldoImagens;
    private ImageButton btnTecladoQ;
    private ImageButton btnTecladoW;
    private ImageButton btnTecladoE;
    private ImageButton btnTecladoR;
    private ImageButton btnTecladoT;
    private ImageButton btnTecladoY;
    private ImageButton btnTecladoU;
    private ImageButton btnTecladoI;
    private ImageButton btnTecladoO;
    private ImageButton btnTecladoP;
    private ImageButton btnTecladoA;
    private ImageButton btnTecladoS;
    private ImageButton btnTecladoD;
    private ImageButton btnTecladoF;
    private ImageButton btnTecladoG;
    private ImageButton btnTecladoH;
    private ImageButton btnTecladoJ;
    private ImageButton btnTecladoK;
    private ImageButton btnTecladoL;
    private ImageButton btnTecladoZ;
    private ImageButton btnTecladoX;
    private ImageButton btnTecladoC;
    private ImageButton btnTecladoV;
    private ImageButton btnTecladoB;
    private ImageButton btnTecladoN;
    private ImageButton btnTecladoM;
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
        this.btnDescription = findViewById(R.id.btDecriptDescription);

        this.llTecaldoImagens = findViewById(R.id.tecladoImagens);
        this.btnTecladoQ = findViewById(R.id.imageButton_q);
        this.btnTecladoW = findViewById(R.id.imageButton_w);
        this.btnTecladoE = findViewById(R.id.imageButton_e);
        this.btnTecladoR = findViewById(R.id.imageButton_r);
        this.btnTecladoT = findViewById(R.id.imageButton_t);
        this.btnTecladoY = findViewById(R.id.imageButton_y);
        this.btnTecladoU = findViewById(R.id.imageButton_u);
        this.btnTecladoI = findViewById(R.id.imageButton_i);
        this.btnTecladoO = findViewById(R.id.imageButton_o);
        this.btnTecladoP = findViewById(R.id.imageButton_p);
        this.btnTecladoA = findViewById(R.id.imageButton_a);
        this.btnTecladoS = findViewById(R.id.imageButton_s);
        this.btnTecladoD = findViewById(R.id.imageButton_d);
        this.btnTecladoF = findViewById(R.id.imageButton_f);
        this.btnTecladoG = findViewById(R.id.imageButton_g);
        this.btnTecladoH = findViewById(R.id.imageButton_h);
        this.btnTecladoJ = findViewById(R.id.imageButton_j);
        this.btnTecladoK = findViewById(R.id.imageButton_k);
        this.btnTecladoL = findViewById(R.id.imageButton_l);
        this.btnTecladoZ = findViewById(R.id.imageButton_z);
        this.btnTecladoX = findViewById(R.id.imageButton_x);
        this.btnTecladoC = findViewById(R.id.imageButton_c);
        this.btnTecladoV = findViewById(R.id.imageButton_v);
        this.btnTecladoB = findViewById(R.id.imageButton_b);
        this.btnTecladoN = findViewById(R.id.imageButton_n);
        this.btnTecladoM = findViewById(R.id.imageButton_m);
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
        this.btnDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openLink = new Intent();
                openLink.setAction(Intent.ACTION_VIEW);
                openLink.addCategory(Intent.CATEGORY_BROWSABLE);
                switch (posCifraSelecionada){
                    case 1:
                        openLink.setData(Uri.parse("https://github.com/mrhenry549/BHJEncryption/wiki/Alfabeto-Invertido"));
                        break;
                    case 2:
                        openLink.setData(Uri.parse("https://github.com/mrhenry549/BHJEncryption/wiki/Alfabeto-Numeral"));
                        break;
                    case 3:
                        openLink.setData(Uri.parse("https://github.com/mrhenry549/BHJEncryption/wiki/Angular"));
                        break;
                    case 4:
                        openLink.setData(Uri.parse("https://github.com/mrhenry549/BHJEncryption/wiki/Batalha-Naval"));
                        break;
                    case 5:
                        openLink.setData(Uri.parse("https://github.com/mrhenry549/BHJEncryption/wiki/Caracol"));
                        break;
                    case 6:
                        openLink.setData(Uri.parse("https://github.com/mrhenry549/BHJEncryption/wiki/Caranguejo"));
                        break;
                    case 7:
                        openLink.setData(Uri.parse("https://github.com/mrhenry549/BHJEncryption/wiki/César"));
                        break;
                    case 8:
                        openLink.setData(Uri.parse("https://github.com/mrhenry549/BHJEncryption/wiki/Código-de-Braile-Falso"));
                        break;
                    case 9:
                        openLink.setData(Uri.parse("https://github.com/mrhenry549/BHJEncryption/wiki/Código-Mais-3"));
                        break;
                    case 10:
                        openLink.setData(Uri.parse("https://github.com/mrhenry549/BHJEncryption/wiki/Código-Chinês-1"));
                        break;
                    case 11:
                        openLink.setData(Uri.parse("https://github.com/mrhenry549/BHJEncryption/wiki/Código-Chinês-2"));
                        break;
                    case 12:
                        openLink.setData(Uri.parse("https://github.com/mrhenry549/BHJEncryption/wiki/Data"));
                        break;
                    case 13:
                        openLink.setData(Uri.parse("https://github.com/mrhenry549/BHJEncryption/wiki/Frase-Chave-Vertical"));
                        break;
                    case 14:
                        openLink.setData(Uri.parse("https://github.com/mrhenry549/BHJEncryption/wiki/Frase-Chave-Horizontal"));
                        break;
                    case 15:
                        openLink.setData(Uri.parse("https://github.com/mrhenry549/BHJEncryption/wiki/Homógrafo"));
                        break;
                    case 16:
                        openLink.setData(Uri.parse("https://github.com/mrhenry549/BHJEncryption/wiki/Horizontal"));
                        break;
                    case 17:
                        openLink.setData(Uri.parse("https://github.com/mrhenry549/BHJEncryption/wiki/Metades"));
                        break;
                    case 18:
                        openLink.setData(Uri.parse("https://github.com/mrhenry549/BHJEncryption/wiki/C%C3%B3digo-de-Morse#internacional"));
                        break;
                    case 19:
                        openLink.setData(Uri.parse("https://github.com/mrhenry549/BHJEncryption/wiki/C%C3%B3digo-de-Morse#n%C3%B3s"));
                        break;
                    case 20:
                        openLink.setData(Uri.parse("https://github.com/mrhenry549/BHJEncryption/wiki/Passa-um-Melro"));
                        break;
                    case 21:
                        openLink.setData(Uri.parse("https://github.com/mrhenry549/BHJEncryption/wiki/Passa-dois-Melros"));
                        break;
                    case 22:
                        openLink.setData(Uri.parse("https://github.com/mrhenry549/BHJEncryption/wiki/C%C3%B3digo-de-Morse#picos"));
                        break;
                    case 23:
                        openLink.setData(Uri.parse("https://github.com/mrhenry549/BHJEncryption/wiki/Primeira-Letra-Falsa"));
                        break;
                    case 24:
                        openLink.setData(Uri.parse("https://github.com/mrhenry549/BHJEncryption/wiki/Romano-Árabe"));
                        break;
                    case 25:
                        openLink.setData(Uri.parse("https://github.com/mrhenry549/BHJEncryption/wiki/SMS"));
                        break;
                    case 26:
                        openLink.setData(Uri.parse("https://github.com/mrhenry549/BHJEncryption/wiki/Alfabeto-Transposto"));
                        break;
                    case 27:
                        openLink.setData(Uri.parse("https://github.com/mrhenry549/BHJEncryption/wiki/Última-Letra-Falsa"));
                        break;
                    case 28:
                        openLink.setData(Uri.parse("https://github.com/mrhenry549/BHJEncryption/wiki/Vertical"));
                        break;
                    case 29:
                        openLink.setData(Uri.parse("https://github.com/mrhenry549/BHJEncryption/wiki/Vogais-por-Pontos"));
                        break;
                    default:
                        break;
                }
                startActivity(openLink);
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
            btnDescription.setVisibility(view.INVISIBLE);
            llTecaldoImagens.setVisibility(view.INVISIBLE);
            llTecladoMorse.setVisibility(view.INVISIBLE);
        }
        else if(isPassCipher()){
            etxtMenInput.setVisibility(view.VISIBLE);
            etxtPassInput.setVisibility(view.VISIBLE);
            btnDescription.setVisibility(view.VISIBLE);
            llTecaldoImagens.setVisibility(view.INVISIBLE);
            llTecladoMorse.setVisibility(view.INVISIBLE);
        }
        else if(isImageCipher()){
            etxtMenInput.setVisibility(view.VISIBLE);
            etxtPassInput.setVisibility(view.INVISIBLE);
            btnDescription.setVisibility(view.VISIBLE);

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
                    this.btnTecladoA.setImageResource(R.drawable.angular_a);
                    this.btnTecladoB.setImageResource(R.drawable.angular_b);
                    this.btnTecladoC.setImageResource(R.drawable.angular_c);
                    this.btnTecladoD.setImageResource(R.drawable.angular_d);
                    this.btnTecladoE.setImageResource(R.drawable.angular_e);
                    this.btnTecladoF.setImageResource(R.drawable.angular_f);
                    this.btnTecladoG.setImageResource(R.drawable.angular_g);
                    this.btnTecladoH.setImageResource(R.drawable.angular_h);
                    this.btnTecladoI.setImageResource(R.drawable.angular_i);
                    this.btnTecladoJ.setImageResource(R.drawable.angular_j);
                    this.btnTecladoK.setImageResource(R.drawable.angular_k);
                    this.btnTecladoL.setImageResource(R.drawable.angular_l);
                    this.btnTecladoM.setImageResource(R.drawable.angular_m);
                    this.btnTecladoN.setImageResource(R.drawable.angular_n);
                    this.btnTecladoO.setImageResource(R.drawable.angular_o);
                    this.btnTecladoP.setImageResource(R.drawable.angular_p);
                    this.btnTecladoQ.setImageResource(R.drawable.angular_q);
                    this.btnTecladoR.setImageResource(R.drawable.angular_r);
                    this.btnTecladoS.setImageResource(R.drawable.angular_s);
                    this.btnTecladoT.setImageResource(R.drawable.angular_t);
                    this.btnTecladoU.setImageResource(R.drawable.angular_u);
                    this.btnTecladoV.setImageResource(R.drawable.angular_v);
                    this.btnTecladoW.setImageResource(R.drawable.angular_w);
                    this.btnTecladoX.setImageResource(R.drawable.angular_x);
                    this.btnTecladoY.setImageResource(R.drawable.angular_y);
                    this.btnTecladoZ.setImageResource(R.drawable.angular_z);
                }
                else if(this.posCifraSelecionada == 8){
                    this.btnTecladoA.setImageResource(R.drawable.braille_a);
                    this.btnTecladoB.setImageResource(R.drawable.braille_b);
                    this.btnTecladoC.setImageResource(R.drawable.braille_c);
                    this.btnTecladoD.setImageResource(R.drawable.braille_d);
                    this.btnTecladoE.setImageResource(R.drawable.braille_e);
                    this.btnTecladoF.setImageResource(R.drawable.braille_f);
                    this.btnTecladoG.setImageResource(R.drawable.braille_g);
                    this.btnTecladoH.setImageResource(R.drawable.braille_h);
                    this.btnTecladoI.setImageResource(R.drawable.braille_i);
                    this.btnTecladoJ.setImageResource(R.drawable.braille_j);
                    this.btnTecladoK.setImageResource(R.drawable.braille_k);
                    this.btnTecladoL.setImageResource(R.drawable.braille_l);
                    this.btnTecladoM.setImageResource(R.drawable.braille_m);
                    this.btnTecladoN.setImageResource(R.drawable.braille_n);
                    this.btnTecladoO.setImageResource(R.drawable.braille_o);
                    this.btnTecladoP.setImageResource(R.drawable.braille_p);
                    this.btnTecladoQ.setImageResource(R.drawable.braille_q);
                    this.btnTecladoR.setImageResource(R.drawable.braille_r);
                    this.btnTecladoS.setImageResource(R.drawable.braille_s);
                    this.btnTecladoT.setImageResource(R.drawable.braille_t);
                    this.btnTecladoU.setImageResource(R.drawable.braille_u);
                    this.btnTecladoV.setImageResource(R.drawable.braille_v);
                    this.btnTecladoW.setImageResource(R.drawable.braille_w);
                    this.btnTecladoX.setImageResource(R.drawable.braille_x);
                    this.btnTecladoY.setImageResource(R.drawable.braille_y);
                    this.btnTecladoZ.setImageResource(R.drawable.braille_z);
                }
                else if(this.posCifraSelecionada == 10){
                    this.btnTecladoA.setImageResource(R.drawable.chinese1_a);
                    this.btnTecladoB.setImageResource(R.drawable.chinese1_b);
                    this.btnTecladoC.setImageResource(R.drawable.chinese1_c);
                    this.btnTecladoD.setImageResource(R.drawable.chinese1_d);
                    this.btnTecladoE.setImageResource(R.drawable.chinese1_e);
                    this.btnTecladoF.setImageResource(R.drawable.chinese1_f);
                    this.btnTecladoG.setImageResource(R.drawable.chinese1_g);
                    this.btnTecladoH.setImageResource(R.drawable.chinese1_h);
                    this.btnTecladoI.setImageResource(R.drawable.chinese1_i);
                    this.btnTecladoJ.setImageResource(R.drawable.chinese1_j);
                    this.btnTecladoK.setImageResource(R.drawable.chinese1_k);
                    this.btnTecladoL.setImageResource(R.drawable.chinese1_l);
                    this.btnTecladoM.setImageResource(R.drawable.chinese1_m);
                    this.btnTecladoN.setImageResource(R.drawable.chinese1_n);
                    this.btnTecladoO.setImageResource(R.drawable.chinese1_o);
                    this.btnTecladoP.setImageResource(R.drawable.chinese1_p);
                    this.btnTecladoQ.setImageResource(R.drawable.chinese1_q);
                    this.btnTecladoR.setImageResource(R.drawable.chinese1_r);
                    this.btnTecladoS.setImageResource(R.drawable.chinese1_s);
                    this.btnTecladoT.setImageResource(R.drawable.chinese1_t);
                    this.btnTecladoU.setImageResource(R.drawable.chinese1_u);
                    this.btnTecladoV.setImageResource(R.drawable.chinese1_v);
                    this.btnTecladoW.setImageResource(R.drawable.chinese1_w);
                    this.btnTecladoX.setImageResource(R.drawable.chinese1_x);
                    this.btnTecladoY.setImageResource(R.drawable.chinese1_y);
                    this.btnTecladoZ.setImageResource(R.drawable.chinese1_z);
                }
                else if(this.posCifraSelecionada == 11){
                    this.btnTecladoA.setImageResource(R.drawable.chinese2_a);
                    this.btnTecladoB.setImageResource(R.drawable.chinese2_b);
                    this.btnTecladoC.setImageResource(R.drawable.chinese2_c);
                    this.btnTecladoD.setImageResource(R.drawable.chinese2_d);
                    this.btnTecladoE.setImageResource(R.drawable.chinese2_e);
                    this.btnTecladoF.setImageResource(R.drawable.chinese2_f);
                    this.btnTecladoG.setImageResource(R.drawable.chinese2_g);
                    this.btnTecladoH.setImageResource(R.drawable.chinese2_h);
                    this.btnTecladoI.setImageResource(R.drawable.chinese2_i);
                    this.btnTecladoJ.setImageResource(R.drawable.chinese2_j);
                    this.btnTecladoK.setImageResource(R.drawable.chinese2_k);
                    this.btnTecladoL.setImageResource(R.drawable.chinese2_l);
                    this.btnTecladoM.setImageResource(R.drawable.chinese2_m);
                    this.btnTecladoN.setImageResource(R.drawable.chinese2_n);
                    this.btnTecladoO.setImageResource(R.drawable.chinese2_o);
                    this.btnTecladoP.setImageResource(R.drawable.chinese2_p);
                    this.btnTecladoQ.setImageResource(R.drawable.chinese2_q);
                    this.btnTecladoR.setImageResource(R.drawable.chinese2_r);
                    this.btnTecladoS.setImageResource(R.drawable.chinese2_s);
                    this.btnTecladoT.setImageResource(R.drawable.chinese2_t);
                    this.btnTecladoU.setImageResource(R.drawable.chinese2_u);
                    this.btnTecladoV.setImageResource(R.drawable.chinese2_v);
                    this.btnTecladoW.setImageResource(R.drawable.chinese2_w);
                    this.btnTecladoX.setImageResource(R.drawable.chinese2_x);
                    this.btnTecladoY.setImageResource(R.drawable.chinese2_y);
                    this.btnTecladoZ.setImageResource(R.drawable.chinese2_z);
                }
                else{
                    this.btnTecladoQ.setImageResource(R.drawable.homograph_q);
                    this.btnTecladoW.setImageResource(R.drawable.homograph_w);
                    this.btnTecladoE.setImageResource(R.drawable.homograph_e);
                    this.btnTecladoR.setImageResource(R.drawable.homograph_r);
                    this.btnTecladoT.setImageResource(R.drawable.homograph_t);
                    this.btnTecladoY.setImageResource(R.drawable.homograph_y);
                    this.btnTecladoU.setImageResource(R.drawable.homograph_u);
                    this.btnTecladoI.setImageResource(R.drawable.homograph_i);
                    this.btnTecladoO.setImageResource(R.drawable.homograph_o);
                    this.btnTecladoP.setImageResource(R.drawable.homograph_p);
                    this.btnTecladoA.setImageResource(R.drawable.homograph_a);
                    this.btnTecladoS.setImageResource(R.drawable.homograph_s);
                    this.btnTecladoD.setImageResource(R.drawable.homograph_d);
                    this.btnTecladoF.setImageResource(R.drawable.homograph_f);
                    this.btnTecladoG.setImageResource(R.drawable.homograph_g);
                    this.btnTecladoH.setImageResource(R.drawable.homograph_h);
                    this.btnTecladoJ.setImageResource(R.drawable.homograph_j);
                    this.btnTecladoK.setImageResource(R.drawable.homograph_k);
                    this.btnTecladoL.setImageResource(R.drawable.homograph_l);
                    this.btnTecladoZ.setImageResource(R.drawable.homograph_z);
                    this.btnTecladoX.setImageResource(R.drawable.homograph_x);
                    this.btnTecladoC.setImageResource(R.drawable.homograph_c);
                    this.btnTecladoV.setImageResource(R.drawable.homograph_v);
                    this.btnTecladoB.setImageResource(R.drawable.homograph_b);
                    this.btnTecladoN.setImageResource(R.drawable.homograph_n);
                    this.btnTecladoM.setImageResource(R.drawable.homograph_m);
                }

                llTecladoMorse.setVisibility(view.INVISIBLE);
                llTecaldoImagens.setVisibility(view.VISIBLE);
            }
        }
        else{
            etxtMenInput.setVisibility(view.VISIBLE);
            etxtPassInput.setVisibility(view.INVISIBLE);
            btnDescription.setVisibility(view.VISIBLE);
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

        if (this.posCifraSelecionada == 0) {
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
        this.etxtMenInput.append("I");
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
