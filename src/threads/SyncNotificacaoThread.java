package threads;

public class SyncNotificacaoThread implements Runnable {

    private Notificacao notificacao;

    public SyncNotificacaoThread(Notificacao notificacao) {
        this.notificacao = notificacao;
    }

    @Override
    public void run() {
        notificacao.sync();
    }
}
