package multithreading.race;

public class ThreadR implements Runnable {
    private Counter counter;
    private int counting = 0;

    public ThreadR(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        while (counter.getCount() < Counter.getGoalNumber()) {
            counter.count();
            counting++;
            System.out.printf("ThreadR %s %d%n",
                    Thread.currentThread().getName(), counter.getCount());
        }
        System.out.println("Final score of ThreadR: " + counting);
    }
}
