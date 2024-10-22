package Brasileirao;

import java.time.LocalDate;

public class Time {
    private String nome;             // Nome do time
    private String estado;           // Estado do time
    private int anoFundacao;        // Ano de fundação do time
    private int numeroTitulos;       // Número de títulos conquistados
    private String estadio;          // Nome do estádio

    // Construtor da classe Time
    public Time(String nome, String estado, int anoFundacao, int numeroTitulos, String estadio) {
        // Validações
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do time não pode ser vazio.");
        }
        if (estado == null || estado.trim().isEmpty()) {
            throw new IllegalArgumentException("Estado do time não pode ser vazio.");
        }
        if (anoFundacao < 1800 || anoFundacao > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("Ano de fundação inválido.");
        }
        if (numeroTitulos < 0) {
            throw new IllegalArgumentException("Número de títulos não pode ser negativo.");
        }
        if (estadio == null || estadio.trim().isEmpty()) {
            throw new IllegalArgumentException("Estádio não pode ser vazio.");
        }
        
        // Inicializa os atributos
        this.nome = nome;
        this.estado = estado;
        this.anoFundacao = anoFundacao;
        this.numeroTitulos = numeroTitulos;
        this.estadio = estadio;
    }

    // Métodos getters
    public String getNome() {
        return nome;
    }

    public String getEstado() {
        return estado;
    }

    public int getAnoFundacao() {
        return anoFundacao;
    }

    public int getNumeroTitulos() {
        return numeroTitulos;
    }

    public String getEstadio() {
        return estadio;
    }

    // Métodos setters
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do time não pode ser vazio.");
        }
        this.nome = nome;
    }

    public void setEstado(String estado) {
        if (estado == null || estado.trim().isEmpty()) {
            throw new IllegalArgumentException("Estado do time não pode ser vazio.");
        }
        this.estado = estado;
    }

    public void setAnoFundacao(int anoFundacao) {
        if (anoFundacao < 1800 || anoFundacao > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("Ano de fundação inválido.");
        }
        this.anoFundacao = anoFundacao;
    }

    public void setNumeroTitulos(int numeroTitulos) {
        if (numeroTitulos < 0) {
            throw new IllegalArgumentException("Número de títulos não pode ser negativo.");
        }
        this.numeroTitulos = numeroTitulos;
    }

    public void setEstadio(String estadio) {
        if (estadio == null || estadio.trim().isEmpty()) {
            throw new IllegalArgumentException("Estádio não pode ser vazio.");
        }
        this.estadio = estadio;
    }

    // Calcula a idade do time
    public int calcularIdade() {
        return LocalDate.now().getYear() - anoFundacao;
    }

    // Verifica se este time tem mais títulos que outro
    public boolean temMaisTitulosQue(Time outroTime) {
        return this.numeroTitulos > outroTime.numeroTitulos;
    }

    // Representação textual do time
    @Override
    public String toString() {
        return String.format("Time { Nome: '%s', Estado: '%s', Ano de Fundação: %d, Número de Títulos: %d, Estádio: '%s' }", 
                nome, estado, anoFundacao, numeroTitulos, estadio);
    }
}
