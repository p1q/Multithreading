package multithreading.race;

public class Counter {
    private int count = 0;
    private static final int GOAL_NUMBER = 100;

    public static int getGoalNumber() {
        return GOAL_NUMBER;
    }

    public int getCount() {
        return count;
    }

    public void count() {
        if (count < GOAL_NUMBER) {
            count++;
        }
    }
}
