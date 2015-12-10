package controller;

import dao.VendaDao;
import dao.VendaDaoBd;
import model.Sessao;
import model.Venda;
import view.JanelaCrudVenda;
import view.PainelFormularioSessao;
import view.PainelFormularioVenda;
import view.PainelTabelaVenda;
import view.PrintUtil;
import view.VendaTableModel;

/**
 *
 * @author silvinha01
 */
public class VendaController {
    
    private final static int TABELA = 0;
    private final static int FORMCADASTRO = 1;
    private final static int FORMEDICAO = 2;
    private final static int FORMVISUALIZACAO = 3;

    private int telaAtual = 0;
    private int linhaSelecionada = -1;

    private JanelaCrudVenda janela;

    public VendaController() {
        telaAtual = TABELA;
    }

    public void setJanela(JanelaCrudVenda janela) {
        this.janela = janela;
    }

    public void cadastrarVenda() {
        PainelFormularioVenda painelFormVenda = this.janela.getPainelFormularioVenda();

        painelFormVenda.zerarCampos();

        painelFormVenda.getLabelPainelFormularioVenda().setText("Venda de Ingressos");
        painelFormVenda.getBotaoSalvar().setVisible(true);
        painelFormVenda.getBotaoSalvar().setText("Cadastrar Venda");
        painelFormVenda.habilitaEdicaoFormVenda(true);

        telaAtual = FORMCADASTRO;
        this.janela.mostrarPainel(JanelaCrudVenda.PAINELFORM);
    }

    public void editarVenda() {
        PainelTabelaVenda painelTabela = this.janela.getPainelTabelaVenda();
        PainelFormularioVenda painelForm = this.janela.getPainelFormularioVenda();
        VendaTableModel tableModel = (VendaTableModel) painelTabela.getTabelaVendas().getModel();

        linhaSelecionada = painelTabela.getTabelaVendas().getSelectedRow();
        if (linhaSelecionada < 0) {
            PrintUtil.printMessageError(null, "Não há nenhum elemento selecionado na tabela");
            return;
        }
        Venda venda = tableModel.getVenda(linhaSelecionada);
        painelForm.carregaDados(venda.getSessao().getId(), venda.getQtdIngressosPorVenda());

        painelForm.getLabelPainelFormularioVenda().setText("Editar Venda");
        painelForm.getBotaoSalvar().setVisible(true);
        painelForm.getBotaoSalvar().setText("Editar");
        painelForm.habilitaEdicaoFormVenda(true);

        telaAtual = FORMEDICAO;
        this.janela.mostrarPainel(JanelaCrudVenda.PAINELFORM);
    }

    public void visualizarVenda() {
        PainelTabelaVenda painelTabela = this.janela.getPainelTabelaVenda();
        PainelFormularioVenda form = this.janela.getPainelFormularioVenda();
        VendaTableModel tableModel = (VendaTableModel) painelTabela.getTabelaVendas().getModel();

        linhaSelecionada = painelTabela.getTabelaVendas().getSelectedRow();
        if (linhaSelecionada < 0) {
            PrintUtil.printMessageError(null, "Não há nenhum elemento selecionado na tabela");
            return;
        }
        Venda venda = tableModel.getVenda(linhaSelecionada);
        form.carregaDados(venda.getSessao().getId(), venda.getQtdIngressosPorVenda());

        form.getLabelPainelFormularioVenda().setText("Visualizar Venda");
        form.getBotaoSalvar().setVisible(false);
        form.getBotaoSalvar().setText("");
        form.habilitaEdicaoFormVenda(false);

        telaAtual = FORMVISUALIZACAO;
        this.janela.mostrarPainel(JanelaCrudVenda.PAINELFORM);
    }

    public void excluirVenda() {
        PainelTabelaVenda painelTabela = this.janela.getPainelTabelaVenda();
        VendaTableModel tableModel = (VendaTableModel) painelTabela.getTabelaVendas().getModel();
        linhaSelecionada = painelTabela.getTabelaVendas().getSelectedRow();
        if (linhaSelecionada < 0) {
            PrintUtil.printMessageError(null, "Não há nenhum elemento selecionado na tabela");
            return;
        }
        Venda venda = tableModel.getVenda(linhaSelecionada);
        VendaDao dao = new VendaDaoBd();
        dao.excluir(venda);
        PrintUtil.printMessageSucesso(null, "Remoção realizada com sucesso!");

        this.atualizaTabela();
    }

    public void salvarVenda(Sessao sessao, int qtdingressos) {
        PainelFormularioVenda painelForm = this.janela.getPainelFormularioVenda();
        PainelTabelaVenda painelTabela = this.janela.getPainelTabelaVenda();
        VendaTableModel tableModel = (VendaTableModel) painelTabela.getTabelaVendas().getModel();
        if (telaAtual == FORMCADASTRO) {
            Venda venda = new Venda(sessao, qtdingressos);
            VendaDao dao = new VendaDaoBd();
            dao.cadastrar(venda);
            PrintUtil.printMessageSucesso(null, "Cadastro realizado com sucesso!");
            painelForm.zerarCampos();
        } else {
            linhaSelecionada = painelTabela.getTabelaVendas().getSelectedRow();
            int idvenda = tableModel.getVenda(linhaSelecionada).getId();
            Venda venda = new Venda(idvenda, sessao, qtdingressos);
            VendaDao dao = new VendaDaoBd();
            dao.editar(venda);
            PrintUtil.printMessageSucesso(null, "Edição realizada com sucesso!");
        }
    }

    public void voltarPrincipal() {
        telaAtual = TABELA;
        this.atualizaTabela();
        this.janela.mostrarPainel(JanelaCrudVenda.PAINELTABELA);
    }

    public void atualizaTabela() {
        PainelTabelaVenda painelTabela = this.janela.getPainelTabelaVenda();
        VendaTableModel tableModel = (VendaTableModel) painelTabela.getTabelaVendas().getModel();

        VendaDao dao = new VendaDaoBd();
        tableModel.setVendas(dao.listarVendas());

        painelTabela.getTabelaVendas().updateUI();
    }
    
}
