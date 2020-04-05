package pt.ual.android.bhjencryption.ui.form.home;

import pt.ual.android.bhjencryption.ui.form.FormGenericPresenter;

public class HomePresenter implements HomeContract.Presenter {

    // instância do model a tratar (declarar quando definido)
    private HomeContract.View homeActivityView; // referência para a View

    public HomePresenter(HomeContract.View homeActivityView) {
        // instancia o model (instanciar quando definido)
        this.homeActivityView = homeActivityView;
    }
}
