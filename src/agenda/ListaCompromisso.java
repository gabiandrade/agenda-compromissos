package agenda;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ListaCompromisso {

    public static List<Compromisso> listaCompromissos() {
        return List.of(
                new Compromisso(
                        "Aula Caixa Verso",
                        "Aula de Java",
                        LocalDate.of(2025, 9, 1),
                        LocalTime.of(19, 0),
                        Duration.ofHours(3)),
                new Compromisso(
                        "Reunião Projeto Java",
                        "E-commerce Ada",
                        LocalDate.now().plusDays(3),
                        LocalTime.of(20, 0),
                        Duration.ofHours(1))
        );
    }
}
