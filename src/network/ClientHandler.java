package network;

import controller.Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClientHandler extends Thread {
    Socket socket;
    DataOutputStream out = null;
    DataOutputStream in = null;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try(DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());) {
            // read the String message from client byte by byte
            StringBuilder message = new StringBuilder();
            int c;
            while((c=in.read())!=0){
                message.append((char) c);
            }
            System.out.println("Message from client: " + message.toString());
            // TODO Something with the command and data
            // TODO Send the message back to the client
            Controller controller = new Controller();
            String response = controller.run(message.toString());
            out.write(response.getBytes(StandardCharsets.UTF_8));
            out.flush();
            System.out.println("Response to client: " + response);
            socket.close();
        } catch (IOException e) {
            System.out.println("Could not close socket");
        }
    }
}
