package controller;

import dao.SessaoDao;
import dao.SessaoDaoBd;
import java.util.Date;
import model.Filme;
import model.Sala;
import model.Sessao;
import view.JanelaCrudSessao;
import view.PainelFormularioSessao;
import view.PainelTabelaSessao;
import view.PrintUtil;
import view.SessaoTableModel;

/**
 *
 * @author silvinha01
 */
public class SessaoController {
    
    private final static int TABELA = 0;
    private final static int FORMCADASTRO = 1;
    private final static int FORMEDICAO = 2;
    private final static int FORMVISUALIZACAO = 3;

    private int telaAtual = 0;
    private int linhaSelecionada = -1;

    private JanelaCrudSessao janela;

    public SessaoController() {
        telaAtual = TABELA;
    }

    public void setJanela(JanelaCrudSessao janela) {
        this.janela = janela;
    }

    public void cadastrarSessao() {
        PainelFormularioSessao painelFormSessao = this.janela.getPainelFormularioSessao();

        painelFormSessao.zerarCampos();

        painelFormSessao.getLabelPainelFormularioSessao().setText("Cadastro de Salas");
        painelFormSessao.getBotaoSalvar().setVisible(true);
        painelFormSessao.getBotaoSalvar().setText("Cadastrar Sala");
        painelFormSessao.habilitaEdicaoFormSessao(true);

        telaAtual = FORMCADASTRO;
        this.janela.mostrarPainel(JanelaCrudSessao.PAINELFORM);
    }

    public void editarSessao() {
        PainelTabelaSessao painelTabela = this.janela.getPainelTabelaSessao();
        PainelFormularioSessao painelForm = this.janela.getPainelFormularioSessao();
        SessaoTableModel tableModel = (SessaoTableModel) painelTabela.getTabelaSessoes().getModel();

        linhaSelecionada = painelTabela.getTabelaSessoes().getSelectedRow();
        if (linhaSelecionada < 0) {
            PrintUtil.printMessageError(null, "Não há nenhum elemento selecionado na tabela");
            return;
        }
        Sessao sessao = tableModel.getSessao(linhaSelecionada);
        painelForm.carregaDados(sessao.getSala().getId(), sessao.getSala().getQtdAssentos(), sessao.getFilme().getId(), sessao.getHorario());

        painelForm.getLabelPainelFormularioSessao().setText("Editar Sessão");
        painelForm.getBotaoSalvar().setVisible(true);
        painelForm.getBotaoSalvar().setText("Editar");
        painelForm.habilitaEdicaoFormSessao(true);

        telaAtual = FORMEDICAO;
        this.janela.mostrarPainel(JanelaCrudSessao.PAINELFORM);
    }

    public void visualizarSessao() {
        PainelTabelaSessao painelTabela = this.janela.getPainelTabelaSessao();
        PainelFormularioSessao form = this.janela.getPainelFormularioSessao();
        SessaoTableModel tableModel = (SessaoTableModel) painelTabela.getTabelaSessoes().getModel();

        linhaSelecionada = painelTabela.getTabelaSessoes().getSelectedRow();
        if (linhaSelecionada < 0) {
            PrintUtil.printMessageError(null, "Não há nenhum elemento selecionado na tabela");
            return;
        }
        Sessao sessao = tableModel.getSessao(linhaSelecionada);
        form.carregaDados(sessao.getSala().getId(), sessao.getSala().getQtdAssentos(), sessao.getFilme().getId(), sessao.getHorario());

        form.getLabelPainelFormularioSessao().setText("Visualizar Sessão");
        form.getBotaoSalvar().setVisible(false);
        form.getBotaoSalvar().setText("");
        form.habilitaEdicaoFormSessao(false);

        telaAtual = FORMVISUALIZACAO;
        this.janela.mostrarPainel(JanelaCrudSessao.PAINELFORM);
    }

    public void excluirSessao() {
        PainelTabelaSessao painelTabela = this.janela.getPainelTabelaSessao();
        SessaoTableModel tableModel = (SessaoTableModel) painelTabela.getTabelaSessoes().getModel();
        linhaSelecionada = painelTabela.getTabelaSessoes().getSelectedRow();
        if (linhaSelecionada < 0) {
            PrintUtil.printMessageError(null, "Não há nenhum elemento selecionado na tabela");
            return;
        }
        Sessao sessao = tableModel.getSessao(linhaSelecionada);
        SessaoDao dao = new SessaoDaoBd();
        dao.excluir(sessao);
        PrintUtil.printMessageSucesso(null, "Remoção realizada com sucesso!");

        this.atualizaTabela();
    }

    public void salvarSessao(Sala sala, int qtdassentos, Filme filme, Date horario) {
        PainelFormularioSessao painelForm = this.janela.getPainelFormularioSessao();
        PainelTabelaSessao painelTabela = this.janela.getPainelTabelaSessao();
        SessaoTableModel tableModel = (SessaoTableModel) painelTabela.getTabelaSessoes().getModel();
        if (telaAtual == FORMCADASTRO) {
            Sessao sessao = new Sessao(sala, qtdassentos, filme, horario);
            SessaoDao dao = new SessaoDaoBd();
            dao.cadastrar(sessao);
            PrintUtil.printMessageSucesso(null, "Cadastro realizado com sucesso!");
            painelForm.zerarCampos();
        } else {
            linhaSelecionada = painelTabela.getTabelaSessoes().getSelectedRow();
            int idsessao = tableModel.getSessao(linhaSelecionada).getId();
            Sessao sessao = new Sessao(idsessao, sala, qtdassentos, filme, horario);
            SessaoDao dao = new SessaoDaoBd();
            dao.editar(sessao);
            PrintUtil.printMessageSucesso(null, "Edição realizada com sucesso!");
        }
    }

    public void voltarPrincipal() {
        telaAtual = TABELA;
        this.atualizaTabela();
        this.janela.mostrarPainel(JanelaCrudSessao.PAINELTABELA);
    }

    public void atualizaTabela() {
        PainelTabelaSessao painelTabela = this.janela.getPainelTabelaSessao();
        SessaoTableModel tableModel = (SessaoTableModel) painelTabela.getTabelaSessoes().getModel();

        SessaoDao dao = new SessaoDaoBd();
        tableModel.setSessoes(dao.listarSessoes());

        painelTabela.getTabelaSessoes().updateUI();
    }
    
}
