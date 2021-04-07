package first_example;

public class SomeThread extends Thread{
    public void run() {
        System.out.println("Тут выполняется побочный поток first_example.SomeThread");
    }
}
