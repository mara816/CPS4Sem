package domain;

import dk.sdu.mmmi.st4.scfs.sensors.TemperatureSensor;

public class TempController implements ISensor {

    TemperatureSensor t = new TemperatureSensor();

    public TempController() {
    }

    @Override
    public String getName() {
        String name = "Temperature: ";
        return name;
    }

    @Override
    public Double getValue() {
        t.start();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Double temp = (double) t.value();
        t.stop();
        return temp;
    }
}