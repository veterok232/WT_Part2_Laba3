package by.application.client.presentation.view.admin;

import by.application.client.entity.student.Student;
import by.application.client.entity.user.User;
import by.application.client.presentation.view.PresentationView;
import by.application.client.service.StudentClientService;
import java.util.List;

/**
 * Edit student form (choose one)
 */
public class EditAllStudentView extends PresentationView
{
    /**
     * Constructor
     *
     * @param studentService student service
     * @param user user instance
     */
    public EditAllStudentView(StudentClientService studentService, User user) {
        super(studentService, user);
    }

    /**
     * Show edit student form
     */
    @Override
    public void show() {
        List<Student> studentList = this.studentService.getAll();
        for (Student student : studentList) {
            System.out.println(student.getId() + ": " + student.getName());
        }

        System.out.println("Enter 'exit' to go back");
        System.out.println("Select student id: ");
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
        if ("exit".equals(input)) {
            return new AdminView(this.studentService, this.currentUser);
        }

        int id;
        try {
            id = Integer.parseInt(input);
        }
        catch (NumberFormatException ex) {
            throw new IllegalArgumentException();
        }

        return new EditStudentView(this.studentService, this.currentUser, id);
    }
}
