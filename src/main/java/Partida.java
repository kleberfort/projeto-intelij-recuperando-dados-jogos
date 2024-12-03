public class Partida {

    private String descricao;
    private Integer rodada;
    private String data;

    private Team mandante;
    private Team visitante;

    private EstatisticaEscanteiosGols estatisticaEscanteiosGolsMandante;
    private EstatisticaEscanteiosGols estatisticaEscanteiosGolsVisitante;

    private EstatisticaCartoes estatisticaCartoesMandante;
    private EstatisticaCartoes estatisticaCartoesVisitante;

    public Partida() {
    }

    public Partida(String descricao, Integer rodada, String data, Team mandante, Team visitante, EstatisticaEscanteiosGols estatisticaEscanteiosGolsMandante, EstatisticaEscanteiosGols estatisticaEscanteiosGolsVisitante, EstatisticaCartoes estatisticaCartoesMandante, EstatisticaCartoes estatisticaCartoesVisitante) {
        this.descricao = descricao;
        this.rodada = rodada;
        this.data = data;
        this.mandante = mandante;
        this.visitante = visitante;
        this.estatisticaEscanteiosGolsMandante = estatisticaEscanteiosGolsMandante;
        this.estatisticaEscanteiosGolsVisitante = estatisticaEscanteiosGolsVisitante;
        this.estatisticaCartoesMandante = estatisticaCartoesMandante;
        this.estatisticaCartoesVisitante = estatisticaCartoesVisitante;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getRodada() {
        return rodada;
    }

    public void setRodada(Integer rodada) {
        this.rodada = rodada;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Team getMandante() {
        return mandante;
    }

    public void setMandante(Team mandante) {
        this.mandante = mandante;
    }

    public Team getVisitante() {
        return visitante;
    }

    public void setVisitante(Team visitante) {
        this.visitante = visitante;
    }

    public EstatisticaEscanteiosGols getEstatisticaEscanteiosGolsMandante() {
        return estatisticaEscanteiosGolsMandante;
    }

    public void setEstatisticaEscanteiosGolsMandante(EstatisticaEscanteiosGols estatisticaEscanteiosGolsMandante) {
        this.estatisticaEscanteiosGolsMandante = estatisticaEscanteiosGolsMandante;
    }

    public EstatisticaEscanteiosGols getEstatisticaEscanteiosGolsVisitante() {
        return estatisticaEscanteiosGolsVisitante;
    }

    public void setEstatisticaEscanteiosGolsVisitante(EstatisticaEscanteiosGols estatisticaEscanteiosGolsVisitante) {
        this.estatisticaEscanteiosGolsVisitante = estatisticaEscanteiosGolsVisitante;
    }

    public EstatisticaCartoes getEstatisticaCartoesMandante() {
        return estatisticaCartoesMandante;
    }

    public void setEstatisticaCartoesMandante(EstatisticaCartoes estatisticaCartoesMandante) {
        this.estatisticaCartoesMandante = estatisticaCartoesMandante;
    }

    public EstatisticaCartoes getEstatisticaCartoesVisitante() {
        return estatisticaCartoesVisitante;
    }

    public void setEstatisticaCartoesVisitante(EstatisticaCartoes estatisticaCartoesVisitante) {
        this.estatisticaCartoesVisitante = estatisticaCartoesVisitante;
    }
}
