package multithreading.race;

public class Main {
    private static Counter counter = new Counter();

    public static void main(String[] args) {
        Thread threadR = new Thread(new ThreadR(counter));
        ThreadE threadE = new ThreadE(counter);
        threadR.start();
        threadE.start();
    }
}
