package pt.ual.android.bhjencryption.ui.form.cipher.decrypt;

import pt.ual.android.bhjencryption.ui.form.FormGenericPresenter;
import pt.ual.android.bhjencryption.ui.form.FormGenericView;
import pt.ual.android.bhjencryption.ui.form.cipher.result.EcraResultadoModel;
import pt.ual.android.bhjencryption.ui.graphics.ImageTextMessage;

public interface EcraDesencriptacaoContract {
    interface View extends FormGenericView {

    }

    interface Presenter extends FormGenericPresenter {
        EcraResultadoModel decrypt(ImageTextMessage message, String password, String cipherType);
    }
}
