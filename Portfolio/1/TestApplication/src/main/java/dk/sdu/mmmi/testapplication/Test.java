package dk.sdu.mmmi.testapplication;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dk.sdu.mmmi.st4.scfs.sensors.CO2Sensor;
import dk.sdu.mmmi.st4.scfs.sensors.TemperatureSensor;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anders
 */
public class Test {

    public static void main(String[] args) {
       CO2Sensor s = new CO2Sensor("CO2 Sensor");
        TemperatureSensor t = new TemperatureSensor();
        
        s.start();
        t.start();
        
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(s.getValue());
            System.out.println(t.value());
        }
        
        s.stop();
        t.stop();


    }




}
