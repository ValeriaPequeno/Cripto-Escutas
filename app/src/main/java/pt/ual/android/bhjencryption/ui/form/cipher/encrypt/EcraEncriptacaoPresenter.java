package pt.ual.android.bhjencryption.ui.form.cipher.encrypt;

import pt.ual.android.bhjencryption.cipher.CipherMessage;
import pt.ual.android.bhjencryption.cipher.CipherResult;
import pt.ual.android.bhjencryption.ui.form.cipher.result.EcraResultadoModel;
import pt.ual.android.bhjencryption.cipher.CipherFactory;
import pt.ual.android.bhjencryption.cipher.CipherValidationResult;
import pt.ual.android.bhjencryption.ui.graphics.ImageTextMessage;

public class EcraEncriptacaoPresenter implements EcraEncriptacaoContract.Presenter {

    // instância do model a tratar (declarar quando definido)
    private EcraEncriptacaoContract.View ecraEncriptacaoActivityView; // referência para a View

    public EcraEncriptacaoPresenter(EcraEncriptacaoContract.View ecraEncriptacaoActivityView) {
        // instancia o model (instanciar quando definido)
        this.ecraEncriptacaoActivityView = ecraEncriptacaoActivityView;
    }

    @Override
    public EcraResultadoModel encrypt(ImageTextMessage message, String password, String cipherType) {
        CipherMessage cm = new CipherMessage(message, password);
        CipherFactory cipherFactory = new CipherFactory(cm, cipherType);
        CipherValidationResult cvr = cipherFactory.validateEncrypt();

        if(cvr.hasErrors())
            return new EcraResultadoModel(null, cvr.getCipherErrorCode().getErrorCode());

        CipherResult cr = cipherFactory.encrypt();

        if(cr.hasResultAsCipherImageMessage())
            return new EcraResultadoModel((ImageTextMessage) cr.getResultAsCipherImageMessage());

        return new EcraResultadoModel(cr.getResultAsString());
    }
}
