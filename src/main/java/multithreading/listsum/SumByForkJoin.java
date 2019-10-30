package multithreading.listsum;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class SumByForkJoin extends RecursiveTask<Integer> {
    private List<Integer> elements;
    private Integer sum = 0;

    SumByForkJoin(List<Integer> elements) {
        this.elements = elements;
    }

    @Override
    protected Integer compute() {
        elements.forEach(i -> sum += i);
        return sum;
    }
}
