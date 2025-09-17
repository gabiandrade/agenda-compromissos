package agenda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class CriarCompromissoCsv {

    static Path path = Paths.get("compromisso.csv");

    public static void main(String[] args) throws IOException {
        CompromissoProcessadorCsv.addToCsv(path, ListaCompromisso.listaCompromissos());
        System.out.println("Arquivo gravado em:" + path.toAbsolutePath());

        //readToCsv
        List<Compromisso> resultado = CompromissoProcessadorCsv.readFromCsv(path);

        if (resultado.isEmpty()) {
            System.out.println("Nenhum compromisso encontrado!");
            try (Stream<String> lines = Files.lines(path)) {
                lines.forEach(System.out::println);
            }
        } else {
            resultado.forEach(System.out::println);

            resultado.stream().filter(c -> c.getData().isAfter(LocalDate.now())).
                    forEach(c -> System.out.println("Compromissos Futuros: " + c));
        }

        System.out.println();
        editarCompromissoCsv("Reunião");
    }


    private static Compromisso buscarCompromissoPorTitulo(String titulo) throws IOException {
        List<Compromisso> listaCompromisso = CompromissoProcessadorCsv.readFromCsv(path);
        for (Compromisso compromisso : listaCompromisso) {
            if (titulo.equals(compromisso.getTitulo())) {
                return compromisso;
            }
        }
        return null;
    }

    private static Optional<Compromisso> buscarCompromisso(Path path, String titulo) throws IOException {
        List<Compromisso> listaCompromisso = CompromissoProcessadorCsv.readFromCsv(path);
        return listaCompromisso.stream()
                .filter(c -> c.getTitulo().contains(titulo))
                .findFirst();
    }

    public static void editarCompromissoCsv(String titulo) throws IOException {
        List<Compromisso> listaCompromissos = CompromissoProcessadorCsv.readFromCsv(path);
        Optional<Compromisso> compromissoOptional = buscarCompromisso(path, titulo);
        if (compromissoOptional.isPresent()) {
            Compromisso compromissoEncontrado = compromissoOptional.get();

            List<Compromisso> novaListaSemCompromissoBuscado = new ArrayList<>();
            for (Compromisso compromisso : listaCompromissos) {
                if (!compromisso.equals(compromissoEncontrado)) {
                    novaListaSemCompromissoBuscado.add(compromisso);
                }
            }

            Compromisso compromissoAtualizado = atualizarCompromisso(compromissoEncontrado, "Reunião do time", null, null, null, null);
            novaListaSemCompromissoBuscado.add(compromissoAtualizado);
            CompromissoProcessadorCsv.writeToCsv(path, novaListaSemCompromissoBuscado);
            System.err.println("Compromisso atualizado com sucesso!");
            System.out.println(CompromissoProcessadorCsv.readFromCsv(path));
        }
    }

    private static Compromisso atualizarCompromisso(Compromisso compromissoAtual,
                                                    String novoTitulo,
                                                    String novaDescricao,
                                                    LocalDate novaData,
                                                    LocalTime novaHora,
                                                    Duration novaDuracao) {

        return new Compromisso(
                novoTitulo != null ? novoTitulo : compromissoAtual.getTitulo(),
                novaDescricao != null ? novaDescricao : compromissoAtual.getDescricao(),
                novaData != null ? novaData : compromissoAtual.getData(),
                novaHora != null ? novaHora : compromissoAtual.getHora(),
                novaDuracao != null ? novaDuracao : compromissoAtual.getDuracao()
        );
    }
}
