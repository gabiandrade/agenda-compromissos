package threads;

public class Notificacao {
    private boolean outOfSync;
    private String mensagem;

    public boolean isOutOfSync() {
        return outOfSync;
    }

    public void setOutOfSync(boolean outOfSync) {
        this.outOfSync = outOfSync;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }


    public synchronized void sync() {
        System.out.println(Thread.currentThread().getName() + ": Sincronizando notificacao...");
        try {
            if (!outOfSync) {
                this.wait();
            }
            outOfSync = false;
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + ": Sincronizada!!");
            this.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized void read() {
        System.out.println(Thread.currentThread().getName() + ": Lendo notificacao...");
        try {
            if (outOfSync) {
                this.wait();
            }
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + ": Mensagem: " + mensagem);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
