import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.topic.ITopic;

import java.util.Scanner;

public class MainCO2 {

    CO2Sensor co2Sensor = new CO2Sensor("CO2: ");
    private static final String TOP_NAME = "top";



    public MainCO2() {
        // Start the Hazelcast Client and connect to an already running Hazelcast Cluster on 127.0.0.1
        HazelcastInstance hz = HazelcastClient.newHazelcastClient();

        ITopic topic = hz.getTopic(TOP_NAME);


        System.out.println("Hazelcast started");
        System.out.println("Press 'Enter' to exit the server");


        for (int i = 0; i < 100; i++) {
            try {
                topic.publish(getValue());
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        hz.shutdown();
        System.exit(0);

    }

    public Double getValue() {
        CO2Sensor co2Sensor = new CO2Sensor(("CO2: "));

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
