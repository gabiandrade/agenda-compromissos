package threads;

public class ContadorTask implements Runnable {

    private int contador = 0;

    @Override
    public void run() {
        //Semáforo para a thread esperar
        synchronized (this) {
            contador++;
            System.out.printf(Thread.currentThread().getName() + ": %d%n", contador);
        }


    }
}
