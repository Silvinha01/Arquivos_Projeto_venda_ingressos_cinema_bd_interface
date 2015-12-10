package dao;

import java.util.List;
import model.Filme;

/**
 *
 * @author silvinha01
 */
public interface FilmeDao {

    public void cadastrarFilme(Filme filme);

    public List<Filme> listarFilmes();

    public Filme buscarPorId(int id);

    public Filme buscarPorNome(String nome);

    public List<Filme> listBuscarPorNome(String nome);

    public void editarFilme(Filme filme);

    public void excluirFilme(Filme filme);

}
