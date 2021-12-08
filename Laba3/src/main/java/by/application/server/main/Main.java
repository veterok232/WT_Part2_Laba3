package by.application.server.main;

import by.application.server.main.server.Server;

/**
 * Server main class
 */
public class Main
{
    /**
     * Main method
     *
     * @param args - arguments of command line
     */
    public static void main(String[] args)
    {
        Server server = new Server(8080);
        server.start();

        try {
            server.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
