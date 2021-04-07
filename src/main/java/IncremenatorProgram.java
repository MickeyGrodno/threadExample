class Increment extends Thread {
    private volatile boolean isIncrement = true;

    public void changeAction() {
        isIncrement = !isIncrement;
    }

    public void run() {
        do {
            if(!Thread.interrupted()) {
                if (isIncrement) IncremenatorProgram.value++;
                else IncremenatorProgram.value--;
            } else return;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
//                return;
            }
        }
        while (true);
    }
}

class IncremenatorProgram {
    public static int value = 0;
    static Increment increment;

    public static void main(String[] args) {
        increment = new Increment();
        System.out.print("Значение = ");
        increment.start();

        for (int i = 1; i <= 1; i++) {
            try {
                Thread.sleep(i * 2 * 1000);
            } catch (InterruptedException e) {}
            increment.changeAction();
        }
        increment.interrupt();
    }
}
