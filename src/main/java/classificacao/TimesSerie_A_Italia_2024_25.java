package classificacao;

public enum TimesSerie_A_Italia_2024_25 {

    ATALANTA("Atalanta", "https://ssl.gstatic.com/onebox/media/sports/logos/0XmrZHobvb6ua5tgMOnTEA_96x96.png"),
    BOLOGNA("Bologna", "https://ssl.gstatic.com/onebox/media/sports/logos/WnKdNmw06v2lz7HjhqPRPw_96x96.png"),
    CAGLIARI("Cagliari", "https://ssl.gstatic.com/onebox/media/sports/logos/e9XfySyGdfyJ4UzEkYwENw_96x96.png"),
    COMO("Como", "https://ssl.gstatic.com/onebox/media/sports/logos/6InMYSIcwGvDV1SD3-cPGA_96x96.png"),
    EMPOLI("Empoli", "https://ssl.gstatic.com/onebox/media/sports/logos/j8L__ffBY15ooTGG-0uZ2w_96x96.png"),
    FIORENTINA("Fiorentina", "https://ssl.gstatic.com/onebox/media/sports/logos/h-HS2cEVCwMJZFSlwYeWmA_96x96.png"),
    GENOA("Genoa", "https://ssl.gstatic.com/onebox/media/sports/logos/85QkdgIOpAt-_EuQ9mKTPg_96x96.png"),
    INTER("Inter", "https://ssl.gstatic.com/onebox/media/sports/logos/l2-icwsMhIvsbRw8AwC1yg_96x96.png"),
    JUVENTUS("Juventus", "https://ssl.gstatic.com/onebox/media/sports/logos/6lal-0xwWtos5HI99HRvuQ_96x96.png"),
    LAZIO("Lazio", "https://ssl.gstatic.com/onebox/media/sports/logos/VCmS5WyitnqY3ECAr0UYGw_96x96.png"),
    LECCE("Lecce", "https://ssl.gstatic.com/onebox/media/sports/logos/tIaC5FB7Gm8CIULc77qMjg_96x96.png"),
    MILAN("Milan", "https://ssl.gstatic.com/onebox/media/sports/logos/0E1JciuKLW0EbG48I5cFRQ_96x96.png"),
    MONZA("Monza", "https://ssl.gstatic.com/onebox/media/sports/logos/g2S0HUWrWZX9hwKC87W11Q_96x96.png"),
    NAPOLI("Napoli", "https://ssl.gstatic.com/onebox/media/sports/logos/uFG5ajpwa-CsTDJbswXkOg_96x96.png"),
    PARMA("Parma", "https://ssl.gstatic.com/onebox/media/sports/logos/Pr7ZXZlx34eEXdUMTkLvkw_96x96.png"),
    ROMA("Roma", "https://ssl.gstatic.com/onebox/media/sports/logos/BQdP4jUBFJfG7U_JBsFIMg_96x96.png"),
    TORINO("Torino", "https://ssl.gstatic.com/onebox/media/sports/logos/ovE3HSEx4GWXkW8GU7KVhA_96x96.png"),
    UDINESE("Udinese", "https://ssl.gstatic.com/onebox/media/sports/logos/92Aw_iasBENKmzvdpbTpHQ_96x96.png"),
    VENEZIA("Venezia", "https://ssl.gstatic.com/onebox/media/sports/logos/NNnNl8N5_KJrxpIrK-zBRA_96x96.png"),
    VERONA("Verona", "https://ssl.gstatic.com/onebox/media/sports/logos/Y23PEIJgTvK3Qpm9il1MGA_96x96.png");

    private final String nome;
    private final String imagem;

    TimesSerie_A_Italia_2024_25(String nome, String imagem) {
        this.nome = nome;
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public String getImagem() {
        return imagem;
    }

}
