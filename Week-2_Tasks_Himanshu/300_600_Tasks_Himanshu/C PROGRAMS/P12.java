import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class P12 {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private Scanner scanner;

    public P12(String serverAddress, int port) {
        try {
            socket = new Socket(serverAddress, port);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            scanner = new Scanner(System.in);

            new Thread(this::receiveMessages).start();
            sendMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessages() {
        while (true) {
            System.out.print("Enter your message: ");
            String message = scanner.nextLine();
            output.println(message);
        }
    }

    private void receiveMessages() {
        try {
            while (true) {
                String message = input.readLine();
                System.out.println("Received from server: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        P12 client = new P12("localhost", 12345);
    }
}
