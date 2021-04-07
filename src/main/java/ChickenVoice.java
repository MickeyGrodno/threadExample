class EggVoice extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                sleep(1000);
            }
            catch (InterruptedException e) {}
            System.out.println("Яйцо!");
        }
    }
}

public class ChickenVoice {
    static EggVoice eggVoice;

    public static void main(String[] args) {
        eggVoice = new EggVoice();
        System.out.println("Спор начат!");
        eggVoice.setDaemon(true);
        eggVoice.start();

        eggVoice.stop();


        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){}
            System.out.println("Курица!");
        }
        if (eggVoice.isAlive()) {
            try {
                eggVoice.join();
            } catch (InterruptedException e) {}
            System.out.println("Первым появилось яйцо!");
        }
        else {
            System.out.println("Первой появилась курица!");
        }
        System.out.println("Спор закончен!");
    }
}
