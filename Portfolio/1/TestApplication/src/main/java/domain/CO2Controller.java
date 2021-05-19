package domain;

import dk.sdu.mmmi.st4.scfs.sensors.CO2Sensor;

public class CO2Controller implements ISensor {

    CO2Sensor c = new CO2Sensor("CO2");

    public CO2Controller() {
    }

    @Override
    public String getName() {
        return c.getId();
    }

    @Override
    public Double getValue() {
        c.start();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Double co2 = (double) c.getValue();
        c.stop();
        return co2;
    }
}
