package presentation;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.topic.ITopic;
import com.hazelcast.topic.MessageListener;
import domain.CO2Subscriber;
import domain.ISensor;
import domain.TempSubscriber;

import java.util.Scanner;

public class Main {
    private static final String TOP_NAME = "top";
    private static final String TOP_NAME1 = "top1";
    private static final ISensor tempSubscriber = new TempSubscriber();
    private static final ISensor co2Subscriber = new CO2Subscriber();

    public Main() {

        // Start the Hazelcast Client and connect to an already running Hazelcast Cluster on 127.0.0.1
        HazelcastInstance hz = HazelcastClient.newHazelcastClient();

        // Get the Distributed Topic from Cluster.
        ITopic<Object> topic = hz.getTopic(TOP_NAME);
        ITopic<Object> topic1 = hz.getTopic(TOP_NAME1);

        topic.addMessageListener((MessageListener) tempSubscriber);
        topic1.addMessageListener((MessageListener) co2Subscriber);

        System.out.println("Welcome");
        System.out.println("Retrieving sensor data...");

        for (int i = 0; i < 50; i++) {
            try {

                Thread.sleep(2000);

                System.out.println("\n" + "Dataset " + (i + 1) + " out of 50");
                System.out.println(tempSubscriber.getName() + ": " + tempSubscriber.getValue());
                System.out.println(co2Subscriber.getName() + ": " + co2Subscriber.getValue());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            if (sc.nextInt() == 0) {
                System.out.println("\n" + "Thank you for using this amazing app! See you soon :-)");
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
    }
}
