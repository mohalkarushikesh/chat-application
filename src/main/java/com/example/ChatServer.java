import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server is running. Waiting for clients...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            // Input stream to receive messages from client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Output stream to send messages to client
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Read messages from the client
            new Thread(() -> {
                try {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println("Client: " + inputLine);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // Send messages to the client
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

