package Brasileirao;

public class Jogador {
    private String nome;
    private String time;
    private int gols;

    public Jogador(String nome, String time) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do jogador não pode ser vazio.");
        }
        if (time == null || time.trim().isEmpty()) {
            throw new IllegalArgumentException("O time do jogador não pode ser vazio.");
        }
        this.nome = nome;
        this.time = time;
        this.gols = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do jogador não pode ser vazio.");
        }
        this.nome = nome;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        if (time == null || time.trim().isEmpty()) {
            throw new IllegalArgumentException("O time do jogador não pode ser vazio.");
        }
        this.time = time;
    }

    public int getGols() {
        return gols;
    }

    public void adicionarGols(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("A quantidade de gols não pode ser negativa.");
        }
        this.gols += quantidade;
    }

    @Override
    public String toString() {
        return String.format("Jogador{nome='%s', time='%s', gols=%d}", nome, time, gols);
    }

    public int compareTo(Jogador outro) {
        return Integer.compare(this.gols, outro.gols);
    }
}
