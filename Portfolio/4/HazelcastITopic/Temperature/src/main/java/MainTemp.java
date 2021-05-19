import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.topic.ITopic;

public class MainTemp {

    private TemperatureSensor temperatureSensor = new TemperatureSensor();
    private static final String TOP_NAME1 = "top1";

    public MainTemp() {
        // Start the Hazelcast Client and connect to an already running Hazelcast Cluster on 127.0.0.1
        HazelcastInstance hz = HazelcastClient.newHazelcastClient();
        ITopic topic = hz.getTopic(TOP_NAME1);
        System.out.println("Hazelcast started");

        for (int i = 0; i < 100; i++) {

            try {
                String str = getName() + "@" + getValue();
                topic.publish(str);
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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

    public String getName() {
        return "Temperature";
    }

    public static void main(String[] args) {
        MainTemp main = new MainTemp();
    }

}
