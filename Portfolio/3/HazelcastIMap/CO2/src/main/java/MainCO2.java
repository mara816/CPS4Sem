import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.util.Scanner;

public class MainCO2 {

    private static final CO2Sensor co2Sensor = new CO2Sensor(("CO2"));
    private static final String MAP_NAME = "map";
    private static final String KEY_NAME = "CO2";

    public MainCO2() {
        // Start the Hazelcast Client and connect to an already running Hazelcast Cluster on 127.0.0.1
        HazelcastInstance hz = HazelcastClient.newHazelcastClient();
        IMap<String, String> map = hz.getMap(MAP_NAME);
        Thread t = new Thread(co2Sensor);
        t.start();

        System.out.println("Hazelcast started");

        for (int i = 0; i < 50; i++) {
            try {
                map.put(KEY_NAME, String.valueOf(getValue()));
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        t.interrupt();
        hz.shutdown();
        System.exit(0);
    }

    public Double getValue() {
        co2Sensor.start();

        try {
            System.out.println("Loading CO2");
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (Double) (double) co2Sensor.getValue();
    }

    public static void main(String[] args) {
        MainCO2 mainCo2 = new MainCO2();
    }
}
