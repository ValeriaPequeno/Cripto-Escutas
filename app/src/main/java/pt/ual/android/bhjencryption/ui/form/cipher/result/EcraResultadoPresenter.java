package pt.ual.android.bhjencryption.ui.form.cipher.result;

public class EcraResultadoPresenter implements EcraResultadoContrat.Presenter {

    // instância do model a tratar (declarar quando definido)
    private EcraResultadoContrat.View ecraResultadoActivityView; // referência para a View

    public EcraResultadoPresenter(EcraResultadoContrat.View ecraResultadoActivityView) {
        // instancia o model (instanciar quando definido)
        this.ecraResultadoActivityView = ecraResultadoActivityView;
    }
}
