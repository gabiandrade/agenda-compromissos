package agenda;

import java.time.Duration;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CompromissoFormatter {


    //Function e BiFunction
    //Function<T,R>
    public static final Function<Compromisso, String> formatter = c ->
            String.format("[%s] %s - %s (%s min)", c.getData(), c.getTitulo(),
                    c.getDescricao(), c.getDuracao().toMinutes());

    //somar as durações do compromisso
    //BiFunction<T, U, R>
    public static final BiFunction<Compromisso, Compromisso, Duration> somarDuracoesCompromissos =
            (a, b) -> a.getDuracao().plus(b.getDuracao());


}
