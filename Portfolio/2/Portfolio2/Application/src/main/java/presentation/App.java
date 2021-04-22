package presentation;

import domain.CO2Controller;
import domain.TempController;

import java.util.Scanner;

public class App {

    public static void getValues() {

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            if (sc.nextInt() == 1) {
                TempController t = new TempController();
                CO2Controller c = new CO2Controller();
                t.getReading();
                c.getReading();
                System.out.println(t.getName() + t.getValue() + "\n" + c.getName() + c.getValue());

            } else {
                System.out.println("\n" + "Thank you for using this amazing app! See you soon :-)");
                sc.close();
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the application!" +
                "\n" + "Type 1 for Temperature and CO2 values" + "\n" +
                "Type 2 to exit");
        getValues();

    }
}
