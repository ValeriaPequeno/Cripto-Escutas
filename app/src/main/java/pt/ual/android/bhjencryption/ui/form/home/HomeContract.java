package pt.ual.android.bhjencryption.ui.form.home;

import pt.ual.android.bhjencryption.ui.form.FormGenericPresenter;
import pt.ual.android.bhjencryption.ui.form.FormGenericView;

/**
 * Este interface permite manter o rastreio de forma centralizada do relacionamento entre a View e o Presenter
 * Define, de certa forma, o que a View pode fazer do Presenter e vice versa. Desta forma, não existe
 * violação da visibilidade/ações a que ambos estão limitados para que o Presenter não manipule UI e
 * a View não manipule lógica.
 */
public interface HomeContract {

    interface View extends FormGenericView {

    }

    interface Presenter extends FormGenericPresenter {

    }
}
