package first_example;

public class Program {
    static RunnableObject runnableObject;
    static SomeThread someThread;
    public static void main(String[] args) {
        runnableObject = new RunnableObject();
        someThread = new SomeThread();
        Thread thread = new Thread(runnableObject);
        thread.start();
        someThread.start();
        System.out.println("Главный поток завершен");
    }
}
