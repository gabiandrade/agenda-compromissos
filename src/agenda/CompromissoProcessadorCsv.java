package agenda;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompromissoProcessadorCsv {

    //  Adicionar uma lista de compromissos no csv
    //Path
    public static void addToCsv(Path path, List<Compromisso> compromissos) {
        boolean arquivoExiste = Files.exists(path);

        try (BufferedWriter writer = Files.newBufferedWriter(path,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {

            if (!arquivoExiste) {
                writer.write("titulo,descricao,data,hora,duracao");
                writer.newLine();
            }

            for (Compromisso compromisso : compromissos) {
                writer.write(toCsvLine(compromisso));
                writer.newLine();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeToCsv(Path path, List<Compromisso> compromissos) throws IOException {

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write("titulo,descricao,data,hora,duracao");
            writer.newLine();
            for (Compromisso compromisso : compromissos) {
                writer.write(toCsvLine(compromisso));
                writer.newLine();
            }
        }
    }

    //Leitura do Csv
    public static List<Compromisso> readFromCsv(Path path) throws IOException {

        if (!Files.exists(path)) {
            return new ArrayList<>();
        }

        try (Stream<String> lines = Files.lines(path)) {
            return lines.map(String::trim)
                    .filter(l -> !l.toLowerCase().startsWith("titulo"))
                    .map(CompromissoProcessadorCsv::parseCsvLine)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
        }
    }

    private static String toCsvLine(Compromisso compromisso) {
        return String.join(",",
                escapeCsv(compromisso.getTitulo()),
                escapeCsv(compromisso.getDescricao()),
                compromisso.getData().toString(),
                compromisso.getHora().toString(),
                String.valueOf(compromisso.getDuracao().toHours()));
    }

    private static Optional<Compromisso> parseCsvLine(String line) {
        try {
            //Aula Ada,parts[0]
            // aula de arquivo,parts[1]
            // 2025-09-16,
            // 20:00,
            // 3
            String[] parts = line.split(",", -1);
            if (parts.length < 5) {
                return Optional.empty();
            }
            String titulo = unquote(parts[0].trim());
            String descricao = unquote(parts[1].trim());
            LocalDate data = LocalDate.parse(parts[2].trim());
            LocalTime hora = LocalTime.parse(parts[3].trim());
            long duracao = Long.parseLong(parts[4].trim());

            return Optional.of(new Compromisso(titulo, descricao, data, hora, Duration.ofHours(duracao)));
        } catch (Exception e) {
            System.err.println("Erro parse linha: " + line + " -> " + e.getMessage());
            return Optional.empty();
        }
    }


    private static String escapeCsv(String s) {
        if (s == null) {
            return "";
        }
        if (s.contains(",") || s.contains("\"") || s.contains("\n") || s.contains("\r")) {
            return "\"" + s.replace("\"", "\"\"") + "\"";
        }
        return s;
    }

    private static String unquote(String s) {
        if (s == null) return "";
        s = s.trim();
        if (s.startsWith("\"") && s.endsWith("\"")) {
            s = s.substring(1, s.length() - 1).replace("\"\"", "\"");
        }
        return s;
    }
}
