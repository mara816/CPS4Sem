package presentation;

import domain.CO2Controller;
import domain.ISensor;
import domain.TempController;

import java.util.Scanner;

public class App {

    public App() {

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {

            int nInt = sc.nextInt();
            ISensor t;
            ISensor c;

            switch (nInt) {
                // Temperature and CO2 name and value
                case (1) -> {
                    t = new TempController();
                    c = new CO2Controller();
                    System.out.println(t.getName() + ": " + t.getValue() + "\n" + c.getName() + ": " + c.getValue());
                }
                //Temperature name
                case (2) -> {
                    t = new TempController();
                    System.out.println(t.getName());
                }
                // Temperature value
                case (3) -> {
                    t = new TempController();
                    System.out.println(t.getValue());
                }
                // Temperature name and value
                case (4) -> {
                    t = new TempController();
                    System.out.println(t.getName() + ": " + t.getValue());
                }
                // CO2 name
                case (5) -> {
                    c = new CO2Controller();
                    System.out.println(c.getName());
                }
                // CO2 value
                case (6) -> {
                    c = new CO2Controller();
                    System.out.println(c.getValue());
                }
                // CO2 name and value
                case (7) -> {
                    c = new CO2Controller();
                    System.out.println(c.getName() + ": " + c.getValue());
                }
                // Wrong int
                case (8), (9) -> System.out.println("Sorry that command does not exist");
                // Exits the program
                case (0) -> {
                    System.out.println("\n" + "Thank you for using this amazing app! See you soon :-)");
                    sc.close();
                    System.exit(0);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("""
                Welcome to the application!
                Type 1 for Temperature and CO2 name value
                Type 2 for Temperature name
                Type 3 for Temperature value
                Type 4 for Temperature name and value
                Type 5 for CO2 name
                Type 6 for CO2 value
                Type 7 for CO2 name and value
                Type 0 to exit the program
                """);
        App app = new App();
    }
}
