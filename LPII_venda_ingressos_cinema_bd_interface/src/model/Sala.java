package model;

/**
 * Essa classe serve para trabalhar com o objeto sala. Trabalha com informações:
 * id, nomeSala, qtdAssentos.
 *
 *
 * @author silvinha01
 */
public class Sala {

    private int id;
    private String nomeSala;
    private int qtdAssentos;

    /**
     * Inicia o objeto sala com seus dados.
     *
     * @param nomeSala String que referencia o atributo nomeSala.
     * @param qtdAssentos Inteiro que referencia o atributo qtdAssentos.
     */
    public Sala(String nomeSala, int qtdAssentos) {
        this.nomeSala = nomeSala;
        this.qtdAssentos = qtdAssentos;
    }

    /**
     * Inicia o objeto sala com seus dados.
     *
     * @param id é auto incremento em bd.
     * @param nomeSala String que referencia o atributo nomeSala.
     * @param qtdAssentos Inteiro que referencia o atributo qtdAssentos.
     */
    public Sala(int id, String nomeSala, int qtdAssentos) {
        this.id = id;
        this.nomeSala = nomeSala;
        this.qtdAssentos = qtdAssentos;
    }

    /*public Sala(int id, int qtdAssentos) {
     this.id = id;
     this.qtdAssentos = qtdAssentos;
     }*/
    /**
     * Retorna o id de uma sala.
     *
     * @return int id.
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna o nomede uma sala.
     *
     * @return String nomeSala.
     */
    public String getNomeSala() {
        return nomeSala;
    }

    /**
     * Altera o nome de uma sala.
     *
     * @param nomeSala String nomeSala
     */
    public void setNomeSala(String nomeSala) {
        this.nomeSala = nomeSala;
    }

    /**
     * Retorna a quantidade de assentos de uma sala.
     *
     * @return int qtdAssentos.
     */
    public int getQtdAssentos() {
        return qtdAssentos;
    }

    /**
     * Altera a quantidade de assentos de uma sala.
     *
     * @param qtdAssentos int qtdAssentos.
     */
    public void setQtdAssentos(int qtdAssentos) {
        this.qtdAssentos = qtdAssentos;
    }
    
}
