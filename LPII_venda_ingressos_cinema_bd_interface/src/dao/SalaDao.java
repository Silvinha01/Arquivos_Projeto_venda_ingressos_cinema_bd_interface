package dao;

import java.util.List;
import model.Sala;

/**
 *
 * @author silvinha01
 */
public interface SalaDao {

    public void cadastrar(Sala sala);

    public List<Sala> listarSalas();

    public Sala buscarPorId(int id);

    public Sala buscarPorNome(String nome);

    public void editarSala(Sala sala);

    public void excluirSala(Sala sala);

}
