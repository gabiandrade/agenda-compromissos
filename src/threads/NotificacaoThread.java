package threads;

public class NotificacaoThread implements Runnable {

    private Notificacao notificacao;

    public NotificacaoThread(Notificacao notificacao) {
        this.notificacao = notificacao;
    }


    @Override
    public void run() {
        notificacao.read();
    }
}
