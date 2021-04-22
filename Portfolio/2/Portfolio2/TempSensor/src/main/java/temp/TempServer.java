package temp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TempServer {

    TemperatureSensor temperatureSensor = new TemperatureSensor();
    //initialize socket and input stream
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;
    private PrintWriter printWriter = null;

    // constructor with port
    public TempServer(int port) {
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
        temperatureSensor.start();

        try {
            System.out.println("Loading Temperature");
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Double temp = (double) temperatureSensor.value();
        return temp;
    }

    public String getName() {
        return "Temperature: ";
    }

    public static void main(String args[]) {
        TempServer server = new TempServer(5000);
    }
}


