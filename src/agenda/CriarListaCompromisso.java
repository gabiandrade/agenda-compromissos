package agenda;

import java.time.Duration;
import java.util.List;

public class CriarListaCompromisso {

    public static void main(String[] args) {

        //Aplicando o Function
        List<String> compromissosString = ListaCompromisso.listaCompromissos().stream()
                .map(CompromissoFormatter.formatter)
                .toList();
        compromissosString.forEach(System.out::println);

        //Aplicar o BiFunction para somar Durações

        Duration total = ListaCompromisso.listaCompromissos().stream()
                .reduce(Duration.ZERO,
                        (acc, c) -> acc.plus(c.getDuracao()),
                        Duration::plus);
        System.out.println("total: " + total);
    }
}
