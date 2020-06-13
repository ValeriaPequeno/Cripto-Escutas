package pt.ual.android.bhjencryption.ui.form.cipher.decrypt;

import pt.ual.android.bhjencryption.cipher.CipherMessage;
import pt.ual.android.bhjencryption.cipher.CipherResult;
import pt.ual.android.bhjencryption.ui.form.cipher.result.EcraResultadoModel;
import pt.ual.android.bhjencryption.cipher.CipherFactory;
import pt.ual.android.bhjencryption.cipher.CipherValidationResult;
import pt.ual.android.bhjencryption.ui.graphics.ImageTextMessage;

public class EcraDesencriptacaoPresenter implements EcraDesencriptacaoContract.Presenter {

    // instância do model a tratar (declarar quando definido)
    private EcraDesencriptacaoContract.View ecraEncriptacaoActivityView; // referência para a View

    public EcraDesencriptacaoPresenter(EcraDesencriptacaoContract.View ecraDesencriptacaoActivityView) {
        // instancia o model (instanciar quando definido)
        this.ecraEncriptacaoActivityView = ecraDesencriptacaoActivityView;
    }

    @Override
    public EcraResultadoModel decrypt(ImageTextMessage message, String password, String cipherType) {
        CipherMessage cm = new CipherMessage(message, password);
        CipherFactory cipherFactory = new CipherFactory(cm, cipherType);
        CipherValidationResult cvr = cipherFactory.validateDecrypt();

        if(cvr.hasErrors())
            return new EcraResultadoModel(null, cvr.getCipherErrorCode().getErrorCode(), cvr.getCipherErrorCode().getErrorParams());

        CipherResult cr = cipherFactory.decrypt();

        if(cr.hasResultAsCipherImageMessage())
            return new EcraResultadoModel((ImageTextMessage) cr.getResultAsCipherImageMessage());

        return new EcraResultadoModel(cr.getResultAsString());
    }
}
