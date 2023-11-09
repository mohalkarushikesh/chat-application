import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);

            // Input stream to receive messages from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Output stream to send messages to the server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Read messages from the server
            new Thread(() -> {
                try {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println("Server: " + inputLine);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // Send messages to the server
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String outputLine;
            while (true) {
                outputLine = consoleReader.readLine();
                out.println(outputLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

