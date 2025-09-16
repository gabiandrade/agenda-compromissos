package agenda;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class CriarCompromisso {
    public static void main(String[] args) {

        Compromisso c1 = new Compromisso(
                "Aula Caixa Verso",
                "Aula de Java",
                LocalDate.of(2025, 9, 1),
                LocalTime.of(19, 0),
                Duration.ofHours(3));

        Compromisso c2 = new Compromisso(
                "Reunião Projeto Java",
                "E-commerce Ada",
                LocalDate.now().plusDays(3),
                LocalTime.of(20, 0),
                Duration.ofHours(1));

        System.out.println(c1);


        if(c1.getData().isBefore(c2.getData())) {
            System.out.println(c1.getTitulo() + " é antes da " + c2.getTitulo());
        }

        long diasFaltam = LocalDate.now().until(c2.getData(), ChronoUnit.DAYS);
        System.out.println("Dias até " + c2.getTitulo() + ": " + diasFaltam + " dias");
    }

}
