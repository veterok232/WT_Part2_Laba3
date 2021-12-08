package by.application.server.main.server.handle;

import by.application.client.entity.request.StudentRequest;
import by.application.client.entity.response.StudentResponse;
import by.application.server.dao.ServerDAOFactory;
import by.application.server.main.server.handle.controller.StudentController;
import by.application.server.service.StudentServerService;
import by.application.server.service.impl.StudentServerServiceImpl;
import java.io.*;
import java.net.Socket;

/**
 * Client handler class
 */
public class ClientHandler extends Thread
{
    /**
     * Input stream
     */
    private final ObjectInputStream in;

    /**
     * Output stream
     */
    private final ObjectOutputStream out;

    /**
     * Request/response controller
     */
    private final StudentController controller;

    /**
     * Constructor
     *
     * @param socket Server socket
     *
     * @throws IOException exception
     */
    public ClientHandler(Socket socket) throws IOException {
        StudentServerService service = new StudentServerServiceImpl(ServerDAOFactory.getInstance().getStudentDAO());

        this.in = new ObjectInputStream(socket.getInputStream());
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.controller = new StudentController(service);

        start();
    }

    /**
     * Start request/response thread
     */
    @Override
    public void run() {
        try {
            while (true) {
                StudentRequest request = (StudentRequest) this.in.readObject();
                StudentResponse response;
                switch (request.getRequestType()) {
                    case LOGIN :
                        response = this.controller.login(request);
                        break;
                    case REGISTER :
                        response = this.controller.register(request);
                        break;
                    case GET_ALL :
                        response = this.controller.getAll();
                        break;
                    case GET :
                        response = this.controller.get(request);
                        break;
                    case EDIT :
                        response = this.controller.edit(request);
                        break;
                    case CREATE :
                        response = this.controller.create(request);
                        break;
                    default : response = this.controller.notFound();
                };

                this.out.writeObject(response);
                this.out.flush();
            }
        }
        catch (EOFException ignored) { }
        catch (IOException | ClassNotFoundException e) {
            System.out.printf("Failed read: %s%n", e.getMessage());
        }
    }
}
