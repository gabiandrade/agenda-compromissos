package threads;

public class Main {

    public static void main(String[] args) {
        //Declara as threads
        Thread task1 = new Thread(new HelloTask("Gabriela"));
        Thread task2 = new Thread(new HelloTask("Edson"));
        Thread task3 = new Thread(new HelloTask("Vivian"));
        Thread task4 = new Thread(new HelloTask("Jefferson"));
        Thread task5 = new Thread(new HelloTask("Maria"));

        //Executa as threads
        task1.start();
        task2.start();
        task3.start();
        task4.start();
        task5.start();
    }
}

