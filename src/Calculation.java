import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Calculation {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5,
                5,
                100,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100),
                runnable -> {
                    Thread thread = Executors.defaultThreadFactory().newThread(runnable);
                    thread.setName("kaka-test");
                    thread.setDaemon(false);
                    return thread;
                });
        AtomicInteger a = new AtomicInteger();
        threadPoolExecutor.execute(() -> {
            while (true) {
                a.getAndIncrement();
            }
        });

    }
}
