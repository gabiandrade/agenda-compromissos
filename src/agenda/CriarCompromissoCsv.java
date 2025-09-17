package agenda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;

public class CriarCompromissoCsv {

    public static void main(String[] args) throws IOException {

        List<Compromisso> listaCompromissos = List.of(
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


       Path path = Paths.get("compromisso.csv");

       CompromissoProcessadorCsv.addToCsv(path, listaCompromissos);
       System.out.println("Arquivo gravado em:" + path.toAbsolutePath());

       //readToCsv
       List<Compromisso> resultado = CompromissoProcessadorCsv.readFromCsv(path);

       if(resultado.isEmpty()){
           System.out.println("Nenhum compromisso encontrado!");
           try(Stream<String> lines = Files.lines(path)) {
               lines.forEach(System.out::println);
           }
       } else {
           resultado.forEach(System.out::println);

           resultado.stream().filter(c -> c.getData().isAfter(LocalDate.now())).
                   forEach(c-> System.out.println("Compromissos Futuros: " + c));
       }
    }
}
