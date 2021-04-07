package first_example;

public class RunnableObject implements Runnable{

    public void run() {
        System.out.println("Побочный поток! Шалом)");
    }
}
