package agenda;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class NotificacaoAssincronaCompromisso {

    public static CompletableFuture<String> enviarNotificacaoAsync(Compromisso compromisso) {
        //Thread.sleep(6000);
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " iniciando envio: " + compromisso.getTitulo());
                Thread.sleep(6000); // simula latência
                return Thread.currentThread().getName() + "Enviado para: " + compromisso.getTitulo();

            } catch (InterruptedException e) {
                e.printStackTrace();
                return "Falha!";
            }
        }, Executors.newFixedThreadPool(4));
    }

}
