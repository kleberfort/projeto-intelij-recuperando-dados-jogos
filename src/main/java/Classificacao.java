public class Classificacao {

    private String classificacao;
    private String nome;

    public Classificacao() {
    }

    public Classificacao(String classificacao, String nome) {
        this.classificacao = classificacao;
        this.nome = nome;
    }



    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "{" +
                "classificacao='" + classificacao + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
