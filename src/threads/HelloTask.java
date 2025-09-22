package threads;

public class HelloTask implements Runnable{

    private String nome;

    public HelloTask(String nome) {
        this.nome = nome;
    }


   @Override
    public void run() {

        try{
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            System.out.println("name" + "foi interrompida!");
            e.printStackTrace();
        }
        System.out.printf("Hello, %s!%n", nome);
    }
}
