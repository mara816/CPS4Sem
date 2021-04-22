package co2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class CO2Server {

    CO2Sensor co2Sensor = new CO2Sensor("CO2: ");

    //initialize socket and input stream
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;
    //private DataOutputStream out = null;
    private PrintWriter printWriter = null;

    // constructor with port
    public CO2Server(int port) {

        // starts server and waits for a connection
        try {

            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            socket = server.accept();
            System.out.println("Client accepted");

            printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.println(getName() + "@" + getValue());
            printWriter.flush();
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            while (socket != null) {
                socket = server.accept();
                in = new DataInputStream(socket.getInputStream());
                printWriter = new PrintWriter(socket.getOutputStream());
                printWriter.println(getName() + "@" + getValue());
                printWriter.flush();
            }

            // close connection
            socket.close();
            in.close();
            printWriter.close();


        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public Double getValue() {

        co2Sensor.start();

        try {
            System.out.println("Loading CO2");
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Double co2 = (double) co2Sensor.getValue();
        return co2;
    }

    public String getName() {
        return co2Sensor.getId();

    }

    public static void main(String args[]) {
        CO2Server co2Server = new CO2Server(5001);
    }

}
