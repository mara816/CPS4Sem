import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.topic.ITopic;

import java.util.Scanner;

public class MainTemp {

    TemperatureSensor temperatureSensor = new TemperatureSensor();

    private static final String TOP_NAME1 = "top1";

    public MainTemp() {
        // Start the Hazelcast Client and connect to an already running Hazelcast Cluster on 127.0.0.1
        HazelcastInstance hz = HazelcastClient.newHazelcastClient();

        ITopic topic = hz.getTopic(TOP_NAME1);

        System.out.println("Hazelcast started");
        System.out.println("Press '0' to exit the server");
        Scanner scanner = new Scanner(System.in);


        for (int i = 0; i < 100; i++) {

            try {
                topic.publish(getValue());
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /////Works - Shutting down the server with '0'
/*            if (scanner.nextInt() == 0) {
                System.out.println("\n" + "See you next time");
                System.exit(0);
            }*/
        }

        scanner.nextLine();
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
