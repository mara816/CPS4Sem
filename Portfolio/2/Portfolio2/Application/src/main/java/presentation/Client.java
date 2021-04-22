package presentation;
/*
import domain.CO2Controller;
import domain.TempController;

import java.net.*;
import java.io.*;

public class Client {
    // initialize socket and input output streams
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;

    // constructor to put ip address and port
    public Client(String address, int port) {
        // establish a connection
        try {
            socket = new Socket(address, port);
            System.out.println("Welcome to the application!" +
                    "\n" + "Type 1 for Temperature and CO2 values" + "\n" +
                    "Type 9 to exit" + "\n>>>   ");
            //System.out.println("Connected");

            // sends output to the socket
            out = new DataOutputStream(socket.getOutputStream());

            // takes input from terminal
            input = new DataInputStream(System.in);

        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException i) {
            System.out.println(i);
        }

        // string to read message from input
        String line = "";

        // keep reading until "9" is input
        while (!line.equals("9")) {
            try {
                line = input.readLine();
                out.writeUTF(line);
                System.out.println(input.readUTF());
            } catch (IOException i) {
                System.out.println(i);
            }
        }


        //Scanner sc = new Scanner(System.in);

        while (line.equals("1")) {
            TempController tempController = new TempController();
            CO2Controller co2Controller = new CO2Controller();
            co2Controller.getReading();
            tempController.getReading();

            System.out.println(tempController.getName() + tempController.getValue() +
                    "\n" + co2Controller.getName() + co2Controller.getValue());
        }
        while (line.equals("9")) {
            System.out.println("\n" + "Thank you for using this amazing app! See you soon :-)");

            // close the connection
            try {
                input.close();
                out.close();
                socket.close();
                System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


/*    public static void getValues() {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            if (sc.nextInt() == 1) {
                TempController t = new TempController();
                CO2Controller c = new CO2Controller();
                c.getReading();
                t.getReading();

                System.out.println(t.getName() + t.getValue() + "\n" + c.getName() + c.getValue());
            } else {
                System.out.println("\n" + "Thank you for using this amazing app! See you soon :-)");
                sc.close();
                System.exit(0);

            }
        }
    }


    public static void main(String args[]) {
        Client client = new Client("127.0.0.1", 5000);
        //getValues();
    }
}
*/