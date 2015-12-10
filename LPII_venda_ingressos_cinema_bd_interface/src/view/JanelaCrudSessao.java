package view;

import controller.SessaoController;
import java.awt.CardLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
 *
 * @author silvinha01
 */
public class JanelaCrudSessao extends JInternalFrame {
 
    private SessaoController controller;

    public final static String PAINELFORM = "Formulario";
    public final static String PAINELTABELA = "Tabela";
    private JPanel painelPrincipal;
    private PainelFormularioSessao painelFormularioSessao;
    private PainelTabelaSessao painelTabelaSessao;

    public JanelaCrudSessao(SessaoController controller) {
        this.controller = controller;
        this.controller.setJanela(this);
        iniciaComponentes();
        controller.atualizaTabela();
        this.setTitle("Cadastro de Sessões");
        this.setResizable(true);
        this.setClosable(true);
        this.pack();
        this.setVisible(true);
    }

    private void iniciaComponentes() {
        painelPrincipal = new JPanel(new CardLayout());
        painelTabelaSessao = new PainelTabelaSessao(controller);
        painelPrincipal.add(painelTabelaSessao, PAINELTABELA);
        painelFormularioSessao = new PainelFormularioSessao(controller);
        painelPrincipal.add(painelFormularioSessao, PAINELFORM);
        this.add(painelPrincipal);
    }

    public void mostrarPainel(String painel) {
        CardLayout card = (CardLayout) (painelPrincipal.getLayout());
        card.show(painelPrincipal, painel);
    }

    public PainelFormularioSessao getPainelFormularioSessao() {
        return painelFormularioSessao;
    }

    public PainelTabelaSessao getPainelTabelaSessao() {
        return painelTabelaSessao;
    }

    public void setController(SessaoController controller) {
        this.controller = controller;
    }
    
}
