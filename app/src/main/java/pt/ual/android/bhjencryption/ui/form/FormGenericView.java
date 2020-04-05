package pt.ual.android.bhjencryption.ui.form;

/**
 * Define comportamentos genéricos que todas as Views devem de ter.
 * <p>
 * A View é completamente estúpida. Não faz mais nada a não ser obter ações do utilizador,
 * tratar dos controlos e apontar para outras actividades/fragmentos. A view reencaminha toda a
 * lógica fica sempre para o Presenter.
 */
public interface FormGenericView {
    /**
     * Permite inicializar a View de acordo com o seu estado inicial
     */
    void initView();

    void loadModel();
}
