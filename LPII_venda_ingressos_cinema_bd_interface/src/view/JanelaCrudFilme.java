package view;

import controller.FilmeController;
import java.awt.CardLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
 *
 * @author silvinha01
 */
public class JanelaCrudFilme extends JInternalFrame {

    private FilmeController controller;

    public final static String PAINELFORM = "Formulario";
    public final static String PAINELTABELA = "Tabela";
    private JPanel painelPrincipal;
    private PainelFormularioFilme painelFormularioFilme;
    private PainelTabelaFilme painelTabelaFilme;

    public JanelaCrudFilme(FilmeController controller) {
        this.controller = controller;
        this.controller.setJanela(this);
        iniciaComponentes();
        controller.atualizaTabela();
        this.setTitle("Cadastro de Filmes");
        this.setResizable(true);
        this.setClosable(true);       
        this.pack();
        this.setVisible(true);
    }

    private void iniciaComponentes() {
        painelPrincipal = new JPanel(new CardLayout());
        painelTabelaFilme = new PainelTabelaFilme(controller);
        painelPrincipal.add(painelTabelaFilme, PAINELTABELA);
        painelFormularioFilme = new PainelFormularioFilme(controller);
        painelPrincipal.add(painelFormularioFilme, PAINELFORM);
        this.add(painelPrincipal);
    }

    public void mostrarPainel(String painel) {
        CardLayout card = (CardLayout) (painelPrincipal.getLayout());
        card.show(painelPrincipal, painel);

    }

    public PainelFormularioFilme getPainelFormularioFilme() {
        return painelFormularioFilme;
    }

    public PainelTabelaFilme getPainelTabelaFilme() {
        return painelTabelaFilme;
    }

    public void setController(FilmeController controller) {
        this.controller = controller;
    }

}
