package Analise;

import java.io.*;
import java.util.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class AnalisarBrasileirao {

    private static final String ARQUIVO_TIMES = "C:/Users/Operação/Desktop/Renato Boranga/Santander Coders 2024/Tecnicas/ProjetoFinalTecProg/data/campeonato-brasileiro-full.csv";
    private static final String ARQUIVO_JOGADORES = "C:/Users/Operação/Desktop/Renato Boranga/Santander Coders 2024/Tecnicas/ProjetoFinalTecProg/data/campeonato-brasileiro-gols.csv";
    private static final String ARQUIVO_PARTIDAS = "C:/Users/Operação/Desktop/Renato Boranga/Santander Coders 2024/Tecnicas/ProjetoFinalTecProg/data/campeonato-brasileiro-partidas.csv";

    public static class Time {
        private String nome;
        private String estado;
        private int anoFundacao;
        private int numeroTitulos;

        public Time(String nome, String estado2, int anoFundacao, int numeroTitulos) {
            this.nome = nome;
            this.estado = estado2;
            this.anoFundacao = anoFundacao;
            this.numeroTitulos = numeroTitulos;
        }

        public Time(String nome2, int vitorias, int derrotas, int empates) {
        }

        public String getNome() {
            return nome;
        }

        public int getAnoFundacao() {
            return anoFundacao;
        }

        public int getNumeroTitulos() {
            return numeroTitulos;
        }

        @Override
        public String toString() {
            return String.format("Time{nome='%s', estado='%s', anoFundacao=%d, numeroTitulos=%d}", nome, estado, anoFundacao, numeroTitulos);
        }

        public void addVitorias(int vitorias) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'addVitorias'");
        }

        public void addDerrotas(int derrotas) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'addDerrotas'");
        }
    }

    public static class Jogador {
        private String nome;
        private String time;
        private int gols;

        public Jogador(String nome, String time, int gols) {
            this.nome = nome;
            this.time = time;
            this.gols = gols;
        }

        public String getNome() {
            return nome;
        }

        public int getGols() {
            return gols;
        }

        @Override
        public String toString() {
            return String.format("Jogador{nome='%s', time='%s', gols=%d}", nome, time, gols);
        }
    }

    public static class Partida {
        private String timeCasa;
        private String timeVisitante;
        private int golsCasa;
        private int golsVisitante;
        private int ano;

        public Partida(String timeCasa, String timeVisitante, int golsCasa, int golsVisitante, int ano) {
            this.timeCasa = timeCasa;
            this.timeVisitante = timeVisitante;
            this.golsCasa = golsCasa;
            this.golsVisitante = golsVisitante;
            this.ano = ano;
        }

        public String getTimeCasa() {
            return timeCasa;
        }

        public String getTimeVisitante() {
            return timeVisitante;
        }

        public int getGolsCasa() {
            return golsCasa;
        }

        public int getGolsVisitante() {
            return golsVisitante;
        }

        public int getAno() {
            return ano;
        }
    }

    public static void main(String[] args) {
        try {
            List<Time> times = carregarTimes(ARQUIVO_TIMES);
            List<Jogador> jogadores = carregarJogadores(ARQUIVO_JOGADORES);
            List<Partida> partidas = carregarPartidas(ARQUIVO_PARTIDAS);

            Time timeMaisVitorias2008 = encontrarTimeMaisVitoriasEmAno(times, partidas, 2008);
            if (timeMaisVitorias2008 != null) {
                System.out.println("Time que mais venceu em 2008: " + timeMaisVitorias2008.getNome());
            } else {
                System.out.println("Nenhum time encontrado para o ano de 2008.");
            }

            Jogador artilheiro = encontrarArtilheiro(jogadores);
            if (artilheiro != null) {
                System.out.println("Artilheiro: " + artilheiro.getNome() + " (" + artilheiro.getGols() + " gols)");
            } else {
                System.out.println("Nenhum jogador encontrado.");
            }

        } catch (IOException e) {
            System.err.println("Erro ao carregar os dados: " + e.getMessage());
        }
    }

    private static List<Time> carregarTimes(String arquivo) throws IOException {
        List<Time> times = new ArrayList<>();
        try (Reader reader = new FileReader(arquivo);
             CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            for (CSVRecord record : parser) {
                String nome = record.get("nome");
                String estado = record.get("estado");
                int anoFundacao = parseInt(record.get("anoFundacao"), "ano de fundação");
                int numeroTitulos = parseInt(record.get("numeroTitulos"), "número de títulos");
                times.add(new Time(nome, estado, anoFundacao, numeroTitulos));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de times: " + e.getMessage());
            throw e;
        }
        return times;
    }

    private static List<Jogador> carregarJogadores(String arquivo) throws IOException {
        List<Jogador> jogadores = new ArrayList<>();
        try (Reader reader = new FileReader(arquivo);
             CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            for (CSVRecord record : parser) {
                String nome = record.get("nome");
                String time = record.get("time");
                int gols = parseInt(record.get("gols"), "gols");
                jogadores.add(new Jogador(nome, time, gols));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de jogadores: " + e.getMessage());
            throw e;
        }
        return jogadores;
    }

    private static List<Partida> carregarPartidas(String arquivo) throws IOException {
        List<Partida> partidas = new ArrayList<>();
        try (Reader reader = new FileReader(arquivo);
             CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            for (CSVRecord record : parser) {
                String timeCasa = record.get("timeCasa");
                String timeVisitante = record.get("timeVisitante");
                int golsCasa = parseInt(record.get("golsCasa"), "gols casa");
                int golsVisitante = parseInt(record.get("golsVisitante"), "gols visitante");
                int ano = parseInt(record.get("ano"), "ano");
                partidas.add(new Partida(timeCasa, timeVisitante, golsCasa, golsVisitante, ano));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de partidas: " + e.getMessage());
            throw e;
        }
        return partidas;
    }

    private static int parseInt(String value, String fieldName) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            System.err.printf("Erro ao converter %s: %s%n", fieldName, e.getMessage());
            return 0;
        }
    }

    private static Time encontrarTimeMaisVitoriasEmAno(List<Time> times, List<Partida> partidas, int ano) {
        Map<String, Integer> vitoriasPorTime = new HashMap<>();

        for (Partida partida : partidas) {
            if (partida.getAno() == ano) {
                String timeCasa = partida.getTimeCasa();
                String timeVisitante = partida.getTimeVisitante();
                int golsCasa = partida.getGolsCasa();
                int golsVisitante = partida.getGolsVisitante();

                if (golsCasa > golsVisitante) {
                    vitoriasPorTime.put(timeCasa, vitoriasPorTime.getOrDefault(timeCasa, 0) + 1);
                } else if (golsVisitante > golsCasa) {
                    vitoriasPorTime.put(timeVisitante, vitoriasPorTime.getOrDefault(timeVisitante, 0) + 1);
                }
            }
        }

        String timeMaisVitorias = vitoriasPorTime.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        return times.stream()
                .filter(t -> t.getNome().equals(timeMaisVitorias))
                .findFirst()
                .orElse(null);
    }

    private static Jogador encontrarArtilheiro(List<Jogador> jogadores) {
        return jogadores.stream()
                .max(Comparator.comparingInt(Jogador::getGols))
                .orElse(null);
    }
}
