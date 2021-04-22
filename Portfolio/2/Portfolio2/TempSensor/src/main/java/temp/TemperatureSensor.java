package temp;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Anders
 */
public class TemperatureSensor implements Runnable {

    private Object value = 0.0;
    private final Object lock = new Object();
    ExecutorService executor = Executors.newFixedThreadPool(1);

    public TemperatureSensor() {

    }

    public void start() {
        executor.submit(this);
    }

    public void stop() {
        executor.shutdownNow();
    }

    @Override
    public void run() {
        Random R = new Random();
        while (true) {
            Double t = Math.random() * 40;
            synchronized (lock) {

                value = Math.random() > 0.5 ? t * -1 : t;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                break;
            }
        }
    }

    public Object value() {
        synchronized (lock) {
            double d = (double) value;
            return d;
        }
    }
}
