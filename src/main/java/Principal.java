import classificacao.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Principal {

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();
        OkHttpClient classificacao = new OkHttpClient();
        OkHttpClient dadosEstatistico = new OkHttpClient();
        OkHttpClient dadosEventos = new OkHttpClient();


       // String roundMain = "1";

      //  String k = "36";

        for (int k = 1; k <= 15; k++) {



        String urlLigaProfessional = String.format("https://sportapi7.p.rapidapi.com/api/v1/unique-tournament/155/season/57478/events/round/%s/last/0", k);
        String urlSerieA = String.format("https://sportapi7.p.rapidapi.com/api/v1/unique-tournament/325/season/58766/events/round/%s/last/0", k);
//        String urlSerieB = String.format("https://sportapi7.p.rapidapi.com/api/v1/unique-tournament/390/season/59015/events/round/%s/last/0", roundMain);
//        String urlPremierLeague_A_2024_25 = String.format("https://sportapi7.p.rapidapi.com/api/v1/unique-tournament/17/season/61627/events/round/%s/last/0", roundMain);
        String urlLaLiga_A_2024_25 = String.format("https://sportapi7.p.rapidapi.com/api/v1/unique-tournament/8/season/61643/events/round/%s/last/0", k);




        int totalJogos = 0;
        int rodada = 0 ;


        Request request = new Request.Builder()
                .url(urlLaLiga_A_2024_25)
                .get()
                .addHeader("x-rapidapi-key", "58b8072752msh548de46c3d61c7ap1b2a99jsn93b7d612311b")
                .addHeader("x-rapidapi-host", "sportapi7.p.rapidapi.com")
                .build();

        try (Response response = client.newCall(request).execute() ) {
            if (response.isSuccessful()) {

                String jsonResponse = response.body().string();
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
                JsonArray events = jsonObject.getAsJsonArray("events");

                //IR PARA PARTIDAD ESPECIFICA
                // JsonObject evento = events.get(9).getAsJsonObject();

                // Acessando o primeiro evento da lista
                for (int i = 0; i < events.size(); i++) {
                    JsonObject firstEvent = events.get(i).getAsJsonObject();
                    //Quando não se tem objetos filhos, faço o acesso direto
                    String idPartida = firstEvent.get("id").getAsString();

                    String url = String.format("https://sportapi7.p.rapidapi.com/api/v1/event/%s/pregame-form", idPartida);

                    Request classifica = new Request.Builder()
                            .url (url)
                            .get()
                            .addHeader("x-rapidapi-key", "58b8072752msh548de46c3d61c7ap1b2a99jsn93b7d612311b")
                            .addHeader("x-rapidapi-host", "sportapi7.p.rapidapi.com")
                            .build();

                    String dados = String.format("https://sportapi7.p.rapidapi.com/api/v1/event/%s/statistics", idPartida);
                    Request estatistica = new Request.Builder()
                            .url(dados)
                            .get()
                            .addHeader("x-rapidapi-key", "58b8072752msh548de46c3d61c7ap1b2a99jsn93b7d612311b")
                            .addHeader("x-rapidapi-host", "sportapi7.p.rapidapi.com")
                            .build();

                    String incident = String.format("https://sportapi7.p.rapidapi.com/api/v1/event/%s/incidents", idPartida);
                    Request eventIncident = new Request.Builder()
                            .url(incident)
                            .get()
                            .addHeader("x-rapidapi-key", "58b8072752msh548de46c3d61c7ap1b2a99jsn93b7d612311b")
                            .addHeader("x-rapidapi-host", "sportapi7.p.rapidapi.com")
                            .build();


                    Response response1 = classificacao.newCall(classifica).execute();

                    Integer homeClassificacao = 1;
                    Integer awayClassificacao = 1;


                    JsonObject torneio = firstEvent.getAsJsonObject("tournament");
                    JsonObject round = firstEvent.getAsJsonObject("roundInfo");
                    Long data = firstEvent.get("startTimestamp").getAsLong();


                    // Convertendo o timestamp Unix para um objeto Instant
                    Instant instant = Instant.ofEpochSecond(data);
                    // Convertendo o Instant para ZonedDateTime, considerando o fuso horário UTC
                    ZonedDateTime dateTime = instant.atZone(ZoneId.of("UTC"));
                    // Formatando a data no estilo dd/MM/yyyy
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String formattedDate = dateTime.format(formatter);

                    //nome do Torneio
                    String nomeTorneio = torneio.get("name").getAsString();
                    //qual rodada do campeonato
                    Integer roundInfo = round.get("round").getAsInt();



                    if(response1.isSuccessful()){
                        String classificacaoTimes = response1.body().string();
                        Gson gson1 = new Gson();
                        JsonObject jsonObject1 = gson1.fromJson(classificacaoTimes, JsonObject.class);

                        JsonObject homeClassi = jsonObject1.getAsJsonObject("homeTeam");
                        JsonObject awayClassi = jsonObject1.getAsJsonObject("awayTeam");

                        //Extraindo a classificação antes do inicio da rodada
                        homeClassificacao = homeClassi.get("position").getAsInt();
                        awayClassificacao = awayClassi.get("position").getAsInt();

                    }else{
                        System.out.println("\n\nERRO: Classificação -> home: " + homeClassificacao + " away: "+awayClassificacao);
                    }


                    // Acessando o time da casa (por exemplo, "homeTeam")
                    JsonObject homeTeam = firstEvent.getAsJsonObject("homeTeam");
                    JsonObject awayTeam = firstEvent.getAsJsonObject("awayTeam");

                    String home = homeTeam.get("shortName").getAsString();
                    String away = awayTeam.get("shortName").getAsString();


                    Integer homeScore1 = 0;
                    Integer homeScore2 = 0;
                    Integer awayScore1 = 0;
                    Integer awayScore2 = 0;
                    Integer  homePlacar ;
                    Integer  awayPlacar ;




                    JsonObject homeScore = firstEvent.getAsJsonObject("homeScore");
                    JsonObject awayScore = firstEvent.getAsJsonObject("awayScore");

                    if (homeScore != null && homeScore.size() > 0) {
                        homeScore1 = homeScore.get("period1").getAsInt();
                        homeScore2 = homeScore.get("period2").getAsInt();

                    } else {
                        // O objeto homeScore está vazio ou a chave "homeScore" não existe
                        System.out.println("\nPlacar 1T Vazio - " + home + " - " + away);
                    }
                    if (awayScore != null && awayScore.size() > 0) {
                        awayScore1 = awayScore.get("period1").getAsInt();
                        awayScore2 = awayScore.get("period2").getAsInt();

                    } else {
                        // O objeto homeScore está vazio ou a chave "homeScore" não existe
                        System.out.println("Placar 2T Vazio - " + home + " - " + away);
                    }

                    homePlacar = homeScore1 + homeScore2;
                    awayPlacar = awayScore1 + awayScore2;


                    // Chamada do método para obter o nome e a imagem
                    String[] homeData = obterTimeData(home);
                    String[] awayData = obterTimeData(away);

                    // Atribui os valores
                    String homeName = homeData[0];
                    String imageHome = homeData[1];

                    String awayName = awayData[0];
                    String imageAway = awayData[1];

                    // Converter a codificação de ISO-8859-1 para UTF-8

//                    byte[] bytesHome = homeName.getBytes(StandardCharsets.ISO_8859_1);
//                    String correctedHomeName = new String(bytesHome, StandardCharsets.UTF_8);
//
//                    byte[] bytesAway = awayName.getBytes(StandardCharsets.ISO_8859_1);
//                    String correctedAwayName = new String(bytesAway, StandardCharsets.UTF_8);

//                    //USADO PARA O NOTEBOOK, POIS JÁ TEM UTF-8
                    String correctedHomeName = homeName;
                    String correctedAwayName = awayName;


                    Response response2 = dadosEstatistico.newCall(estatistica).execute();

                    // Encadeamento das chamadas para acessar os valores de 'home' e 'away'

                    Integer escanteioPrimeiroTempoCasa =0 ;
                    Integer escanteioPrimeiroTempoFora = 0;

                    Integer escanteioSegundoTempoCasa = 0;
                    Integer escanteioSegundoTempoFora = 0;

                    if(response2.isSuccessful()){
                        String dadosTimes = response2.body().string();
                        Gson gson2 = new Gson();
                        JsonObject jsonObject2 = gson2.fromJson(dadosTimes, JsonObject.class);


                        // Verifica se "statistics" existe e tem ao menos 2 elementos
                        if (jsonObject2.has("statistics") && jsonObject2.getAsJsonArray("statistics").size() > 1) {
                            JsonArray dadosEscanteiosPrimeiroTempo = jsonObject2.getAsJsonArray("statistics")
                                    .get(1).getAsJsonObject()
                                    .getAsJsonArray("groups")
                                    .get(0).getAsJsonObject()
                                    .getAsJsonArray("statisticsItems");

                            // Percorre o array para encontrar a chave "cornerKicks"
                            for (JsonElement element : dadosEscanteiosPrimeiroTempo) {
                                JsonObject jsonObject6 = element.getAsJsonObject();
                                if ("cornerKicks".equals(jsonObject6.get("key").getAsString())) {
                                    escanteioPrimeiroTempoCasa = jsonObject6.get("home").getAsInt();
                                    escanteioPrimeiroTempoFora = jsonObject6.get("away").getAsInt();
                                    break; // Sai do loop assim que "cornerKicks" for encontrado
                                }
                            }

                            // Presumindo que você também tenha um JsonArray para o segundo tempo:
                            JsonArray dadosEscanteiosSegundoTempo = jsonObject2.getAsJsonArray("statistics")
                                    .get(2).getAsJsonObject()
                                    .getAsJsonArray("groups")
                                    .get(0).getAsJsonObject()
                                    .getAsJsonArray("statisticsItems");

                            // Percorre o array para encontrar a chave "cornerKicks"
                            for (JsonElement element : dadosEscanteiosSegundoTempo) {
                                JsonObject jsonObject7 = element.getAsJsonObject();
                                if ("cornerKicks".equals(jsonObject7.get("key").getAsString())) {
                                    escanteioSegundoTempoCasa = jsonObject7.get("home").getAsInt();
                                    escanteioSegundoTempoFora = jsonObject7.get("away").getAsInt();
                                    break; // Sai do loop assim que "cornerKicks" for encontrado
                                }
                            }
                        }else{
                            System.out.println("  ESCANTEIOS-> ERRO no jogo do " + home + "  X  " + away);
                        }


                    }else{
                        System.out.println("ERRO BUSCA: ESCANTEIOS " + home +" - " + away );
                    }


                    // Extraindo o nome do time
                    String teamHomeName = homeTeam.get("name").getAsString();
                    String teamAwayName = awayTeam.get("name").getAsString();

                    Response response3 = classificacao.newCall(eventIncident).execute();


                    // Variáveis para contar os cartões do time CASA
                    int amarelosCasaPrimeiroTempo = 0;
                    int amarelosCasaSegundoTempo = 0;
                    int vermelhosCasaPrimeiroTempo = 0;
                    int vermelhosCasaSegundoTempo = 0;

                    // Variáveis para contar os cartões time VISITANTE
                    int amarelosVisitantePrimeiroTempo = 0;
                    int amarelosVisitanteSegundoTempo = 0;
                    int vermelhosVisitantePrimeioTempo = 0;
                    int vermelhosVisitanteSegundoTempo = 0;


                    if(response3.isSuccessful()){
                        String incidents = response3.body().string();
                        Gson gson3 = new Gson();
                        JsonObject jsonObject3 = gson3.fromJson(incidents, JsonObject.class);

                        JsonArray dadosEvento = jsonObject3.getAsJsonArray("incidents");

                        for (int l = dadosEvento.size() - 1; l >= 0; l--)  {
                            JsonObject teste2 = dadosEvento.get(l).getAsJsonObject();

                            // Obtenha os valores de "time", "length", e "incidentType"
                            int time = teste2.get("time").getAsInt();
                            String incidentType = teste2.get("incidentType").getAsString();
                            if(("injuryTime".equals(incidentType) && time == 45) || ( "period".equals(incidentType) && time == 45 )){

                                int  tempoDecorrido = 0;
                                if(teste2.has("length")){
                                    int length = teste2.get("length").getAsInt();
                                    tempoDecorrido = time + length;
                                }else{
                                    tempoDecorrido = time;
                                }


                                for (int j = dadosEvento.size() - 1; j >= 0; j--){
                                    JsonObject teste3 = dadosEvento.get(j).getAsJsonObject();
                                    Integer tempo = teste3.get("time").getAsInt();
                                    String cartao = teste3.get("incidentType").getAsString();

                                    if (teste3.has("player") &&
                                            (( "yellow".equals(teste3.get("incidentClass").getAsString()) &&  !"-5".equals(teste3.get("time").getAsString() ) ) ||
                                                    ( "yellowRed".equals(teste3.get("incidentClass").getAsString()) &&  !"-5".equals(teste3.get("time").getAsString()) ))
                                    ){
                                        boolean casa = teste3.get("isHome").getAsBoolean();
                                        String teamName = casa ? homeTeam.get("name").getAsString() : awayTeam.get("name").getAsString();
                                        String player = teste3.get("playerName").getAsString();


                                        if("card".equals(cartao) &&  teste2.get("time").getAsInt() <=  tempoDecorrido && teste3.get("time").getAsInt() <= 45  && player != null){
                                            if(casa){
                                                amarelosCasaPrimeiroTempo+=1;

                                            }else {
                                                amarelosVisitantePrimeiroTempo+=1;

                                            }
                                            //  System.out.println("cartao amarelo 1 tempo: " + teamName +": " + teste3.get("playerName").getAsString() + " "  + teste3.get("time").getAsString()  + (teste3.has("addedTime") ?  " + "+ teste3.get("addedTime").getAsInt() +"'" : "'")   );
                                        }else if("card".equals(cartao) && teste3.get("time").getAsInt() > 45){
                                            if(casa) {
                                                amarelosCasaSegundoTempo+=1;

                                            } else {
                                                amarelosVisitanteSegundoTempo+=1;

                                            }
                                            //  System.out.println("cartao amarelo 2 tempo: " + teamName + ": " + teste3.get("playerName").getAsString() + " " + teste3.get("time").getAsString() + (teste3.has("addedTime") ?  " + "+ teste3.get("addedTime").getAsInt() +"'" : "'"));
                                        }

                                    }else if((teste3.has("player") &&
                                            ("red".equals(teste3.get("incidentClass").getAsString()) && !"-5".equals(teste3.get("time").getAsString())))){
                                        boolean casa = teste3.get("isHome").getAsBoolean();
                                        String teamName = casa ? homeTeam.get("name").getAsString() : awayTeam.get("name").getAsString();
                                        String player = teste3.get("playerName").getAsString();

                                        if("card".equals(cartao) &&   teste2.get("time").getAsInt() <=  tempoDecorrido && teste3.get("time").getAsInt() <= 45 && player != null){
                                            if(casa){
                                                vermelhosCasaPrimeiroTempo+=1;

                                            }else{
                                                vermelhosVisitantePrimeioTempo+=1;

                                            }
                                            //  System.out.println("cartao vermelho 1 tempo: " + teamName +": " + teste3.get("playerName").getAsString() + " "  + teste3.get("time").getAsString()  + (teste3.has("addedTime") ?  " + "+ teste3.get("addedTime").getAsInt() +"'" : "'")   );
                                        }else if("card".equals(cartao) && teste3.get("time").getAsInt() > 45){
                                            if(casa) {
                                                vermelhosCasaSegundoTempo+=1;

                                            } else {
                                                vermelhosVisitanteSegundoTempo+=1;

                                            }
                                            //System.out.println("cartao vermelho 2 tempo: " + teamName + ": " + teste3.get("playerName").getAsString() + " " + teste3.get("time").getAsString() + (teste3.has("addedTime") ?  " + "+ teste3.get("addedTime").getAsInt() +"'" : "'"));
                                        }

                                    }



                                }//fim do for que trata dos cartões 1Tempo e 2Tempopp

                                break;

                            }





                        }//fim do if inicial para pegar o tempo decorrido do 1Tempo






                    }else{
                        System.out.println("ERRO BUSCA: CARTÕES " + home +" - " + away );
                    }



                    System.out.println();












                    String jsonString = "{\n" +
                            "    \"descricao\": \"" + nomeTorneio + "\",\n" +
                            "    \"rodada\": " + roundInfo + ",\n" +
                            "    \"data\": \"" + formattedDate + "\",\n" +
                            "\n" +
                            "    \"mandante\":{\n" +
                            "        \"nome\": \"" + correctedHomeName + "\",\n" +
                            "        \"imagem\": \"" + imageHome + "\",\n" +
                            "        \"classificacao\": " + homeClassificacao + ",\n" +
                            "        \"placar\": " + (homeScore1 + homeScore2) + "\n" +
                            "    },\n" +
                            "\n" +
                            "    \"visitante\":{\n" +
                            "        \"nome\": \"" + correctedAwayName + "\",\n" +
                            "        \"imagem\": \"" + imageAway + "\",\n" +
                            "        \"classificacao\": " + awayClassificacao + ",\n" +
                            "        \"placar\": " + (awayScore1 + awayScore2) + "\n" +
                            "    },\n" +
                            "\n" +
                            "    \"estatistiticaJogoMandante\":{\n" +
                            "        \"escanteiosPrimeiroTempo\": " + escanteioPrimeiroTempoCasa + ",\n" +
                            "        \"escanteiosSegundoTempo\": " + escanteioSegundoTempoCasa + ",\n" +
                            "        \"golsPrimeiroTempo\": " + homeScore1 + ",\n" +
                            "        \"golsSegundoTempo\": " + homeScore2 + "\n" +
                            "    },\n" +
                            "\n" +
                            "    \"estatisticaJogoVisitante\":{\n" +
                            "        \"escanteiosPrimeiroTempo\": " + escanteioPrimeiroTempoFora + ",\n" +
                            "        \"escanteiosSegundoTempo\": " + escanteioSegundoTempoFora + ",\n" +
                            "        \"golsPrimeiroTempo\": " + awayScore1 + ",\n" +
                            "        \"golsSegundoTempo\": " + awayScore2 + "\n" +
                            "    },\n" +
                            "\n" +
                            "    \"EstatisticaCartoesMandante\":{\n" +
                            "        \"cartoesAmareloPrimeiroTempo\": " + amarelosCasaPrimeiroTempo + ",\n" +
                            "        \"cartoesAmareloSegundoTempo\": " + amarelosCasaSegundoTempo + ",\n" +
                            "        \"cartoesVermelhoPrimeiroTempo\": " + vermelhosCasaPrimeiroTempo + ",\n" +
                            "        \"cartoesVermelhoSegundoTempo\": " + vermelhosCasaSegundoTempo + "\n" +
                            "    },\n" +
                            "\n" +
                            "    \"EstatisticaCartoesVisitante\":{\n" +
                            "        \"cartoesAmareloPrimeiroTempo\": " + amarelosVisitantePrimeiroTempo + ",\n" +
                            "        \"cartoesAmareloSegundoTempo\": " + amarelosVisitanteSegundoTempo + ",\n" +
                            "        \"cartoesVermelhoPrimeiroTempo\": " + vermelhosVisitantePrimeioTempo + ",\n" +
                            "        \"cartoesVermelhoSegundoTempo\": " + vermelhosVisitanteSegundoTempo + "\n" +
                            "    }\n" +
                            "}," +
                            "";


//                    String jsonString = String.format(
//                            "Partida ID: %s\nTorneio: %s\nRodada: %s\n\n" +
//                                    "Casa: %s (Classificação: %d)\n" +
//                                    "Placar: %d - %d\n" +
//                                    "Escanteios: 1º Tempo - %d, 2º Tempo - %d\n" +
//                                    "Cartões Amarelos: 1º Tempo - %d, 2º Tempo - %d\n" +
//                                    "Cartões Vermelhos: 1º Tempo - %d, 2º Tempo - %d\n" +
//                                    "Gols: 1° Tempo - %d, 2° Tempo - %d\n\n"+
//                                    "Visitante: %s (Classificação: %d)\n" +
//                                    "Escanteios: 1º Tempo - %d, 2º Tempo - %d\n" +
//                                    "Cartões Amarelos: 1º Tempo - %d, 2º Tempo - %d\n" +
//                                    "Cartões Vermelhos: 1º Tempo - %d, 2º Tempo - %d\n"+
//                                    "Gols: 1° Tempo - %d, 2° Tempo - %d\n\n",
//                            idPartida, nomeTorneio, roundInfo,  // Strings
//                            teamHomeName, homeClassificacao,    // Strings e Integers
//                            homePlacar, awayPlacar,             // Integers (Placar)
//                            escanteioPrimeiroTempoCasa, escanteioSegundoTempoCasa,  // Integers (Escanteios Casa)
//                            amarelosCasaPrimeiroTempo, amarelosCasaSegundoTempo,    // Integers (Cartões Amarelos Casa)
//                            vermelhosCasaPrimeiroTempo, vermelhosCasaSegundoTempo,  // Integers (Cartões Vermelhos Casa)
//                            homeScore1, homeScore2,
//                            teamAwayName, awayClassificacao,    // Strings e Integers
//                            escanteioPrimeiroTempoFora, escanteioSegundoTempoFora,  // Integers (Escanteios Visitante)
//                            amarelosVisitantePrimeiroTempo, amarelosVisitanteSegundoTempo,  // Integers (Cartões Amarelos Visitante)
//                            vermelhosVisitantePrimeioTempo, vermelhosVisitanteSegundoTempo, // Integers (Cartões Vermelhos Visitante)
//                            awayScore1, awayScore2
//
//                    );




                    System.out.println(jsonString);

                    totalJogos = events.size();
                    rodada = roundInfo;


//                    // Exibindo o nome do time
//                    System.out.println("id: " + idPartida+ " Torneio: " + nomeTorneio + " Jogo: " + i + " -->  " + teamHomeName + " " + homePlacar +" X "  + awayPlacar + " " + teamAwayName + ": " + roundInfo);
//                    // System.out.println("Away time: " + teamAwayName);
//
//                    System.out.println();

                }//fim do for que trata dos dados




            } else {
                System.out.println("Request failed: " + response.code());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\n\n"+"Rodada: " + rodada);
        System.out.println("Total de Jogos: " + totalJogos);

        }//fim do for para recuperar varias rodadas

    }//fim do main


    private static String[] obterTimeData(String time) {
        String nome = null;
        String imagem = null;

        switch (time) {
            case "Atlético-GO":
                nome = TimesBrasilSerie_A_2024.ATLETICO_GO.getNome();
                imagem = TimesBrasilSerie_A_2024.ATLETICO_GO.getImagem();
                break;
            case "Atlético-MG":
                nome = TimesBrasilSerie_A_2024.ATLETICO_MG.getNome();
                imagem = TimesBrasilSerie_A_2024.ATLETICO_MG.getImagem();
                break;
            case "Athletico":
                nome = TimesBrasilSerie_A_2024.ATlETICO_PR.getNome();
                imagem = TimesBrasilSerie_A_2024.ATlETICO_PR.getImagem();
                break;
            case "Bahia":
                nome = TimesBrasilSerie_A_2024.BAHIA.getNome();
                imagem = TimesBrasilSerie_A_2024.BAHIA.getImagem();
                break;
            case "Botafogo":
                nome = TimesBrasilSerie_A_2024.BOTAFOGO.getNome();
                imagem = TimesBrasilSerie_A_2024.BOTAFOGO.getImagem();
                break;
            case "Corinthians":
                nome = TimesBrasilSerie_A_2024.CORINTHIANS.getNome();
                imagem = TimesBrasilSerie_A_2024.CORINTHIANS.getImagem();
                break;
            case "Cruzeiro":
                nome = TimesBrasilSerie_A_2024.CRUZEIRO.getNome();
                imagem = TimesBrasilSerie_A_2024.CRUZEIRO.getImagem();
                break;
            case "Criciúma":
                nome = TimesBrasilSerie_A_2024.CRICIUMA.getNome();
                imagem = TimesBrasilSerie_A_2024.CRICIUMA.getImagem();
                break;
            case "Cuiabá":
                nome = TimesBrasilSerie_A_2024.CUIABA.getNome();
                imagem = TimesBrasilSerie_A_2024.CUIABA.getImagem();
                break;
            case "Flamengo":
                nome = TimesBrasilSerie_A_2024.FLAMENGO.getNome();
                imagem = TimesBrasilSerie_A_2024.FLAMENGO.getImagem();
                break;
            case "Fluminense":
                nome = TimesBrasilSerie_A_2024.FLUMINENSE.getNome();
                imagem = TimesBrasilSerie_A_2024.FLUMINENSE.getImagem();
                break;
            case "Fortaleza":
                nome = TimesBrasilSerie_A_2024.FORTALEZA.getNome();
                imagem = TimesBrasilSerie_A_2024.FORTALEZA.getImagem();
                break;
            case "Juventude":
                nome = TimesBrasilSerie_A_2024.JUVENTUDE.getNome();
                imagem = TimesBrasilSerie_A_2024.JUVENTUDE.getImagem();
                break;
            case "Palmeiras":
                nome = TimesBrasilSerie_A_2024.PALMEIRAS.getNome();
                imagem = TimesBrasilSerie_A_2024.PALMEIRAS.getImagem();
                break;
            case "RB Bragantino":
                nome = TimesBrasilSerie_A_2024.BRAGANTINO.getNome();
                imagem = TimesBrasilSerie_A_2024.BRAGANTINO.getImagem();
                break;
            case "São Paulo":
                nome = TimesBrasilSerie_A_2024.SAO_PAULO.getNome();
                imagem = TimesBrasilSerie_A_2024.SAO_PAULO.getImagem();
                break;
            case "Vasco":
                nome = TimesBrasilSerie_A_2024.VASCO.getNome();
                imagem = TimesBrasilSerie_A_2024.VASCO.getImagem();
                break;
            case "Vitória":
                nome = TimesBrasilSerie_A_2024.VITORIA.getNome();
                imagem = TimesBrasilSerie_A_2024.VITORIA.getImagem();
                break;
            case "Grêmio":
                nome = TimesBrasilSerie_A_2024.GREMIO.getNome();
                imagem = TimesBrasilSerie_A_2024.GREMIO.getImagem();
                break;
            case "Internacional":
                nome = TimesBrasilSerie_A_2024.INTERNACIONAL.getNome();
                imagem = TimesBrasilSerie_A_2024.INTERNACIONAL.getImagem();
                break;

                //CAMPEONATO INGLES A 2024-2025
            case "Aston Villa":
                nome = TimesPremierLeague_A_2024_2025.ASTON_VILLA.getNome();
                imagem = TimesPremierLeague_A_2024_2025.ASTON_VILLA.getImagem();
                break;
            case "Arsenal":
                nome = TimesPremierLeague_A_2024_2025.ARSENAL.getNome();
                imagem = TimesPremierLeague_A_2024_2025.ARSENAL.getImagem();
                break;
            case "Brentford":
                nome = TimesPremierLeague_A_2024_2025.BRENTFORD.getNome();
                imagem = TimesPremierLeague_A_2024_2025.BRENTFORD.getImagem();
                break;
            case "Brighton":
                nome = TimesPremierLeague_A_2024_2025.BRIGHTON.getNome();
                imagem = TimesPremierLeague_A_2024_2025.BRIGHTON.getImagem();
                break;
            case "Bournemouth":
                nome = TimesPremierLeague_A_2024_2025.BOURNEMOUTH.getNome();
                imagem = TimesPremierLeague_A_2024_2025.BOURNEMOUTH.getImagem();
                break;
            case "Chelsea":
                nome = TimesPremierLeague_A_2024_2025.CHELSEA.getNome();
                imagem = TimesPremierLeague_A_2024_2025.CHELSEA.getImagem();
                break;
            case "Crystal Palace":
                nome = TimesPremierLeague_A_2024_2025.CRYSTAL_PALACE.getNome();
                imagem = TimesPremierLeague_A_2024_2025.CRYSTAL_PALACE.getImagem();
                break;
            case "Everton":
                nome = TimesPremierLeague_A_2024_2025.EVERTON.getNome();
                imagem = TimesPremierLeague_A_2024_2025.EVERTON.getImagem();
                break;
            case "Forest":
                nome = TimesPremierLeague_A_2024_2025.FOREST.getNome();
                imagem = TimesPremierLeague_A_2024_2025.FOREST.getImagem();
                break;
            case "Fulham":
                nome = TimesPremierLeague_A_2024_2025.FULHAM.getNome();
                imagem = TimesPremierLeague_A_2024_2025.FULHAM.getImagem();
                break;
            case "Ipswich":
                nome = TimesPremierLeague_A_2024_2025.IPSWWICH.getNome();
                imagem = TimesPremierLeague_A_2024_2025.IPSWWICH.getImagem();
                break;
            case "Leicester":
                nome = TimesPremierLeague_A_2024_2025.LEICESTER.getNome();
                imagem = TimesPremierLeague_A_2024_2025.LEICESTER.getImagem();
                break;
            case "Liverpool":
                nome = TimesPremierLeague_A_2024_2025.LIVERPOOL.getNome();
                imagem = TimesPremierLeague_A_2024_2025.LIVERPOOL.getImagem();
                break;
            case "Man City":
                nome = TimesPremierLeague_A_2024_2025.MAN_CITY.getNome();
                imagem = TimesPremierLeague_A_2024_2025.MAN_CITY.getImagem();
                break;
            case "Man Utd":
                nome = TimesPremierLeague_A_2024_2025.MAN_UNITED.getNome();
                imagem = TimesPremierLeague_A_2024_2025.MAN_UNITED.getImagem();
                break;
            case "Newcastle":
                nome = TimesPremierLeague_A_2024_2025.NEWCASTLE.getNome();
                imagem = TimesPremierLeague_A_2024_2025.NEWCASTLE.getImagem();
                break;
            case "Southampton":
                nome = TimesPremierLeague_A_2024_2025.SOUTHAMPTON.getNome();
                imagem = TimesPremierLeague_A_2024_2025.SOUTHAMPTON.getImagem();
                break;
            case "Tottenham ":
                nome = TimesPremierLeague_A_2024_2025.TOTTENHAM.getNome();
                imagem = TimesPremierLeague_A_2024_2025.TOTTENHAM.getImagem();
                break;
            case "West Ham":
                nome = TimesPremierLeague_A_2024_2025.WESTHAM.getNome();
                imagem = TimesPremierLeague_A_2024_2025.WESTHAM.getImagem();
                break;
            case "Wolves":
                nome = TimesPremierLeague_A_2024_2025.WOLVES.getNome();
                imagem = TimesPremierLeague_A_2024_2025.WOLVES.getImagem();
                break;

            //LISTA CAMPEONATO SERIE B
            case "Avaí":
                nome = TimesBrasilSerie_B_2024.AVAI.getNome();
                imagem = TimesBrasilSerie_B_2024.AVAI.getImagem();
                break;
            case "Brusque":
                nome = TimesBrasilSerie_B_2024.BRUSQUE.getNome();
                imagem = TimesBrasilSerie_B_2024.BRUSQUE.getImagem();
                break;
            case "Operário-PR":
                nome = TimesBrasilSerie_B_2024.OPERARIO.getNome();
                imagem = TimesBrasilSerie_B_2024.OPERARIO.getImagem();
                break;
            case "Paysandu":
                nome = TimesBrasilSerie_B_2024.PAYSANDU.getNome();
                imagem = TimesBrasilSerie_B_2024.PAYSANDU.getImagem();
                break;
            case "Santos":
                nome = TimesBrasilSerie_B_2024.SANTOS.getNome();
                imagem = TimesBrasilSerie_B_2024.SANTOS.getImagem();
                break;
            case "Mirassol":
                nome = TimesBrasilSerie_B_2024.MIRASSOL.getNome();
                imagem = TimesBrasilSerie_B_2024.MIRASSOL.getImagem();
                break;
            case "América-MG":
                nome = TimesBrasilSerie_B_2024.AMERICA_MG.getNome();
                imagem = TimesBrasilSerie_B_2024.AMERICA_MG.getImagem();
                break;
            case "Botafogo-SP":
                nome = TimesBrasilSerie_B_2024.BOTAFOGO_SP.getNome();
                imagem = TimesBrasilSerie_B_2024.BOTAFOGO_SP.getImagem();
                break;
            case "Sport":
                nome = TimesBrasilSerie_B_2024.SPORT.getNome();
                imagem = TimesBrasilSerie_B_2024.SPORT.getImagem();
                break;
            case "Amazonas":
                nome = TimesBrasilSerie_B_2024.AMAZONAS.getNome();
                imagem = TimesBrasilSerie_B_2024.AMAZONAS.getImagem();
                break;
            case "Ituano":
                nome = TimesBrasilSerie_B_2024.ITUANO.getNome();
                imagem = TimesBrasilSerie_B_2024.ITUANO.getImagem();
                break;
            case "Chapecoense":
                nome = TimesBrasilSerie_B_2024.CHAPECOENSE.getNome();
                imagem = TimesBrasilSerie_B_2024.CHAPECOENSE.getImagem();
                break;
            case "Coritiba":
                nome = TimesBrasilSerie_B_2024.CORITIBA.getNome();
                imagem = TimesBrasilSerie_B_2024.CORITIBA.getImagem();
                break;
            case "Ponte Preta":
                nome = TimesBrasilSerie_B_2024.PONTE_PRETA.getNome();
                imagem = TimesBrasilSerie_B_2024.PONTE_PRETA.getImagem();
                break;
            case "CRB":
                nome = TimesBrasilSerie_B_2024.CRB.getNome();
                imagem = TimesBrasilSerie_B_2024.CRB.getImagem();
                break;
            case "Novorizontino":
                nome = TimesBrasilSerie_B_2024.NOVORIZONTINO.getNome();
                imagem = TimesBrasilSerie_B_2024.NOVORIZONTINO.getImagem();
                break;
            case "Guarani":
                nome = TimesBrasilSerie_B_2024.GUARANI.getNome();
                imagem = TimesBrasilSerie_B_2024.GUARANI.getImagem();
                break;
            case "Vila Nova":
                nome = TimesBrasilSerie_B_2024.VILA_NOVA.getNome();
                imagem = TimesBrasilSerie_B_2024.VILA_NOVA.getImagem();
                break;
            case "Goiás":
                nome = TimesBrasilSerie_B_2024.GOIAS.getNome();
                imagem = TimesBrasilSerie_B_2024.GOIAS.getImagem();
                break;
            case "Ceará":
                nome = TimesBrasilSerie_B_2024.CEARA.getNome();
                imagem = TimesBrasilSerie_B_2024.CEARA.getImagem();
                break;
            // CAMPEONATO LIGA PROFISSIONAL

            case "Newell's":
                nome = TimesArgentinaLigaProfissionalA2024.NEWELLS_OLD_BOYS.getNome();
                imagem = TimesArgentinaLigaProfissionalA2024.NEWELLS_OLD_BOYS.getImagem();
                break;
            case "Estudiantes":
                nome = TimesArgentinaLigaProfissionalA2024.ESTUDIANTES_LA_PLATA.getNome();
                imagem = TimesArgentinaLigaProfissionalA2024.ESTUDIANTES_LA_PLATA.getImagem();
                break;
            case "Vélez Sarsfield":
                nome = TimesArgentinaLigaProfissionalA2024.VELEZ_SARSFIELD.getNome();
                imagem = TimesArgentinaLigaProfissionalA2024.VELEZ_SARSFIELD.getImagem();
                break;
            case "Def y Justicia":
                nome = TimesArgentinaLigaProfissionalA2024.DEFENSA_Y_JUSTICIA.getNome();
                imagem = TimesArgentinaLigaProfissionalA2024.DEFENSA_Y_JUSTICIA.getImagem();
                break;
            case "Godoy Cruz":
                nome = TimesArgentinaLigaProfissionalA2024.GODOY_CRUZ.getNome();
                imagem = TimesArgentinaLigaProfissionalA2024.GODOY_CRUZ.getImagem();
                break;
            case "Argentinos Jrs.":
                nome = TimesArgentinaLigaProfissionalA2024.ARGENTINOS_JUNIORS.getNome();
                imagem = TimesArgentinaLigaProfissionalA2024.ARGENTINOS_JUNIORS.getImagem();
                break;
            case "Talleres":
                nome = TimesArgentinaLigaProfissionalA2024.TALLERES.getNome();
                imagem = TimesArgentinaLigaProfissionalA2024.TALLERES.getImagem();
                break;
            case "Instituto":
                nome = TimesArgentinaLigaProfissionalA2024.INSTITUTO.getNome();
                imagem = TimesArgentinaLigaProfissionalA2024.INSTITUTO.getImagem();
                break;
            case "Independiente":
                nome = TimesArgentinaLigaProfissionalA2024.INDEPENDIENTE.getNome();
                imagem = TimesArgentinaLigaProfissionalA2024.INDEPENDIENTE.getImagem();
                break;
            case "San Lorenzo":
                nome = TimesArgentinaLigaProfissionalA2024.SAN_LORENZO.getNome();
                imagem = TimesArgentinaLigaProfissionalA2024.SAN_LORENZO.getImagem();
                break;
            case "Huracán":
                nome = TimesArgentinaLigaProfissionalA2024.HURACAN.getNome();
                imagem = TimesArgentinaLigaProfissionalA2024.HURACAN.getImagem();
                break;
            case "Racing":
                nome = TimesArgentinaLigaProfissionalA2024.RACING.getNome();
                imagem = TimesArgentinaLigaProfissionalA2024.RACING.getImagem();
                break;
            case "Unión de Santa Fe":
                nome = TimesArgentinaLigaProfissionalA2024.UNION_SANTA_FE.getNome();
                imagem = TimesArgentinaLigaProfissionalA2024.UNION_SANTA_FE.getImagem();
                break;
            case "River":
                nome = TimesArgentinaLigaProfissionalA2024.RIVER_PLATE.getNome();
                imagem = TimesArgentinaLigaProfissionalA2024.RIVER_PLATE.getImagem();
                break;
            case "Sarmiento":
                nome = TimesArgentinaLigaProfissionalA2024.SARMIENTO.getNome();
                imagem = TimesArgentinaLigaProfissionalA2024.SARMIENTO.getImagem();
                break;
            case "Belgrano":
                nome = TimesArgentinaLigaProfissionalA2024.BELGRANO.getNome();
                imagem = TimesArgentinaLigaProfissionalA2024.BELGRANO.getImagem();
                break;
            case "Boca Juniors":
                nome = TimesArgentinaLigaProfissionalA2024.BOCA_JUNIORS.getNome();
                imagem = TimesArgentinaLigaProfissionalA2024.BOCA_JUNIORS.getImagem();
                break;
            case "Barracas":
                nome = TimesArgentinaLigaProfissionalA2024.BARRACAS_CENTRAL.getNome();
                imagem = TimesArgentinaLigaProfissionalA2024.BARRACAS_CENTRAL.getImagem();
                break;
            case "Lanús":
                nome = TimesArgentinaLigaProfissionalA2024.LANUS.getNome();
                imagem = TimesArgentinaLigaProfissionalA2024.LANUS.getImagem();
                break;
            case "Tigre":
                nome = TimesArgentinaLigaProfissionalA2024.TIGRE.getNome();
                imagem = TimesArgentinaLigaProfissionalA2024.TIGRE.getImagem();
                break;
            case "Atlético Tucumán":
                nome = TimesArgentinaLigaProfissionalA2024.ATLETICO_TUCUMAN.getNome();
                imagem = TimesArgentinaLigaProfissionalA2024.ATLETICO_TUCUMAN.getImagem();
                break;
            case "Ind. Rivadavia":
                nome = TimesArgentinaLigaProfissionalA2024.INDEPENDIENTE_RIVADAVIA.getNome();
                imagem = TimesArgentinaLigaProfissionalA2024.INDEPENDIENTE_RIVADAVIA.getImagem();
                break;
            case "Dep. Riestra":
                nome = TimesArgentinaLigaProfissionalA2024.DEPORTIVO_RIESTRA.getNome();
                imagem = TimesArgentinaLigaProfissionalA2024.DEPORTIVO_RIESTRA.getImagem();
                break;
            case "Central Córdoba":
                nome = TimesArgentinaLigaProfissionalA2024.CENTRAL_CORDOBA_SDE.getNome();
                imagem = TimesArgentinaLigaProfissionalA2024.CENTRAL_CORDOBA_SDE.getImagem();
                break;
            case "Gimnasia":
                nome = TimesArgentinaLigaProfissionalA2024.GIMNASIA_ESGRIMA.getNome();
                imagem = TimesArgentinaLigaProfissionalA2024.GIMNASIA_ESGRIMA.getImagem();
                break;
            case "Rosario Central":
                nome = TimesArgentinaLigaProfissionalA2024.ROSARIO_CENTRAL.getNome();
                imagem = TimesArgentinaLigaProfissionalA2024.ROSARIO_CENTRAL.getImagem();
                break;
            case "Platense":
                nome = TimesArgentinaLigaProfissionalA2024.PLATENSE.getNome();
                imagem = TimesArgentinaLigaProfissionalA2024.PLATENSE.getImagem();
                break;
            case "Banfield":
                nome = TimesArgentinaLigaProfissionalA2024.BANFIELD.getNome();
                imagem = TimesArgentinaLigaProfissionalA2024.BANFIELD.getImagem();
                break;

                //CAMPEONATO LA LIGA 2024-2025
            case "Alavés":
                nome = TimesLaLiga_A_2024_25.ALAVES.getNome();
                imagem = TimesLaLiga_A_2024_25.ALAVES.getImagem();
                break;
            case "Athletic Club":
                nome = TimesLaLiga_A_2024_25.ATHLETIC_CLUB.getNome();
                imagem = TimesLaLiga_A_2024_25.ATHLETIC_CLUB.getImagem();
                break;
            case "Atl. Madrid":
                nome = TimesLaLiga_A_2024_25.ATL_MADRID.getNome();
                imagem = TimesLaLiga_A_2024_25.ATL_MADRID.getImagem();
                break;
            case "Barcelona":
                nome = TimesLaLiga_A_2024_25.BARCELONA.getNome();
                imagem = TimesLaLiga_A_2024_25.BARCELONA.getImagem();
                break;
            case "Celta":
                nome = TimesLaLiga_A_2024_25.CELTA.getNome();
                imagem = TimesLaLiga_A_2024_25.CELTA.getImagem();
                break;
            case "Espanyol":
                nome = TimesLaLiga_A_2024_25.ESPANYOL.getNome();
                imagem = TimesLaLiga_A_2024_25.ESPANYOL.getImagem();
                break;
            case "Getafe":
                nome = TimesLaLiga_A_2024_25.GETAFE.getNome();
                imagem = TimesLaLiga_A_2024_25.GETAFE.getImagem();
                break;
            case "Girona":
                nome = TimesLaLiga_A_2024_25.GIRONA.getNome();
                imagem = TimesLaLiga_A_2024_25.GIRONA.getImagem();
                break;
            case "Las Palmas":
                nome = TimesLaLiga_A_2024_25.LAS_PALMAS.getNome();
                imagem = TimesLaLiga_A_2024_25.LAS_PALMAS.getImagem();
                break;
            case "Leganés":
                nome = TimesLaLiga_A_2024_25.LEGANES.getNome();
                imagem = TimesLaLiga_A_2024_25.LEGANES.getImagem();
                break;
            case "Mallorca":
                nome = TimesLaLiga_A_2024_25.MALLORCA.getNome();
                imagem = TimesLaLiga_A_2024_25.MALLORCA.getImagem();
                break;
            case "Osasuna":
                nome = TimesLaLiga_A_2024_25.OSASUNA.getNome();
                imagem = TimesLaLiga_A_2024_25.OSASUNA.getImagem();
                break;
            case "Rayo Vallecano":
                nome = TimesLaLiga_A_2024_25.RAYO_VALLECANO.getNome();
                imagem = TimesLaLiga_A_2024_25.RAYO_VALLECANO.getImagem();
                break;
            case "Real Betis":
                nome = TimesLaLiga_A_2024_25.REAL_BETIS.getNome();
                imagem = TimesLaLiga_A_2024_25.REAL_BETIS.getImagem();
                break;
            case "Real Madrid":
                nome = TimesLaLiga_A_2024_25.REAL_MADRID.getNome();
                imagem = TimesLaLiga_A_2024_25.REAL_MADRID.getImagem();
                break;
            case "Real Sociedad":
                nome = TimesLaLiga_A_2024_25.REAL_SOCIEDAD.getNome();
                imagem = TimesLaLiga_A_2024_25.REAL_SOCIEDAD.getImagem();
                break;
            case "Real Valladolid":
                nome = TimesLaLiga_A_2024_25.REAL_VALLADOLID.getNome();
                imagem = TimesLaLiga_A_2024_25.REAL_VALLADOLID.getImagem();
                break;
            case "Sevilla":
                nome = TimesLaLiga_A_2024_25.SEVILLA.getNome();
                imagem = TimesLaLiga_A_2024_25.SEVILLA.getImagem();
                break;
            case "Valencia":
                nome = TimesLaLiga_A_2024_25.VALENCIA.getNome();
                imagem = TimesLaLiga_A_2024_25.VALENCIA.getImagem();
                break;
            case "Villarreal":
                nome = TimesLaLiga_A_2024_25.VILLARREAL.getNome();
                imagem = TimesLaLiga_A_2024_25.VILLARREAL.getImagem();
                break;

            default:
                return new String[]{null, null}; // Retorna null para nome e imagem
        }

        return new String[]{nome, imagem}; // Retorna um array com o nome e a imagem
    }//FIM DO MÉTODO












}
