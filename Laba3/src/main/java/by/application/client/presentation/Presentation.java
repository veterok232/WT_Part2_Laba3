package by.application.client.presentation;

import by.application.client.entity.role.UserRole;
import by.application.client.entity.user.User;
import by.application.client.presentation.view.guest.GuestView;
import by.application.client.presentation.view.PresentationView;
import by.application.client.service.StudentClientService;
import java.util.Scanner;

/**
 * Form view
 */
public class Presentation
{
    /**
     * View user role
     */
    private PresentationView view;

    /**
     * Current user
     */
    private User currentUser;

    /**
     * Constructor
     *
     * @param studentService Student service
     */
    public Presentation(StudentClientService studentService) {
        this.currentUser = new User();
        this.currentUser.setRole(UserRole.GUEST);

        this.view = new GuestView(studentService, this.currentUser);
    }

    /**
     * Get user input command
     *
     * @param input Input command
     *
     * @return boolean
     */
    private boolean getInput(String input) {
        try {
            this.view = this.view.getInput(input);

            return true;
        }
        catch (IllegalArgumentException ex) {
            return false;
        }
    }

    /**
     * Show a new form user role
     */
    public void show() {
        Scanner scanner = new Scanner(System.in);
        while (this.view != null)
        {
            this.view.show();

            while (!getInput(scanner.nextLine())) {
                System.out.println("Input fail!");
            }
        }
    }
}
