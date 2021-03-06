package presentation;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.map.listener.MapListener;
import domain.CO2Observer;
import domain.ISensor;
import domain.TemperatureObserver;

public class Main {
    private static final String MAP_NAME = "map";
    private static final String MAP_NAME1 = "map1";
    private static final String KEY_NAME = "CO2";
    private static final String KEY_NAME1 = "Temperature";

    private static final ISensor temperatureObserver = new TemperatureObserver();
    private static final ISensor co2Observer = new CO2Observer();

    public Main() {

        // Start the Hazelcast Client and connect to an already running Hazelcast Cluster on 127.0.0.1
        HazelcastInstance hz = HazelcastClient.newHazelcastClient();

        // Get the Distributed Map from Cluster.
        IMap<String, String> mapC = hz.getMap(MAP_NAME);
        IMap<String, String> mapT = hz.getMap(MAP_NAME1);

        mapC.addEntryListener((MapListener) co2Observer, KEY_NAME, true);
        mapT.addEntryListener((MapListener) temperatureObserver, KEY_NAME1, true);
        System.out.println("Welcome");
        System.out.println("Loading Temperature and CO2 values..." + "\n");

        for (int i = 0; i < 50; i++) {
            try {
                Thread.sleep(2000);
                System.out.println(temperatureObserver.getName() + ": " + temperatureObserver.getValue());
                System.out.println(co2Observer.getName() + ": " + co2Observer.getValue());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
    }
}
