package Brasileirao;

import java.time.LocalDate;

public class Partida {
    private LocalDate data;
    private Time mandante;
    private Time visitante;
    private int golsMandante;
    private int golsVisitante;
    private String estadio;
    private int rodada;
    private String competicao;

    public Partida(LocalDate data, Time mandante, Time visitante, int golsMandante, int golsVisitante, String estadio, int rodada, String competicao) {
        if (data == null) {
            throw new IllegalArgumentException("A data da partida não pode ser nula.");
        }
        if (mandante == null || visitante == null) {
            throw new IllegalArgumentException("Os times mandante e visitante não podem ser nulos.");
        }
        if (mandante.equals(visitante)) {
            throw new IllegalArgumentException("Um time não pode jogar contra ele mesmo.");
        }
        if (golsMandante < 0 || golsVisitante < 0) {
            throw new IllegalArgumentException("O número de gols não pode ser negativo.");
        }
        if (estadio == null || estadio.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do estádio não pode ser vazio.");
        }
        this.data = data;
        this.mandante = mandante;
        this.visitante = visitante;
        this.golsMandante = golsMandante;
        this.golsVisitante = golsVisitante;
        this.estadio = estadio;
        this.rodada = rodada;
        this.competicao = competicao;
    }

    public Partida(String timeCasa, String timeVisitante, int golsCasa, int golsVisitante, int ano) {
        if (timeCasa == null || timeCasa.trim().isEmpty()) {
            throw new IllegalArgumentException("O time da casa não pode ser nulo ou vazio.");
        }
        if (timeVisitante == null || timeVisitante.trim().isEmpty()) {
            throw new IllegalArgumentException("O time visitante não pode ser nulo ou vazio.");
        }
        if (timeCasa.equals(timeVisitante)) {
            throw new IllegalArgumentException("Um time não pode jogar contra ele mesmo.");
        }
        if (golsCasa < 0 || golsVisitante < 0) {
            throw new IllegalArgumentException("O número de gols não pode ser negativo.");
        }
        if (ano < 1900) {
            throw new IllegalArgumentException("O ano da partida deve ser válido.");
        }
    
        this.mandante = new Time(timeCasa, timeVisitante, ano, ano, timeVisitante);
        this.visitante = new Time(timeVisitante, timeVisitante, ano, ano, timeVisitante);
        this.golsMandante = golsCasa;
        this.golsVisitante = golsVisitante;
        this.data = LocalDate.of(ano, 1, 1); 
        this.estadio = "Estádio Desconhecido"; 
        this.rodada = 1; 
        this.competicao = "Competição Desconhecida"; 
    }
    

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        if (data == null) {
            throw new IllegalArgumentException("A data da partida não pode ser nula.");
        }
        this.data = data;
    }

    public Time getMandante() {
        return mandante;
    }

    public void setMandante(Time mandante) {
        if (mandante == null) {
            throw new IllegalArgumentException("O time mandante não pode ser nulo.");
        }
        this.mandante = mandante;
    }

    public Time getVisitante() {
        return visitante;
    }

    public void setVisitante(Time visitante) {
        if (visitante == null) {
            throw new IllegalArgumentException("O time visitante não pode ser nulo.");
        }
        this.visitante = visitante;
    }

    public int getGolsMandante() {
        return golsMandante;
    }

    public void setGolsMandante(int golsMandante) {
        if (golsMandante < 0) {
            throw new IllegalArgumentException("O número de gols do mandante não pode ser negativo.");
        }
        this.golsMandante = golsMandante;
    }

    public int getGolsVisitante() {
        return golsVisitante;
    }

    public void setGolsVisitante(int golsVisitante) {
        if (golsVisitante < 0) {
            throw new IllegalArgumentException("O número de gols do visitante não pode ser negativo.");
        }
        this.golsVisitante = golsVisitante;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        if (estadio == null || estadio.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do estádio não pode ser vazio.");
        }
        this.estadio = estadio;
    }

    public int getRodada() {
        return rodada;
    }

    public void setRodada(int rodada) {
        if (rodada < 1) {
            throw new IllegalArgumentException("A rodada deve ser maior ou igual a 1.");
        }
        this.rodada = rodada;
    }

    public String getCompeticao() {
        return competicao;
    }

    public void setCompeticao(String competicao) {
        if (competicao == null || competicao.trim().isEmpty()) {
            throw new IllegalArgumentException("A competição não pode ser vazia.");
        }
        this.competicao = competicao;
    }

    public boolean foiEmpate() {
        return golsMandante == golsVisitante;
    }

    public Time getVencedor() {
        if (golsMandante > golsVisitante) {
            return mandante;
        } else if (golsMandante < golsVisitante) {
            return visitante;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format("Partida{data=%s, mandante=%s, visitante=%s, golsMandante=%d, golsVisitante=%d, estadio='%s', rodada=%d, competicao='%s'}",
                data, mandante, visitante, golsMandante, golsVisitante, estadio, rodada, competicao);
    }

    public int getAno() {
        throw new UnsupportedOperationException("Unimplemented method 'getAno'");
    }

    public String getTimeCasa() {
        throw new UnsupportedOperationException("Unimplemented method 'getTimeCasa'");
    }

    public int getGolsCasa() {
        throw new UnsupportedOperationException("Unimplemented method 'getGolsCasa'");
    }
}
