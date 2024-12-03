package classificacao;

public enum TimesLaLiga_A_2024_25 {

    ALAVES("Alavés", "https://ssl.gstatic.com/onebox/media/sports/logos/RUcTOeFcBc7q7uku1nR_iQ_96x96.png"),
    ATHLETIC_CLUB("Athletic Club", "https://ssl.gstatic.com/onebox/media/sports/logos/OSL_5Zm6kYPP1J17Bo5uDA_96x96.png"),
    ATL_MADRID("Atl. Madrid", "https://ssl.gstatic.com/onebox/media/sports/logos/pEqmA7CL-VRo4Llq3rwIPA_96x96.png"),
    BARCELONA("Barcelona", "https://ssl.gstatic.com/onebox/media/sports/logos/paYnEE8hcrP96neHRNofhQ_96x96.png"),
    CELTA("Celta", "https://ssl.gstatic.com/onebox/media/sports/logos/wpdhixHP_sloegfP0UlwAw_96x96.png"),
    ESPANYOL("Espanyol", "https://ssl.gstatic.com/onebox/media/sports/logos/TKitIqelDyN6M-kYt4Uc0g_96x96.png"),
    GETAFE("Getafe", "https://ssl.gstatic.com/onebox/media/sports/logos/1UDHZMdKZD15W5gus7dJyg_96x96.png"),
    GIRONA("Girona", "https://ssl.gstatic.com/onebox/media/sports/logos/sHiSmLm_rA0BOC5MfrNt8A_96x96.png"),
    LAS_PALMAS("Las Palmas", "https://ssl.gstatic.com/onebox/media/sports/logos/yOIfuEFVFzu1dD8HiJt9vA_96x96.png"),
    LEGANES("Leganés", "https://ssl.gstatic.com/onebox/media/sports/logos/Id84F7Ji9rZGVacaazlBYA_96x96.png"),
    MALLORCA("Mallorca", "https://ssl.gstatic.com/onebox/media/sports/logos/Ss21P4CUmigjrEtcoapjVg_96x96.png"),
    OSASUNA("Osasuna", "https://ssl.gstatic.com/onebox/media/sports/logos/pk-hNCNpGM_JDoHHvLVF-Q_96x96.png"),
    RAYO_VALLECANO("Rayo Vallecano", "https://ssl.gstatic.com/onebox/media/sports/logos/i5LifmxEVIl0sbvIysiyhw_96x96.png"),
    REAL_BETIS("Real Betis", "https://ssl.gstatic.com/onebox/media/sports/logos/XDClkrMKtkm3UTP2YKN6oQ_96x96.png"),
    REAL_MADRID("Real Madrid", "https://ssl.gstatic.com/onebox/media/sports/logos/Th4fAVAZeCJWRcKoLW7koA_96x96.png"),
    REAL_SOCIEDAD("Real Sociedad", "https://ssl.gstatic.com/onebox/media/sports/logos/w8tb1aeBfVZIj9tZXf7eZg_96x96.png"),
    REAL_VALLADOLID("Real Valladolid", "https://ssl.gstatic.com/onebox/media/sports/logos/DdERt3jquzbYD95zvHQJWw_96x96.png"),
    SEVILLA("Sevilla", "https://ssl.gstatic.com/onebox/media/sports/logos/hCTs5EX3WjCMC5Jl3QE4Rw_96x96.png"),
    VALENCIA("Valencia", "https://ssl.gstatic.com/onebox/media/sports/logos/QPbjvDwI_0Wuu4tCS2O6uw_96x96.png"),
    VILLARREAL("Villarreal", "https://ssl.gstatic.com/onebox/media/sports/logos/WKH7Ak5cYD6Qm1EEqXzmVw_96x96.png");

    private final String nome;
    private final String imagem;

    TimesLaLiga_A_2024_25(String nome, String imagem) {
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
