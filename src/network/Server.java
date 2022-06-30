package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket serverSocket;
    public  void start() {
        try {
            serverSocket = new ServerSocket(8000);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected" + socket.getInetAddress() + ":" + socket.getPort());
                ClientHandler clientHandler = new ClientHandler(socket);
                clientHandler.start();
            }
        } catch (IOException e) {
            System.out.println("Could not listen on port: 8000");
        }
    }

}

