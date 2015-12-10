package controller;

import dao.SalaDao;
import dao.SalaDaoBd;
import model.Sala;
import view.JanelaCrudSala;
import view.PainelFormularioSala;
import view.PainelTabelaSala;
import view.PrintUtil;
import view.SalaTableModel;

/**
 *
 * @author silvinha01
 */
public class SalaController {

    private final static int TABELA = 0;
    private final static int FORMCADASTRO = 1;
    private final static int FORMEDICAO = 2;
    private final static int FORMVISUALIZACAO = 3;

    private int telaAtual = 0;
    private int linhaSelecionada = -1;

    private JanelaCrudSala janela;

    public SalaController() {
        telaAtual = TABELA;
    }

    public void setJanela(JanelaCrudSala janela) {
        this.janela = janela;
    }

    public void cadastrarSala() {
        PainelFormularioSala painelFormSala = this.janela.getPainelFormularioSala();

        painelFormSala.zerarCampos();

        painelFormSala.getLabelPainelFormularioSala().setText("Cadastro de Salas");
        painelFormSala.getBotaoSalvar().setVisible(true);
        painelFormSala.getBotaoSalvar().setText("Cadastrar Sala");
        painelFormSala.habilitaEdicaoFormSala(true);

        telaAtual = FORMCADASTRO;
        this.janela.mostrarPainel(JanelaCrudSala.PAINELFORM);
    }

    public void editarSala() {
        PainelTabelaSala painelTabela = this.janela.getPainelTabelaSala();
        PainelFormularioSala painelForm = this.janela.getPainelFormularioSala();
        SalaTableModel tableModel = (SalaTableModel) painelTabela.getTabelaSalas().getModel();

        linhaSelecionada = painelTabela.getTabelaSalas().getSelectedRow();
        if (linhaSelecionada < 0) {
            PrintUtil.printMessageError(null, "Não há nenhum elemento selecionado na tabela");
            return;
        }
        Sala sala = tableModel.getSala(linhaSelecionada);
        painelForm.carregaDados(sala.getNomeSala(), sala.getQtdAssentos());

        painelForm.getLabelPainelFormularioSala().setText("Editar Sala");
        painelForm.getBotaoSalvar().setVisible(true);
        painelForm.getBotaoSalvar().setText("Editar");
        painelForm.habilitaEdicaoFormSala(true);

        telaAtual = FORMEDICAO;
        this.janela.mostrarPainel(JanelaCrudSala.PAINELFORM);
    }

    public void visualizarSala() {
        PainelTabelaSala painelTabela = this.janela.getPainelTabelaSala();
        PainelFormularioSala form = this.janela.getPainelFormularioSala();
        SalaTableModel tableModel = (SalaTableModel) painelTabela.getTabelaSalas().getModel();

        linhaSelecionada = painelTabela.getTabelaSalas().getSelectedRow();
        if (linhaSelecionada < 0) {
            PrintUtil.printMessageError(null, "Não há nenhum elemento selecionado na tabela");
            return;
        }
        Sala sala = tableModel.getSala(linhaSelecionada);
        form.carregaDados(sala.getNomeSala(), sala.getQtdAssentos());

        form.getLabelPainelFormularioSala().setText("Visualizar Sala");
        form.getBotaoSalvar().setVisible(false);
        form.getBotaoSalvar().setText("");
        form.habilitaEdicaoFormSala(false);

        telaAtual = FORMVISUALIZACAO;
        this.janela.mostrarPainel(JanelaCrudSala.PAINELFORM);
    }

    public void excluirSala() {
        PainelTabelaSala painelTabela = this.janela.getPainelTabelaSala();
        SalaTableModel tableModel = (SalaTableModel) painelTabela.getTabelaSalas().getModel();
        linhaSelecionada = painelTabela.getTabelaSalas().getSelectedRow();
        if (linhaSelecionada < 0) {
            PrintUtil.printMessageError(null, "Não há nenhum elemento selecionado na tabela");
            return;
        }
        Sala sala = tableModel.getSala(linhaSelecionada);
        SalaDao dao = new SalaDaoBd();
        dao.excluirSala(sala);
        PrintUtil.printMessageSucesso(null, "Remoção realizada com sucesso!");

        this.atualizaTabela();
    }

    public void salvarSala(String nomesala, int qtdassentos) {
        PainelFormularioSala painelForm = this.janela.getPainelFormularioSala();
        PainelTabelaSala painelTabela = this.janela.getPainelTabelaSala();
        SalaTableModel tableModel = (SalaTableModel) painelTabela.getTabelaSalas().getModel();
        if (telaAtual == FORMCADASTRO) {
            Sala sala = new Sala(nomesala, qtdassentos);
            SalaDao dao = new SalaDaoBd();
            dao.cadastrar(sala);
            PrintUtil.printMessageSucesso(null, "Cadastro realizado com sucesso!");
            painelForm.zerarCampos();
        } else {
            linhaSelecionada = painelTabela.getTabelaSalas().getSelectedRow();
            int idsala = tableModel.getSala(linhaSelecionada).getId();
            Sala sala = new Sala(idsala, nomesala, qtdassentos);
            SalaDao dao = new SalaDaoBd();
            dao.editarSala(sala);
            PrintUtil.printMessageSucesso(null, "Edição realizada com sucesso!");
        }
    }

    public void voltarPrincipal() {
        telaAtual = TABELA;
        this.atualizaTabela();
        this.janela.mostrarPainel(JanelaCrudSala.PAINELTABELA);
    }

    public void atualizaTabela() {
        PainelTabelaSala painelTabela = this.janela.getPainelTabelaSala();
        SalaTableModel tableModel = (SalaTableModel) painelTabela.getTabelaSalas().getModel();

        SalaDao dao = new SalaDaoBd();
        tableModel.setSalas(dao.listarSalas());

        painelTabela.getTabelaSalas().updateUI();
    }

}
