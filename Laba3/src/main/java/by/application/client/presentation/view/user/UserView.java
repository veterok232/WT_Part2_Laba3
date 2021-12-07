package by.application.client.presentation.view.user;

import by.application.client.entity.user.User;
import by.application.client.presentation.view.PresentationView;
import by.application.client.service.StudentClientService;

/**
 * User default form
 */
public class UserView extends PresentationView
{
    /**
     * Constructor
     *
     * @param studentService Student service
     * @param user User instance
     */
    public UserView(StudentClientService studentService, User user) {
        super(studentService, user);
    }

    /**
     * Show form
     */
    @Override
    public void show() {
        System.out.println("1: Get students list\n2: Exit");
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
            case "1" -> new GetAllStudentView(this.studentService, this.currentUser);
            case "2" -> null;
            default -> throw new IllegalArgumentException();
        };
    }
}