package multithreading.race;

public class ThreadE extends Thread {
    private Counter counter;
    private int counting = 0;

    public ThreadE(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        while (counter.getCount() < Counter.getGoalNumber()) {
            counter.count();
            counting++;
            System.out.printf("ThreadE %s %d%n",
                    ThreadE.currentThread().getName(), counter.getCount());
        }
        System.out.println("Final score of ThreadE: " + counting);
    }
}
