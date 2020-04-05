package pt.ual.android.bhjencryption.ui.form.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pt.ual.android.bhjencryption.ui.form.cipher.decrypt.EcraDesencriptacaoActivityView;
import pt.ual.android.bhjencryption.ui.form.cipher.encrypt.EcraEncriptacaoActivityView;
import pt.ual.android.bhjencryption.R;

/* TODO: fazer refactor a todos os IDs dos controlos (não o fiz, pois, existem várias referências e o refactor poderia levar a problemas.
         É importante normalizar nome, identificá-los com prefixos que identifiquem o tipo de controlos e os nomes serem iguais em todos
         os locais onde são definidos
 */
public class HomeActivityView extends AppCompatActivity implements HomeContract.View {

    private static final String TAG = "HomeActivityView";

    // Presenter
    private HomeContract.Presenter homePresenter;

    // UI controls
    private TextView txtvMenBoasVindas;
    private Button btnEncript;
    private Button btnDecript;

    /* inicialização */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecra_home);
        loadModel();
        initView();
    }

    @Override
    public void initView() {
        // Inicializar o Presenter da View
        this.homePresenter = new HomePresenter(this);

        // Guardar a referência dos elementos/controlos do xml do layout da View (servirá para mais fácil manipulação do controlo)
        this.txtvMenBoasVindas = findViewById(R.id.boasVindas);
        this.btnEncript = findViewById(R.id.btMainEncript);
        this.btnDecript = findViewById(R.id.btMainDecript);

        // Meter texto na TextView (inicializar a TextView)
        this.txtvMenBoasVindas.setText("Bem-vindo ao Encriptador e Desencriptador de Mensagens");

        // Definir qual é o objecto responsável por ficar à escuta quando os botões são premidos (uma vez que a view é a responsável, não é necessário uma classe interna anónima como antes estava)
        this.btnEncript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivityView.this.onClickEncryptButton(v);
            }
        });
        this.btnDecript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivityView.this.onClickDecryptButton(v);
            }
        });
    }

    @Override
    public void loadModel() {

    }

    /* Eventos */

    private void onClickEncryptButton(View view) {
        Log.d(TAG, "onClick: Cliquei no botão de encriptação");

        Intent entrarEcraEncript = new Intent(HomeActivityView.this, EcraEncriptacaoActivityView.class);
        startActivity(entrarEcraEncript);
    }

    private void onClickDecryptButton(View view) {
        Log.d(TAG, "onClick: Cliquei no botão de desencriptação");

        Intent entrarEcraDecript = new Intent(HomeActivityView.this, EcraDesencriptacaoActivityView.class);
        startActivity(entrarEcraDecript);
    }
}