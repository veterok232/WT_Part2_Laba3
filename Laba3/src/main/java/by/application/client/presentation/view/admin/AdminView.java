package by.application.client.presentation.view.admin;

import by.application.client.entity.user.User;
import by.application.client.presentation.view.user.GetAllStudentView;
import by.application.client.presentation.view.PresentationView;
import by.application.client.service.StudentClientService;

/**
 * Admin view
 */
public class AdminView extends PresentationView
{
    /**
     * Constructor
     *
     * @param studentService Student service
     * @param user User instance
     */
    public AdminView(StudentClientService studentService, User user) {
        super(studentService, user);
    }

    /**
     * Show form
     */
    @Override
    public void show() {
        System.out.println("1: Get students list\n2: Edit student\n3: Create student\n4: Exit");
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
        return switch (input) {
            case "1" -> new GetAllStudentView(this.studentService, this.currentUser);
            case "2" -> new EditAllStudentView(this.studentService, this.currentUser);
            case "3" -> new CreateStudentView(this.studentService, this.currentUser);
            case "4" -> null;
            default -> throw new IllegalArgumentException();
        };
    }
}
