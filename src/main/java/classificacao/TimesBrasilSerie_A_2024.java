package classificacao;

public enum TimesBrasilSerie_A_2024 {

    ATlETICO_PR("Atlético-PR", "https://i.pinimg.com/originals/b8/86/3f/b8863f2e056204741c402d467cbafdea.png"),
    ATLETICO_GO("Atlético-GO", "https://ssl.gstatic.com/onebox/media/sports/logos/9mqMGndwoR9og_Z0uEl2kw_96x96.png"),
    ATLETICO_MG("Atlético-MG", "https://lh3.googleusercontent.com/-PnSdQx5SHPs/WRnQRxEIdLI/AAAAAAAAEHM/7iiEf5mOu5s4sOFNxio-IOlZ9pdjniFdQCEw/s766/atletico-mineiro.png"),
    BAHIA("Bahia", "https://ssl.gstatic.com/onebox/media/sports/logos/nIdbR6qIUDyZUBO9vojSPw_96x96.png"),
    BOTAFOGO("Botafogo", "https://ssl.gstatic.com/onebox/media/sports/logos/KLDWYp-H8CAOT9H_JgizRg_96x96.png"),
    BRAGANTINO("Bragantino", "https://lh3.ggpht.com/-2H8TG2xgkNM/XioxlJNOhuI/AAAAAAAAExA/Ev9a8vKC-VUstZYHNGP-7Ju9hbvi6BbqQCEwYBhgL/s724/bragantino.png"),
    CORINTHIANS("Corinthians", "https://logodetimes.com/times/corinthians/logo-corinthians-4096.png"),
    CRICIUMA("Criciúma", "https://ssl.gstatic.com/onebox/media/sports/logos/u_L7Mkp33uNmFTv3uUlXeQ_96x96.png"),
    CRUZEIRO("Cruzeiro", "https://ssl.gstatic.com/onebox/media/sports/logos/cfkLbPGt7TD_FSDotajcbA_96x96.png"),
    CUIABA("Cuiabá", "https://ssl.gstatic.com/onebox/media/sports/logos/j6U8Rgt_6yyf0Egs9nREXw_96x96.png"),
    FLAMENGO("Flamengo", "https://upload.wikimedia.org/wikipedia/commons/9/93/Flamengo-RJ_%28BRA%29.png"),
    FLUMINENSE("Fluminense", "https://ssl.gstatic.com/onebox/media/sports/logos/fCMxMMDF2AZPU7LzYKSlig_96x96.png"),
    FORTALEZA("Fortaleza", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/40/FortalezaEsporteClube.svg/1200px-FortalezaEsporteClube.svg.png"),
    GREMIO("Grêmio", "https://ssl.gstatic.com/onebox/media/sports/logos/Ku-73v_TW9kpex-IEGb0ZA_96x96.png"),
    INTERNACIONAL("Internacional", "https://ssl.gstatic.com/onebox/media/sports/logos/OWVFKuHrQuf4q2Wk0hEmSA_96x96.png"),
    JUVENTUDE("Juventude", "https://ssl.gstatic.com/onebox/media/sports/logos/JrXw-m4Dov0gE2Sh6XJQMQ_96x96.png"),
    PALMEIRAS("Palmeiras", "https://ssl.gstatic.com/onebox/media/sports/logos/7spurne-xDt2p6C0imYYNA_96x96.png"),
    SAO_PAULO("São-Paulo", "https://ssl.gstatic.com/onebox/media/sports/logos/4w2Z97Hf9CSOqICK3a8AxQ_96x96.png"),
    VASCO("Vasco", "https://ssl.gstatic.com/onebox/media/sports/logos/hHwT8LwRmYCAGxQ-STLxYA_96x96.png"),
    VITORIA("Vitória", "https://ssl.gstatic.com/onebox/media/sports/logos/LHSM6VchpkI4NIptoSTHOg_96x96.png");

    private final String nome;
    private final String imagem;

    TimesBrasilSerie_A_2024(String nome, String imagem) {
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
