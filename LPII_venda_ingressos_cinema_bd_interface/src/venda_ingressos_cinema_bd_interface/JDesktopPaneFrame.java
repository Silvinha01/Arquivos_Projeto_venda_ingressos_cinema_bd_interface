package venda_ingressos_cinema_bd_interface;

import controller.FilmeController;
import controller.SalaController;
import controller.SessaoController;
import controller.VendaController;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import view.JanelaCrudFilme;
import view.JanelaCrudSala;
import view.JanelaCrudSessao;
import view.JanelaCrudVenda;

/**
 *
 * @author silvinha01
 */
class JDesktopPaneFrame extends JFrame {

    public JDesktopPaneFrame() {
        super("Venda ingressos de Cinema");
        iniciaComponentes();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);
    }

    private void iniciaComponentes() {

        JDesktopPane painelDesktop = new JDesktopPane();
        painelDesktop.setLayout(null);

        FilmeController controller = new FilmeController();
        JanelaCrudFilme janela = new JanelaCrudFilme(controller);
        controller.setJanela(janela);

        painelDesktop.setSelectedFrame(janela);
        painelDesktop.add(janela);

        SalaController controller2 = new SalaController();
        JanelaCrudSala janela2 = new JanelaCrudSala(controller2);
        controller2.setJanela(janela2);

        painelDesktop.setSelectedFrame(janela2);
        painelDesktop.add(janela2);

        SessaoController controller3 = new SessaoController();
        JanelaCrudSessao janela3 = new JanelaCrudSessao(controller3);
        controller3.setJanela(janela3);

        painelDesktop.setSelectedFrame(janela3);
        painelDesktop.add(janela3);

        VendaController controller4 = new VendaController();
        JanelaCrudVenda janela4 = new JanelaCrudVenda(controller4);
        controller4.setJanela(janela4);

        painelDesktop.setSelectedFrame(janela4);
        painelDesktop.add(janela4);

        getContentPane().add(painelDesktop);

    }

}
