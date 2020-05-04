package pt.ual.android.bhjencryption.ui.form.cipher.encrypt;

import pt.ual.android.bhjencryption.ui.form.cipher.result.EcraResultadoModel;
import pt.ual.android.bhjencryption.utils.cipher.CipherFactory;
import pt.ual.android.bhjencryption.utils.cipher.CipherValidationResult;

public class EcraEncriptacaoPresenter implements EcraEncriptacaoContract.Presenter {

    // instância do model a tratar (declarar quando definido)
    private EcraEncriptacaoContract.View ecraEncriptacaoActivityView; // referência para a View

    public EcraEncriptacaoPresenter(EcraEncriptacaoContract.View ecraEncriptacaoActivityView) {
        // instancia o model (instanciar quando definido)
        this.ecraEncriptacaoActivityView = ecraEncriptacaoActivityView;
    }

    @Override
    public EcraResultadoModel encrypt(String mensagem, String password, String cifraSelecionada, int posCifraSelecionada) {
        CipherFactory cipherFactory = new CipherFactory(mensagem.trim(), password, cifraSelecionada);
        CipherValidationResult cvr = cipherFactory.validateEncrypt();

        if(cvr.hasErrors())
            return new EcraResultadoModel(null, cvr.getCipherErrorCode().getErrorCode());

        return new EcraResultadoModel(cipherFactory.encrypt().getResultAsString());
    }
}
