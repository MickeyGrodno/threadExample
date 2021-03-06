import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    //Парковочное место занято - true, свободно - false
    private static final boolean[] PARKING_PLACES = new boolean[5];
    //Устанавливаем флаг "справедливый", в таком случае метод
    //aсquire() будет раздавать разрешения в порядке очереди
    private static final Semaphore SEMAPHORE = new Semaphore(5, true);

    public static void main(String[] args) {
        for(int i = 1; i <=7; i++) {
            new Thread(new Car(i)).start();

            try {Thread.sleep(400);}
            catch (InterruptedException e){}
        }
    }

    private static class Car implements Runnable {
        private int carNumber;
        public Car(int carNumber) {
            this.carNumber=carNumber;
        }

        public void run() {
            System.out.printf("Автомобиль №%d подъехал к парковке.\n", carNumber);
            try {
                //acquire() запрашивает доступ к следующему за вызовом этого метода блоку кода,
                //если доступ не разрешен, поток вызвавший этот метод блокируется до тех пор,
                //пока семафор не разрешит доступ
                SEMAPHORE.acquire();

                //Ищем свободное место и паркуемся
                int parkingNumber = -1;
                synchronized (PARKING_PLACES) {
                    for (int i = 0; i < 5; i++) {    //Если место свободно
                        if (!PARKING_PLACES[i]) {
                            PARKING_PLACES[i] = true;  //занимаем его
                            parkingNumber = i+1;
                            System.out.printf("Автомобиль №%d припарковался на месте %d.\n", carNumber, i);
                            break;
                        }
                    }
                }
                Thread.sleep(5000);  //Уходим за покупками, к примеру

                synchronized (PARKING_PLACES) {
                    PARKING_PLACES[parkingNumber-1] = false; //Освобождаем место
                    SEMAPHORE.release();                 //release(), напротив, освобождает ресурс
                    System.out.printf("Автомобиль №%d покинул парковку.\n", carNumber);
                }
            } catch (InterruptedException e) { }
        }
    }
}
