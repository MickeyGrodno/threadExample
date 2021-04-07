public class SynchronizedExample {

    public static void main(String[] args) {
        CommonResource resource = new CommonResource();

        for(int i = 1; i < 6; i++) {
            Thread t = new Thread(new CountThread(resource));
            t.setName("Thread #"+i);
            t.start();
        }
    }

    private static class CommonResource {
        int x = 0;
        synchronized void increment() {
            x = 1;
            for (int i = 1; i < 5; i++) {
                System.out.printf("%s %s \n", Thread.currentThread().getName(), x);
                x++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        }

    }

    private static class CountThread implements Runnable {
        CommonResource res;

        CountThread(CommonResource res) {
            this.res = res;
        }

        public void run() {
            res.increment();
        }
    }
}
