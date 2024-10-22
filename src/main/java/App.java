import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import Analise.AnalisarBrasileirao.Jogador;
import Analise.AnalisarBrasileirao.Partida;
import Analise.AnalisarBrasileirao.Time;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static final String CAMPEONATO_JOGADORES_FILE = "C:\\Users\\Operação\\Desktop\\Renato Boranga\\Santander Coders 2024\\Tecnicas\\ProjetoFinalTecProg\\src\\resources\\data\\campeonato-brasileiro-full.csv";
    public static final String CAMPEONATO_PARTIDAS_FILE_ESTATISTICAS = "C:\\Users\\Operação\\Desktop\\Renato Boranga\\Santander Coders 2024\\Tecnicas\\ProjetoFinalTecProg\\src\\resources\\data\\campeonato-brasileiro-estatisticas-full.csv";
    public static final String CAMPEONATO_TIMES_FILE = "C:\\Users\\Operação\\Desktop\\Renato Boranga\\Santander Coders 2024\\Tecnicas\\ProjetoFinalTecProg\\src\\resources\\data\\campeonato-brasileiro-gols.csv";

    public static void main(String[] args) {
        try {
            List<Time> times = carregarTimes(CAMPEONATO_TIMES_FILE);
            List<Jogador> jogadores = carregarJogadores(CAMPEONATO_JOGADORES_FILE);
            List<Partida> partidas = carregarPartidas(CAMPEONATO_PARTIDAS_FILE_ESTATISTICAS);
            realizarConsultas(times, jogadores, partidas);
        } catch (IOException e) {
            System.err.println("Erro ao carregar os dados: " + e.getMessage());
        }
    }

    private static void realizarConsultas(List<Time> times, List<Jogador> jogadores, List<Partida> partidas) {
        Time timeMaisVitorias2008 = encontrarTimeMaisVitoriasEmAno(times, partidas, 2008);
        exibirTimeMaisVitorias(timeMaisVitorias2008, 2008);
        Jogador artilheiro = encontrarArtilheiro(jogadores);
        exibirArtilheiro(artilheiro);
    }

    private static void exibirTimeMaisVitorias(Time time, int ano) {
        if (time != null) {
            System.out.println("Time que mais venceu em " + ano + ": " + time.getNome());
        } else {
            System.out.println("Nenhum time encontrado para o ano " + ano + ".");
        }
    }

    private static void exibirArtilheiro(Jogador jogador) {
        if (jogador != null) {
            System.out.println("Artilheiro: " + jogador.getNome() + " (" + jogador.getGols() + " gols)");
        } else {
            System.out.println("Nenhum jogador encontrado.");
        }
    }

    private static List<Time> carregarTimes(String arquivo) throws IOException {
        Map<String, Time> timesMap = new HashMap<>();

        try (Reader reader = new FileReader(arquivo)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(reader);
            for (CSVRecord record : records) {
                String mandante = record.get("mandante");
                int vitorias = Integer.parseInt(record.get("mandante_Placar"));
                int derrotas = Integer.parseInt(record.get("visitante_Placar"));

                // Inicializa ou atualiza o time no mapa
                timesMap.putIfAbsent(mandante, new Time(mandante, 0, 0, 0));
                Time time = timesMap.get(mandante);
                time.addVitorias(vitorias);
                time.addDerrotas(derrotas);
            }
        }

        return new ArrayList<>(timesMap.values());
    }

    private static List<Jogador> carregarJogadores(String arquivo) throws IOException {
        Map<String, Integer> golsPorJogador = new HashMap<>();

        try (Reader reader = new FileReader(arquivo)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(reader);
            for (CSVRecord record : records) {
                String nome = record.get("atleta");
                golsPorJogador.put(nome, golsPorJogador.getOrDefault(nome, 0) + 1);
            }
        }

        List<Jogador> jogadores = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : golsPorJogador.entrySet()) {
            jogadores.add(new Jogador(entry.getKey(), entry.getKey(), entry.getValue()));
        }

        return jogadores;
    }

    private static List<Partida> carregarPartidas(String arquivo) throws IOException {
        List<Partida> partidas = new ArrayList<>();
        try (Reader reader = new FileReader(arquivo)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(reader);
            for (CSVRecord record : records) {
                String timeCasa = record.get("mandante");
                String timeVisitante = record.get("visitante");
                int golsCasa = Integer.parseInt(record.get("mandante_Placar"));
                int golsVisitante = Integer.parseInt(record.get("visitante_Placar"));
                int ano = Integer.parseInt(record.get("rodata").substring(0, 4));

                partidas.add(new Partida(timeCasa, timeVisitante, golsCasa, golsVisitante, ano));
            }
        }
        return partidas;
    }

    private static Time encontrarTimeMaisVitoriasEmAno(List<Time> times, List<Partida> partidas, int ano) {
        Map<String, Integer> vitoriasPorTime = new HashMap<>();
        
        for (Partida partida : partidas) {
            if (partida.getAno() == ano) {
                if (partida.getGolsCasa() > partida.getGolsVisitante()) {
                    vitoriasPorTime.merge(partida.getTimeCasa(), 1, Integer::sum);
                } else if (partida.getGolsVisitante() > partida.getGolsCasa()) {
                    vitoriasPorTime.merge(partida.getTimeVisitante(), 1, Integer::sum);
                }
            }
        }

        return times.stream()
                .max((t1, t2) -> Integer.compare(vitoriasPorTime.getOrDefault(t1.getNome(), 0), vitoriasPorTime.getOrDefault(t2.getNome(), 0)))
                .orElse(null);
    }

    private static Jogador encontrarArtilheiro(List<Jogador> jogadores) {
        return jogadores.stream()
                .max((j1, j2) -> Integer.compare(j1.getGols(), j2.getGols()))
                .orElse(null);
    }
}
