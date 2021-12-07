package by.application.client.main;

import by.application.client.dao.ClientDAOFactory;
import by.application.client.presentation.Presentation;
import by.application.client.service.ServiceClientFactory;

/**
 * Client main class
 */
public class Main
{
    /**
     * Main method
     *
     * @param args - arguments of command line
     */
    public static void main(String[] args) {
        ServiceClientFactory factory = ServiceClientFactory.getInstance();
        ClientDAOFactory daoFactory = ClientDAOFactory.getInstance();

        Presentation presentation = new Presentation(factory.getStudentService(daoFactory.getStudentDAO()));
        presentation.show();
    }
}
