package classificacao;

public enum TimesArgentinaLigaProfissionalA2024 {

    ARGENTINOS_JUNIORS("Argentinos Jrs", "https://a.espncdn.com/combiner/i?img=/i/teamlogos/soccer/500/3.png&scale=crop&cquality=40&location=origin&w=80&h=80"),
    ATLETICO_TUCUMAN("Atl. Tucuman", "https://a.espncdn.com/combiner/i?img=/i/teamlogos/soccer/500/9785.png&scale=crop&cquality=40&location=origin&w=80&h=80"),
    BANFIELD("Banfield", "https://a.espncdn.com/combiner/i?img=/i/teamlogos/soccer/500/235.png&scale=crop&cquality=40&location=origin&w=80&h=80"),
    BARRACAS_CENTRAL("Barracas Central", "https://a.espncdn.com/combiner/i?img=/i/teamlogos/soccer/500/10060.png&scale=crop&cquality=40&location=origin&w=80&h=80"),
    BELGRANO("Belgrano", "https://a.espncdn.com/combiner/i?img=/i/teamlogos/soccer/500/4.png&scale=crop&cquality=40&location=origin&w=80&h=80"),
    BOCA_JUNIORS("Boca Juniors", "https://a.espncdn.com/combiner/i?img=/i/teamlogos/soccer/500/5.png&scale=crop&cquality=40&location=origin&w=80&h=80"),
    CENTRAL_CORDOBA_SDE("Central Córdoba", "https://a.espncdn.com/combiner/i?img=/i/teamlogos/soccer/500/11989.png&scale=crop&cquality=40&location=origin&w=80&h=80"),
    DEFENSA_Y_JUSTICIA("Def. Justicia", "https://a.espncdn.com/combiner/i?img=/i/teamlogos/soccer/500/8950.png&scale=crop&cquality=40&location=origin&w=80&h=80"),
    DEPORTIVO_RIESTRA("Deportivo Riestra", "https://a.espncdn.com/combiner/i?img=/i/teamlogos/soccer/500/17702.png&scale=crop&cquality=40&location=origin&w=80&h=80"),
    ESTUDIANTES_LA_PLATA("Estudiantes", "https://a.espncdn.com/combiner/i?img=/i/teamlogos/soccer/500/8.png&scale=crop&cquality=40&location=origin&w=80&h=80"),
    GIMNASIA_ESGRIMA("Gimnasia", "https://a.espncdn.com/combiner/i?img=/i/teamlogos/soccer/500/9.png&scale=crop&cquality=40&location=origin&w=80&h=80"),
    GODOY_CRUZ("Godoy Cruz", "https://a.espncdn.com/combiner/i?img=/i/teamlogos/soccer/500/6756.png&scale=crop&cquality=40&location=origin&w=80&h=80"),
    HURACAN("Huracán", "https://a.espncdn.com/combiner/i?img=/i/teamlogos/soccer/500/10.png&scale=crop&cquality=40&location=origin&w=80&h=80"),
    INDEPENDIENTE("Independiente", "https://a.espncdn.com/combiner/i?img=/i/teamlogos/soccer/500/11.png&scale=crop&cquality=40&location=origin&w=80&h=80"),
    INDEPENDIENTE_RIVADAVIA("Ind. Rivadavia", "https://a.espncdn.com/combiner/i?img=/i/teamlogos/soccer/500/9744.png&scale=crop&cquality=40&location=origin&w=80&h=80"),
    INSTITUTO("Instituto", "https://a.espncdn.com/combiner/i?img=/i/teamlogos/soccer/500/2975.png&scale=crop&cquality=40&location=origin&w=80&h=80"),
    LANUS("Lanús", "https://a.espncdn.com/combiner/i?img=/i/teamlogos/soccer/500/12.png&scale=crop&cquality=40&location=origin&w=80&h=80"),
    NEWELLS_OLD_BOYS("Newell Old Boys", "https://a.espncdn.com/combiner/i?img=/i/teamlogos/soccer/500/14.png&scale=crop&cquality=40&location=origin&w=80&h=80"),
    PLATENSE("Platense", "https://a.espncdn.com/combiner/i?img=/i/teamlogos/soccer/500/7764.png&scale=crop&cquality=40&location=origin&w=80&h=80"),
    RACING("Racing", "https://a.espncdn.com/combiner/i?img=/i/teamlogos/soccer/500/15.png&scale=crop&cquality=40&location=origin&w=80&h=80"),
    RIVER_PLATE("River Plate", "https://a.espncdn.com/combiner/i?img=/i/teamlogos/soccer/500/16.png&scale=crop&cquality=40&location=origin&w=80&h=80"),
    ROSARIO_CENTRAL("Rosario", "https://a.espncdn.com/combiner/i?img=/i/teamlogos/soccer/500/17.png&scale=crop&cquality=40&location=origin&w=80&h=80"),
    SAN_LORENZO("San Lorenzo", "https://a.espncdn.com/combiner/i?img=/i/teamlogos/soccer/500/18.png&scale=crop&cquality=40&location=origin&w=80&h=80"),
    SARMIENTO("Sarmiento", "https://a.espncdn.com/combiner/i?img=/i/teamlogos/soccer/500/10158.png&scale=crop&cquality=40&location=origin&w=80&h=80"),
    TALLERES("Talleres", "https://a.espncdn.com/combiner/i?img=/i/teamlogos/soccer/500/19.png&scale=crop&cquality=40&location=origin&w=80&h=80"),
    TIGRE("Tigre", "https://a.espncdn.com/combiner/i?img=/i/teamlogos/soccer/500/7767.png&scale=crop&cquality=40&location=origin&w=80&h=80"),
    UNION_SANTA_FE("Union Santa Fe", "https://a.espncdn.com/combiner/i?img=/i/teamlogos/soccer/500/20.png&scale=crop&cquality=40&location=origin&w=80&h=80"),
    VELEZ_SARSFIELD("Vélez Sársfield", "https://a.espncdn.com/combiner/i?img=/i/teamlogos/soccer/500/21.png&scale=crop&cquality=40&location=origin&w=80&h=80");

    private final String nome;
    private final String imagem;

    TimesArgentinaLigaProfissionalA2024(String nome, String imagem) {
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
