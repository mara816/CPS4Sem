package domain;

import java.io.*;
import java.net.Socket;

public class CO2Controller implements ISensor {

    private Socket socket = null;
    private DataInputStream input = null;
    private PrintWriter printWriter = null;

    private String name;
    private Double value;


    public CO2Controller() {
    }

    public void getReading() {

        // establish a connection
        try {
            socket = new Socket("localhost", 5001);
            System.out.println("Connected to CO2 server");

            // sends output to the socket
            printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.write("hello");
            input = new DataInputStream(socket.getInputStream());

            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String resp = bufferedReader.readLine();

            String[] arr = resp.split("@");
            name = arr[0];
            value = Double.valueOf(arr[1]);

            // close the connection
            input.close();
            socket.close();
            printWriter.close();

        } catch (IOException i) {
            System.out.println(i);
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double getValue() {
        return this.value;
    }
}
