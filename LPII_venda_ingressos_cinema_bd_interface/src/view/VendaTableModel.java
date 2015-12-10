package view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Venda;

/**
 *
 * @author silvinha01
 */
public class VendaTableModel extends AbstractTableModel {

    private String header[];
    private List<Venda> vendas;

    public VendaTableModel() {
        this.header = new String[]{"Código Sala", "Ingressos disponíveis"};
        this.vendas = new ArrayList<Venda>();
    }

    public VendaTableModel(String[] header, List<Venda> Vendas) {
        this.header = header;
        this.vendas = vendas;

    }

    @Override
    public int getRowCount() {
        return (this.vendas.size());
    }

    @Override
    public int getColumnCount() {
        return (2);
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return (this.vendas.get(rowIndex).getSessao().getId());
        } else if (columnIndex == 1) {
            return (this.vendas.get(rowIndex).getQtdIngressosPorVenda());
        } else {
            return null;
        }
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public Venda getVenda(int linha) {
        return (vendas.get(linha));
    }

}
