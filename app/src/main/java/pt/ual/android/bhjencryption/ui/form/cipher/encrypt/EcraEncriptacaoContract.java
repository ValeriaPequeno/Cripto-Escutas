package pt.ual.android.bhjencryption.ui.form.cipher.encrypt;

import pt.ual.android.bhjencryption.ui.form.FormGenericPresenter;
import pt.ual.android.bhjencryption.ui.form.FormGenericView;
import pt.ual.android.bhjencryption.ui.form.cipher.result.EcraResultadoModel;

public interface EcraEncriptacaoContract {
    interface View extends FormGenericView {

    }

    interface Presenter extends FormGenericPresenter {
        EcraResultadoModel encrypt(String mensagem, String password, String cifraSelecionada, int posCifraSelecionada);
    }
}
