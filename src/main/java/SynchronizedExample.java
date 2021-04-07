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
    }

    private static class CountThread implements Runnable {
        CommonResource res;
        CountThread(CommonResource resouce) {
            this.res = resouce;
        }

        public void run() {
            synchronized (res) {
                res.x = 1;
                for (int i = 1; i < 5; i++) {
                    System.out.printf("%s %s \n", Thread.currentThread().getName(), res.x);
                    res.x++;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
    }
}
