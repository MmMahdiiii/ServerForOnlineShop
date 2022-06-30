import network.Server;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.next();
            if (command.equals("exit")) {
                server.turnOff();
                break;
            }
        }

    }
}
