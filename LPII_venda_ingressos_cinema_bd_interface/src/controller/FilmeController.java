package controller;

import dao.FilmeDao;
import dao.FilmeDaoBd;
import model.Filme;
import view.JanelaCrudFilme;
import view.FilmeTableModel;
import view.PainelFormularioFilme;
import view.PainelTabelaFilme;
import view.PrintUtil;

/**
 *
 * @author silvinha01
 */
public class FilmeController {

    private final static int TABELA = 0;
    private final static int FORMCADASTRO = 1;
    private final static int FORMEDICAO = 2;
    private final static int FORMVISUALIZACAO = 3;

    private int telaAtual = 0;
    private int linhaSelecionada = -1;

    private JanelaCrudFilme janela;

    public FilmeController() {
        telaAtual = TABELA;
    }

    public void setJanela(JanelaCrudFilme janela) {
        this.janela = janela;
    }

    public void cadastrarFilme() {
        PainelFormularioFilme painelFormFilme = this.janela.getPainelFormularioFilme();

        painelFormFilme.zerarCampos();

        painelFormFilme.getLabelPainelFormularioFilme().setText("Cadastro de Filmes");
        painelFormFilme.getBotaoSalvar().setVisible(true);
        painelFormFilme.getBotaoSalvar().setText("Cadastrar Filme");
        painelFormFilme.habilitaEdicaoFormFilme(true);

        telaAtual = FORMCADASTRO;
        this.janela.mostrarPainel(JanelaCrudFilme.PAINELFORM);
    }

    public void editarFilme() {
        PainelTabelaFilme painelTabela = this.janela.getPainelTabelaFilme();
        PainelFormularioFilme painelForm = this.janela.getPainelFormularioFilme();
        FilmeTableModel tableModel = (FilmeTableModel) painelTabela.getTabelaFilmes().getModel();

        linhaSelecionada = painelTabela.getTabelaFilmes().getSelectedRow();
        if (linhaSelecionada < 0) {
            PrintUtil.printMessageError(null, "Não há nenhum elemento selecionado na tabela");
            return;
        }
        Filme filme = tableModel.getFilme(linhaSelecionada);
        painelForm.carregaDados(filme.getNomeFilme(), filme.getGenero(), filme.getSinopse());

        painelForm.getLabelPainelFormularioFilme().setText("Editar Filme");
        painelForm.getBotaoSalvar().setVisible(true);
        painelForm.getBotaoSalvar().setText("Editar");
        painelForm.habilitaEdicaoFormFilme(true);

        telaAtual = FORMEDICAO;
        this.janela.mostrarPainel(JanelaCrudFilme.PAINELFORM);
    }

    public void visualizarFilme() {
        PainelTabelaFilme painelTabela = this.janela.getPainelTabelaFilme();
        PainelFormularioFilme form = this.janela.getPainelFormularioFilme();
        FilmeTableModel tableModel = (FilmeTableModel) painelTabela.getTabelaFilmes().getModel();

        linhaSelecionada = painelTabela.getTabelaFilmes().getSelectedRow();
        if (linhaSelecionada < 0) {
            PrintUtil.printMessageError(null, "Não há nenhum elemento selecionado na tabela");
            return;
        }
        Filme filme = tableModel.getFilme(linhaSelecionada);
        form.carregaDados(filme.getNomeFilme(), filme.getGenero(), filme.getSinopse());

        form.getLabelPainelFormularioFilme().setText("Visualizar Filme");
        form.getBotaoSalvar().setVisible(false);
        form.getBotaoSalvar().setText("");
        form.habilitaEdicaoFormFilme(false);

        telaAtual = FORMVISUALIZACAO;
        this.janela.mostrarPainel(JanelaCrudFilme.PAINELFORM);
    }

    public void excluirFilme() {
        PainelTabelaFilme painelTabela = this.janela.getPainelTabelaFilme();
        FilmeTableModel tableModel = (FilmeTableModel) painelTabela.getTabelaFilmes().getModel();
        linhaSelecionada = painelTabela.getTabelaFilmes().getSelectedRow();
        if (linhaSelecionada < 0) {
            PrintUtil.printMessageError(null, "Não há nenhum elemento selecionado na tabela");//substitui por null
            return;
        }
        Filme filme = tableModel.getFilme(linhaSelecionada);
        FilmeDao dao = new FilmeDaoBd();
        dao.excluirFilme(filme);
        PrintUtil.printMessageSucesso(null, "Remoção realizada com sucesso!");

        this.atualizaTabela();
    }

    public void salvarFilme(String nome, String genero, String sinopse) {
        PainelFormularioFilme painelForm = this.janela.getPainelFormularioFilme();
        PainelTabelaFilme painelTabela = this.janela.getPainelTabelaFilme();
        FilmeTableModel tableModel = (FilmeTableModel) painelTabela.getTabelaFilmes().getModel();
        if (telaAtual == FORMCADASTRO) {
            Filme filme = new Filme(nome, genero, sinopse);
            FilmeDao dao = new FilmeDaoBd();
            dao.cadastrarFilme(filme);
            PrintUtil.printMessageSucesso(null, "Cadastro realizado com sucesso!");
            painelForm.zerarCampos();
        } else {
            linhaSelecionada = painelTabela.getTabelaFilmes().getSelectedRow();
            int idFilme = tableModel.getFilme(linhaSelecionada).getId();
            Filme filme = new Filme(idFilme, nome, genero, sinopse);
            FilmeDao dao = new FilmeDaoBd();
            dao.editarFilme(filme);
            PrintUtil.printMessageSucesso(null, "Edição realizada com sucesso!");
        }
    }

    public void voltarPrincipal() {
        telaAtual = TABELA;
        this.atualizaTabela();
        this.janela.mostrarPainel(JanelaCrudFilme.PAINELTABELA);
    }

    public void atualizaTabela() {
        PainelTabelaFilme painelTabela = this.janela.getPainelTabelaFilme();
        FilmeTableModel tableModel = (FilmeTableModel) painelTabela.getTabelaFilmes().getModel();

        FilmeDao dao = new FilmeDaoBd();
        tableModel.setFilmes(dao.listarFilmes());

        painelTabela.getTabelaFilmes().updateUI();
    }

}
