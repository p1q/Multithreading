package multithreading.listsum;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static final int NUMBER_ELEMENTS = 1000000;
    private static final int THREADS_NUMBER = 20;
    private static final int RANGE = NUMBER_ELEMENTS / THREADS_NUMBER;
    private static final int MIN_ELEMENT = 10;
    private static final int MAX_ELEMENT = 1000;

    public static void main(String[] args) {
        List<Integer> elements = IntStream.range(0, NUMBER_ELEMENTS)
                .mapToObj(i -> randomInt(MIN_ELEMENT, MAX_ELEMENT))
                .collect(Collectors.toList());

        System.out.printf("Sum of List with %d elements using ExecutorService equals %d%n",
                NUMBER_ELEMENTS, executorServiceSum(elements));
        System.out.printf("Sum of List with %d elements using ForkJoin equals %d%n",
                NUMBER_ELEMENTS, forkJoinSum(elements));
    }

    private static int executorServiceSum(List<Integer> elements) {
        int sum = 0;
        int index = RANGE;
        Future<Integer> interimSum;
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++) {
            List<Integer> subList = elements.subList(index - RANGE, index);
            interimSum = executorService.submit(new SumByExecutorService(subList));
            index += RANGE;
            try {
                sum += interimSum.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
        return sum;
    }

    private static int forkJoinSum(List<Integer> elements) {
        int sum = 0;
        int index = RANGE;

        for (int i = 0; i < THREADS_NUMBER; i++) {
            List<Integer> subList = elements.subList(index - RANGE, index);
            int interimSum = new ForkJoinPool().invoke(new SumByForkJoin(subList));
            index += RANGE;
            sum += interimSum;
        }
        return sum;
    }

    private static int randomInt(int min, int max) {
        int range = max - min + 1;
        return (int)(Math.random() * range) + min;
    }
}
