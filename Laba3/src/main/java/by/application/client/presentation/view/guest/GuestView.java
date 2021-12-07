package by.application.client.presentation.view.guest;

import by.application.client.entity.user.User;
import by.application.client.presentation.view.PresentationView;
import by.application.client.service.StudentClientService;

/**
 * Guest form
 */
public class GuestView extends PresentationView
{
    /**
     * Constructor
     *
     * @param studentService student service
     * @param user user instance
     */
    public GuestView(StudentClientService studentService, User user) {
        super(studentService, user);
    }

    /**
     * Show guest form
     */
    @Override
    public void show() {
        System.out.println("1: Register\n2: Login\n3: Exit");
    }

    /**
     * Get user input command
     *
     * @param input User input command
     *
     * @return PresentationView
     */
    @Override
    public PresentationView getInput(String input) {
        return switch (input)
        {
            case "1" -> new RegisterView(this.studentService, this.currentUser);
            case "2" -> new LoginView(this.studentService, this.currentUser);
            case "3" -> null;
            default -> throw new IllegalArgumentException();
        };
    }
}
