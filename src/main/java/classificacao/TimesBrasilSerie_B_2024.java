package classificacao;

public enum TimesBrasilSerie_B_2024 {
    AMAZONAS("Amazonas", "https://ssl.gstatic.com/onebox/media/sports/logos/uuUY0RAXejVcckwdPoVyBQ_96x96.png"),
    AMERICA_MG("América-MG", "https://logodetimes.com/times/america-mineiro/logo-america-mineiro-512.png"),
    AVAI("Avaí", "https://www.escudosfc.com.br/images/avai.gif"),
    BOTAFOGO_SP("Botafogo-SP", "https://ssl.gstatic.com/onebox/media/sports/logos/VYXHOgQwI7tw3EyFlWngWg_96x96.png"),
    BRUSQUE("Brusque", "https://ssl.gstatic.com/onebox/media/sports/logos/ykKt1U6PEpMP7iiXwZvdBQ_48x48.png"),
    CRB("CRB", "https://ssl.gstatic.com/onebox/media/sports/logos/zEM-aepnkjTSoBMW9LH_Qw_96x96.png"),
    CEARA("Ceará", "https://ssl.gstatic.com/onebox/media/sports/logos/mSl0cz3i2t8uv4zcprobOg_96x96.png"),
    CHAPECOENSE("Chapecoense", "https://ssl.gstatic.com/onebox/media/sports/logos/K7JQUKTRsuXfO9YrD5dq5g_96x96.png"),
    CORITIBA("Coritiba", "https://ssl.gstatic.com/onebox/media/sports/logos/LaFlBADLmsjHfGTehCYtuA_48x48.png"),
    GOIAS("Goiás", "https://ssl.gstatic.com/onebox/media/sports/logos/gb8bo2x00XsbvsVp9nGniA_48x48.png"),
    GUARANI("Guarani", "https://ssl.gstatic.com/onebox/media/sports/logos/fxJElzuqyxKVwsUcfsC49Q_96x96.png"),
    ITUANO("Ituano", "https://ssl.gstatic.com/onebox/media/sports/logos/d_-UoAOHszNZufgbaFSLXQ_96x96.png"),
    MIRASSOL("Mirassol", "https://ssl.gstatic.com/onebox/media/sports/logos/eQ05kONtFs6kLBgww3MHsg_96x96.png"),
    NOVORIZONTINO("Novorizontino", "https://ssl.gstatic.com/onebox/media/sports/logos/2YeE0Oupr5ApZJR0CyX_3g_96x96.png"),
    OPERARIO("Operário", "https://ssl.gstatic.com/onebox/media/sports/logos/GmLvorr4MqC4aRinQQ4Mdw_48x48.png"),
    PAYSANDU("Paysandu", "https://ssl.gstatic.com/onebox/media/sports/logos/1aw29215gcFtsyu07fCifw_48x48.png"),
    PONTE_PRETA("Ponte Preta", "https://ssl.gstatic.com/onebox/media/sports/logos/0e4mOPeQPUipr9oh54-7NQ_96x96.png"),
    SANTOS("Santos", "https://ssl.gstatic.com/onebox/media/sports/logos/VHdNOT6wWOw_vJ38GMjMzg_96x96.png"),
    SPORT("Sport", "https://ssl.gstatic.com/onebox/media/sports/logos/u9Ydy0qt6JJjWhTaI6r10A_96x96.png"),
    VILA_NOVA("Vila Nova", "https://ssl.gstatic.com/onebox/media/sports/logos/v3upsCABZm2skuxyszzvwg_96x96.png");

    private final String nome;
    private final String imagem;

    TimesBrasilSerie_B_2024(String nome, String imagem) {
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