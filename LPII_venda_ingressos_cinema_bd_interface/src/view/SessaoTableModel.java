package view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Sessao;

/**
 *
 * @author silvinha01
 */
public class SessaoTableModel extends AbstractTableModel {

    private String header[];
    private List<Sessao> sessoes;

    public SessaoTableModel() {
        this.header = new String[]{"Código Sala", "Assentos disponíveis", "Código Filme", "Horario"};
        this.sessoes = new ArrayList<Sessao>();
    }

    public SessaoTableModel(String[] header, List<Sessao> Sessoes) {
        this.header = header;
        this.sessoes = sessoes;

    }

    @Override
    public int getRowCount() {
        return (this.sessoes.size());
    }

    @Override
    public int getColumnCount() {
        return (4);
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return (this.sessoes.get(rowIndex).getSala().getId());
        } else if (columnIndex == 1) {
            return (this.sessoes.get(rowIndex).getSala().getQtdAssentos());
        } else if (columnIndex == 2) {
            return (this.sessoes.get(rowIndex).getFilme().getId());
        } else if (columnIndex == 3) {
            return (this.sessoes.get(rowIndex).getHorario());
        } else {
            return null;
        }
    }

    public void setSessoes(List<Sessao> sessoes) {
        this.sessoes = sessoes;
    }

    public Sessao getSessao(int linha) {
        return (sessoes.get(linha));
    }

}
