package agenda;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CriarListaCompromisso {

    public static void main(String[] args) {

        List<Compromisso> compromissos = List.of(
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


        //Aplicando o Function
        List<String> compromissosString = compromissos.stream()
                .map(CompromissoFormatter.formatter)
                .toList();
        compromissosString.forEach(System.out::println);

        //Aplicar o BiFunction para somar Durações

        Duration total = compromissos.stream()
                .reduce(Duration.ZERO,
                        (acc, c) -> acc.plus(c.getDuracao()),
                        Duration::plus);
        System.out.println("total: " + total);
    }
}
