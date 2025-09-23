package agenda;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class EnviarNotificacaoAssincronaCompromisso {

    public static void main(String[] args)  {
        List<Compromisso> compromissos = ListaCompromisso.listaCompromissos();

        //Dispara todas as notificacoes async
        List<CompletableFuture<String>> futures = compromissos.stream()
                .map(NotificacaoAssincronaCompromisso::enviarNotificacaoAsync)
                .toList();

        CompletableFuture<Void> all = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        all.thenRun(() -> System.out.println("Todas as notificações disparadas."));

        all.join();
        List<String> resultados = futures.stream()
                .map(CompletableFuture::join)
                .toList();

        resultados.forEach(System.out::println);
    }
}
