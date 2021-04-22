import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Anders
 */
public class CO2Sensor implements Runnable {

    private Integer co2_value = 0;
    private String id;
    private final Object lock = new Object();
    ExecutorService executor = Executors.newFixedThreadPool(1);

    public CO2Sensor(String id) {
        this.id = id;
    }

    public void start() {
        executor.submit(this);
    }

    @Override
    public void run() {
        Random R = new Random();
        while (true) {

            synchronized (lock) {
                co2_value = (int) (300 + Math.random() * 2000);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                break;
            }
        }
    }
    
    public void stop() {
        executor.shutdownNow();
    }

    public Integer getValue() {
        synchronized (lock) {
            int v = co2_value;
            return v;
        }
    }

    public String getId() {
        return id;
    }
}
