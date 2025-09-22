package threads;

public class SimuladorNotificacao {
    public static void main(String[] args) {


        Notificacao notificacao = new Notificacao();
        notificacao.setMensagem("Penúltima aula");
        //notificacao.setOutOfSync(true);

        Thread readNotificacao = new Thread(new NotificacaoThread(notificacao));
        Thread syncNotificacao = new Thread(new SyncNotificacaoThread(notificacao));

        readNotificacao.start();

        //Encerrar thread evitando loop
        syncNotificacao.setDaemon(true);
        syncNotificacao.start();
    }
}