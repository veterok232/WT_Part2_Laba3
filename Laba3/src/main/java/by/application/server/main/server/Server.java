package by.application.server.main.server;

import by.application.server.main.server.handle.ClientHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 * Server class
 */
public class Server extends Thread
{
    /**
     * Server port
     */
    private final int port;

    /**
     * All connection clients
     */
    private static LinkedList<ClientHandler> clients = new LinkedList<>();

    /**
     * Constructor
     *
     * @param port Server port
     */
    public Server(int port) {
        this.port = port;
    }

    /**
     * Start new server instance
     */
    @Override
    public void run() {
        ServerSocket server = null;
        try {
            try {
                server = new ServerSocket(this.port);
                while (true) {
                    Socket socket = server.accept();
                    try {
                        clients.add(new ClientHandler(socket));
                    } catch (IOException e) {
                        socket.close();
                    }
                }
            }
            catch (IOException ignored) { }
            finally {
                if ((server != null) && !server.isClosed()) {
                    server.close();
                }
            }
        }
        catch (IOException ignored) { }
    }
}
