package domain;

import java.io.*;
import java.net.Socket;

public class TempController implements ISensor {

    private Socket socket = null;
    //private InputStreamReader input = null;
    private DataInputStream input = null;
    //private DataOutputStream out = null;
    private PrintWriter printWriter = null;

    private String name;
    private Double value;

    public TempController() {
    }

    public void getReading() {

        // establish a connection
        try {
            socket = new Socket("localhost", 5000);
            System.out.println("Connected to Temperature server");

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
            //out.close();
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