package servico;

import dao.SalaDao;
import dao.SalaDaoBd;
import java.util.List;
import model.Sala;

/**
 *
 * @author silvinha01
 */
public class SalaServico {

    public boolean salaIdExiste(int id) {
        SalaDao dao = new SalaDaoBd();
        Sala sala = dao.buscarPorId(id);
        return (sala != null);
    }

    public boolean salaNomeExiste(String nome) {
        SalaDao dao = new SalaDaoBd();
        Sala sala = dao.buscarPorNome(nome);
        return (sala != null);
    }

    public void addSala(Sala s) {
        new SalaDaoBd().cadastrar(s);
    }

    public List<Sala> listarSalas() {
        return (new SalaDaoBd().listarSalas());
    }

    public Sala mostrarSalaPorId(int id) {
        return (new SalaDaoBd().buscarPorId(id));
    }
    
    public Sala mostrarSalaPorNome(String nome) {
        return (new SalaDaoBd().buscarPorNome(nome));
    }

    public void excluirSala(Sala s) {
        new SalaDaoBd().excluirSala(s);
    }

    public void editarSala(Sala s) {
        SalaDao dao = new SalaDaoBd();
        dao.editarSala(s);
    }

}
