package agenda;

public class SimularEnvioNotificacaoCompromisso {

    public static void enviarNotificacao(Compromisso compromisso) {
        try {
            System.out.printf(" %s: Enviando notificacao... %s.%n",
                    Thread.currentThread().getName(), compromisso.getTitulo());
            Thread.sleep(6000); //simula o trabalho de envio
            System.out.println(Thread.currentThread().getName()
                    + " notificação enviada: " + compromisso.getTitulo());
        } catch (InterruptedException e){
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
