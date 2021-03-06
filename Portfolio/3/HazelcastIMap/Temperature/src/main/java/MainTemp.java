import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.util.Scanner;

public class MainTemp {

    private static final TemperatureSensor temperatureSensor = new TemperatureSensor();
    private static final String MAP_NAME1 = "map1";
    private static final String KEY_NAME1 = "Temperature";

    public MainTemp() {
        // Start the Hazelcast Client and connect to an already running Hazelcast Cluster on 127.0.0.1
        HazelcastInstance hz = HazelcastClient.newHazelcastClient();

        IMap<String, String> map1 = hz.getMap(MAP_NAME1);
        Thread t = new Thread(temperatureSensor);
        t.start();

        System.out.println("Hazelcast started");
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 50; i++) {
            try {
                map1.put(KEY_NAME1, String.valueOf(getValue()));
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        scanner.nextLine();
        t.interrupt();
        hz.shutdown();
        System.exit(0);
    }

    public Double getValue() {
        temperatureSensor.start();

        try {
            System.out.println("Loading Temperature");
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (Double) (double) temperatureSensor.value();
    }

    public static void main(String[] args) {
        MainTemp main = new MainTemp();
    }
}
