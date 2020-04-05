package pt.ual.android.bhjencryption.ui.form.cipher.decrypt;

import pt.ual.android.bhjencryption.ui.form.cipher.result.EcraResultadoModel;
import pt.ual.android.bhjencryption.utils.cipher.CipherFactory;
import pt.ual.android.bhjencryption.utils.cipher.CipherResult;

public class EcraDesencriptacaoPresenter implements EcraDesencriptacaoContract.Presenter {

    // instância do model a tratar (declarar quando definido)
    private EcraDesencriptacaoContract.View ecraEncriptacaoActivityView; // referência para a View

    public EcraDesencriptacaoPresenter(EcraDesencriptacaoContract.View ecraDesencriptacaoActivityView) {
        // instancia o model (instanciar quando definido)
        this.ecraEncriptacaoActivityView = ecraDesencriptacaoActivityView;
    }

    @Override
    public EcraResultadoModel decrypt(String mensagem, String password, String cifraSelecionada, int posCifraSelecionada) {
        CipherFactory cipherFactory = new CipherFactory(mensagem, password, cifraSelecionada);
        CipherResult cipherResult = cipherFactory.decrypt();

        return new EcraResultadoModel(cipherResult.getResultAsString());
    }
}
