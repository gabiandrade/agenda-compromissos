package agenda;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Compromisso {
    private String titulo;
    private String descricao;
    private LocalDate data;
    private LocalTime hora;
    private Duration duracao;

    public Compromisso(String titulo, String descricao, LocalDate data, LocalTime hora, Duration duracao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.hora = hora;
        this.duracao = duracao;
    }

    public LocalDateTime getStart() {
        return LocalDateTime.of(data, hora);
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public Duration getDuracao() {
        return duracao;
    }

    public String toString() {
        return String.format("%s - %s (%s %s, %shr.)", titulo, descricao, data, hora, duracao.toHours());
    }


}

