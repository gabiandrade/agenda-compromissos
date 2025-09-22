package threads;

public class ContadorThread {

    public static void main(String[] args) {
        ContadorTask contador = new ContadorTask();
        Thread contador1 = new Thread(contador);
        Thread contador2 = new Thread(contador);
        Thread contador3 = new Thread(contador);
        Thread contador4 = new Thread(contador);

        contador1.start();
        contador2.start();
        contador3.start();
        contador4.start();

    }
}
