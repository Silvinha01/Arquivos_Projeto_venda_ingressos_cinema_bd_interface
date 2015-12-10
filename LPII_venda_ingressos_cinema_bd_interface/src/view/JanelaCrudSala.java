package view;

import controller.SalaController;
import java.awt.CardLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
 *
 * @author silvinha01
 */
public class JanelaCrudSala extends JInternalFrame {

    private SalaController controller;

    public final static String PAINELFORM = "Formulario";
    public final static String PAINELTABELA = "Tabela";
    private JPanel painelPrincipal;
    private PainelFormularioSala painelFormularioSala;
    private PainelTabelaSala painelTabelaSala;

    public JanelaCrudSala(SalaController controller) {
        this.controller = controller;
        this.controller.setJanela(this);
        iniciaComponentes();
        controller.atualizaTabela();
        this.setTitle("Cadastro de Salas");
        this.setResizable(true);
        this.setClosable(true);
        this.pack();
        this.setVisible(true);
    }

    private void iniciaComponentes() {
        painelPrincipal = new JPanel(new CardLayout());
        painelTabelaSala = new PainelTabelaSala(controller);
        painelPrincipal.add(painelTabelaSala, PAINELTABELA);
        painelFormularioSala = new PainelFormularioSala(controller);
        painelPrincipal.add(painelFormularioSala, PAINELFORM);
        this.add(painelPrincipal);
    }

    public void mostrarPainel(String painel) {
        CardLayout card = (CardLayout) (painelPrincipal.getLayout());
        card.show(painelPrincipal, painel);
    }

    public PainelFormularioSala getPainelFormularioSala() {
        return painelFormularioSala;
    }

    public PainelTabelaSala getPainelTabelaSala() {
        return painelTabelaSala;
    }

    public void setController(SalaController controller) {
        this.controller = controller;
    }

}
