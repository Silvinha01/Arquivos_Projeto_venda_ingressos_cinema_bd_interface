package view;

import controller.VendaController;
import java.awt.CardLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
 *
 * @author silvinha01
 */
public class JanelaCrudVenda extends JInternalFrame {
    
    private VendaController controller;

    public final static String PAINELFORM = "Formulario";
    public final static String PAINELTABELA = "Tabela";
    private JPanel painelPrincipal;
    private PainelFormularioVenda painelFormularioVenda;
    private PainelTabelaVenda painelTabelaVenda;

    public JanelaCrudVenda(VendaController controller) {
        this.controller = controller;
        this.controller.setJanela(this);
        iniciaComponentes();
        controller.atualizaTabela();
        this.setTitle("Cadastro de Vendas");
        this.setResizable(true);
        this.setClosable(true);
        this.pack();
        this.setVisible(true);
    }

    private void iniciaComponentes() {
        painelPrincipal = new JPanel(new CardLayout());
        painelTabelaVenda = new PainelTabelaVenda(controller);
        painelPrincipal.add(painelTabelaVenda, PAINELTABELA);
        painelFormularioVenda = new PainelFormularioVenda(controller);
        painelPrincipal.add(painelFormularioVenda, PAINELFORM);
        this.add(painelPrincipal);
    }

    public void mostrarPainel(String painel) {
        CardLayout card = (CardLayout) (painelPrincipal.getLayout());
        card.show(painelPrincipal, painel);
    }

    public PainelFormularioVenda getPainelFormularioVenda() {
        return painelFormularioVenda;
    }

    public PainelTabelaVenda getPainelTabelaVenda() {
        return painelTabelaVenda;
    }

    public void setController(VendaController controller) {
        this.controller = controller;
    }
    
}
