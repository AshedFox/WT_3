package bsuir.wt.lab3;

import bsuir.wt.lab3.controller.Controller;
import bsuir.wt.lab3.entity.MessageType;
import bsuir.wt.lab3.entity.Role;
import bsuir.wt.lab3.entity.User;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.XMLFormatter;

public class Server {
    private ServerSocket serverSocket;

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            while (!serverSocket.isClosed()) {
                var client = serverSocket.accept();
                new ClientHandler(client).start();
            }

        } catch (IOException ignored) {

        } finally {
            stop();
        }
    }

    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException ignored) {}
    }

    public static void main(String[] args) {
        var server = new Server();
        server.start(8080);
    }

    private static class ClientHandler extends Thread {
        private static final Controller controller = new Controller();
        private final Socket clientSocket;
        private final BufferedWriter out;
        private final BufferedReader in;
        private User user;

        public ClientHandler(Socket socket) throws IOException {
            clientSocket = socket;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }

        @Override
        public void run() {
            String inputLine;
            try {
                while ((inputLine = in.readLine()) != null) {
                    var result = controller.doAction(inputLine, user);

                    if (result.startsWith("AUTH")) {
                        user = new User();

                        var start = result.indexOf("User");
                        var userString = result.substring(start + 1);

                        user = User.parse(userString);
                    }

                    out.write(result + "\n");
                    out.flush();
                }

                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException ignored) {}
        }
    }
}
