package pt.ual.android.bhjencryption.ui.form.cipher.decrypt;

import pt.ual.android.bhjencryption.ui.form.cipher.result.EcraResultadoModel;
import pt.ual.android.bhjencryption.utils.cipher.CipherFactory;
import pt.ual.android.bhjencryption.utils.cipher.CipherResult;
import pt.ual.android.bhjencryption.utils.cipher.CipherValidationResult;

public class EcraDesencriptacaoPresenter implements EcraDesencriptacaoContract.Presenter {

    // instância do model a tratar (declarar quando definido)
    private EcraDesencriptacaoContract.View ecraEncriptacaoActivityView; // referência para a View

    public EcraDesencriptacaoPresenter(EcraDesencriptacaoContract.View ecraDesencriptacaoActivityView) {
        // instancia o model (instanciar quando definido)
        this.ecraEncriptacaoActivityView = ecraDesencriptacaoActivityView;
    }

    @Override
    public EcraResultadoModel decrypt(String mensagem, String password, String cifraSelecionada, int posCifraSelecionada) {
        CipherFactory cipherFactory = new CipherFactory(mensagem.trim(), password, cifraSelecionada);
        CipherValidationResult cvr = cipherFactory.validateDecrypt();

        if(cvr.hasErrors())
            return new EcraResultadoModel(null, cvr.getCipherErrorCode().getErrorCode());

        return new EcraResultadoModel(cipherFactory.decrypt().getResultAsString());
    }
}
