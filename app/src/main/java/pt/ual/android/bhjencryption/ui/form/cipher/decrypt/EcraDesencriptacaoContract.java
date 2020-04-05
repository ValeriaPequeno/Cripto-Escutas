package pt.ual.android.bhjencryption.ui.form.cipher.decrypt;

import pt.ual.android.bhjencryption.ui.form.FormGenericPresenter;
import pt.ual.android.bhjencryption.ui.form.FormGenericView;
import pt.ual.android.bhjencryption.ui.form.cipher.result.EcraResultadoModel;

public interface EcraDesencriptacaoContract {
    interface View extends FormGenericView {

    }

    interface Presenter extends FormGenericPresenter {
        EcraResultadoModel decrypt(String mensagem, String password, String cifraSelecionada, int posCifraSelecionada);
    }
}
