package agenda;

public class EnviarNotificacaoCompromisso {


    public static void main(String[] args) throws InterruptedException {

        for (Compromisso compromisso: ListaCompromisso.listaCompromissos()){
            Thread thread1 = new Thread(()-> SimularEnvioNotificacaoCompromisso.enviarNotificacao(compromisso));
            thread1.start();
            Thread.sleep(2000);
        }

        Thread.sleep(6000);
        System.out.println("Fim das Threads!!");
    }
}
