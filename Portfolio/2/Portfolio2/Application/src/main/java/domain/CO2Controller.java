package domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CO2Controller implements ISensor {

    private Socket socket = null;
    private PrintWriter printWriter = null;
    private String name;
    private Double value;

    public CO2Controller() {

        // establish a connection
        try {
            socket = new Socket("localhost", 5001);

            // sends output to the socket
            printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.write("hello");

            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String resp = bufferedReader.readLine();

            String[] arr = resp.split("@");
            name = arr[0];
            value = Double.valueOf(arr[1]);

            // close the connection
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
