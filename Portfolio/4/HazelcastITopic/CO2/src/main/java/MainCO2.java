import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.topic.ITopic;

public class MainCO2 {

    private CO2Sensor co2Sensor = new CO2Sensor("CO2");
    private static final String TOP_NAME = "top";

    public MainCO2() {
        // Start the Hazelcast Client and connect to an already running Hazelcast Cluster on 127.0.0.1
        HazelcastInstance hz = HazelcastClient.newHazelcastClient();

        ITopic topic = hz.getTopic(TOP_NAME);
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
        co2Sensor.start();

        try {
            System.out.println("Loading CO2");
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (Double) (double) co2Sensor.getValue();
    }

    public String getName() {
        return co2Sensor.getId();
    }

    public static void main(String[] args) {
        MainCO2 mainCo2 = new MainCO2();
    }
}
