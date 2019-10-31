package multithreading.listsum;

import java.util.List;
import java.util.concurrent.Callable;

public class SumByExecutorService implements Callable<Integer> {
    private List<Integer> elements;
    private Integer sum = 0;

    public SumByExecutorService(List<Integer> elements) {
        this.elements = elements;
    }

    @Override
    public Integer call() {
        elements.forEach(i -> sum += i);
        return sum;
    }
}
